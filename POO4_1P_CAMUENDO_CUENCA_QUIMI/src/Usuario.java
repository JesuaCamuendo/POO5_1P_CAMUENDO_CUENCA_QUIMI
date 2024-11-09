package POO4_1P_CAMUENDO_CUENCA_QUIMI.src;
public abstract class Usuario {
    private String codigoUnico;
    private String cedula;
    private String nombre;
    private String apellido;
    private String usuario;
    private String contrasenia;
    private String correo;
    private char rol;

    
    Usuario(String codigoUnico, String cedula, String nombre, String apellido, String usuario
    , String contrasenia, String correo, char rol){
        this.codigoUnico = codigoUnico;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.correo = correo;
        this.rol = rol;
    }
    
    public void gestionarReserva(){}

    public void notificarReserva(){}

    public void notificarReserva(String materia){}

    public void notificarReserva(TipoEstado estado){}

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

    public char getRol() {
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

    public void setRol(char rol) {
        this.rol = rol;
    }
}
