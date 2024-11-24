
package POO5_1P_CAMUENDO_CUENCA_QUIMI.src;

import java.util.ArrayList;

public class Sistema {

    public static ArrayList<Reserva> reservas;
    public static ArrayList<Usuario> usuarios;
    public static ArrayList<Espacio> espacios;


    public static void main(String[] args) {
        ManejoArchivos m = new ManejoArchivos();

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

    public void iniciarSeccion() {
    }

    public boolean verificar(){
        return false;
    }

    public void mostrarmenu(){
        
    }

}