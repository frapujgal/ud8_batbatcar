package es.progcipfpbatoi.model.entidades.types;

import es.progcipfpbatoi.controller.ViajesController;
import es.progcipfpbatoi.exceptions.*;

public class OpcionAddReserva extends Opcion {

    public OpcionAddReserva() {
        super("Realizar reserva");
    }

    @Override
    public void ejecutar(ViajesController viajesController) throws UsuarioSinEstablecerException, FechaPasadaException, CredencialesInvalidasException, MaximoIntentosAlcanzadosException, ReservaNoValidaException, ReservaNoCancelableException, ViajeNoValidoException {
        viajesController.realizarReserva();
    }
}
