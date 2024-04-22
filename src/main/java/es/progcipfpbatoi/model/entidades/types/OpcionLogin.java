package es.progcipfpbatoi.model.entidades.types;

import es.progcipfpbatoi.controller.UsuariosController;
import es.progcipfpbatoi.controller.ViajesController;
import es.progcipfpbatoi.exceptions.MaximoIntentosAlcanzadosException;

public class OpcionLogin extends Opcion {

    public OpcionLogin() {
        super("Establecer usuario (login)");
    }

    @Override
    public void ejecutar(ViajesController viajesController) throws MaximoIntentosAlcanzadosException {
        UsuariosController usuariosController = new UsuariosController();
        viajesController.setUsuario(usuariosController.login());
    }
}
