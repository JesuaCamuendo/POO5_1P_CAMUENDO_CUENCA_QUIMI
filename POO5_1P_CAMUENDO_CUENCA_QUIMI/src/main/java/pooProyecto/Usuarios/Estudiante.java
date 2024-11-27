package pooProyecto.Usuarios;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

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
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-mm-dd");
        Date fecha = new Date();
        try {
            fecha = formato.parse(fechaReserva);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ;
        System.out.print('\n' + "Elija el tipo de espacio que desea reservar [CANCHA/AULA]: ");
        String espacio = s.nextLine().toUpperCase();
        int c1 = espacio.compareTo("CANCHA");
        int c2 = espacio.compareTo("AULA");
        if (c1 == 0 || c2 == 0) {
            veracidad = true;
        }
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
        TipoEspacio tipo = TipoEspacio.valueOf(espacio);
        ManejoArchivos m = new ManejoArchivos();
        switch (tipo) {
            case CANCHA:
                System.out.println('\n' + "------- Espacios Disponibles -------");
                System.out.println("Código de Espacio  |     Nombre");
                ArrayList<String> codigos = new ArrayList<>();
                for (Espacio space : Sistema.espacios) {
                    if (space.getTipo() == tipo && space.mostrarDisponibilidad()) {
                        System.out.println("        " + space.getCodigoEspacio() + "        |  " + space.getNombre());
                        codigos.add(space.getCodigoEspacio());
                    }
                }
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
                System.out.print('\n' + "Mencione el motivo de la reserva: ");
                String motivo = s.nextLine();
                System.out.print('\n' + "Desea crear su reserva en la CANCHA con código: " + codigoEspacio
                        + " para el: " + fechaReserva + " [SI/NO]: ");
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
                    String usuario = Sistema.getUsuario();
                    String cedula = "null";
                    String codigoUnico = "null";
                    TipoEstado tipoEstado = TipoEstado.valueOf("PENDIENTE");
                    for (Usuario user : Sistema.usuarios) {
                        if (user.getUsuario().compareTo(usuario) == 0) {
                            cedula = user.getCedula();
                            codigoUnico = user.getCodigoUnico();
                        }
                    }
                    String codigoReserva = String.valueOf(5001 + Reserva.ReservasCreadas);
                    String linea = codigoReserva + " | " + codigoUnico + " | " + cedula + " | " + fechaReserva + " | "
                            + codigoEspacio + " | " + espacio + " | " +
                            "PENDIENTE" + " | " + motivo;
                    m.EcribirArchivo("reservas", linea);
                    int cod = Integer.parseInt(codigoReserva);
                    Reserva reserva = new Reserva(cod, codigoUnico, cedula, fecha, codigoEspacio, tipo, tipoEstado,
                            motivo);
                    Sistema.reservas.add(reserva);
                }
                break;
            case AULA:
                veracidad = false;
                System.out.println('\n' + "------- Espacios Disponibles -------");
                System.out.println("Código de Espacio  |     Nombre");
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
                System.out.print('\n' + "Mencione el motivo de la reserva: ");
                String motivo1 = s.nextLine();
                System.out.print('\n' + "Desea crear su reserva en el AULA con código: " + codigoEspacio1 + " para el: "
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
                    String usuario = Sistema.getUsuario();
                    String cedula = "null";
                    String codigoUnico = "null";
                    TipoEstado tipoEstado = TipoEstado.valueOf("PENDIENTE");
                    for (Usuario user : Sistema.usuarios) {
                        if (user.getUsuario().compareTo(usuario) == 0) {
                            cedula = user.getCedula();
                            codigoUnico = user.getCodigoUnico();
                        }
                    }
                    String codigoReserva = String.valueOf(5001 + Reserva.ReservasCreadas);
                    String linea = codigoReserva + " | " + codigoUnico + " | " + cedula + " | " + fechaReserva + " | "
                            + codigoEspacio1 + " | " + espacio + " | " +
                            "PENDIENTE" + " | " + motivo1;
                    m.EcribirArchivo("reservas", linea);
                    int cod = Integer.parseInt(codigoReserva);
                    Reserva reserva = new Reserva(cod, codigoUnico, cedula, fecha, codigoEspacio1, tipo, tipoEstado,
                            motivo1);
                    Sistema.reservas.add(reserva);
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
            for (Reserva reserva : Sistema.reservas) {
                if (reserva.getFecha().equals(fechaReservada)) {
                    reservaBuscada = true;
                    String fechaFormateada = outputDateFormat.format(reserva.getFecha());
                    System.out.println("\n------------------Datos de la reserva--------------------");
                    System.out.println("Código reserva: " + reserva.getCodigoReserva() + " - Fecha: " + fechaFormateada + " - Tipo espacio: " + reserva.getTipoEspacio());
                    for (Espacio espacio : Sistema.espacios) {
                        if (reserva.getCodigoEspacio().equals(espacio.getCodigoEspacio())) {
                            System.out.println("Nombre espacio: " + espacio.getNombre() + " - Capacidad: "
                                    + espacio.getCapacidad() + " - Estado: " + espacio.getEstado());
                        }
                    }
                    for (Usuario usuario : Sistema.usuarios) {
                        if (reserva.getCodigoUnico().equals(usuario.getCodigoUnico())) {
                            System.out.println(
                                    "Nombre: " + usuario.getNombre() + " - Apellido: " + usuario.getApellido());
                        }
                    }
                }
            }

            if (!reservaBuscada) {
                System.out.println("No se encontraron reservas para esta fecha");
            }
        } catch (ParseException e) {
            System.out.println("Error al procesar la fecha");
            e.printStackTrace();
        }

    }

    @Override
    public void mostrarMenu() {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        while (opcion != 3) {
            System.out.println('\n' + "................Cargando menú.................");
            System.out.println('\n' + "-------------- Menú Estudiante -------------- ");
            System.out.println("1. Reservar");
            System.out.println("2. Consultar Reserva");
            System.out.println("3. Salir" + '\n');
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
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
        // sc.close();
    }

}