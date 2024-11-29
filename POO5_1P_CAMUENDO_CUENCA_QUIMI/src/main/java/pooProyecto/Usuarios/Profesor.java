package pooProyecto.Usuarios;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import io.github.cdimascio.dotenv.*;
import java.util.Properties;

import pooProyecto.Sistema.*;
import pooProyecto.Recursos.*;
import pooProyecto.Tipos.*;

public class Profesor extends Usuario {
    private String facultad;
    private String materia;

    // contructor de la clase Profesor
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

    // metodo toString
    @Override
    public String toString() {
        return "Profesor [facultad=" + facultad + ", materia=" + materia + ", getFacultad()=" + getFacultad()
                + ", getMateria()=" + getMateria() + ", toString()=" + super.toString() + ", getCodigoUnico()="
                + getCodigoUnico() + ", getCedula()=" + getCedula() + ", getNombre()=" + getNombre()
                + ", getApellido()=" + getApellido() + ", getUsuario()=" + getUsuario() + ", getCorreo()=" + getCorreo()
                + ", getContrasenia()=" + getContrasenia() + ", getRol()=" + getRol() + ", getClass()=" + getClass()
                + ", hashCode()=" + hashCode() + "]";
    }


    // Sobreescritura de metodos abstractos
    @Override
    public void reservar() {
        Scanner s = new Scanner(System.in);
        // El estudiante debe ingresar la fecha de la reserva
        System.out.println('\n' + "-------- RESERVAR --------");
        String fechaReserva;
        do {
            System.out.print("Ingrese la fecha de la reserva [YYYY-MM-DD]: ");
            fechaReserva = s.nextLine();
            if (!validarFormatoFecha(fechaReserva)) {
                System.out.println("Error al ingresar la fecha, por favor use el formato [YYYY-MM-DD]: ");
            }
        } while (!validarFormatoFecha(fechaReserva));

        Date fecha = convertirFecha(fechaReserva);





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
        ManejoArchivos m = new ManejoArchivos();
        String usuario = Sistema.getUsuario();
        String cedula = "null";
        String codigoUnico = "null";
        String correo = "null";
        TipoEstado tipoEstado = TipoEstado.valueOf("APROBADO");
        String nombre = "null";
        String apellido = "null";
        String Nombreespacio = "null";
        //Hallar los datos del Profesor que está usando el programa
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
        String[] materias = {};
        for (Usuario user : Sistema.usuarios) {
            if (user.getUsuario().compareTo(usuario) == 0) {
                Profesor pro = (Profesor) user;
                materias = pro.getMateria().split(",");
            }
        }
        materias[0] = materias[0].trim();
        materias[1] = materias[1].trim();
        switch (tipo) {
            case LABORATORIO:
                System.out.println('\n' + "------- Espacios Disponibles -------");
                System.out.println("Código de Espacio  |     Nombre");
                ArrayList<String> codigos = new ArrayList<>();
                for (Espacio space : Sistema.espacios) {
                    if (space.getTipo() == tipo && space.mostrarDisponibilidad()) {
                        System.out.println("        " + space.getCodigoEspacio() + "        |  " + space.getNombre());
                        codigos.add(space.getCodigoEspacio());
                    }
                }
                System.out.print('\n' + "Elija el Laboratorio a reservar, colocando su Código (1XX): ");
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
                //obtener nombre del espacio
                for(Espacio esp : Sistema.espacios){
                    if(codigoEspacio.equals(esp.getCodigoEspacio())){
                        Nombreespacio = esp.getNombre();
                    }
                }
                //Mostrar materias disponibles
                System.out.println('\n' + "Materias Disponibles: ");
                System.out.println("1."+materias[0]+"  2."+materias[1]);
                System.out.print('\n' + "Seleccione el motivo de la reserva: ");
                String motivo = s.nextLine();
                c1 = motivo.compareTo("1");
                c2 = motivo.compareTo("2");
                if(motivo.matches("[0-9]*")){
                    if(c1==0 || c2 == 0){
                        if(c1==0){
                            motivo=materias[0];
                        }
                        else{motivo=materias[1];}
                        veracidad = true;
                    }
                }
                //Validar opción
                while (veracidad==false) {
                    System.out.print('\n' + "OPCIÓN NO VALIDA. ELIJA ENTRE 1 o 2:  ");
                    motivo = s.nextLine();
                    c1 = motivo.compareTo("1");
                    c2 = motivo.compareTo("2");
                    if(motivo.matches("[0-9]*")){
                        if(c1==0 || c2 == 0){
                            if(c1==0){
                                motivo=materias[0];
                            }
                            else{motivo=materias[1];}
                            veracidad = true;
                        }
                    }
                }
                System.out.print('\n' + "Desea crear su reserva en el "+Nombreespacio+" con código " + codigoEspacio
                + " para la fecha " + fechaReserva + " [SI/NO]: ");
                String confirmacion = s.nextLine().toUpperCase();
                c1 = confirmacion.compareTo("SI");
                c2 = confirmacion.compareTo("NO");
                if (c1 == 0 || c2 == 0) {
                    veracidad = true;
                }
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
                            "APROBADO" + " | " + motivo;
                    m.EcribirArchivo("reservas", linea);
                    Reserva reserva = new Reserva(cod, codigoUnico, cedula, fecha, codigoEspacio, tipo, tipoEstado,
                            motivo);
                    Sistema.reservas.add(reserva);
                    enviarCorreo(correo,nombre,apellido,codigoReserva,fechaReserva,Nombreespacio,motivo);
                }
                break;
            case AULA:
                veracidad = false;
                System.out.println('\n' + "------- Espacios Disponibles -------");
                System.out.println("Código de Espacio  |     Nombre");
                System.out.println("------------------------------------------");
                ArrayList<String> codigos1 = new ArrayList<>();
                for (Espacio space : Sistema.espacios) {
                    if (space.getTipo() == tipo && space.mostrarDisponibilidad()) {
                        System.out.println("        " + space.getCodigoEspacio() + "        |    " + space.getNombre());
                        codigos1.add(space.getCodigoEspacio());
                    }
                }
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
                //obtener nombre del espacio
                for(Espacio esp : Sistema.espacios){
                    if(codigoEspacio1.equals(esp.getCodigoEspacio())){
                        Nombreespacio = esp.getNombre();
                    }
                }
                //Mostrar materias disponibles
                System.out.println('\n' + "Materias Disponibles: ");
                System.out.println("1."+materias[0]+"  2."+materias[1]);
                System.out.print('\n' + "Seleccione el motivo de la reserva: ");
                String motivo1 = s.nextLine();
                c1 = motivo1.compareTo("1");
                c2 = motivo1.compareTo("2");
                if(motivo1.matches("[0-9]*")){
                    if(c1==0 || c2 == 0){
                        if(c1==0){
                            motivo1=materias[0];
                        }
                        else{motivo1=materias[1];}
                        veracidad = true;
                    }
                }
                //Validar opción
                while (veracidad==false) {
                    System.out.print('\n' + "OPCIÓN NO VALIDA. ELIJA ENTRE 1 o 2:  ");
                    motivo1 = s.nextLine();
                    c1 = motivo1.compareTo("1");
                    c2 = motivo1.compareTo("2");
                    if(motivo1.matches("[0-9]*")){
                        if(c1==0 || c2 == 0){
                            if(c1==0){
                                motivo1=materias[0];
                            }
                            else{motivo1=materias[1];}
                            veracidad = true;
                        }
                    }
                }
                System.out.print('\n' + "Desea crear su reserva en el "+Nombreespacio+" con código " + codigoEspacio1 + " para la fecha "
                        + fechaReserva + " [SI/NO]: ");
                String confirmacion1 = s.nextLine().toUpperCase();
                c1 = confirmacion1.compareTo("SI");
                c2 = confirmacion1.compareTo("NO");
                if (c1 == 0 || c2 == 0) {
                    veracidad = true;
                }
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
                            "APROBADO" + " | " + motivo1;
                    m.EcribirArchivo("reservas", linea);
                    Reserva reserva = new Reserva(cod, codigoUnico, cedula, fecha, codigoEspacio1, tipo, tipoEstado,
                            motivo1);
                    Sistema.reservas.add(reserva);
                    enviarCorreo(correo,nombre,apellido,codigoReserva,fechaReserva,Nombreespacio,motivo1);
                }
                break;
            case AUDITORIO:
                veracidad = false;
                System.out.println('\n' + "---------- Espacios Disponibles ----------");
                System.out.println("Código de Espacio  |         Nombre");
                System.out.println("------------------------------------------");
                ArrayList<String> codigos2 = new ArrayList<>();
                for (Espacio space : Sistema.espacios) {
                    if (space.getTipo() == tipo && space.mostrarDisponibilidad()) {
                        System.out.println("        " + space.getCodigoEspacio() + "        |    " + space.getNombre());
                        codigos2.add(space.getCodigoEspacio());
                    }
                }
                System.out.print('\n' + "Elija el AUDITORIO a reservar, colocando su Código (1XX): ");
                String codigoEspacio2 = s.nextLine();
                if (codigoEspacio2.matches("[0-9]*")) {
                    if (codigoEspacio2.length() == 3) {
                        for (String st : codigos2) {
                            if (st.compareTo(codigoEspacio2) == 0) {
                                veracidad = true;
                            }
                        }
                    }
                }
                while (veracidad == false) {
                    System.out.print('\n' + "OPCION NO EXISTE. ELEGIR ENTRE LOS CODIGOS MOSTRADOS (1XX):  ");
                    codigoEspacio2 = s.nextLine().toUpperCase();
                    if (codigoEspacio2.matches("[0-9]*")) {
                        if (codigoEspacio2.length() == 3) {
                            for (String st : codigos2) {
                                if (st.compareTo(codigoEspacio2) == 0) {
                                    veracidad = true;
                                }
                            }
                        }
                    }
                }
                veracidad = false;
                //obtener nombre del espacio
                for(Espacio esp : Sistema.espacios){
                    if(codigoEspacio2.equals(esp.getCodigoEspacio())){
                        Nombreespacio = esp.getNombre();
                    }
                }
                //Mostrar materias disponibles
                System.out.println('\n' + "Materias Disponibles: ");
                System.out.println("1."+materias[0]+"  2."+materias[1]);
                System.out.print('\n' + "Seleccione el motivo de la reserva: ");
                String motivo2 = s.nextLine();
                c1 = motivo2.compareTo("1");
                c2 = motivo2.compareTo("2");
                if(motivo2.matches("[0-9]*")){
                    if(c1==0 || c2 == 0){
                        if(c1==0){
                            motivo2=materias[0];
                        }
                        else{motivo2=materias[1];}
                        veracidad = true;
                    }
                }
                //Validar opción
                while (veracidad==false) {
                    System.out.print('\n' + "OPCIÓN NO VALIDA. ELIJA ENTRE 1 o 2:  ");
                    motivo2 = s.nextLine();
                    c1 = motivo2.compareTo("1");
                    c2 = motivo2.compareTo("2");
                    if(motivo2.matches("[0-9]*")){
                        if(c1==0 || c2 == 0){
                            if(c1==0){
                                motivo2=materias[0];
                            }
                            else{motivo2=materias[1];}
                            veracidad = true;
                        }
                    }
                }
                veracidad = false;
                System.out.print('\n' + "Desea crear su reserva en el "+Nombreespacio+" con código " + codigoEspacio2 + " para la fecha "
                 + fechaReserva + " [SI/NO]: ");
                String confirmacion2 = s.nextLine().toUpperCase();
                c1 = confirmacion2.compareTo("SI");
                c2 = confirmacion2.compareTo("NO");
                if (c1 == 0 || c2 == 0) {
                    veracidad = true;
                }
                while (veracidad == false) {
                    System.out.print('\n' + "OPCIÓN NO VALIDA. ELIJA ENTRE SI / NO:  ");
                    confirmacion2 = s.nextLine().toUpperCase();
                    c1 = confirmacion2.compareTo("SI");
                    c2 = confirmacion2.compareTo("NO");
                    if (c1 == 0 || c2 == 0) {
                        veracidad = true;
                    }
                }
                if (confirmacion2.compareTo("SI") == 0) {
                    String linea = codigoReserva + " | " + codigoUnico + " | " + cedula + " | " + fechaReserva + " | "
                            + codigoEspacio2 + " | " + espacio + " | " +
                            "APROBADO" + " | " + motivo2;
                    m.EcribirArchivo("reservas", linea);
                    Reserva reserva = new Reserva(cod,codigoUnico,cedula,fecha,codigoEspacio2,tipo,tipoEstado,motivo2);
                    Sistema.reservas.add(reserva);
                    enviarCorreo(correo,nombre,apellido,codigoReserva,fechaReserva,Nombreespacio,motivo2);
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
        if (fechaReserva.length() == 10) {
            String numero = fechaReserva.substring(0, 4);
            if (fechaReserva.charAt(4) == '-' && fechaReserva.charAt(7) == '-') {
                if (numero.matches("[0-9]*")) {
                    numero = fechaReserva.substring(5, 7);
                    if (numero.matches("[0-9]*")) {
                        numero = fechaReserva.substring(8);
                        if (numero.matches("[0-9]*")) {
                            veracidad = true;
                        }
                    }
                }
            }
        }
        while (veracidad == false) {
            System.out.print("Error al ingresar la fecha, por favor use el formato [YYYY-MM-DD]: ");
            fechaReserva = s.nextLine();
            if (fechaReserva.length() == 10) {
                String numero = fechaReserva.substring(0, 4);
                if (fechaReserva.charAt(4) == '-' && fechaReserva.charAt(7) == '-') {
                    if (numero.matches("[0-9]*")) {
                        numero = fechaReserva.substring(5, 7);
                        if (numero.matches("[0-9]*")) {
                            numero = fechaReserva.substring(8);
                            if (numero.matches("[0-9]*")) {
                                veracidad = true;
                            }
                        }
                    }
                }
            }
        }
        veracidad = false;
        try {
            SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaReservada = inputDateFormat.parse(fechaReserva);
        
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
                        String fechaFormato = outputDateFormat.format(reserva.getFecha());
                        System.out.println("\n------------------Datos de la reserva--------------------");
                        System.out.println("Código reserva: " + reserva.getCodigoReserva() + " - Fecha: " + fechaFormato
                                + " - Tipo espacio: " + reserva.getTipoEspacio());
        
                        for (Espacio espacio : Sistema.espacios) {
                            if (reserva.getCodigoEspacio().equals(espacio.getCodigoEspacio())) {
                                System.out.println("Nombre espacio: " + espacio.getNombre() + " - Capacidad: "
                                        + espacio.getCapacidad() + " - Estado: " + espacio.getEstado()+"\nUsuario actual: " + usu.getNombre() + " " + usu.getApellido());
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
        } catch (ParseException e) {
            System.out.println("Error al procesar la fecha");
            e.printStackTrace();
        }
        
    }

    @Override
    public void mostrarMenu() {
        System.out.println('\n' + "............ Cargando menú ...............");
        Scanner sc = new Scanner(System.in);
        String opcion = "";
        while (!(opcion.equals("3"))) {
            System.out.println('\n' + "═══════════  Menú Profesor ═══════════ ");
            System.out.println("1. Reservar");
            System.out.println("2. Consultar Reserva");
            System.out.println("3. Salir" + '\n');
            System.out.print("Seleccione una opción: ");
            opcion =sc.nextLine();
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
    public void enviarCorreo(String correoRemitente,String nombre,String apellido,String codigo,String fecha,String espacio,String materia){
        try {
            Session session = enviarCorreo();
            String destinatario = "jcuencasaez3@gmail.com";
            // se crea el mensaje
            Message mes = new MimeMessage(session);
            mes.setFrom(new InternetAddress(correoRemitente,nombre+" "+apellido));
            mes.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            mes.setSubject("Reserva realizada");
            mes.setText("Se le notifica que el profesor "+nombre+" "+apellido+" ha realizado una reserva con código "+codigo+
            " para la fecha "+fecha+" en el "+espacio+" para la materia "+materia);
            // se envia el mensaje
            Transport.send(mes);
            System.out.println("Correo enviado con éxito al administrador.");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al enviar el correo de notificación.");
        }
    }

}
