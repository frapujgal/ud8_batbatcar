package es.progcipfpbatoi.model.entidades.types;

import es.progcipfpbatoi.controller.ViajesController;
import es.progcipfpbatoi.exceptions.*;

public abstract class Opcion {

    private String titulo;
    private boolean finalizar;

    public Opcion(String titulo) {
        this.titulo = titulo;
    }

    public void mostrar(int numOpcion) {
        System.out.print("\n" + numOpcion + " - " + titulo);
    }

    public abstract void ejecutar(ViajesController viajesController) throws UsuarioSinEstablecerException, FechaPasadaException, CredencialesInvalidasException, MaximoIntentosAlcanzadosException, ReservaNoValidaException, ReservaNoCancelableException, ViajeNoValidoException;

    public boolean finalizar() {
        return finalizar;
    }

    public void setFinalizar(boolean finalizar) {
        this.finalizar = finalizar;
    }
}
