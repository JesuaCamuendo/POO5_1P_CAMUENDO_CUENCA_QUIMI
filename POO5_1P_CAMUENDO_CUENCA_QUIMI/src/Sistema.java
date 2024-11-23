package POO5_1P_CAMUENDO_CUENCA_QUIMI.src;

import java.util.ArrayList;

public class Sistema {

    public ArrayList<Reserva> reservas;
    public ArrayList<Usuario> usuarios;
    public ArrayList<Espacio> espacios;

    public static void main(String[] args) {
        ManejoArchivos m = new ManejoArchivos();

        m.LeerFichero("C:\\Users\\Usuario\\OneDrive\\Documentos\\GitHub\\-POO4_1P_CAMUENDO_CUENCA_QUIMI\\POO5_1P_CAMUENDO_CUENCA_QUIMI\\src\\Archivos\\usuarios.txt");
    }

    public void iniciarSeccion() {
    }

    public boolean verificar(){
        return false;
    }

    public void mostrarmenu(){
        
    }
}
