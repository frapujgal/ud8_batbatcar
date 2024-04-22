package es.progcipfpbatoi.model.entidades.types;

import es.progcipfpbatoi.controller.ViajesController;
import es.progcipfpbatoi.exceptions.FechaPasadaException;
import es.progcipfpbatoi.exceptions.UsuarioSinEstablecerException;

public class OpcionAddViaje extends Opcion {

    public OpcionAddViaje() {
        super("Añadir viaje");
    }

    @Override
    public void ejecutar(ViajesController viajesController) throws UsuarioSinEstablecerException, FechaPasadaException {
        viajesController.anyadirViaje();
    }
}
