package POO5_1P_CAMUENDO_CUENCA_QUIMI.src.Usuarios;

import POO5_1P_CAMUENDO_CUENCA_QUIMI.src.Tipos.TipoRol;

public abstract class Usuario {
    private String codigoUnico;
    private String cedula;
    private String nombre;
    private String apellido;
    private String usuario;
    private String contrasenia;
    private String correo;
    private TipoRol rol;

    Usuario(String codigoUnico, String cedula, String nombre, String apellido, String usuario
    , String contrasenia, String correo, TipoRol rol){
        this.codigoUnico = codigoUnico;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.correo = correo;
        this.rol = rol;
    
    

    }

    public abstract void reservar();

    //metodo para enviar correo
    public void notificarReserva(){};

    public abstract void mostrarMenu();


    public String toString() {
        return "Usuario [codigoUnico=" + codigoUnico + ", cedula=" + cedula + ", nombre="
           + nombre + ", apellido=" + apellido + ", usuario=" + usuario + ", contraseña=" + contrasenia
           + ", correo=" + correo + ", rol=" + rol;
        
    }

    public String getCodigoUnico() {
        return codigoUnico;
    }

    public String getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getCorreo() {
        return correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public TipoRol getRol() {
        return rol;
    }

    public void setCodigoUnico(String codigoUnico) {
        this.codigoUnico = codigoUnico;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setRol(TipoRol rol) {
        this.rol = rol;
    }

}
