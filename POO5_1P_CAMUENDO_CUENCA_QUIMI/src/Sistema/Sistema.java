
package POO5_1P_CAMUENDO_CUENCA_QUIMI.src.Sistema;

import java.util.ArrayList;
import java.util.Scanner;

import POO5_1P_CAMUENDO_CUENCA_QUIMI.src.Espacio;
import POO5_1P_CAMUENDO_CUENCA_QUIMI.src.ManejoArchivos;
import POO5_1P_CAMUENDO_CUENCA_QUIMI.src.Reserva;
import POO5_1P_CAMUENDO_CUENCA_QUIMI.src.Tipos.*;
import POO5_1P_CAMUENDO_CUENCA_QUIMI.src.Usuarios.*;

public class Sistema {

    public static ArrayList<Reserva> reservas;
    public static ArrayList<Usuario> usuarios;
    public static ArrayList<Espacio> espacios;

    public static void main(String[] args) {

        ManejoArchivos m = new ManejoArchivos();
        espacios = new ArrayList<>();
        usuarios = new ArrayList<>();
        reservas= new ArrayList<>();

        ArrayList<String[]> datos = m.LeerFichero("espacios");
        for (String[] atributo : datos) {
            String codigoEspacio = atributo[0].trim();
            TipoEspacio tipo = TipoEspacio.valueOf(atributo[1].trim());
            String nombre = atributo[2].trim();
            int capacidad = Integer.parseInt(atributo[3].trim());
            String estado = atributo[4].trim();
            TipoRolPermitido rolPermitido = TipoRolPermitido.valueOf(atributo[5].trim());

            Espacio espacio = new Espacio(codigoEspacio, tipo, nombre, capacidad, estado, rolPermitido);
            espacios.add(espacio);
        }


        ArrayList<String[]> usuario = m.LeerFichero("usuarios");
        ArrayList<String[]> estudiante = m.LeerFichero("estudiantes");
        ArrayList<String[]> profesor = m.LeerFichero("profesores");
        ArrayList<String[]> administrador = m.LeerFichero("administradores");
        for (String[] u : usuario) {
            int i = 0;
            String rol = u[7].trim();
            TipoRol tipo = Enum.valueOf(TipoRol.class, rol);
            switch (tipo) {
                case E:
                    Usuario es = new Estudiante(u[0].trim(), u[1].trim(), u[2].trim(), u[3].trim(), u[4].trim(),
                    u[5].trim(), u[6].trim(), tipo, estudiante.get(i)[4].trim(),
                    estudiante.get(i)[5].trim());
                    i++;
                    usuarios.add(es);
                break;
                case A:
                    Usuario ad = new Administrador(u[0].trim(), u[1].trim(), u[2].trim(), u[3].trim(), u[4].trim(),
                    u[5].trim(), u[6].trim(), tipo,
                    administrador.get(i)[4].trim());
                    i++;
                    usuarios.add(ad);
                break;
                case P:
                    Usuario pr = new Profesor(u[0].trim(), u[1].trim(), u[2].trim(), u[3].trim(), u[4].trim(),
                    u[5].trim(), u[6].trim(), tipo, profesor.get(i)[4].trim(),
                    profesor.get(i)[5].trim());
                    i++;
                    usuarios.add(pr);
                break;
        }
    }

        iniciarSeccion();
    }


        /*
         * reservas= new ArrayList<>();
         * ArrayList<String[]> datosReservas = m.LeerFichero("reservas");
         * for(String[] atributo : datosReservas){
         * int codigoUnico = Integer.parseInt(atributo[0]);
         * Date fecha = new Date(Long.parseLong(atributo[1]));
         * Usuario usuario = atributo[2];
         * TipoEspacio tipoEspacio = TipoEspacio.valueOf(atributo[3]);
         * String motivo = atributo[4];
         * TipoEstado tipoEstado = TipoEstado.valueOf(atributo[5]);
         * }
         */


    
    public static void iniciarSeccion() {
        Scanner s = new Scanner(System.in);
        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.println("║  Sistema De Reserva de Espacios en la Universidad  ║");
        System.out.println("╚════════════════════════════════════════════════════╝");
        System.out.print('\n' + "Ingrese su usuario: ");
        String usuario = s.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String contrasenia = s.nextLine();

        verificar(usuario, contrasenia);
        s.close();
    }

    // Verificar que al usuario
    public static boolean verificar(String usuario, String contrasenia) {

        boolean veracidad=false;

        for (Usuario e : usuarios) {

            // Si es instancia de Estudiante
            if (e instanceof Estudiante && e.getUsuario().equals(usuario) && e.getContrasenia().equals(contrasenia)) {
                    Estudiante estudiante = (Estudiante) e;
                    estudiante.mostrarMenu();
                    veracidad = true;
                
            }

            // Si es instancia de Profesor
            else if (e instanceof Profesor && e.getUsuario().equals(usuario) && e.getContrasenia().equals(contrasenia)) {
                    Profesor profesor = (Profesor) e;
                    profesor.mostrarMenu();
                    veracidad = true;
                
            }

            // si es instancia de Administrador 
            else if (e instanceof Administrador && e.getUsuario().equals(usuario) && e.getContrasenia().equals(contrasenia))  {
                    Administrador administrador = (Administrador) e;
                    administrador.mostrarMenu();
                    veracidad = true;
                

            }
            
        }
        if (veracidad==false){
            System.out.println("Credenciales incorrectas");
        }
        
        return veracidad;
    }
}
