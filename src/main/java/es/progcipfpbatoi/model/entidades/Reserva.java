package es.progcipfpbatoi.model.entidades;

import es.progcipfpbatoi.model.entidades.types.Viaje;

public class Reserva {

    private int id;
    private Usuario cliente;
    private int numPlazasSolicitadas;
    private Viaje viaje;
    private static int contador = 1;

    public Reserva(Usuario cliente, int numPlazasSolicitadas, Viaje viaje) {
        this.id = contador;
        this.cliente = cliente;
        this.numPlazasSolicitadas = numPlazasSolicitadas;
        this.viaje = viaje;
        this.contador++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    public int getNumPlazasSolicitadas() {
        return numPlazasSolicitadas;
    }

    public void setNumPlazasSolicitadas(int numPlazasSolicitadas) {
        this.numPlazasSolicitadas = numPlazasSolicitadas;
    }

    public Viaje getViaje() {
        return viaje;
    }
}
