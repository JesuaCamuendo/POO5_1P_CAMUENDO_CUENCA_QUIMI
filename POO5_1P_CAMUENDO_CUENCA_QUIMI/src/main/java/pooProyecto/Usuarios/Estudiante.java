package pooProyecto.Usuarios;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
            System.out.print("Error al ingresar la fecha, por favor use el formato [YYYY-MM-DD]: ");
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
        int c1 = espacio.compareTo("CANCHA");
        int c2 = espacio.compareTo("AULA");
        if (c1 == 0 || c2 == 0) {
            veracidad = true;
        }
        // Validar opción
        while (veracidad == false) {
            System.out.print('\n' + "OPCION NO VALIDA. ELIJA ENTRE CANCHA/AULA:  ");
            espacio = s.nextLine().toUpperCase();
            c1 = espacio.compareTo("CANCHA");
            c2 = espacio.compareTo("AULA");
            if (c1 == 0 || c2 == 0) {
                veracidad = true;
            }
        }
        veracidad = false;
        // Obtener todos los datos necesarios del Estudiante
        TipoEspacio tipo = TipoEspacio.valueOf(espacio);
        ManejoArchivos m = new ManejoArchivos();
        String nombre = "null";
        String usuario = Sistema.getUsuario();
        String cedula = "null";
        String codigoUnico = "null";
        String correo = "null";
        String apellido = "null";
        String Nombreespacio = "null";
        TipoEstado tipoEstado = TipoEstado.valueOf("PENDIENTE");
        for (Usuario user : Sistema.usuarios) {
            if (user.getUsuario().compareTo(usuario) == 0) {
                cedula = user.getCedula();
                codigoUnico = user.getCodigoUnico();
                correo = user.getCorreo();
                nombre = user.getNombre();
                apellido = user.getApellido();

            }
        }
        String codigoReserva = String.valueOf(5001 + Reserva.ReservasCreadas);
        int cod = Integer.parseInt(codigoReserva);
        switch (tipo) {
            case CANCHA:
                System.out.println('\n' + "--------- Espacios Disponibles ---------");
                System.out.println("Código de Espacio  |     Nombre");
                System.out.println("----------------------------------------------");
                ArrayList<String> codigos = new ArrayList<>();
                for (Espacio space : Sistema.espacios) {
                    if (space.getTipo() == tipo && space.mostrarDisponibilidad()) {
                        System.out.println("        " + space.getCodigoEspacio() + "        |  " + space.getNombre());
                        codigos.add(space.getCodigoEspacio());
                    }
                }
                // Elegir la Cancha
                System.out.print('\n' + "Elija la Cancha a reservar, colocando su Código (1XX): ");
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
                veracidad = false;
                System.out.print('\n' + "Mencione el motivo de la reserva: ");
                String motivo = s.nextLine();
                // obtener nombre del espacio
                for (Espacio esp : Sistema.espacios) {
                    if (codigoEspacio.equals(esp.getCodigoEspacio())) {
                        nombre = esp.getNombre();
                    }
                }
                // Confirmar reserva
                System.out.print('\n' + "Desea crear su reserva en la " + nombre + " con código: " + codigoEspacio
                        + " para el: " + fechaReserva + " [SI/NO]: ");
                String confirmacion = s.nextLine().toUpperCase();
                c1 = confirmacion.compareTo("SI");
                c2 = confirmacion.compareTo("NO");
                if (c1 == 0 || c2 == 0) {
                    veracidad = true;
                }
                // Validar opción
                while (veracidad == false) {
                    System.out.print('\n' + "OPCIÓN NO VALIDA. ELIJA ENTRE SI / NO:  ");
                    confirmacion = s.nextLine().toUpperCase();
                    c1 = confirmacion.compareTo("SI");
                    c2 = confirmacion.compareTo("NO");
                    if (c1 == 0 || c2 == 0) {
                        veracidad = true;
                    }
                }
                if (confirmacion.compareTo("SI") == 0) {
                    String linea = codigoReserva + " | " + codigoUnico + " | " + cedula + " | " + fechaReserva + " | "
                            + codigoEspacio + " | " + espacio + " | " +
                            "PENDIENTE" + " | " + motivo;
                    m.EcribirArchivo("reservas", linea);
                    Reserva reserva = new Reserva(cod, codigoUnico, cedula, fecha, codigoEspacio, tipo, tipoEstado,
                            motivo);
                    Sistema.reservas.add(reserva);
                    enviarCorreo(correo, nombre, apellido, codigoReserva, fechaReserva, Nombreespacio, motivo);
                }
                break;
            case AULA:
                veracidad = false;
                System.out.println('\n' + "--------- Espacios Disponibles ---------");
                System.out.println("Código de Espacio  |     Nombre");
                System.out.println("----------------------------------------------");
                ArrayList<String> codigos1 = new ArrayList<>();
                for (Espacio space : Sistema.espacios) {
                    if (space.getTipo() == tipo && space.mostrarDisponibilidad()) {
                        System.out.println("        " + space.getCodigoEspacio() + "        |    " + space.getNombre());
                        codigos1.add(space.getCodigoEspacio());
                    }
                }
                // Elegir el Aula a reservar
                System.out.print('\n' + "Elija el AULA a reservar, colocando su Código (1XX): ");
                String codigoEspacio1 = s.nextLine();
                if (codigoEspacio1.matches("[0-9]*")) {
                    if (codigoEspacio1.length() == 3) {
                        for (String st : codigos1) {
                            if (st.compareTo(codigoEspacio1) == 0) {
                                veracidad = true;
                            }
                        }
                    }
                }
                // Validar opción
                while (veracidad == false) {
                    System.out.print('\n' + "OPCION NO EXISTE. ELEGIR ENTRE LOS CODIGOS MOSTRADOS (1XX):  ");
                    codigoEspacio1 = s.nextLine().toUpperCase();
                    if (codigoEspacio1.matches("[0-9]*")) {
                        if (codigoEspacio1.length() == 3) {
                            for (String st : codigos1) {
                                if (st.compareTo(codigoEspacio1) == 0) {
                                    veracidad = true;
                                }
                            }
                        }
                    }
                }
                veracidad = false;
                // obtener nombre del espacio
                for (Espacio esp : Sistema.espacios) {
                    if (codigoEspacio1.equals(esp.getCodigoEspacio())) {
                        nombre = esp.getNombre();
                    }
                }
                System.out.print('\n' + "Mencione el motivo de la reserva: ");
                String motivo1 = s.nextLine();
                // Confirmar reserva
                System.out.print('\n' + "Desea crear su reserva en el " + nombre + " con código: " + codigoEspacio1
                        + " para el: "
                        + fechaReserva + " [SI/NO]: ");
                String confirmacion1 = s.nextLine().toUpperCase();
                c1 = confirmacion1.compareTo("SI");
                c2 = confirmacion1.compareTo("NO");
                if (c1 == 0 || c2 == 0) {
                    veracidad = true;
                }
                // Validar opción
                while (veracidad == false) {
                    System.out.print('\n' + "OPCIÓN NO VALIDA. ELIJA ENTRE SI / NO:  ");
                    confirmacion1 = s.nextLine().toUpperCase();
                    c1 = confirmacion1.compareTo("SI");
                    c2 = confirmacion1.compareTo("NO");
                    if (c1 == 0 || c2 == 0) {
                        veracidad = true;
                    }
                }
                if (confirmacion1.compareTo("SI") == 0) {
                    String linea = codigoReserva + " | " + codigoUnico + " | " + cedula + " | " + fechaReserva + " | "
                            + codigoEspacio1 + " | " + espacio + " | " +
                            "PENDIENTE" + " | " + motivo1;
                    m.EcribirArchivo("reservas", linea);
                    Reserva reserva = new Reserva(cod, codigoUnico, cedula, fecha, codigoEspacio1, tipo, tipoEstado,
                            motivo1);
                    Sistema.reservas.add(reserva);
                    enviarCorreo(correo, nombre, apellido, codigoReserva, fechaReserva, Nombreespacio, motivo1);
                }
                break;
            default:
                break;
        }

    }

    @Override
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
        veracidad = false;

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
                            + " - Tipo espacio: " + reserva.getTipoEspacio());

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

    // sobrecarga del metodo enviar correo
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
                    + ".\nIngrese al sistemapara aprobar o rechzar la reserva.");
            // se envia el mensaje
            Transport.send(mes);
            System.out.println('\n' + "Correo enviado con éxito al administrador.");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al enviar el correo de notificación.");
        }
    }

}
