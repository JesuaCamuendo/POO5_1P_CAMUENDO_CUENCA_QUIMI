package pooProyecto.Usuarios;

import java.text.SimpleDateFormat;
import java.util.Scanner;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import pooProyecto.Sistema.*;
import pooProyecto.Tipos.TipoRol;
import pooProyecto.Recursos.*;

public class Administrador extends Usuario {
    private String cargo;

    public Administrador(String codigoUnico, String cedula, String nombre, String apellido, String usuario,
            String contrasenia, String correo, TipoRol rol, String cargo) {
        super(codigoUnico, cedula, nombre, apellido, usuario, contrasenia, correo, rol);
        this.cargo = cargo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    @Override
    public String toString() {
        return super.toString() + ", cargo=" + cargo + "]";
    }

    // Para Administrador este método funciona como gestionador de reservas
    public void reservar() {
        System.out.println("---------------Gestionar Reserva-----------------");
        System.out.print('\n' +"Ingrese el código de la reserva a gestionar: ");
        Scanner sc = new Scanner(System.in);
        String codigo = sc.nextLine();
        ManejoArchivos m = new ManejoArchivos();
        boolean veracidad = false;
        String remitente = "null";
        String cedula = "null";
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
                        fecha = String.valueOf(reser.getFecha());
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
                        fecha = String.valueOf(reser.getFecha());
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
        int c1 = decision.compareTo("APROBADO");
        int c2 = decision.compareTo("RECHAZADO");
        if(c1 == 0 || c2 ==0){
            veracidad = true;
        }
        while (veracidad==false) {
            System.out.print('\n' +"Opción inválida. Ingrese APROBADO o RECHAZADO: ");
            decision = sc.nextLine().toUpperCase();
            c1 = decision.compareTo("APROBADO");
            c2 = decision.compareTo("RECHAZADO");
            if(c1 == 0 || c2 ==0){
                veracidad = true;
            }
        }
        String motivo = "null";
        if (c1 == 0) {
            motivo = "Sin motivo";
            m.SobreescribirDesicion("reservas", decision, codigo);
            enviarCorreo(remitente,destinatario,decision,codigo,motivo);
        }
        else if(c2 == 0){
            System.out.print('\n' +"Ingrese el motivo del rechazo: ");
            motivo = sc.nextLine();
            m.SobreescribirDesicion("reservas", decision, codigo);
            enviarCorreo(remitente,destinatario,decision,codigo,motivo);
        }
    }

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
                Estudiante e = (Estudiante) usu;
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
        System.out.println("Salida Exitosa");
        // sc.close();
    }

    public void enviarCorreo(String correoRemitente,String destinatario,String decision,String codigo,String motivo){
        try {
            Session session = enviarCorreo();
            destinatario = "jcuencasaez3@gmail.com";
            // se crea el mensaje
            Message mes = new MimeMessage(session);
            mes.setFrom(new InternetAddress(correoRemitente));
            mes.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            mes.setSubject("Reserva "+decision);
            mes.setText("Se ha "+decision+" con código "+codigo+" por el siguiente motivo: "+motivo+'.'+'\n'+'\n'
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



