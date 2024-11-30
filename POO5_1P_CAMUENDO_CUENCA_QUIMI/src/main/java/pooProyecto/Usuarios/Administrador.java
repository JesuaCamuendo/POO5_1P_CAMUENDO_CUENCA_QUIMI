package pooProyecto.Usuarios;

import java.text.SimpleDateFormat;
import java.util.Scanner;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import pooProyecto.Sistema.*;
import pooProyecto.Tipos.TipoEstado;
import pooProyecto.Tipos.TipoRol;
import pooProyecto.Recursos.*;

public class Administrador extends Usuario {
    private String cargo;

    /**
 * Constructor de la clase Administrador.
 *
 * @param codigoUnico El código único que identifica al administrador.
 * @param cedula La cédula de identidad del administrador.
 * @param nombre El nombre del administrador.
 * @param apellido El apellido del administrador.
 * @param usuario El nombre de usuario para el administrador.
 * @param contrasenia La contraseña asociada al usuario del administrador.
 * @param correo El correo electrónico del administrador.
 * @param rol El rol del administrador, representado por un valor de tipo `TipoRol` (por ejemplo, "Administrador").
 * @param cargo El cargo específico que ocupa el administrador dentro de la organización
 */
    public Administrador(String codigoUnico, String cedula, String nombre, String apellido, String usuario,
            String contrasenia, String correo, TipoRol rol, String cargo) {
        super(codigoUnico, cedula, nombre, apellido, usuario, contrasenia, correo, rol);
        this.cargo = cargo;
    }

    //getters y setters
    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }



    /**
 *  Permite al usuario gestionar una reserva mediante su código, 
 * revisar los detalles de la reserva, y luego aprobar o rechazarla. Si se aprueba o rechaza la reserva, 
 * se envía un correo de notificación al administrador.
 *
 * @param none
 * @return void
 */

    // Para Administrador este método funciona como gestionador de reservas
    public void reservar() {
        System.out.println("---------------Gestionar Reserva-----------------");
        System.out.print('\n' +"Ingrese el código de la reserva a gestionar: ");
        Scanner sc = new Scanner(System.in);
        String codigo = sc.nextLine();
        boolean veracidad = false;
        String remitente = "null";
        String cedula = "null";
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        String fecha = "null";
        String tipoespacio = "null";
        String nombrespacio = "null";
        String capacidad = "null";
        String nombre = "null";
        String apellido = "null";
        String codigoespacio = "null";
        String destinatario = "null";
        if (codigo.matches("[0-9]*")) {
            if (codigo.length() == 4) {
                for (Reserva reser : Sistema.reservas) {
                    String codigoReserva = String.valueOf(reser.getCodigoReserva());
                    if (codigoReserva.equals(codigo)) {
                        cedula = reser.getCedula();
                        fecha = formatoFecha.format(reser.getFecha());
                        tipoespacio = String.valueOf(reser.getTipoEspacio());
                        codigoespacio = reser.getCodigoEspacio();
                        veracidad = true;
                    }
                }
            }
        }
        while (veracidad==false) {
            System.out.print('\n' +"Código inválido. Vuelva a intentarlo: ");
            codigo = sc.nextLine();
            if (codigo.length() == 4) {
                for (Reserva reser : Sistema.reservas) {
                    String codigoReserva = String.valueOf(reser.getCodigoReserva());
                    if (codigoReserva.equals(codigo)) {
                        cedula = reser.getCedula();
                        fecha = formatoFecha.format(reser.getFecha());
                        tipoespacio = String.valueOf(reser.getTipoEspacio());
                        codigoespacio = reser.getCodigoEspacio();
                        veracidad = true;
                    }
                }
            }
        }
        veracidad = false;
        for (Usuario user : Sistema.usuarios) {
            if (user.getCedula().compareTo(cedula) == 0) {
                nombre = user.getNombre();
                apellido = user.getApellido();
                destinatario = user.getCorreo();
            }
            if(user.getUsuario().equals(Sistema.getUsuario())){
                remitente = user.getCorreo();
            }
        }
        for (Espacio esp : Sistema.espacios) {
            if (esp.getCodigoEspacio().compareTo(codigoespacio) == 0) {
                nombrespacio = esp.getNombre();
                capacidad = String.valueOf(esp.getCapacidad());
            }
        }
        System.out.println('\n' +codigo+" - "+fecha+" - "+tipoespacio+" - "+nombrespacio+" - "+capacidad+" - "+
        nombre+" "+apellido);
        System.out.print('\n' +"Desea aprobar o rechazar esta reserva [APROBADO/RECHAZADO]: ");
        String decision = sc.nextLine().toUpperCase();
        decision = Elegiropciones(decision, "APROBADO", "RECHAZADO");
        String motivo = "null";
        String asunto = "null";
        if (decision.compareTo("APROBADO") == 0) {
            motivo = "Sin motivo";
            asunto = "aprobada";
            for (Reserva r: Sistema.reservas){
                String codigoReserva = String.valueOf(r.getCodigoReserva());
                if(codigoReserva.equals(codigo)){
                    r.setTipoEstado(TipoEstado.valueOf(decision));
                }
            }
            enviarCorreo(remitente,destinatario,decision,codigo,motivo,asunto);
        }
        else if(decision.compareTo("RECHAZADO") == 0){
            System.out.print('\n' +"Ingrese el motivo del rechazo: ");
            motivo = sc.nextLine();
            asunto = "rechazada";
            for (Reserva r: Sistema.reservas){
                String codigoReserva = String.valueOf(r.getCodigoReserva());
                if(codigoReserva.equals(codigo)){
                    r.setTipoEstado(TipoEstado.valueOf(decision));
                }
            }
            enviarCorreo(remitente,destinatario,decision,codigo,motivo,asunto);
        }
    }

    /**
 * Muestra una lista de todas las reservas, incluyendo detalles como el código de la reserva, 
 * el estado, la fecha y la información del usuario (estudiante, profesor o administrador).
 *
 * @param none
 * @return void
 */

    @Override
    public void ConsultarReserva() {
        System.out.println("\n-----------------Consulta de reserva----------------");
        System.out.println("\nNúmero de reservas creadas: " + Reserva.ReservasCreadas);
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        for (Reserva reserva : Sistema.reservas) {
            Usuario usu= null;
            String fecha = formatoFecha.format(reserva.getFecha());
            for (Usuario usuario : Sistema.usuarios) {
                if (usuario.getCedula().equals(reserva.getCedula())) {
                   usu= usuario;
                }
            }
            if (usu instanceof Estudiante ) {
                Estudiante e = (Estudiante) usu; //downcasting
                System.out.println(reserva.getCodigoReserva() + " - " + reserva.getTipoEstado() + " - "
                        + fecha + " - " +usu.getNombre() + " " + usu.getApellido()
                        + " - " + e.getMatricula() + " - " + usu.getRol());
            } else if (usu instanceof Profesor){
                Profesor p = (Profesor) usu;
                System.out.println(reserva.getCodigoReserva() + " - " + reserva.getTipoEstado() + " - "
                        + fecha + " - " + usu.getNombre() + " " + usu.getApellido()
                        + " - " + p.getMateria() + " - " + usu.getRol());
            }else{
                Administrador a= (Administrador) usu;
                System.out.println(reserva.getCodigoReserva() + " - " + reserva.getTipoEstado() + " - "
                        + fecha + " - " + usu.getNombre() + " " + usu.getApellido()
                        + " - " + a.getCargo() + " - " + usu.getRol());
            }
        }

    }

    /**
 * Muestra el menú de opciones para el Administrador, permitiéndole gestionar y consultar reservas.
 * El menú permite al Administrador elegir entre gestionar una reserva, consultar reservas o salir.
 * El ciclo se repite hasta que se elige la opción de salir (opción 3).
 *
 * @param none
 * @return void
 */

    @Override
    public void mostrarMenu() {
        System.out.println('\n' + "............ Cargando menú ...............");
        Scanner sc = new Scanner(System.in);
        String opcion = "";
        while (!(opcion.equals("3"))) {
            System.out.println('\n' + "═══════════ Menú Administrador ═══════════ ");
            System.out.println("1. Gestionar Reserva");
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
    }

    /**
 * Envia un correo electrónico notificando sobre el estado de una reserva (aprobada o rechazada), 
 * con el código de la reserva, el motivo y el asunto correspondiente.
 *
 * @param correoRemitente El correo del remitente que está enviando el mensaje.
 * @param destinatario El correo del destinatario que recibirá la notificación.
 * @param decision La decisión tomada sobre la reserva (APROBADO o RECHAZADO).
 * @param codigo El código único de la reserva.
 * @param motivo El motivo de la decisión tomada (si es RECHAZADO, por ejemplo).
 * @param asunto El asunto del correo, que será "aprobada" o "rechazada" según el caso.
 * @return void
 */
    public void enviarCorreo(String correoRemitente,String destinatario,String decision,String codigo,String motivo,String asunto){
        try {
            Session session = enviarCorreo();
            destinatario = "jcuencasaez3@gmail.com";
            // se crea el mensaje
            Message mes = new MimeMessage(session);
            mes.setFrom(new InternetAddress(correoRemitente));
            mes.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            mes.setSubject("Reserva "+asunto);
            mes.setText("Se ha "+decision+" su reserva con código "+codigo+" por el siguiente motivo: "+motivo+'.'+'\n'+'\n'
            +"Atentamente,"+'\n'+"Departamento Administrativo");
            // se envia el mensaje
            Transport.send(mes);
            System.out.println('\n'+"Correo enviado con éxito al estudiante.");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al enviar el correo de notificación.");
        }
    }
}



