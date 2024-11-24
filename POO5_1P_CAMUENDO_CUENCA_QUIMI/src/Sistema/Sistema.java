
package POO5_1P_CAMUENDO_CUENCA_QUIMI.src.Sistema;

import java.util.ArrayList;

import POO5_1P_CAMUENDO_CUENCA_QUIMI.src.Espacio;
import POO5_1P_CAMUENDO_CUENCA_QUIMI.src.ManejoArchivos;
import POO5_1P_CAMUENDO_CUENCA_QUIMI.src.Reserva;
import POO5_1P_CAMUENDO_CUENCA_QUIMI.src.Tipos.TipoEspacio;
import POO5_1P_CAMUENDO_CUENCA_QUIMI.src.Tipos.TipoRol;
import POO5_1P_CAMUENDO_CUENCA_QUIMI.src.Tipos.TipoRolPermitido;
import POO5_1P_CAMUENDO_CUENCA_QUIMI.src.Usuarios.Administrador;
import POO5_1P_CAMUENDO_CUENCA_QUIMI.src.Usuarios.Estudiante;
import POO5_1P_CAMUENDO_CUENCA_QUIMI.src.Usuarios.Profesor;
import POO5_1P_CAMUENDO_CUENCA_QUIMI.src.Usuarios.Usuario;

public class Sistema {

    public static ArrayList<Reserva> reservas;
    public static ArrayList<Usuario> usuarios;
    public static ArrayList<Espacio> espacios;


    public static void main(String[] args) {
        ManejoArchivos m = new ManejoArchivos();
        ArrayList<String[]> datos = m.LeerFichero("espacios");


        for (String[] atributo : datos) {
            String codigoEspacio = atributo[0].trim();
            TipoEspacio tipo = TipoEspacio.valueOf(atributo[1].trim());
            String nombre = atributo[2].trim();
            int capacidad = Integer.parseInt(atributo[3].trim());
            String estado = atributo[4].trim();
            TipoRolPermitido rolPermitido= TipoRolPermitido.valueOf(atributo[5].trim());

            Espacio espacio= new Espacio(codigoEspacio, tipo, nombre, capacidad, estado, rolPermitido);
            espacios.add(espacio);
        }
       for (Espacio e:espacios){
        System.out.println(e);
       }

<<<<<<< HEAD
        ArrayList<String[]> espacio = m.LeerFichero("usuarios");
        // prueba de que sirve
       // for (String[] esp : espacio) {
           // System.out.println(esp[7]);
        //}
=======
        
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
        
>>>>>>> a5af250f2e9b22dee7a9c2d2f185b82d5de161a9
    }

   // public void iniciarSeccion() {
   // }

    //public boolean verificar() {
       // return false;
  //  }

    //public void mostrarmenu() {

   // }

}