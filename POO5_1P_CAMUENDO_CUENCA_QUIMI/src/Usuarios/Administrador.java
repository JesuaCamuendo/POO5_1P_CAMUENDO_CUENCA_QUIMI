package POO5_1P_CAMUENDO_CUENCA_QUIMI.src.Usuarios;

import java.util.Scanner;

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

    @Override
    public void reservar() {
        // TODO Auto-generated method stub
    }

    @Override
    public void notificarReserva() {
        // TODO Auto-generated method stub
    }

    @Override
    public void mostrarMenu() {
        System.out.println("............ Cargando menú ...............");
        System.out.println(".......... Menú Administrador .............");
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        while (opcion != 3) {
            System.out.println("1. Gestionar reserva");
            System.out.println("2. Consultar Reserva");
            System.out.println("3. Salir");
            System.out.println("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    reservar();
                    break;
                case 2:
                    notificarReserva();
                    break;
                case 3:
                    System.out.println("Salida Exitosa");
                    break;
                default:
                    System.out.println("Opción no valida");
            }
        }

        sc.close();
    }
}
