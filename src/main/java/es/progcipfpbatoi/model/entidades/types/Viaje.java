package es.progcipfpbatoi.model.entidades.types;
import es.progcipfpbatoi.model.entidades.Reserva;
import es.progcipfpbatoi.model.entidades.Usuario;
import java.util.ArrayList;
import java.util.List;

/*
 * Clase que representa a un viaje estándar
*/
public class Viaje {

    private int id;
    private Usuario propietario;
    private String ruta;
    private int duracion;
    private int plazasLibres;
    private int plazasReservadas;
    private double precio;
    private boolean cerrado;
    private boolean cancelado;
    private ArrayList<Reserva> reservas;

    public Viaje(int id, Usuario propietario, String ruta, int duracion, int plazasLibres, double precio) {
        this.id = id;
        this.propietario = propietario;
        this.ruta = ruta;
        this.duracion = duracion;
        this.plazasLibres = plazasLibres;
        this.plazasReservadas = 0;
        this.precio = precio;
        this.cerrado = false;
        this.cancelado = false;
        this.reservas = new ArrayList<>();
    }

    public Viaje(Usuario propietario, String ruta, int duracion, int plazasLibres, double precio) {
        this.id = 0;
        this.propietario = propietario;
        this.ruta = ruta;
        this.duracion = duracion;
        this.plazasLibres = plazasLibres;
        this.plazasReservadas = 0;
        this.precio = precio;
        this.cerrado = false;
        this.cancelado = false;
        this.reservas = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getPropietario() {
        return propietario;
    }

    public String getRuta() {
        return ruta;
    }

    public int getPlazasLibres() {
        return plazasLibres;
    }

    public void setPlazasLibres(int plazasLibres) {
        this.plazasLibres = plazasLibres;
    }

    public int getPlazasReservadas() {
        return plazasReservadas;
    }

    public void setPlazasReservadas(int plazasReservadas) {
        this.plazasReservadas = plazasReservadas;
    }

    public void addReserva(Reserva reserva) {
        reservas.add(reserva);
    }

    public double getPrecio() {
        return precio;
    }

    public String isCanceladoString() {
        if (this.cancelado) {
            return "Sí";
        }
        else {
            return "No";
        }
    }

    public boolean isCerrado() {
        return cerrado;
    }

    public void setCerrado(boolean cerrado) {
        this.cerrado = cerrado;
    }

    public boolean isCancelado() {
        return cancelado;
    }

    public void setCancelado(boolean cancelado) {
        this.cancelado = cancelado;
    }

    public String getTipoViaje() {
        return "Estándar";
    }


}


