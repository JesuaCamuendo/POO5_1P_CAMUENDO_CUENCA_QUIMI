package pooProyecto.Recursos;

import java.util.Date;

import pooProyecto.Tipos.TipoEspacio;
import pooProyecto.Tipos.TipoEstado;

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

    /**
     * Constructor para crear una instancia de la clase Reserva. Inicializa los
     * atributos de la reserva. Además, incrementa el contador de reservas
     * creadas.
     *
     * @param codigoReserva El código único de la reserva.
     * @param codigoUnico El código único del usuario que realiza la reserva.
     * @param cedula La cédula del usuario que realiza la reserva.
     * @param fecha La fecha de la reserva.
     * @param codigoEspacio El código del espacio reservado.
     * @param tipoEspacio El tipo de espacio reservado (laboratorio, aula,
     * auditorio).
     * @param tipoEstado El estado de la reserva (por ejemplo, aprobado,
     * pendiente).
     * @param motivo El motivo de la reserva.
     */
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

    public static int getReservasCreadas() {
        return ReservasCreadas;
    }

    public static void setReservasCreadas(int reservasCreadas) {
        ReservasCreadas = reservasCreadas;
    }

}
