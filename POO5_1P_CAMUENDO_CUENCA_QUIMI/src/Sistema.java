
package POO5_1P_CAMUENDO_CUENCA_QUIMI.src;

import java.util.ArrayList;
import java.util.Scanner;

public class Sistema {

    public static ArrayList<Reserva> reservas;
    public static ArrayList<Usuario> usuarios;
    public static ArrayList<Espacio> espacios;


    public static void main(String[] args) {
        mostrarmenu();
        ManejoArchivos m = new ManejoArchivos();
        ArrayList<String[]> datos = m.LeerFichero("espacios");
        for (String[] atributo : datos) {
            String codigoEspacio = atributo[0].trim();
            TipoEspacio tipo = TipoEspacio.valueOf(atributo[1].trim());
            String nombre = atributo[2].trim();
            int capacidad = Integer.parseInt(atributo[3].trim());
            String estado = atributo[4].trim();
            TipoRol rolPermitido= TipoRol.valueOf(atributo[5].trim());

            Espacio espacio= new Espacio(codigoEspacio, tipo, nombre, capacidad, estado, rolPermitido);
            espacios.add(espacio);
        }
       for (Espacio e:espacios){
        System.out.println(e);
       }

        
        ArrayList<String[]> usuario = m.LeerFichero("usuarios");
        ArrayList<String[]> estudiante = m.LeerFichero("estudiante");
        ArrayList<String[]> profesor = m.LeerFichero("profesor");
        ArrayList<String[]> administrador = m.LeerFichero("administrador");
        for(String[] u: usuario){
            int i = 0;
            String rol = u[7];
            TipoRol tipo = Enum.valueOf(TipoRol.class, rol);
            switch (tipo) {
                case E:
                Usuario es = new Estudiante(u[0], u[1], u[2], u[3], u[4], u[5], u[6], tipo, estudiante.get(i)[4], estudiante.get(i)[5]);
                i++;  
                usuarios.add(es); 
                    break;
                case A:
                Usuario ad = new Administrador(u[0], u[1], u[2], u[3], u[4], u[5], u[6], tipo, administrador.get(i)[4]);
                i++;  
                usuarios.add(ad); 
                    break;
                case P:
                Usuario pr = new Profesor(u[0], u[1], u[2], u[3], u[4], u[5], u[6], tipo, profesor.get(i)[4],profesor.get(i)[5]);
                i++;  
                usuarios.add(pr);
                    break;
            }
        }
        
    }

   // public void iniciarSeccion() {
   // }

    //public boolean verificar() {
       // return false;
  //  }

    public static void mostrarmenu() {
        Scanner s = new Scanner(System.in);
        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.println("║  Sistema De Reserva de Espacios en la Universidad  ║");
        System.out.println("╚════════════════════════════════════════════════════╝");
        System.out.print('\n'+"Ingrese su usuario: "); String usuario = s.nextLine();
        System.out.print("Ingrese su contraseña: "); String contrasenia = s.nextLine();
        System.out.println(usuario+' '+contrasenia); s.close();
    }

}