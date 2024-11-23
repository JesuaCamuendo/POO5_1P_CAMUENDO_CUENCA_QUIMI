package POO5_1P_CAMUENDO_CUENCA_QUIMI.src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;

public class ManejoArchivos {
    public void LeerFichero(String nombreArchivo) {
        //ArrayList<String> l = new ArrayList<>();
        File archivo = new File(nombreArchivo);
        try{
        BufferedReader entrada = new BufferedReader(new FileReader(archivo));
        String cadena = entrada.readLine();
            while (cadena !=null){
                System.out.println(cadena);
                cadena = entrada.readLine();
                //return l;
            }
         entrada.close();
        
        }catch (FileNotFoundException ex){
            ex.printStackTrace(System.out);
        }catch (IOException ex){
            ex.printStackTrace(System.out);
        }
    }

    public void EcribirArchivo(String nombreArchivo, String linea) {
    }
}
