package pooProyecto.Usuarios;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import pooProyecto.Sistema.*;
import pooProyecto.Recursos.*;
import pooProyecto.Tipos.*;

public class Estudiante extends Usuario {

    private String matricula;
    private String carrera;

    /**
 * Constructor de la clase Estudiante.
 *
 * @param codigoUnico El código único que identifica al estudiante.
 * @param cedula La cédula de identidad del estudiante.
 * @param nombre El nombre del estudiante.
 * @param apellido El apellido del estudiante.
 * @param usuario El nombre de usuario del estudiante.
 * @param contrasenia La contraseña asociada al usuario del estudiante.
 * @param correo El correo electrónico del estudiante.
 * @param rol El rol del estudiante, representado por un valor de tipo `TipoRol`.
 * @param matricula El número de matrícula del estudiante.
 * @param carrera La carrera que está cursando el estudiante.
 */
    public Estudiante(String codigoUnico, String cedula, String nombre, String apellido, String usuario,
            String contrasenia, String correo, TipoRol rol, String matricula, String carrera) {
        super(codigoUnico, cedula, nombre, apellido, usuario, contrasenia, correo, rol);
        this.matricula = matricula;
        this.carrera = carrera;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    @Override
    public String toString() {
        return "Estudiante [matricula=" + matricula + ", carrera=" + carrera + ", getMatricula()=" + getMatricula()
                + ", getCarrera()=" + getCarrera() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
                + ", toString()=" + super.toString() + "]";
    }

    /**
 * Permite al estudiante realizar una reserva de espacio.
 * El estudiante debe ingresar la fecha de la reserva, elegir el tipo de espacio (cancha o aula),
 * seleccionar un espacio disponible, proporcionar el motivo de la reserva y confirmar la reserva.
 * Finalmente, se envía un correo de confirmación al estudiante con los detalles de la reserva.
 */

    @Override
    public void reservar() {
        Scanner s = new Scanner(System.in);
        boolean veracidad = false;
        // El estudiante debe ingresar la fecha de la reserva
        System.out.println('\n' + "-------- RESERVAR --------");
        System.out.print("Ingrese la fecha de la reserva [YYYY-MM-DD]: ");
        String fechaReserva = s.nextLine();
        veracidad = validarFormatoFecha(fechaReserva);
        // Validar fecha
        while (veracidad == false) {
            System.out.print('\n' +"Error al ingresar la fecha, por favor use el formato [YYYY-MM-DD]: ");
            fechaReserva = s.nextLine();
            veracidad = validarFormatoFecha(fechaReserva);
        }
        veracidad = false;
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-mm-dd");
        Date fecha = new Date();
        try {
            fecha = formato.parse(fechaReserva);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ;
        // Elegir tipo Espacio
        System.out.print('\n' + "Elija el tipo de espacio que desea reservar [CANCHA/AULA]: ");
        String espacio = s.nextLine().toUpperCase();
        espacio = Elegiropciones(espacio, "CANCHA", "AULA");
        // Obtener todos los datos necesarios del Estudiante
        TipoEspacio tipo = TipoEspacio.valueOf(espacio);
        String nombre = "null";
        String usuario = Sistema.getUsuario();
        String correo = "null";
        String apellido = "null";
        String Nombreespacio = "null";
        for (Usuario user : Sistema.usuarios) {
            if (user.getUsuario().compareTo(usuario) == 0) {
                correo = user.getCorreo();
                nombre = user.getNombre();
                apellido = user.getApellido();
            }
        }
        String codigoReserva = String.valueOf(5001 + Reserva.ReservasCreadas);
        switch (tipo) {
            case CANCHA:
                System.out.println('\n' + "--------- Espacios Disponibles ---------");
                System.out.println("Código de Espacio  |     Nombre");
                System.out.println("----------------------------------------------");
                //Elije entre los espacios tipo Cancha
                String codigoEspacio = ElegirEspacios(tipo, "la");
                // motivo de reserva
                System.out.print('\n' + "Mencione el motivo de la reserva: ");
                String motivo = s.nextLine();
                // obtener nombre del espacio
                Nombreespacio = NombreEspacio(codigoEspacio);
                // Confirmar reserva
                System.out.print('\n' + "Desea crear su reserva en la " + Nombreespacio + " con código " + codigoEspacio
                        + " para la fecha " + fechaReserva + " [SI/NO]: ");
                String confirmacion = s.nextLine().toUpperCase();
                confirmacion = Elegiropciones(confirmacion, "SI", "NO");
                if (Confirmar(confirmacion, "PENDIENTE", fecha, codigoEspacio, espacio, motivo, Nombreespacio, fechaReserva)) {
                    enviarCorreo(correo, nombre, apellido, codigoReserva, fechaReserva, Nombreespacio, motivo);
                }
                break;
            case AULA:
                veracidad = false;
                System.out.println('\n' + "--------- Espacios Disponibles ---------");
                System.out.println("Código de Espacio  |     Nombre");
                System.out.println("----------------------------------------");
                //Elije entre los espacios tipo Aula
                String codigoEspacio1 = ElegirEspacios(tipo, "el");
                // obtener nombre del espacio
                Nombreespacio = NombreEspacio(codigoEspacio1);
                // motivo de reserva
                System.out.print('\n' + "Mencione el motivo de la reserva: ");
                String motivo1 = s.nextLine();
                // Confirmar reserva
                System.out.print('\n' + "Desea crear su reserva en el " + Nombreespacio + " con código " + codigoEspacio1
                        + " para la fecha "+ fechaReserva + " [SI/NO]: ");
                String confirmacion1 = s.nextLine().toUpperCase();
                confirmacion1 = Elegiropciones(confirmacion1, "SI", "NO");
                if (Confirmar(confirmacion1, "PENDIENTE", fecha, codigoEspacio1, espacio, motivo1, Nombreespacio, fechaReserva)) {
                    enviarCorreo(correo, nombre, apellido, codigoReserva, fechaReserva, Nombreespacio, motivo1);
                }
                break;
            default:
                break;
        }

    }

   /**
 * Muestra el menú principal para que el estudiante seleccione opciones como reservar, 
 * consultar reservas o salir.
 *
 * @param none
 * @return none
 */

    @Override
    public void mostrarMenu() {
        Scanner sc = new Scanner(System.in);
        String opcion = "";
        while (!(opcion.equals("3"))) {
            System.out.println('\n' + "....................Cargando menú.....................");
            System.out.println('\n' + "══════════════════ Menú Estudiante ══════════════════ ");
            System.out.println("1. Reservar");
            System.out.println("2. Consultar Reserva");
            System.out.println("3. Salir" + '\n');
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextLine();
            switch (opcion) {
                case "1":
                    reservar();
                    break;
                case "2":
                    ConsultarReserva();
                    break;
                case "3":
                    System.out.println("-------------- Salida Exitosa --------------");
                    break;
                default:
                    System.out.println("-------------- Opción no valida --------------");
            }
        }
        // sc.close();
    }

 /**
 * Sobrecarga del método para enviar un correo electrónico notificando sobre una reserva realizada.
 * El correo es enviado al administrador con los detalles de la reserva, incluyendo el nombre del estudiante, 
 * código de la reserva, fecha, espacio y motivo.
 *
 * @param correoRemitente El correo del remitente que está enviando el mensaje.
 * @param nombre El nombre del estudiante que realiza la reserva.
 * @param apellido El apellido del estudiante que realiza la reserva.
 * @param codigo El código único de la reserva realizada.
 * @param fecha La fecha en la que se realiza la reserva.
 * @param espacio El espacio reservado por el estudiante.
 * @param motivo El motivo de la reserva realizada.
 * @return void
 */
    public void enviarCorreo(String correoRemitente, String nombre, String apellido, String codigo, String fecha,
            String espacio, String motivo) {
        try {
            Session session = enviarCorreo();
            String destinatario = "jcuencasaez3@gmail.com";
            // se crea el mensaje
            Message mes = new MimeMessage(session);
            mes.setFrom(new InternetAddress(correoRemitente, nombre + " " + apellido));
            mes.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            mes.setSubject("Reserva realizada");
            mes.setText("El estudiante " + nombre + " " + apellido + " ha realizado una reserva con código " + codigo +
                    " para la fecha " + fecha + " en " + espacio
                    + ".\nIngrese al sistema para aprobar o rechzar la reserva.");
            // se envia el mensaje
            Transport.send(mes);
            System.out.println('\n' + "Correo enviado con éxito al administrador.");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al enviar el correo de notificación.");
        }
    }

}