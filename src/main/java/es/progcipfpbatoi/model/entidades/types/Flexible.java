package es.progcipfpbatoi.model.entidades.types;

import es.progcipfpbatoi.model.entidades.Usuario;

public class Flexible extends Cancelable {

    public Flexible(int id, Usuario propietario, String ruta, int duracion, int plazasLibres, double precio) {
        super(id, propietario, ruta, duracion, plazasLibres, precio);
    }

    public Flexible(Usuario propietario, String ruta, int duracion, int plazasLibres, double precio) {
        super(propietario, ruta, duracion, plazasLibres, precio);
    }

    @Override
    public String getTipoViaje() {
        return "Flexible";
    }
}
