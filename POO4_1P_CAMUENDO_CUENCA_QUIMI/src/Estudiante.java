
public class Estudiante extends Usuario {

    private String matricula;
    private String carrera;

    
    public Estudiante(String codigoUnico, String cedula, String nombre, String apellido, String usuario, String contrasenia, String correo, char rol, String matricula, String carrera) {
        super(codigoUnico, cedula, nombre, apellido, usuario, contrasenia, correo, rol);
        this.matricula = matricula;
        this.carrera = carrera;
    }


    public String getMatricula() {
        return matricula;
    }


    public String getCarrera() {
        return carrera;
    }


    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }


    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    
}
