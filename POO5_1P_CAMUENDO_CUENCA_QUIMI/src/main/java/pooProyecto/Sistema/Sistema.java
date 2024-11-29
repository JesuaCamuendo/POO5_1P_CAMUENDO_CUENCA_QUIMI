package pooProyecto.Sistema;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import pooProyecto.Recursos.*;
import pooProyecto.Tipos.*;
import pooProyecto.Usuarios.*;

public class Sistema {

    public static ArrayList<Reserva> reservas;
    public static ArrayList<Usuario> usuarios;
    public static ArrayList<Espacio> espacios;
    private static String usuario;

    public static void main(String[] args) throws ParseException {

        ManejoArchivos m = new ManejoArchivos();
        espacios = new ArrayList<>();
        usuarios = new ArrayList<>();
        reservas = new ArrayList<>();

        ArrayList<String[]> datos = m.LeerFichero("espacios");
        for (String[] atributo : datos) {
            String codigoEspacio = atributo[0].trim();
            TipoEspacio tipo = TipoEspacio.valueOf(atributo[1].trim());
            String nombre = atributo[2].trim();
            int capacidad = Integer.parseInt(atributo[3].trim());
            String estado = atributo[4].trim();
            TipoRolPermitido rolPermitido = TipoRolPermitido.valueOf(atributo[5].trim());

            Espacio espacio = new Espacio(codigoEspacio, tipo, nombre, capacidad, estado, rolPermitido);
            espacios.add(espacio);
        }

        ArrayList<String[]> usuario = m.LeerFichero("usuarios");
        ArrayList<String[]> estudiante = m.LeerFichero("estudiantes");
        ArrayList<String[]> profesor = m.LeerFichero("profesores");
        ArrayList<String[]> administrador = m.LeerFichero("administradores");
        for (String[] u : usuario) {
            String rol = u[7].trim();
            TipoRol tipo = Enum.valueOf(TipoRol.class, rol);
            switch (tipo) {
                case E:
                    for (String[] est : estudiante) {
                        if (u[0].trim().equals(est[0].trim())) {
                            Usuario es = new Estudiante(u[0].trim(), u[1].trim(), u[2].trim(), u[3].trim(), u[4].trim(),
                                    u[5].trim(), u[6].trim(), tipo, est[4].trim(), est[5].trim());
                            usuarios.add(es);
                        }
                    }
                    break;
                case A:
                    for (String[] adm : administrador) {
                        if (u[0].trim().equals(adm[0].trim())) {
                            Usuario ad = new Administrador(u[0].trim(), u[1].trim(), u[2].trim(), u[3].trim(),
                                    u[4].trim(),
                                    u[5].trim(), u[6].trim(), tipo, adm[4]);
                            usuarios.add(ad);
                        }
                    }
                    break;
                case P:
                    for (String[] pro : profesor) {
                        if (u[0].trim().equals(pro[0].trim())) {
                            Usuario pr = new Profesor(u[0].trim(), u[1].trim(), u[2].trim(), u[3].trim(), u[4].trim(),
                                    u[5].trim(), u[6].trim(), tipo, pro[4], pro[5]);
                            usuarios.add(pr);
                        }
                    }
                    break;
            }
        }

        ArrayList<String[]> datosReservas = m.LeerFichero("reservas");
        for (String[] atributo : datosReservas) {
            int codigoReserva = Integer.parseInt(atributo[0].trim());
            String codigoUnico = atributo[1].trim();
            String cedula = atributo[2].trim();
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha = formato.parse(atributo[3].trim());
            String codigoEspacio = atributo[4].trim();
            TipoEspacio tipoEspacio = TipoEspacio.valueOf(atributo[5].trim());
            TipoEstado tipoEstado = TipoEstado.valueOf(atributo[6].trim());
            String motivo = atributo[7].trim();

            Reserva reserva = new Reserva(codigoReserva, codigoUnico, cedula, fecha, codigoEspacio, tipoEspacio,
                    tipoEstado, motivo);
            reservas.add(reserva);
        }

        iniciarSeccion();
    }

    public static void iniciarSeccion() {
        Scanner s = new Scanner(System.in);
        String contrasenia = "null";
        boolean veracidad = false;
        while (veracidad == false) {
            System.out.println("╔════════════════════════════════════════════════════╗");
            System.out.println("║  Sistema De Reserva de Espacios en la Universidad  ║");
            System.out.println("╚════════════════════════════════════════════════════╝");
            System.out.print('\n' + "Ingrese su usuario: ");
            usuario = s.nextLine();
            System.out.print("Ingrese su contraseña: ");
            contrasenia = s.nextLine();
            veracidad = verificar(usuario, contrasenia);
        }

        // s.close();
    }

    // Verificar que al usuario
    public static boolean verificar(String usuario, String contrasenia) {

        boolean veracidad = false;

        for (Usuario e : usuarios) {

            // Si es instancia de Estudiante
            if (e instanceof Estudiante && e.getUsuario().equals(usuario) && e.getContrasenia().equals(contrasenia)) {
                Estudiante estudiante = (Estudiante) e;
                estudiante.mostrarMenu();
                veracidad = true;

            }

            // Si es instancia de Profesor
            else if (e instanceof Profesor && e.getUsuario().equals(usuario)
                    && e.getContrasenia().equals(contrasenia)) {
                Profesor profesor = (Profesor) e;
                profesor.mostrarMenu();
                veracidad = true;

            }

            // si es instancia de Administrador
            else if (e instanceof Administrador && e.getUsuario().equals(usuario)
                    && e.getContrasenia().equals(contrasenia)) {
                Administrador administrador = (Administrador) e;
                administrador.mostrarMenu();
                veracidad = true;

            }

        }
        if (veracidad == false) {
            System.out.println('\n' + "Credenciales incorrectas VUELVA A INTENTARLO por favor" + '\n');
        }

        return veracidad;
    }

    public static String getUsuario() {
        return usuario;
    }
}