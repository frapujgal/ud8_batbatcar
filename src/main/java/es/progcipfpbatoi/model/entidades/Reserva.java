package es.progcipfpbatoi.model.entidades;

public class Reserva {

    private int id;
    private Usuario cliente;
    private int numPlazasSolicitadas;

    public Reserva(int id, Usuario cliente, int numPlazasSolicitadas) {
        this.id = id;
        this.cliente = cliente;
        this.numPlazasSolicitadas = numPlazasSolicitadas;
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

}
