package es.progcipfpbatoi.model.entidades.types;

import es.progcipfpbatoi.controller.ViajesController;

public class OpcionSalir extends Opcion {

    public OpcionSalir() {
        super("Salir");
    }

    @Override
    public void ejecutar(ViajesController viajesController) {
        System.out.println("Adios");
        setFinalizar(true);
    }

}
