public class Administrador extends Usuario{
    private String cargo;

    Administrador(String cargo){
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void gestionarReserva(){
    }
}
