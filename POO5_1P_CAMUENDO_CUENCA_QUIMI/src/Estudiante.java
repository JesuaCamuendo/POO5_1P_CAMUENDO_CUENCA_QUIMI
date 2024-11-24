package POO5_1P_CAMUENDO_CUENCA_QUIMI.src;

import java.util.Scanner;
import java.util.ArrayList;
public class Estudiante extends Usuario {

    private String matricula;
    private String carrera;

    public Estudiante(String codigoUnico, String cedula, String nombre, String apellido, String usuario,
            String contrasenia, String correo, char rol, String matricula, String carrera) {
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

    public void mostrarMenuEstudiante() {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        while (opcion != 3) {
            System.out.println("Menú Estudiante");
            System.out.println("1. Reservar");
            System.out.println("2. Consultar Reserva");
            System.out.println("3. Salir");
            System.out.println("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    gestionarReserva();
                    break;
                case 2:
                    consultarReserva();
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

    @Override
    public void reservar(){

    }
    
    public void reservar(ArrayList<Espacio> espacioDisponible, ArrayList<Reserva> reserva ) {
        Scanner sc = new Scanner(System.in);
        // El estudiante debe ingresar la fecha de la reserva
        System.out.println("Ingrese la fecha de la reserva(dddd-mm-dd): ");
        String fechaReserva = sc.nextLine();

        //Mostrar los espacios disponibles en esa fecha
        System.out.println("* Espacios disponibles *");
        
    }

    @Override
    public void gestionarReserva() {

    }

    @Override
    public void consultarReserva() {

    }

    @Override
    public void notificarReserva() {

    }

}
