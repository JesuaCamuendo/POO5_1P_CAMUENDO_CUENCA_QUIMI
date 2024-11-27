package POO5_1P_CAMUENDO_CUENCA_QUIMI.src.Usuarios;

import java.util.Scanner;
import POO5_1P_CAMUENDO_CUENCA_QUIMI.src.Sistema;
import POO5_1P_CAMUENDO_CUENCA_QUIMI.src.Tipos.TipoRol;

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

    public void reservar() {
        // TODO Auto-generated method stub
    }

    @Override
    public void ConsultarReserva() {
        System.out.println("-----------------Consulta de reserva----------------");
        System.out.println("Número de reservas creadas: " + Reserva.ReservasCreadas());
        for (Reserva reserva : Sistema.reservas) {
            for (Usuario usuario : Sistema.usuarios) {
                if (reserva.getCodigoReserva().equals(usuario.getCodigoReserva())) {
                    if (usuario instanceof Estudiante) {
                        Estudiante e = (Estudiante) usuario;
                        System.out.println(reserva.getCodigoReserva() + " - " + reserva.getEstado() + " - "
                                + reserva.getFecha() + " - " + usuario.getNombre() + " " + usuario.getApellido()
                                + " - " + e.getMatricula() + " - " + usuario.getRol());
                    } else {
                        Profesor p = (Profesor) usuario;
                        System.out.println(reserva.getCodigoReserva() + " - " + reserva.getEstado() + " - "
                                + reserva.getFecha() + " - " + usuario.getNombre() + " " + usuario.getApellido()
                                + " - " + p.getMateria() + " - " + usuario.getRol());
                    }
                }

            }
        }

    }

    public void GestionarReserva() {
    }

    @Override
    public void mostrarMenu() {
        System.out.println('\n' + "............ Cargando menú ...............");
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        while (opcion != 3) {
            System.out.println('\n' + ".......... Menú Administrador ..........");
            System.out.println("1. Gestionar Reserva");
            System.out.println("2. Consultar Reserva");
            System.out.println("3. Salir" + '\n');
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    GestionarReserva();
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
        System.out.println("Salida Exitosa");
        // sc.close();
    }
}
