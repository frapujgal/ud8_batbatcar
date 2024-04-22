package es.progcipfpbatoi.model.managers;
import es.progcipfpbatoi.model.entidades.Usuario;
import es.progcipfpbatoi.model.entidades.types.Cancelable;
import es.progcipfpbatoi.model.entidades.types.Exclusivo;
import es.progcipfpbatoi.model.entidades.types.Flexible;
import es.progcipfpbatoi.model.entidades.types.Viaje;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    public List<Viaje> getViajesReservables(Usuario usuario) {
        List<Viaje> viajes = new ArrayList<>();

        for (Viaje v : findAll()) {
            if(!v.getPropietario().equals(usuario) && !v.isCancelado() && !v.isCerrado()) {
                viajes.add(v);
            }
        }
        return viajes;
    }

    public List<Viaje> getViajesFlexibles(Usuario usuario) {
        List<Viaje> viajes = new ArrayList<>();

        for (Viaje v : findAll()) {
            if(v instanceof Flexible && !v.getPropietario().equals(usuario) && !v.isCancelado() && !v.isCerrado()) {
                viajes.add(v);
            }
        }
        return viajes;
    }

    public List<Viaje> getViajesCancelables(Usuario usuario) {
        List<Viaje> viajes = new ArrayList<>();

        for (Viaje v : findAll()) {
            if(v instanceof Cancelable && v.getPropietario().equals(usuario) && !v.isCancelado() && !v.isCerrado()) {
                viajes.add(v);
            }
        }
        return viajes;
    }

    private void init() {
        viajes.add(new Viaje(1, new Usuario("frapujgal", "12345"), "Alcoy-Cocentaina", 5, 4, 2, LocalDateTime.parse("15-04-2024T06:30", DateTimeFormatter.ofPattern("dd-MM-yyyy'T'HH:mm"))));
        viajes.add(new Flexible(2, new Usuario("javloplah", "12345"), "Alcoy-Onil", 20, 4, 6, LocalDateTime.parse("16-04-2024T07:30", DateTimeFormatter.ofPattern("dd-MM-yyyy'T'HH:mm"))));
        viajes.add(new Exclusivo(3, new Usuario("Oscar", "12345"), "Alcoy-L'Orxa", 30, 4, 10, LocalDateTime.parse("17-04-2024T08:30", DateTimeFormatter.ofPattern("dd-MM-yyyy'T'HH:mm"))));
        viajes.add(new Flexible(4, new Usuario("Felipe", "12345"), "Alcoy-Ibi", 15, 4, 3, LocalDateTime.parse("18-04-2024T09:30", DateTimeFormatter.ofPattern("dd-MM-yyyy'T'HH:mm"))));
        viajes.add(new Exclusivo(5, new Usuario("Sergio", "12345"), "Alcoy-Castalla", 15, 4, 4, LocalDateTime.parse("19-04-2024T10:30", DateTimeFormatter.ofPattern("dd-MM-yyyy'T'HH:mm"))));
        viajes.add(new Viaje(6, new Usuario("Denis", "12345"), "Alcoy-Formentera", 200, 4, 50, LocalDateTime.parse("20-04-2024T11:30", DateTimeFormatter.ofPattern("dd-MM-yyyy'T'HH:mm"))));
        viajes.add(new Cancelable(7, new Usuario("Jorge", "12345"), "Alcoy-Ibiza", 220, 4, 50, LocalDateTime.parse("21-04-2024T12:30", DateTimeFormatter.ofPattern("dd-MM-yyyy'T'HH:mm"))));
        viajes.add(new Cancelable(8, new Usuario("Joan", "12345"), "Alcoy-Mallorca", 250, 4, 50, LocalDateTime.parse("22-04-2024T13:30", DateTimeFormatter.ofPattern("dd-MM-yyyy'T'HH:mm"))));
        viajes.add(new Flexible(9, new Usuario("frapujgal", "12345"), "Alcoy-Fuenlabrada", 100, 4, 30, LocalDateTime.parse("23-04-2024T14:30", DateTimeFormatter.ofPattern("dd-MM-yyyy'T'HH:mm"))));
        viajes.add(new Cancelable(10, new Usuario("frapujgal", "12345"), "Alcoy-Teruel", 150, 4, 25, LocalDateTime.parse("24-04-2024T15:30", DateTimeFormatter.ofPattern("dd-MM-yyyy'T'HH:mm"))));
    }


}
