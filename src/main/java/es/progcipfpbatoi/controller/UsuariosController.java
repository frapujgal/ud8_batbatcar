package es.progcipfpbatoi.controller;

import es.progcipfpbatoi.exceptions.CredencialesInvalidasException;
import es.progcipfpbatoi.exceptions.MaximoIntentosAlcanzadosException;
import es.progcipfpbatoi.model.entidades.Usuario;
import es.progcipfpbatoi.model.managers.UsuariosManager;
import es.progcipfpbatoi.utils.GestorIO;
import es.progcipfpbatoi.views.ExceptionView;

public class UsuariosController {

    private UsuariosManager usuariosManager;

    public UsuariosController() {
        this.usuariosManager = new UsuariosManager();
    }

    public Usuario login() throws MaximoIntentosAlcanzadosException {
        int intentos = 3;

        try {
            do {
                String user = GestorIO.getString("Username");
                String password = GestorIO.getString("Password");

                try {
                    if (usuariosManager.comprobarUsuario(user, password)) {
                        return new Usuario(user, password);
                    }
                } catch (CredencialesInvalidasException e) {
                    System.out.println(new ExceptionView(e.getMessage()));
                }

                intentos--;
            }  while (intentos > 0);

            throw new MaximoIntentosAlcanzadosException("Se ha alcanzado el número máximo de intentos. Adiós");

        } catch (MaximoIntentosAlcanzadosException e) {
            System.err.println(new ExceptionView(e.getMessage()));
            System.exit(0);
        }

        return null;
    }

}
