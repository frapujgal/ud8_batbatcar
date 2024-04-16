package es.progcipfpbatoi.model.entidades.types;

import es.progcipfpbatoi.model.entidades.Usuario;

public class Exclusivo extends Viaje {

    public Exclusivo(int id, Usuario propietario, String ruta, int duracion, int plazasLibres, double precio) {
        super(id, propietario, ruta, duracion, plazasLibres, precio);
    }

    public Exclusivo(Usuario propietario, String ruta, int duracion, int plazasLibres, double precio) {
        super(propietario, ruta, duracion, plazasLibres, precio);
    }

    @Override
    public String getTipoViaje() {
        return "Viaje Exclusivo";
    }
}
