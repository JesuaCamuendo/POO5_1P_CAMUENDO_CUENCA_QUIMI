package POO5_1P_CAMUENDO_CUENCA_QUIMI.src.Usuarios;

import java.util.Scanner;

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
    public void notificarReserva() {
        // TODO Auto-generated method stub

    }

    @Override
    public void reservar() {

    }
@Override
    public void mostrarMenu(){
        System.out.println("............ Cargando menú ...............");
        System.out.println("............ Menú Profesor ...............");
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        while (opcion != 3) {
            System.out.println("1. Reservar");
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
