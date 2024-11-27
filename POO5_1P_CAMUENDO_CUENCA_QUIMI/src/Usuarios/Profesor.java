package POO5_1P_CAMUENDO_CUENCA_QUIMI.src.Usuarios;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import POO5_1P_CAMUENDO_CUENCA_QUIMI.src.Espacio;
import POO5_1P_CAMUENDO_CUENCA_QUIMI.src.Reserva;
import POO5_1P_CAMUENDO_CUENCA_QUIMI.src.Sistema.Sistema;
import POO5_1P_CAMUENDO_CUENCA_QUIMI.src.Tipos.TipoEspacio;
import POO5_1P_CAMUENDO_CUENCA_QUIMI.src.Tipos.TipoRol;

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
        Scanner sc = new Scanner(System.in);
        // El estudiante/profesor debe ingresar la fecha de la reserva
        System.out.println('\n' + "-------- RESERVAR --------");
        System.out.print("Ingrese la fecha de la reserva (YYYY-MM-DD): ");
        String fechaReserva = sc.nextLine();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-mm-dd");
        try {
            Date fecha = formato.parse(fechaReserva);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.print('\n' + "Elija el tipo de espacio que desea reservar (LABORATORIO/AULA/AUDITORIO): ");
        String espacio = sc.nextLine().toUpperCase().trim();
        while (espacio != "LABORATORIO" || espacio != "AULA" || espacio != "AUDITORIO") {
            System.out.print('\n' + "OPCION NO VALIDA. ELIJA ENTRE: LABORATORIO/AULA/AUDITORIO: ");
        }
        TipoEspacio tipo = TipoEspacio.valueOf(espacio.toUpperCase());
        //sc.close();
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
                    System.out.println("Código reserva: " + reserva.getCodigoReserva() + " - Fecha: " + fechaFormateada
                            + " - Tipo espacio: " + reserva.getTipoEspacio());
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

        // sc.close();

    }

}
