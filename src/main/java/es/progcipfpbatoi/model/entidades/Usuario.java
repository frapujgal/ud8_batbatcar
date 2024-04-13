package es.progcipfpbatoi.model.entidades;

import java.util.Objects;

/**
 *
 * Clase que representa a un usuario de la aplicación
 */
public class Usuario {

    private String usuario;
    private String contraseña;

    public Usuario(String usuario, String contraseña) {
        this.usuario = usuario;
        this.contraseña = contraseña;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario1 = (Usuario) o;
        return Objects.equals(this.getUsuario(), usuario1.getUsuario());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(usuario);
    }
}
