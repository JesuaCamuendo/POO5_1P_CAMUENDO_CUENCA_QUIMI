
package POO5_1P_CAMUENDO_CUENCA_QUIMI.src;

import java.util.ArrayList;

public class Sistema {

    public ArrayList<Reserva> reservas;
    public ArrayList<Usuario> usuarios;
    public ArrayList<Espacio> espacios;


    public static void main(String[] args) {
        ManejoArchivos m = new ManejoArchivos();
        ArrayList<Espacio> espacios = new ArrayList<>();
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

<<<<<<< HEAD
        ArrayList<String[]> espacio = m.LeerFichero("usuarios");
        // prueba de que sirve
       // for (String[] esp : espacio) {
           // System.out.println(esp[7]);
        //}
=======
        ArrayList<String[]> usuario = m.LeerFichero("usuarios");
        ArrayList<String[]> estudiante = m.LeerFichero("estudiante");
        for(String[] u: usuario){
            String rol = u[7];
            TipoRol tipo = Enum.valueOf(TipoRol.class, rol);
            switch (tipo) {
                case E:
                for (String[] e: estudiante){
                Usuario us = new Estudiante(u[0], u[1], u[2], u[3], u[4], u[5], u[6], tipo, "da", "da");}
                    break;
            
                case A:

                    break;
                case P:

                    break;
            }
        }
        
>>>>>>> 20fd3fd0fdcb83e4f6e7d76e30e3db3b3c9316fb
    }

   // public void iniciarSeccion() {
   // }

    //public boolean verificar() {
       // return false;
  //  }

    //public void mostrarmenu() {

   // }

}