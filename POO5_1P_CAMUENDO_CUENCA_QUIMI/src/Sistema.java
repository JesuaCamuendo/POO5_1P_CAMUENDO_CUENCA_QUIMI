
package POO5_1P_CAMUENDO_CUENCA_QUIMI.src;

import java.util.ArrayList;

public class Sistema {

    public ArrayList<Reserva> reservas;
    public ArrayList<Usuario> usuarios;
    public ArrayList<Espacio> espacios;


    public static void main(String[] args) {
        ManejoArchivos m = new ManejoArchivos();

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
        
    }

    public void iniciarSeccion() {
    }

    public boolean verificar(){
        return false;
    }

    public void mostrarmenu(){
        
    }

}