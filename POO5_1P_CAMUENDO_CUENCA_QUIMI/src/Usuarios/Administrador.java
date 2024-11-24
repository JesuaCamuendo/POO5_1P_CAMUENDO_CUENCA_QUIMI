package POO5_1P_CAMUENDO_CUENCA_QUIMI.src.Usuarios;

import POO5_1P_CAMUENDO_CUENCA_QUIMI.src.Tipos.TipoRol;

public class Administrador extends Usuario{
    private String cargo;

    public Administrador(String codigoUnico, String cedula, String nombre, String apellido, String usuario,
            String contrasenia, String correo, TipoRol rol, String cargo) {
        super(codigoUnico, cedula, nombre, apellido, usuario, contrasenia, correo, rol);
        this.cargo = cargo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }


    @Override
    public String toString(){
        return super.toString() + ", cargo=" + cargo + "]";
    }

    @Override
    public void reservar() {
        // TODO Auto-generated method stub
    }

    @Override
    public void notificarReserva() {
        // TODO Auto-generated method stub
    }
}
