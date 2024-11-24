
package POO5_1P_CAMUENDO_CUENCA_QUIMI.src;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;

public class Sistema {

    public static ArrayList<Reserva> reservas= new ArrayList<>();
    public ArrayList<Usuario> usuarios;
    public ArrayList<Espacio> espacios;

    public static void main(String[] args) {
        ManejoArchivos m = new ManejoArchivos();

        ArrayList<String[]> espacio = m.LeerFichero("usuarios");
        //prueba de que sirve
        for (String[] esp:espacio){
            System.out.println(esp[7]);
        }
    }

    public void iniciarSeccion() {
    }

    public boolean verificar(){
        return false;
    }

    public void mostrarmenu(){
        
    }

}