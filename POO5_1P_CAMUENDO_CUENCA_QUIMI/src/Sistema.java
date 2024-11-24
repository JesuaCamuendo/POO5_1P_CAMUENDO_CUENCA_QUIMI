
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
            TipoRolPermitido rolPermitido= TipoRolPermitido.valueOf(atributo[5].trim());

            Espacio espacio= new Espacio(codigoEspacio, tipo, nombre, capacidad, estado, rolPermitido);
            espacios.add(espacio);
        }
       for (Espacio e:espacios){
        System.out.println(e);
       }

        ArrayList<String[]> espacio = m.LeerFichero("usuarios");
        // prueba de que sirve
       // for (String[] esp : espacio) {
           // System.out.println(esp[7]);
        //}
    }

   // public void iniciarSeccion() {
   // }

    //public boolean verificar() {
       // return false;
  //  }

    //public void mostrarmenu() {

   // }

}