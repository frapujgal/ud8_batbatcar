package es.progcipfpbatoi.model.entidades.types;

import es.progcipfpbatoi.controller.ViajesController;

public class OpcionListarViajes extends Opcion {

    public OpcionListarViajes() {
        super("Listar todos los viajes");
    }

    @Override
    public void ejecutar(ViajesController viajesController) {
        viajesController.listarViajes();
    }
}
