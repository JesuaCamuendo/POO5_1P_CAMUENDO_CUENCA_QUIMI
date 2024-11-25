package POO5_1P_CAMUENDO_CUENCA_QUIMI.src.Usuarios;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import POO5_1P_CAMUENDO_CUENCA_QUIMI.src.Sistema.Sistema;
import POO5_1P_CAMUENDO_CUENCA_QUIMI.src.Espacio;
import POO5_1P_CAMUENDO_CUENCA_QUIMI.src.ManejoArchivos;
import POO5_1P_CAMUENDO_CUENCA_QUIMI.src.Reserva;
import POO5_1P_CAMUENDO_CUENCA_QUIMI.src.Tipos.TipoEspacio;
import POO5_1P_CAMUENDO_CUENCA_QUIMI.src.Tipos.TipoRol;

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
        Scanner sc = new Scanner(System.in);
        boolean veracidad = false;
        // El estudiante debe ingresar la fecha de la reserva
        System.out.println('\n'+"-------- RESERVAR --------");
        System.out.print("Ingrese la fecha de la reserva [YYYY-MM-DD]: ");
        String fechaReserva = sc.nextLine();
        if (fechaReserva.length()==10){
            String numero = fechaReserva.substring(0,4);
            if(fechaReserva.charAt(4)=='-' && fechaReserva.charAt(7)=='-'){
                if(numero.matches("[0-9]*")){
                    numero = fechaReserva.substring(5,7);
                    if(numero.matches("[0-9]*")){
                        numero = fechaReserva.substring(8);
                        if(numero.matches("[0-9]*")){
                            veracidad = true;
                        }
                    }
                }
            }
        }
        while (veracidad == false) {
            System.out.println("Error al ingresar la fecha, por favor use el formato [YYYY-MM-DD]");
            fechaReserva = sc.nextLine();
            if (fechaReserva.length()==10){
                String numero = fechaReserva.substring(0,4);
                if(fechaReserva.charAt(4)=='-' && fechaReserva.charAt(7)=='-'){
                    if(numero.matches("[0-9]*")){
                        numero = fechaReserva.substring(5,7);
                        if(numero.matches("[0-9]*")){
                            numero = fechaReserva.substring(8);
                            if(numero.matches("[0-9]*")){
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
        };
        System.out.print('\n'+"Elija el tipo de espacio que desea reservar [CANCHA/AULA]: ");
        String espacio = sc.nextLine().toUpperCase();
        int c1 = espacio.compareTo("CANCHA");
        int c2 = espacio.compareTo("AULA");
        if (c1 == 0 || c2 == 0) {
            veracidad = true;
        }
        while(veracidad==false){
            System.out.print('\n'+"OPCIÓN NO VALIDA. ELIJA ENTRE CANCHA/AULA:  ");
            espacio = sc.nextLine().toUpperCase(); 
            c1 = espacio.compareTo("CANCHA");
            c2 = espacio.compareTo("AULA");
            if (c1 == 0 || c2 == 0) {
                veracidad = true;
            }
        }
        veracidad = false;
        TipoEspacio tipo = TipoEspacio.valueOf(espacio);
        switch (tipo) {
            case CANCHA:
            System.out.println('\n'+"----- Espacios Disponibles -----");
            System.out.println("Código de Espacio  |     Nombre");
            for (Espacio space: Sistema.espacios){
                if(space.getTipo() == tipo && space.mostrarDisponibilidad()){
                    System.out.println("        "+space.getCodigoEspacio()+"        |  "+space.getNombre());
                }
            }
            System.out.print('\n'+"Elija la Cancha a reservar, colocando su Código (1XX): ");
            String codigoEspacio = sc.nextLine();
            System.out.print('\n'+"Mencione el motivo de la reserva: ");
            String motivo = sc.nextLine();
            System.out.print('\n'+"Desea crear su reserva en la CANCHA con código: "+codigoEspacio+" para el: "+fechaReserva+" [SI/NO]: ");
            String confirmacion = sc.nextLine().toUpperCase();
            c1 = confirmacion.compareTo("SI");
            c2 = confirmacion.compareTo("NO");
            if (c1 == 0 || c2 == 0) {
                veracidad = true;
            }
            while(veracidad==false){
                System.out.print('\n'+"OPCIÓN NO VALIDA. ELIJA ENTRE SI / NO:  ");
                confirmacion = sc.nextLine().toUpperCase(); 
                c1 = confirmacion.compareTo("SI");
                c2 = confirmacion.compareTo("NO");
                if (c1 == 0 || c2 == 0) {
                veracidad = true;
                }
            }
            if(confirmacion == "SI"){
                ManejoArchivos m = new ManejoArchivos();
                String usuario = Sistema.getUsuario();
                String cedula = "null";
                String codigoUnico = "null";
                for(Usuario user: Sistema.usuarios){
                    if(user.getUsuario().compareTo(usuario)==0){
                        cedula = user.getCedula();
                        codigoUnico = user.getCodigoUnico();
                    }
                }
                String codigoReserva= String.valueOf(5001+Reserva.ReservasCreadas);
                String linea = codigoReserva+" | "+codigoUnico+" | "+cedula+" | "+fechaReserva+" | "+codigoEspacio+" | "+espacio+" | "+
                "PENDIENTE";
                m.EcribirArchivo("reservas", linea);
            }
                break;
            case AULA:

                break;
            }
        
        sc.close();
    }

    @Override
    public void ConsultarReserva() {

    }

    @Override
    public void mostrarMenu() {
        Scanner sc = new Scanner(System.in);
        int opcion=0;
        while (opcion != 3) {
            System.out.println('\n' + "............ Cargando menú ...............");
            System.out.println('\n' + "............. Menú Estudiante ...............");
            System.out.println("1. Reservar");
            System.out.println("2. Consultar Reserva");
            System.out.println("3. Salir" + '\n');
            System.out.print("Seleccione una opción: ");
            opcion= sc.nextInt();
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
        sc.close();
    }

}

