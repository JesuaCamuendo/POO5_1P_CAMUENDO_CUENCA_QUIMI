package pooProyecto.Recursos;

import java.util.Date;

import pooProyecto.Tipos.*;

public class Reserva {

    private int codigoReserva;
    private String codigoUnico;
    private String cedula;
    private Date fecha;
    private String codigoEspacio;
    private String motivo;
    private TipoEspacio tipoEspacio;
    private TipoEstado tipoEstado;
    public static int ReservasCreadas = 0;

    // constructor de la clase Reserva
    public Reserva(int codigoReserva, String codigoUnico, String cedula, Date fecha, String codigoEspacio,
            TipoEspacio tipoEspacio, TipoEstado tipoEstado, String motivo) {
        ReservasCreadas++;
        this.codigoReserva = codigoReserva;
        this.codigoUnico = codigoUnico;
        this.cedula = cedula;
        this.fecha = fecha;
        this.codigoEspacio = codigoEspacio;
        this.motivo = motivo;
        this.tipoEspacio = tipoEspacio;
        this.tipoEstado = tipoEstado;
    }

    // getters y setters
    public int getCodigoReserva() {
        return codigoReserva;
    }

    public String getCodigoUnico() {
        return codigoUnico;
    }

    public void setCodigoUnico(String codigoUnico) {
        this.codigoUnico = codigoUnico;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCodigoReserva(int codigoReserva) {
        this.codigoReserva = codigoReserva;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getCodigoEspacio() {
        return codigoEspacio;
    }

    public void setCodigoEspacio(String codigoEspacio) {
        this.codigoEspacio = codigoEspacio;
    }

    public TipoEspacio getTipoEspacio() {
        return tipoEspacio;
    }

    public void setTipoEspacio(TipoEspacio tipoEspacio) {
        this.tipoEspacio = tipoEspacio;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public TipoEstado getTipoEstado() {
        return tipoEstado;
    }

    public void setTipoEstado(TipoEstado tipoEstado) {
        this.tipoEstado = tipoEstado;
    }

    

    // metodo toString
    @Override
    public String toString() {
        return "Reserva [codigoUnico=" + codigoReserva + ", fecha=" + fecha + ", usuario=" + codigoUnico
                + ", tipoEspacio="
                + tipoEspacio + ", motivo=" + motivo + ", tipoEstado=" + tipoEstado + "]";
    }

    public static int getReservasCreadas() {
        return ReservasCreadas;
    }

    public static void setReservasCreadas(int reservasCreadas) {
        ReservasCreadas = reservasCreadas;
    }

}
