package POO5_1P_CAMUENDO_CUENCA_QUIMI.src;

import java.util.Date;

public class Reserva {

    private int codigoUnico;
    private Date fecha;
    private Usuario usuario;
    private TipoEspacio tipoEspacio;
    private String motivo;
    private TipoEstado tipoEstado;
    public static int ReservasCreadas = 0;

    // constructor de la clase Reserva
    public Reserva(int codigoUnico, Date fecha, Usuario usuario, TipoEspacio tipoEspacio, String motivo,
            TipoEstado tipoEstado) {
        ReservasCreadas++;
        this.codigoUnico = codigoUnico;
        this.fecha = fecha;
        this.usuario = usuario;
        this.tipoEspacio = tipoEspacio;
        this.motivo = motivo;
        this.tipoEstado = tipoEstado;
    }

    // getters y setters
    public int getCodigoUnico() {
        return codigoUnico;
    }

    public void setCodigoUnico(int codigoUnico) {
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
        return "Reserva [codigoUnico=" + codigoUnico + ", fecha=" + fecha + ", usuario=" + usuario + ", tipoEspacio="
                + tipoEspacio + ", motivo=" + motivo + ", tipoEstado=" + tipoEstado + "]";
    }

}
