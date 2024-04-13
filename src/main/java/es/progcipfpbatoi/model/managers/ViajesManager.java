package es.progcipfpbatoi.model.managers;
import es.progcipfpbatoi.controller.ViajesController;
import es.progcipfpbatoi.model.entidades.Usuario;
import es.progcipfpbatoi.model.entidades.types.Cancelable;
import es.progcipfpbatoi.model.entidades.types.Exclusivo;
import es.progcipfpbatoi.model.entidades.types.Flexible;
import es.progcipfpbatoi.model.entidades.types.Viaje;
import java.util.ArrayList;
import java.util.List;

/**
 * Gestor de viajes. Manejará la lista de los viajes tanto para almancenar nueva 
 * información sobre ella como para recuperar la totalidad o parte de la información
 * como también información relacionada con dicha lista.
 * @author batoi
 */

public class ViajesManager {

    private List<Viaje> viajes;

    public ViajesManager() {
        this.viajes = new ArrayList<>();
        init();
    }

    /**
     * Añade un nuevo viaje al repositorio
     * @param viaje
     */
    public void add(Viaje viaje) {
        int id = viajes.get(viajes.size() - 1).getId() + 1;
        viaje.setId(id);
        viajes.add(viaje);
    }

    /**
     * Cancela un viaje
     * @param viaje
     */
    public void cancel(Viaje viaje){
        viaje.setCancelado(true);
    }

    /**
     * Obtiene el número de viajes actualmente registrados
     * @return
     */
    public int getNumViajes() {
        return viajes.size();
    }

    /**
     * Obtiene todos los viajes
     * @return
     */
    public List<Viaje> findAll() {
        return viajes;
    }

    private void init() {
        viajes.add(new Viaje(1, new Usuario("frapujgal", "12345"), "Alcoy-Cocentaina", 5, 4, 2));
        viajes.add(new Flexible(2, new Usuario("javloplah", "12345"), "Alcoy-Onil", 20, 4, 6));
        viajes.add(new Exclusivo(3, new Usuario("Oscar", "12345"), "Alcoy-L'Orxa", 30, 4, 10));
        viajes.add(new Flexible(4, new Usuario("Felipe", "12345"), "Alcoy-Ibi", 15, 4, 3));
        viajes.add(new Exclusivo(5, new Usuario("Sergio", "12345"), "Alcoy-Castalla", 15, 4, 4));
        viajes.add(new Cancelable(6, new Usuario("frapujgal", "12345"), "Alcoy-Formentera", 200, 4, 50));
        viajes.add(new Exclusivo(7, new Usuario("frapujgal", "12345"), "Alcoy-Ibiza", 220, 4, 50));
        viajes.add(new Cancelable(8, new Usuario("frapujgal", "12345"), "Alcoy-Mallorca", 250, 4, 50));
        viajes.add(new Flexible(9, new Usuario("frapujgal", "12345"), "Alcoy-Fuenlabrada", 100, 4, 30));
        viajes.add(new Cancelable(10, new Usuario("frapujgal", "12345"), "Alcoy-Teruel", 150, 4, 25));
    }
}
