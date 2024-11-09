package POO4_1P_CAMUENDO_CUENCA_QUIMI.src;

import java.util.Date;

public class Reserva {

    private String codigoUnico;
    private Date fecha;
    private Usuario usuario;
    private Espacio espacio;
    private String motivo;
    private TipoEstado estado;
    public static int ReservasCreadas;

    // constructor de la clase Reserva
    public Reserva(String codigoUnico, Date fecha, Usuario usuario, Espacio espacio, String motivo, TipoEstado estado) {
        this.codigoUnico = codigoUnico;
        this.fecha = fecha;
        this.usuario = usuario;
        this.espacio = espacio;
        this.motivo = motivo;
        this.estado = estado;
    }

    // getters y setters
    public String getCodigoUnico() {
        return codigoUnico;
    }

    public void setCodigoUnico(String codigoUnico) {
        this.codigoUnico = codigoUnico;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Espacio getEspacio() {
        return espacio;
    }

    public void setEspacio(Espacio espacio) {
        this.espacio = espacio;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public TipoEstado getEstado() {
        return estado;
    }

    public void setEstado(TipoEstado estado) {
        this.estado = estado;
    }

    // metodo Reservar
    public void Reservar(Date fecha, TipoEstado estado) {
    }

    // metodo toString
    @Override
    public String toString() {
        return "Reserva [codigoUnico=" + codigoUnico + ", fecha=" + fecha + ", usuario=" + usuario + ", espacio="
                + espacio + ", motivo=" + motivo + ", estado=" + estado + "]";
    }

}
