package es.progcipfpbatoi.model.managers;

import es.progcipfpbatoi.model.entidades.Reserva;
import es.progcipfpbatoi.model.entidades.Usuario;

import java.util.ArrayList;
import java.util.List;

public class ReservasManager {

    private List<Reserva> reservas;

    public ReservasManager() {
        this.reservas = new ArrayList<>();
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void add(Reserva reserva) {
        reservas.add(reserva);
    }


}
