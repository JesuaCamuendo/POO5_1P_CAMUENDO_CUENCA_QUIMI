package pooProyecto.Recursos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.naming.spi.ResolveResult;

import pooProyecto.Sistema.Sistema;

/**
 * Lee el contenido de un archivo de texto y lo devuelve como un ArrayList de arreglos de cadenas.
 * Cada línea del archivo se divide en un arreglo de cadenas usando el delimitador "|" 
 * y se agrega a la lista.
 *
 * @param nombreArchivo El nombre del archivo que se desea leer (sin la extensión ".txt").
 *                      Este archivo se buscará en la ruta definida dentro del método.
 * @return Un ArrayList de arreglos de cadenas, donde cada arreglo contiene los datos
 *         de una línea del archivo, separada por el delimitador "|".
 * @throws FileNotFoundException Si el archivo especificado no se encuentra en la ruta proporcionada.
 * @throws IOException Si ocurre un error de entrada/salida al leer el archivo.
 */
public class ManejoArchivos {
    public  ArrayList<String[]> LeerFichero(String nombreArchivo) {
        nombreArchivo = "POO5_1P_CAMUENDO_CUENCA_QUIMI\\src\\main\\java\\pooProyecto\\Archivos\\"+nombreArchivo+".txt";
        ArrayList<String[]> l = new ArrayList<>();
        File archivo = new File(nombreArchivo);
        try{
        BufferedReader entrada = new BufferedReader((new InputStreamReader(new FileInputStream(archivo), "UTF-8")));
        String cadena = entrada.readLine();
            while (cadena !=null){
                l.add(cadena.split("\\|"));
                cadena = entrada.readLine();
            }
            entrada.close();
        }catch (FileNotFoundException ex){
            ex.printStackTrace(System.out);
        }catch (IOException ex){
            ex.printStackTrace(System.out);
        }
        return l;
    }

    /**
 * Escribe una línea en un archivo de texto, añadiéndola al final del archivo.
 * Si el archivo no existe, se crea automáticamente.
 *
 * @param nombreArchivo El nombre del archivo en el cual se escribirá la línea (sin la extensión ".txt").
 *                      Este archivo se buscará en la ruta definida dentro del método.
 * @param linea La línea de texto que se desea escribir en el archivo.
 * @throws IOException Si ocurre un error de entrada/salida al escribir en el archivo.
 */
    public void EcribirArchivo(String nombreArchivo, String linea) {
        nombreArchivo = "POO5_1P_CAMUENDO_CUENCA_QUIMI\\src\\main\\java\\pooProyecto\\Archivos\\"+nombreArchivo+".txt";
        try {
            FileWriter escritura = new FileWriter(nombreArchivo,true);
            escritura.write('\n'+linea);
            escritura.close();
            System.out.println("Escritura exitosa c:"+'\n');
        } catch (IOException exception) {
            exception.printStackTrace(System.out);
        }
    }
}
