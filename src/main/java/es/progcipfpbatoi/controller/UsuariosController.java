package es.progcipfpbatoi.controller;

import es.progcipfpbatoi.model.entidades.Usuario;
import es.progcipfpbatoi.model.managers.UsuariosManager;
import es.progcipfpbatoi.utils.GestorIO;

public class UsuariosController {

    private UsuariosManager usuariosManager;

    public UsuariosController() {
        this.usuariosManager = new UsuariosManager();
    }

    public Usuario login() {
        int intentos = 3;

        do {
            String user = GestorIO.getString("Username");
            String password = GestorIO.getString("Password");

            if (usuariosManager.comprobarUsuario(user, password)) {
                return new Usuario(user, password);
            }

            intentos--;
        }  while (intentos > 0);

        GestorIO.print("Se ha alcanzado el número máximo de intentos. Adiós");
        System.exit(0);
        return null;
    }

}
