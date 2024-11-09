public class Estudiante extends Usuario {

    private String matricula;
    private String carrera;

    
    public Estudiante(String matricula, String carrera) {
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
