package pooProyecto.Recursos;

import pooProyecto.Tipos.*;

public class Espacio {

    private String codigoEspacio;
    private TipoEspacio tipo;
    private String nombre;
    private int capacidad;
    private String estado;
    private TipoRolPermitido rolPermitido;


    /**
 * Constructor de la clase Espacio que inicializa un espacio con la información proporcionada.
 *
 * @param codigoEspacio El código único que identifica al espacio 
 * @param tipo El tipo de espacio 
 * @param nombre El nombre del espacio 
 * @param capacidad La capacidad máxima del espacio 
 * @param estado El estado actual del espacio 
 * @param rolPermitido El rol permitido para acceder a este espacio
 */
    public Espacio(String codigoEspacio, TipoEspacio tipo, String nombre, int capacidad, String estado, TipoRolPermitido rolPermitido) {
        this.codigoEspacio = codigoEspacio;
        this.tipo = tipo;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.estado = estado;
    }


    public String getCodigoEspacio() {
        return codigoEspacio;
    }


    public TipoEspacio getTipo() {
        return tipo;
    }


    public String getNombre() {
        return nombre;
    }


    public int getCapacidad() {
        return capacidad;
    }


    public String getEstado() {
        return estado;
    }


    public void setCodigoEspacio(String codigoEspacio) {
        this.codigoEspacio = codigoEspacio;
    }


    public void setTipo(TipoEspacio tipo) {
        this.tipo = tipo;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }


    public void setEstado(String estado) {
        this.estado = estado;
    }

    public TipoRolPermitido getRolPermitido() {
        return rolPermitido;
    }


    public void setRolPermitido(TipoRolPermitido rolPermitido) {
        this.rolPermitido = rolPermitido;
    }

    
    public boolean mostrarDisponibilidad(){
        return this.estado.equalsIgnoreCase("DISPONIBLE");
        }

    @Override
    public String toString() {
        return "Espacio: \nCodigoEspacio : " + codigoEspacio + " Tipo : " + tipo + ", Nombre : " + nombre + ", Capacidad : "
                + capacidad + ", Estado." + estado;
    }

    
}