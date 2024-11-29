package pooProyecto.Usuarios;

import java.util.Date;
import java.util.Scanner;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import pooProyecto.Recursos.Reserva;
import pooProyecto.Sistema.Sistema;
import pooProyecto.Tipos.TipoEspacio;
import pooProyecto.Tipos.TipoRol;

public class Profesor extends Usuario {

    private String facultad;
    private String materia;

    /**
     * Constructor para crear un objeto Profesor. Inicializa los atributos del
     * Profesor, incluyendo la facultad y la materia, además de los atributos
     * heredados de la clase base (Usuario).
     *
     * @param codigoUnico El código único del profesor.
     * @param cedula La cédula de identidad del profesor.
     * @param nombre El nombre del profesor.
     * @param apellido El apellido del profesor.
     * @param usuario El nombre de usuario del profesor.
     * @param contrasenia La contraseña del profesor.
     * @param correo El correo electrónico del profesor.
     * @param rol El rol del usuario (TipoRol) del profesor.
     * @param facultad La facultad a la que pertenece el profesor.
     * @param materia La materia que imparte el profesor.
     */
    public Profesor(String codigoUnico, String cedula, String nombre, String apellido, String usuario,
            String contrasenia, String correo, TipoRol rol, String facultad, String materia) {
        super(codigoUnico, cedula, nombre, apellido, usuario, contrasenia, correo, rol);
        this.facultad = facultad;
        this.materia = materia;
    }

    // getters y setters
    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    // Sobreescritura de metodos abstractos
    /**
     * Permite a un profesor realizar una reserva de un espacio (laboratorio,
     * aula o auditorio) para una fecha determinada.
     *
     * @param none No recibe nada
     * @return void No retorna nada
     */
    @Override
    public void reservar() {
        Scanner s = new Scanner(System.in);
        // 1. El profesor debe ingresar la fecha de la reserva
        System.out.println("\n" + "--------- RESERVAR ---------");
        String fechaReserva;
        do {
            System.out.print("Ingrese la fecha de la reserva [YYYY-MM-DD]: ");
            fechaReserva = s.nextLine();
            if (!validarFormatoFecha(fechaReserva)) {
                System.out.print('\n' + "Error al ingresar la fecha, por favor use el formato [YYYY-MM-DD]: ");
            }
        } while (!validarFormatoFecha(fechaReserva));
        Date fecha = convertirFecha(fechaReserva);
        // 2. El profesor selecciona el espacio
        boolean veracidad = false;
        System.out.print('\n' + "Elija el tipo de espacio que desea reservar [LABORATORIO/AULA/AUDITORIO]: ");
        String espacio = s.nextLine().toUpperCase();
        int c1 = espacio.compareTo("LABORATORIO");
        int c2 = espacio.compareTo("AULA");
        int c3 = espacio.compareTo("AUDITORIO");
        if (c1 == 0 || c2 == 0 || c3 == 0) {
            veracidad = true;
        }
        while (veracidad == false) {
            System.out.print('\n' + "OPCION NO VALIDA. ELIJA ENTRE LABORATORIO/AULA/AUDITORIO:  ");
            espacio = s.nextLine().toUpperCase();
            c1 = espacio.compareTo("LABORATORIO");
            c2 = espacio.compareTo("AULA");
            c3 = espacio.compareTo("AUDITORIO");
            if (c1 == 0 || c2 == 0 || c3 == 0) {
                veracidad = true;
            }
        }
        veracidad = false;
        TipoEspacio tipo = TipoEspacio.valueOf(espacio);
        String usuario = Sistema.getUsuario();
        String correo = "null";
        String nombre = "null";
        String apellido = "null";
        String Nombreespacio = "null";
        // Hallar los datos del Profesor que está usando el programa
        for (Usuario user : Sistema.usuarios) {
            if (user.getUsuario().compareTo(usuario) == 0) {
                correo = user.getCorreo();
                nombre = user.getNombre();
                apellido = user.getApellido();
            }
        }
        String codigoReserva = String.valueOf(5001 + Reserva.ReservasCreadas);
        switch (tipo) {
            case LABORATORIO:
                System.out.println('\n' + "--------- Espacios Disponibles ---------");
                System.out.println("Código de Espacio  |     Nombre");
                System.out.println("----------------------------------------");
                // Elije entre los espacios tipo Laboratorio
                String codigoEspacio = ElegirEspacios(tipo, "el");
                // obtener nombre del espacio
                Nombreespacio = NombreEspacio(codigoEspacio);
                // Mostrar materias disponibles
                String motivo = ElegirMateria();
                //Crear Reserva
                System.out.print('\n' + "Desea crear su reserva en el " + Nombreespacio + " con código " + codigoEspacio
                        + " para la fecha " + fechaReserva + " [SI/NO]: ");
                String confirmacion = s.nextLine().toUpperCase();
                confirmacion = Elegiropciones(confirmacion, "SI", "NO");
                //confirmamos la reserva y enviamos correo
                if (Confirmar(confirmacion, "APROBADO", fecha, codigoEspacio, espacio, motivo, Nombreespacio, fechaReserva)) {
                    enviarCorreo(correo, nombre, apellido, codigoReserva, fechaReserva, Nombreespacio, motivo);
                }
                break;
            case AULA:
                System.out.println('\n' + "--------- Espacios Disponibles ---------");
                System.out.println("Código de Espacio  |     Nombre");
                System.out.println("----------------------------------------");
                // Elije entre los espacios tipo Aula
                String codigoEspacio1 = ElegirEspacios(tipo, "el");
                // obtener nombre del espacio
                Nombreespacio = NombreEspacio(codigoEspacio1);
                // Mostrar materias disponibles
                String motivo1 = ElegirMateria();
                //Crear Reserva
                System.out.print('\n' + "Desea crear su reserva en el " + Nombreespacio + " con código "
                        + codigoEspacio1 + " para la fecha " + fechaReserva + " [SI/NO]: ");
                String confirmacion1 = s.nextLine().toUpperCase();
                confirmacion1 = Elegiropciones(confirmacion1, "SI", "NO");
                //confirmamos la reserva y enviamos correo
                if (Confirmar(confirmacion1, "APROBADO", fecha, codigoEspacio1, espacio, motivo1, Nombreespacio, fechaReserva)) {
                    enviarCorreo(correo, nombre, apellido, codigoReserva, fechaReserva, Nombreespacio, motivo1);
                }
                break;
            case AUDITORIO:
                System.out.println('\n' + "------------ Espacios Disponibles ------------");
                System.out.println("Código de Espacio  |         Nombre");
                System.out.println("----------------------------------------------");
                // Elije entre los espacios tipo Auditorio
                String codigoEspacio2 = ElegirEspacios(tipo, "el");
                // obtener nombre del espacio
                Nombreespacio = NombreEspacio(codigoEspacio2);
                // Mostrar materias disponibles
                String motivo2 = ElegirMateria();
                //Crear Reserva
                System.out.print('\n' + "Desea crear su reserva en el " + Nombreespacio + " con código "
                        + codigoEspacio2 + " para la fecha " + fechaReserva + " [SI/NO]: ");
                String confirmacion2 = s.nextLine().toUpperCase();
                confirmacion2 = Elegiropciones(confirmacion2, "SI", "NO");
                //confirmamos la reserva y enviamos correo
                if (Confirmar(confirmacion2, "APROBADO", fecha, codigoEspacio2, espacio, motivo2, Nombreespacio, fechaReserva)) {
                    enviarCorreo(correo, nombre, apellido, codigoReserva, fechaReserva, Nombreespacio, motivo2);
                }
                break;
            default:
                break;
        }

    }

    /**
     * Muestra el menú interactivo para el profesor, permitiéndole elegir entre
     * varias opciones: 1. Reservar un espacio. 2. Consultar una reserva
     * existente. 3. Salir del menú. El menú se repite hasta que el profesor
     * selecciona la opción de salir.
     *
     * @param none No recibe nada
     * @return void No retorna nada
     */
    @Override
    public void mostrarMenu() {
        System.out.println('\n' + "............ Cargando menú ...............");
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        while (opcion != 3) {
            System.out.println('\n' + "--------------  Menú Profesor -------------- ");
            System.out.println("1. Reservar");
            System.out.println("2. Consultar Reserva");
            System.out.println("3. Salir" + '\n');
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    reservar();
                    break;
                case 2:
                    ConsultarReserva();
                    break;
                case 3:
                    System.out.println("-------------- Salida Exitosa --------------");
                    break;
                default:
                    System.out.println("-------------- Opción no valida --------------");
            }
        }
    }

    /**
     * Envia un correo de notificación a un destinatario específico para
     * informar sobre una reserva realizada por un profesor.
     *
     * @param correoRemitente Dirección de correo electrónico del remitente
     * (profesor).
     * @param nombre Nombre del profesor que realiza la reserva.
     * @param apellido Apellido del profesor que realiza la reserva.
     * @param codigo Código único asociado a la reserva.
     * @param fecha Fecha de la reserva.
     * @param espacio Espacio o lugar reservado.
     * @param materia Materia para la cual se realiza la reserva.
     */
    public void enviarCorreo(String correoRemitente, String nombre, String apellido, String codigo, String fecha,
            String espacio, String materia) {
        try {
            Session session = enviarCorreo();
            String destinatario = "jcuencasaez3@gmail.com";
            // se crea el mensaje
            Message mes = new MimeMessage(session);
            mes.setFrom(new InternetAddress(correoRemitente, nombre + " " + apellido));
            mes.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            mes.setSubject("Reserva realizada");
            mes.setText("Se le notifica que el profesor " + nombre + " " + apellido
                    + " ha realizado una reserva con código " + codigo
                    + " para la fecha " + fecha + " en el " + espacio + " para la materia " + materia);
            // se envia el mensaje
            Transport.send(mes);
            System.out.println('\n' + "Correo enviado con éxito al administrador.");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al enviar el correo de notificación.");
        }
    }

    /**
     * Este metodo sirve para Elegir la materia de profesor
     *
     * @return String Devuelte la materia como motivo de reserva
     */
    public String ElegirMateria() {
        String[] materias = {};
        Scanner s = new Scanner(System.in);
        boolean veracidad = false;
        for (Usuario user : Sistema.usuarios) {
            if (user.getUsuario().compareTo(usuario) == 0) {
                Profesor pro = (Profesor) user;
                materias = pro.getMateria().split(",");
            }
        }
        materias[0] = materias[0].trim();
        materias[1] = materias[1].trim();
        System.out.println('\n' + "Materias Disponibles: ");
        System.out.println("1." + materias[0] + "  2." + materias[1]);
        System.out.print('\n' + "Seleccione el motivo de la reserva: ");
        String motivo = s.nextLine();
        int c1 = motivo.compareTo("1");
        int c2 = motivo.compareTo("2");
        if (motivo.matches("[0-9]*")) {
            if (c1 == 0 || c2 == 0) {
                if (c1 == 0) {
                    motivo = materias[0];
                } else {
                    motivo = materias[1];
                }
                veracidad = true;
            }
        }
        // Validar opción
        while (veracidad == false) {
            System.out.print('\n' + "OPCIÓN NO VALIDA. ELIJA ENTRE 1 o 2:  ");
            motivo = s.nextLine();
            c1 = motivo.compareTo("1");
            c2 = motivo.compareTo("2");
            if (motivo.matches("[0-9]*")) {
                if (c1 == 0 || c2 == 0) {
                    if (c1 == 0) {
                        motivo = materias[0];
                    } else {
                        motivo = materias[1];
                    }
                    veracidad = true;
                }
            }
        }
        return motivo;
    }
}
