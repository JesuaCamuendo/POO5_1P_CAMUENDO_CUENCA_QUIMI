package POO5_1P_CAMUENDO_CUENCA_QUIMI.src.Usuarios;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import POO5_1P_CAMUENDO_CUENCA_QUIMI.src.Espacio;
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
    public void ConsultarReserva() {

    }

    @Override
    public void reservar() {
             Scanner sc = new Scanner(System.in);
        // El estudiante/profesor debe ingresar la fecha de la reserva
        System.out.println('\n'+"-------- RESERVAR --------");
        System.out.print("Ingrese la fecha de la reserva (YYYY-MM-DD): ");
        String fechaReserva = sc.nextLine();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-mm-dd");
        try {
            Date fecha = formato.parse(fechaReserva);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.print('\n'+"Elija el tipo de espacio que desea reservar (LABORATORIO/AULA/AUDITORIO): ");
            String espacio = sc.nextLine().toUpperCase().trim();
            while(espacio != "LABORATORIO" || espacio != "AULA" || espacio != "AUDITORIO"){
                System.out.print('\n'+"OPCION NO VALIDA. ELIJA ENTRE: LABORATORIO/AULA/AUDITORIO: "); 
            }
            TipoEspacio tipo = TipoEspacio.valueOf(espacio.toUpperCase());
        sc.close();
    }
@Override
    public void mostrarMenu(){
        System.out.println('\n'+"............ Cargando menú ...............");
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        while (opcion != 3) {
            System.out.println('\n'+"............ Menú Profesor ...............");
            System.out.println("1. Reservar");
            System.out.println("2. Consultar Reserva");
            System.out.println("3. Salir"+'\n');
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
