package es.progcipfpbatoi.model.entidades;

import java.util.Objects;

/**
 *
 * Clase que representa a un usuario de la aplicación
 */
public class Usuario {

    private String username;
    private String contraseña;

    public Usuario(String username, String contraseña) {
        this.username = username;
        this.contraseña = contraseña;
    }

    public String getUsername() {
        return username;
    }

    public String getContraseña() {
        return contraseña;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario1 = (Usuario) o;
        return Objects.equals(this.getUsername(), usuario1.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(username);
    }
}
