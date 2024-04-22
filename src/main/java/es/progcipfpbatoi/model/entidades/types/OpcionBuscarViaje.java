package es.progcipfpbatoi.model.entidades.types;

import es.progcipfpbatoi.controller.ViajesController;
import es.progcipfpbatoi.exceptions.*;

public class OpcionBuscarViaje extends Opcion {

    public OpcionBuscarViaje() {
        super("Buscar viaje");
    }

    @Override
    public void ejecutar(ViajesController viajesController) throws UsuarioSinEstablecerException, FechaPasadaException, CredencialesInvalidasException, MaximoIntentosAlcanzadosException, ReservaNoValidaException, ReservaNoCancelableException, ViajeNoValidoException {
        viajesController.buscarViaje();
    }
}
