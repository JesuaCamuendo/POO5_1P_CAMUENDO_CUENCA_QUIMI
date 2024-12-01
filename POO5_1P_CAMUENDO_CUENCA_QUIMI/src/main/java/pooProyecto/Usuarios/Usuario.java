package pooProyecto.Usuarios;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import javax.mail.*;
import io.github.cdimascio.dotenv.*;
import java.util.Properties;
import pooProyecto.Recursos.Espacio;
import pooProyecto.Recursos.ManejoArchivos;
import pooProyecto.Recursos.Reserva;
import pooProyecto.Sistema.Sistema;
import pooProyecto.Tipos.*;

public abstract class Usuario {
    protected String codigoUnico;
    protected String cedula;
    protected String nombre;
    protected String apellido;
    protected String usuario;
    protected String contrasenia;
    protected String correo;
    protected TipoRol rol;

    /**
     * Constructor de la clase Usuario.
     * 
     * @param codigoUnico el identificador único del usuario.
     * @param cedula      la cédula del usuario.
     * @param nombre      el nombre del usuario.
     * @param apellido    el apellido del usuario.
     * @param usuario     el nombre de usuario para iniciar sesión.
     * @param contrasenia la contraseña del usuario.
     * @param correo      el correo electrónico del usuario.
     * @param rol         el rol del usuario, representado por un objeto
     *                    {@link TipoRol}.
     */
    Usuario(String codigoUnico, String cedula, String nombre, String apellido, String usuario, String contrasenia,
            String correo, TipoRol rol) {
        this.codigoUnico = codigoUnico;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.correo = correo;
        this.rol = rol;
    }

    /**
     * Reserva un recurso para el usuario.
     * 
     * Este método debe ser implementado por las clases derivadas.
     */
    public abstract void reservar();

    /**
     * Consulta una reserva del usuario en una fecha específica.
     * 
     * Solicita la fecha en formato [YYYY-MM-DD], valida la entrada
     * y busca la reserva correspondiente en el sistema. Si se encuentra,
     * muestra los detalles de la reserva; de lo contrario, notifica al usuario.
     */
    public void ConsultarReserva() {
        System.out.println("\n-------------- Consultar reserva --------------  ");
        System.out.print("Ingrese la fecha de reserva [YYYY-MM-DD]: ");
        Scanner s = new Scanner(System.in);
        boolean veracidad = false;
        String fechaReserva = s.nextLine();
        veracidad = validarFormatoFecha(fechaReserva);
        while (veracidad == false) {
            System.out.print("Error al ingresar la fecha, por favor use el formato [YYYY-MM-DD]: ");
            fechaReserva = s.nextLine();
            veracidad = validarFormatoFecha(fechaReserva);
        }
        SimpleDateFormat Format = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaReservada = convertirFecha(fechaReserva); // cambia de String a Date

        boolean reservaBuscada = false;
        Usuario usu = null;

        // Identificar al usuario que inició sesión
        for (Usuario usuario : Sistema.usuarios) {
            if (usuario.getUsuario().equals(Sistema.getUsuario())) {
                usu = usuario;
                break;
            }
        }
        if (usu != null) {
            for (Reserva reserva : Sistema.reservas) {
                if (reserva.getFecha().equals(fechaReservada) &&
                        reserva.getCedula().equals(usu.getCedula())) { // Verifica la reserva del usuario
                    reservaBuscada = true;
                    String fechaFormato = Format.format(reserva.getFecha()); // cambia de Date a String
                    System.out.println("\n------------------Datos de la reserva--------------------");
                    System.out.println("Código reserva: " + reserva.getCodigoReserva() + " - Fecha: " + fechaFormato
                            + " - Tipo espacio: " + reserva.getTipoEspacio() + " - Estado: " + reserva.getTipoEstado());

                    for (Espacio espacio : Sistema.espacios) {
                        if (reserva.getCodigoEspacio().equals(espacio.getCodigoEspacio())) {
                            System.out.println("Nombre espacio: " + espacio.getNombre() + " - Capacidad: "
                                    + espacio.getCapacidad() + "\nUsuario: "
                                    + usu.getNombre() + " " + usu.getApellido());
                        }
                    }
                }
            }
            if (!reservaBuscada) {
                System.out.println("\nNo se encontraron reservas para esta fecha.");
            }
        } else {
            System.out.println("Usuario no encontrado en el sistema.");
        }

    }

    /**
     * Este metodo sirve para mostrar menú
     * 
     * @param No recibe nada
     * @return void No devuelve nada
     */
    public abstract void mostrarMenu();

    /**
     * Configura y devuelve una sesión de correo electrónico para el envío de
     * mensajes.
     * 
     * <p>
     * Este método utiliza las variables de entorno configuradas en un archivo
     * `.env`
     * para obtener los parámetros necesarios para la conexión al servidor SMTP,
     * incluyendo
     * el host, el puerto, el usuario y la contraseña. También establece las
     * propiedades
     * necesarias para la autenticación y la conexión segura.
     * </p>
     * 
     * @return una instancia de {@link Session} configurada con los parámetros del
     *         servidor SMTP.
     */
    protected Session enviarCorreo() {
        Dotenv dot = Dotenv.load();
        String host = dot.get("MAIL_HOST");
        String port = dot.get("MAIL_PORT");
        String user = dot.get("MAIL_USER");
        String pass = dot.get("MAIL_PASS");

        Properties prop = new Properties();
        prop.put("mail.smtp.host", host);
        prop.put("mail.smtp.port", port);
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");

        return Session.getInstance(prop, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });
    }

    /**
     * Valida si una fecha ingresada cumple con el formato [YYYY-MM-DD] y ciertas
     * restricciones.
     * 
     * Tambien verifica que la fecha tenga el formato correcto, que el año sea 2024,
     * que el mes esté entre 1 y 12, y que el día esté entre 1 y 31.
     * 
     * @param fecha la fecha en formato [YYYY-MM-DD] que se desea validar.
     * @return {@code true} si la fecha es válida; {@code false} en caso contrario.
     */

    protected boolean validarFormatoFecha(String fecha) {
        boolean veracidad = false;
        if (fecha.length() == 10) {
            if (fecha.charAt(4) == '-' && fecha.charAt(7) == '-') { // verfica que esten los giones en las posiciones
                                                                    // correctas
                String anio = fecha.substring(0, 4);
                String mes = fecha.substring(5, 7);
                String dia = fecha.substring(8);
                if (anio.matches("\\d{4}") && mes.matches("\\d{2}") && dia.matches("\\d{2}")) { //
                    if (anio.compareTo("2024") == 0) {
                        if (Integer.parseInt(mes) <= 12) {
                            if (Integer.parseInt(dia) <= 31) {
                                veracidad = true;
                            } else {
                                System.out.println("Solo hay hasta 31 días en un mes.");
                            }
                        } else {
                            System.out.println("Solo existen 12 meses.");
                        }
                    } else {
                        System.out.println("Año incorrecto.");
                    }
                }
            }
        }
        return veracidad;
    }

    /**
     * Convierte una cadena de texto en formato "yyyy-MM-dd" a un objeto Date.
     * 
     * Retorna null si ocurre un error de formato.
     *
     * @param fechaReserva la fecha en formato "yyyy-MM-dd".
     * @return un objeto {@link Date} o {@code null} si ocurre un error.
     */
    protected Date convertirFecha(String fechaReserva) {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); // define como debe verse el String para luego se
                                                                       // pueda convertir a tipo Date
        try {
            return formato.parse(fechaReserva);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Valida y procesa la elección del usuario entre dos opciones predefinidas.
     * 
     * @param eleccion la elección inicial proporcionada por el usuario.
     * @param opcion1  la primera opción válida.
     * @param opcion2  la segunda opción válida.
     * @return la elección validada del usuario, que será igual a {@code opcion1} o
     *         {@code opcion2}.
     */
    protected String Elegiropciones(String eleccion, String opcion1, String opcion2) {
        int c1 = eleccion.compareTo(opcion1);
        int c2 = eleccion.compareTo(opcion2);
        boolean veracidad = false;
        Scanner s = new Scanner(System.in);
        if (c1 == 0 || c2 == 0) {
            veracidad = true;
        }
        while (veracidad == false) {
            System.out.print('\n' + "Opción no válida. Elija Entre " + opcion1 + " o " + opcion2 + ": ");
            eleccion = s.nextLine().toUpperCase();
            c1 = eleccion.compareTo(opcion1);
            c2 = eleccion.compareTo(opcion2);
            if (c1 == 0 || c2 == 0) {
                veracidad = true;
            }
        }
        return eleccion;
    }

    /**
     * Obtiene el nombre de un espacio a partir de su código.
     * 
     * @param codigoEspacio el código único del espacio a buscar.
     * @return el nombre del espacio correspondiente al código proporcionado,
     *         o "null" si no se encuentra un espacio con el código especificado.
     */
    protected String NombreEspacio(String codigoEspacio) {
        String Nombreespacio = "null";
        for (Espacio esp : Sistema.espacios) {
            if (codigoEspacio.equals(esp.getCodigoEspacio())) {
                Nombreespacio = esp.getNombre();
            }
        }
        return Nombreespacio;
    }

    /**
     * Permite al usuario elegir un espacio disponible de un tipo específico para
     * realizar una reserva.
     * 
     * @param espacio  el tipo de espacio a elegir, representado por un objeto
     *                 {@link TipoEspacio}.
     * @param articulo una cadena que indica el artículo asociado al espacio (por
     *                 ejemplo, "el" o "la").
     * @return el código del espacio seleccionado por el usuario, garantizando que
     *         sea válido.
     */
    protected String ElegirEspacios(TipoEspacio espacio, String articulo) {
        ArrayList<String> codigos = new ArrayList<>();
        boolean veracidad = false;
        Scanner s = new Scanner(System.in);
        for (Espacio space : Sistema.espacios) {
            if (space.getTipo() == espacio && space.mostrarDisponibilidad()) {
                System.out.println("        " + space.getCodigoEspacio() + "        |      " + space.getNombre());
                codigos.add(space.getCodigoEspacio());
            }
        }

        // Elegir la Cancha
        System.out.print('\n' + "Elija " + articulo + " " + espacio + " a reservar, colocando su Código (1XX): ");
        String codigoEspacio = s.nextLine();
        if (codigoEspacio.matches("[0-9]*")) {
            if (codigoEspacio.length() == 3) {
                for (String st : codigos) {
                    if (st.compareTo(codigoEspacio) == 0) {
                        veracidad = true;
                    }
                }
            }
        }
        // Validar opción
        while (veracidad == false) {
            System.out.print('\n' + "OPCION NO EXISTE. ELEGIR ENTRE LOS CODIGOS MOSTRADOS (1XX):  ");
            codigoEspacio = s.nextLine().toUpperCase();
            if (codigoEspacio.matches("[0-9]*")) {
                if (codigoEspacio.length() == 3) {
                    for (String st : codigos) {
                        if (st.compareTo(codigoEspacio) == 0) {
                            veracidad = true;
                        }
                    }
                }
            }
        }
        return codigoEspacio;
    }

    /**
     * Confirma y registra una reserva en el sistema si la confirmación es positiva.
     * 
     * @param confirmacion  indica si el usuario desea confirmar la reserva ("SI"
     *                      para confirmar).
     * @param Estado        el estado de la reserva (por ejemplo, "APROBADO",
     *                      "PENDIENTE").
     * @param fecha         la fecha en que se realiza la reserva.
     * @param codigoEspacio el código del espacio reservado.
     * @param espacio       el tipo de espacio reservado, representado como una
     *                      cadena.
     * @param motivo        el motivo de la reserva.
     * @param Nombreespacio el nombre del espacio reservado.
     * @param fechaReserva  la fecha de la reserva como cadena.
     * @return {@code true} si la reserva fue confirmada y registrada exitosamente,
     *         {@code false} en caso contrario.
     */
    protected boolean Confirmar(String confirmacion, String Estado, Date fecha, String codigoEspacio, String espacio,
            String motivo, String Nombreespacio, String fechaReserva) {
        boolean veracidad = false;
        ManejoArchivos m = new ManejoArchivos();
        TipoEspacio tipo = TipoEspacio.valueOf(espacio);
        String usuario = Sistema.getUsuario();
        String cedula = "null";
        String codigoUnico = "null";
        TipoEstado tipoEstado = TipoEstado.valueOf(Estado);
        // Hallar los datos del Profesor que está usando el programa
        for (Usuario user : Sistema.usuarios) {
            if (user.getUsuario().compareTo(usuario) == 0) {
                cedula = user.getCedula();
                codigoUnico = user.getCodigoUnico();
            }
        }
        String codigoReserva = String.valueOf(5001 + Reserva.ReservasCreadas);
        int cod = Integer.parseInt(codigoReserva);
        if (confirmacion.compareTo("SI") == 0) {
            String linea = codigoReserva + " | " + codigoUnico + " | " + cedula + " | " + fechaReserva + " | "
                    + codigoEspacio + " | " + espacio + " | " +
                    Estado + " | " + motivo;
            m.EcribirArchivo("reservas", linea);
            Reserva reserva = new Reserva(cod, codigoUnico, cedula, fecha, codigoEspacio, tipo, tipoEstado,
                    motivo);
            Sistema.reservas.add(reserva);
            veracidad = true;
        }
        return veracidad;
    }

    public String getCodigoUnico() {
        return codigoUnico;
    }

    public String getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getCorreo() {
        return correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public TipoRol getRol() {
        return rol;
    }

    public void setCodigoUnico(String codigoUnico) {
        this.codigoUnico = codigoUnico;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setRol(TipoRol rol) {
        this.rol = rol;
    }
}
