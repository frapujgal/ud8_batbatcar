package es.progcipfpbatoi.model.entidades.types;

import es.progcipfpbatoi.controller.ViajesController;
import es.progcipfpbatoi.exceptions.*;

public class OpcionCancelViaje extends Opcion {

    public OpcionCancelViaje() {
        super("Cancelar viaje");
    }

    @Override
    public void ejecutar(ViajesController viajesController) throws UsuarioSinEstablecerException, FechaPasadaException, CredencialesInvalidasException, MaximoIntentosAlcanzadosException, ReservaNoValidaException, ReservaNoCancelableException, ViajeNoValidoException {
        viajesController.cancelarViaje();
    }
}
