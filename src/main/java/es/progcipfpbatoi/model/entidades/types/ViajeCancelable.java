package es.progcipfpbatoi.model.entidades.types;

import es.progcipfpbatoi.model.entidades.Usuario;

import java.time.LocalDateTime;

public class ViajeCancelable extends Viaje {

    public ViajeCancelable(int id, Usuario propietario, String ruta, int duracion, int plazasLibres, double precio, LocalDateTime fecha) {
        super(id, propietario, ruta, duracion, plazasLibres, precio, fecha);
    }

    public ViajeCancelable(Usuario propietario, String ruta, int duracion, int plazasLibres, double precio, LocalDateTime fecha) {
        super(propietario, ruta, duracion, plazasLibres, precio, fecha);
    }

    @Override
    public String getTipoViaje() {
        return "Viaje Cancelable";
    }
}
