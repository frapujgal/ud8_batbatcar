package es.progcipfpbatoi.model.managers;

import es.progcipfpbatoi.model.entidades.Usuario;
import es.progcipfpbatoi.utils.GestorIO;

import java.util.ArrayList;
import java.util.List;

public class UsuariosManager {

    private List<Usuario> usuarios;

    public UsuariosManager() {
        this.usuarios = new ArrayList<>();
        usuarios.add(new Usuario("frapujgal", "12345"));
        usuarios.add(new Usuario("javloplah", "12345"));
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public boolean comprobarUsuario(String user, String password) {
        boolean usuarioEncontrado = false;
        boolean contrasenyaCorrecta = false;

        for (Usuario u: usuarios) {
            if (u.getUsername().equalsIgnoreCase(user)) {
                usuarioEncontrado = true;
                if(u.getContraseña().equalsIgnoreCase(password)) {
                    contrasenyaCorrecta = true;
                    GestorIO.print("Bienvenido " + user);
                    return true;
                }
            }
        }

        if (!usuarioEncontrado) {
            GestorIO.print("Error, el usuario introducido no existe");
        }
        else if (!contrasenyaCorrecta) {
            GestorIO.print("Error, la contraseña introducida es errónea");
        }

        return false;
    }

}
