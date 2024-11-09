package POO5_1P_CAMUENDO_CUENCA_QUIMI.src;
public class Administrador extends Usuario{
    private String cargo;

    public Administrador(String codigoUnico, String cedula, String nombre, String apellido, String usuario,
            String contrasenia, String correo, char rol, String cargo) {
        super(codigoUnico, cedula, nombre, apellido, usuario, contrasenia, correo, rol);
        this.cargo = cargo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void gestionarReserva(){
    }

    @Override
    public String toString(){
        return super.toString() + ", cargo=" + cargo + "]";
    }
}
