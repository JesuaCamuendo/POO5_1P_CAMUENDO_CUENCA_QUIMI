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

    public void EcribirArchivo(String nombreArchivo, String linea) {
        nombreArchivo = "POO5_1P_CAMUENDO_CUENCA_QUIMI\\src\\main\\java\\pooProyecto\\Archivos\\"+nombreArchivo+".txt";
        try {
            FileWriter escritura = new FileWriter(nombreArchivo,true);
            escritura.write(linea+'\n');
            escritura.close();
            System.out.println("Escritura exitosa c:"+'\n');
        } catch (IOException exception) {
            exception.printStackTrace(System.out);
        }
    }

    public void SobreescribirDesicion(String nombreArchivo, String decision, String codigo) {
        nombreArchivo = "POO5_1P_CAMUENDO_CUENCA_QUIMI\\src\\main\\java\\pooProyecto\\Archivos\\"+nombreArchivo+".txt";
        try {
            FileWriter escritura = new FileWriter(nombreArchivo);
            for (Reserva r: Sistema.reservas){
                String estado = String.valueOf(r.getTipoEstado());
                String codigoReserva = String.valueOf(r.getCodigoReserva());
                if(codigoReserva.equals(codigo)){
                    estado = decision;
                }
                String linea = r.getCodigoReserva() + " | " + r.getCodigoUnico() + " | " + r.getCedula() + " | " + String.valueOf(r.getFecha()) + " | "
                            + r.getCodigoEspacio() + " | " + String.valueOf(r.getTipoEspacio()) + " | " + estado + " | " + r.getMotivo();
                escritura.write(linea);
            }
            escritura.close();
            System.out.println("Escritura exitosa c:"+'\n');
        } catch (IOException exception) {
            exception.printStackTrace(System.out);
        }
    }
}
