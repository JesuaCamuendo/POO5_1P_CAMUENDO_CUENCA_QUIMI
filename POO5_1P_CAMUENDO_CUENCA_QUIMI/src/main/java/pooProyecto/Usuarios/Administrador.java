package pooProyecto.Usuarios;

import java.text.SimpleDateFormat;
import java.util.Scanner;

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
}



