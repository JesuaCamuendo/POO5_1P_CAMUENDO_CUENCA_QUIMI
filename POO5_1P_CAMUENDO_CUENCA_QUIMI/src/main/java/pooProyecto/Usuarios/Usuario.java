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

    /*
     * Este metodo es abstracto para que las clases hijas puedan sobreescribirlo
     * @param No recibe nada
     * @return No devuelve nada
     */
    public abstract void reservar();

    /*
     * Este metodo sirve para consultar las rese
     * @param No recibe nada
     * @return void:No devuelve nada
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
        Date fechaReservada = convertirFecha(fechaReserva);

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
                    String fechaFormato = Format.format(reserva.getFecha());
                    System.out.println("\n------------------Datos de la reserva--------------------");
                    System.out.println("Código reserva: " + reserva.getCodigoReserva() + " - Fecha: " + fechaFormato
                            + " - Tipo espacio: " + reserva.getTipoEspacio()+" - Estado: "+reserva.getTipoEstado());

                    for (Espacio espacio : Sistema.espacios) {
                        if (reserva.getCodigoEspacio().equals(espacio.getCodigoEspacio())) {
                            System.out.println("Nombre espacio: " + espacio.getNombre() + " - Capacidad: "
                                    + espacio.getCapacidad() + " - Estado: " + espacio.getEstado() + "\nUsuario: "
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

    /*
     * Este metodo sirve para mostrar menú
     * @param No recibe nada
     * @return void No devuelve nada
     */
    public abstract void mostrarMenu();

    /*
     * Este metodo sirve para enviar correo
     * @param No recibe nada
     * @return Session retorna una Session iniciada
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
     * Este metodo sirve para validar la fecha
     * @param String fecha
     * @return void No devuelve nada
     */
    protected boolean validarFormatoFecha(String fecha) {
        boolean veracidad = false;
        if (fecha.length() == 10) {
            if (fecha.charAt(4) == '-' && fecha.charAt(7) == '-') {
                String anio = fecha.substring(0, 4);
                String mes = fecha.substring(5, 7);
                String dia = fecha.substring(8);
                if(anio.matches("\\d{4}") && mes.matches("\\d{2}") && dia.matches("\\d{2}")){
                    if (anio.compareTo("2024")==0){
                        if (Integer.parseInt(mes)<=12){
                            if(Integer.parseInt(dia)<=31){
                                veracidad = true;
                            }
                            else{System.out.println("Solo hay hasta 31 días en un mes.");}
                        }
                        else{System.out.println("Solo existen 12 meses.");}
                    }
                    else{System.out.println("Año incorrecto.");}
                }
            }   
        }
        return veracidad;
    }

    // metodo para convertir la fecha ingresada en tipo Date
    protected Date convertirFecha(String fechaReserva) {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return formato.parse(fechaReserva);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    //metodo para elegir entre dos opciones
    protected String Elegiropciones(String eleccion,String opcion1, String opcion2){
        int c1 = eleccion.compareTo(opcion1);
        int c2 = eleccion.compareTo(opcion2);
        boolean veracidad = false;
        Scanner s = new Scanner(System.in);
        if (c1 == 0 || c2 == 0) {
            veracidad = true;
        }
        while (veracidad == false) {
            System.out.print('\n' + "Opción no válida. Elija Entre "+opcion1+" o "+opcion2+": ");
            eleccion = s.nextLine().toUpperCase();
            c1 = eleccion.compareTo(opcion1);
            c2 = eleccion.compareTo(opcion2);
            if (c1 == 0 || c2 == 0) {
                veracidad = true;
            }
        }
        return eleccion;
    }

    //obtener nombre del espacio
    protected String NombreEspacio(String codigoEspacio){
        String Nombreespacio = "null";
        for (Espacio esp : Sistema.espacios) {
            if (codigoEspacio.equals(esp.getCodigoEspacio())) {
                Nombreespacio = esp.getNombre();
            }
        }
        return Nombreespacio;
    }

    //metodo elegir espacios
    protected String ElegirEspacios(TipoEspacio espacio,String articulo){
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
                System.out.print('\n' + "Elija "+articulo+" "+espacio+" a reservar, colocando su Código (1XX): ");
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

    //metodo confirmar Reserva
    protected boolean Confirmar(String confirmacion,String Estado,Date fecha, String codigoEspacio, String espacio, String motivo,String Nombreespacio, String fechaReserva){
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

    public String toString() {
        return "Usuario [codigoUnico=" + codigoUnico + ", cedula=" + cedula + ", nombre="
                + nombre + ", apellido=" + apellido + ", usuario=" + usuario + ", contraseña=" + contrasenia
                + ", correo=" + correo + ", rol=" + rol;

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
