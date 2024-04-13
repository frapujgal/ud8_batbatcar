package es.progcipfpbatoi.controller;

import es.progcipfpbatoi.model.entidades.Reserva;
import es.progcipfpbatoi.model.entidades.Usuario;
import es.progcipfpbatoi.model.entidades.types.Cancelable;
import es.progcipfpbatoi.model.entidades.types.Exclusivo;
import es.progcipfpbatoi.model.entidades.types.Flexible;
import es.progcipfpbatoi.model.entidades.types.Viaje;
import es.progcipfpbatoi.model.managers.ReservasManager;
import es.progcipfpbatoi.model.managers.ViajesManager;
import es.progcipfpbatoi.utils.GestorIO;
import es.progcipfpbatoi.views.ListadoViajesView;

import java.util.ArrayList;
import java.util.List;

public class ViajesController {

    private Usuario usuario;
    private ViajesManager viajesManager;
    private ReservasManager reservasManager;

    public ViajesController() {
        this.viajesManager = new ViajesManager();
        this.reservasManager = new ReservasManager();
        this.usuario = null;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * Lista todos los viajes del sistema.
     */
    public void listarViajes() {
        List<Viaje> viajes = viajesManager.findAll();
        System.out.println(new ListadoViajesView(viajes));
    }

    public void listarViajesCancelables() {
        List<Viaje> viajes = new ArrayList<>();

        for (Viaje v : viajesManager.findAll()) {
            if(v instanceof Cancelable && v.getPropietario().equals(this.usuario) && !v.isCancelado()) {
                viajes.add(v);
            }
        }
        System.out.println(new ListadoViajesView(viajes));
    }

    public void listarViajesReservables() {
        List<Viaje> viajes = new ArrayList<>();

        for (Viaje v : viajesManager.findAll()) {
            if(!v.isCancelado() && !v.isCerrado() && v.getPlazasLibres() > 0 && !v.getPropietario().equals(this.usuario)) {
                viajes.add(v);
            }
        }
        System.out.println(new ListadoViajesView(viajes));
    }

    /**
     * Añade un viaje al sistema, preguntando previamente por toda la información necesaria para crearlo.
     */
    public void anyadirViaje() {
        if(this.usuario == null) {
            GestorIO.print("Por favor, ¡identifícate primero!");
            return;
        }

        GestorIO.print("");
        GestorIO.print("1- Viaje Estándar");
        GestorIO.print("2- Viaje Cancelable");
        GestorIO.print("3- Viaje Exclusivo");
        GestorIO.print("4- Viaje Flexible");

        int seleccion = GestorIO.getInt("Seleccione el tipo de viaje", 1, 4);
        String ruta = GestorIO.getString("Introduzca la ruta a realizar (Ej: Alcoy-Alicante)");
        int duracion = GestorIO.getInt("Introduce la duración del viaje en minutos");
        double precio = GestorIO.getFloat("Introduce el precio de cada plaza");
        int plazasDisponibles = GestorIO.getInt("Introduce el número de plazas disponibles");

        if(seleccion == 1) {
            this.viajesManager.add(new Viaje(this.usuario, ruta, duracion, plazasDisponibles, precio));
        }
        else if(seleccion == 2) {
            this.viajesManager.add(new Cancelable(this.usuario, ruta, duracion, plazasDisponibles, precio));
        }
        else if(seleccion == 3) {
            this.viajesManager.add(new Exclusivo(this.usuario, ruta, duracion, plazasDisponibles, precio));
        }
        else if(seleccion == 4) {
            this.viajesManager.add(new Flexible(this.usuario, ruta, duracion, plazasDisponibles, precio));
        }
        GestorIO.print("¡Viaje creado con éxito!");
    }

    public void cancelarViaje() {
        if(this.usuario == null) {
            GestorIO.print("Por favor, ¡identifícate primero!");
            return;
        }

        listarViajesCancelables();
        int seleccion = GestorIO.getInt("Introduce el código de viaje a seleccionar");

        for(Viaje v : viajesManager.findAll()) {
            if(v.getId() == seleccion && v instanceof Cancelable) {
                viajesManager.cancel(v);
                GestorIO.print("El viaje se ha cancelado correctamente.");
                return;
            }
        }
        GestorIO.print("No se ha encontrado el viaje.");
    }

    public void realizarReserva() {
        if(this.usuario == null) {
            GestorIO.print("Por favor, ¡identifícate primero!");
            return;
        }

        listarViajesReservables();
        int idViaje = GestorIO.getInt("Introduzca el código de viaje a seleccionar");
        int numPlazas = GestorIO.getInt("Introduzca el número de plazas a reservar");

        for(Viaje v : viajesManager.findAll()) {
            if(v.getId() == idViaje && !v.getPropietario().equals(this.usuario)) {
                if(v.getPlazasLibres() > numPlazas) {
                    int id;
                    if(reservasManager.getReservas().isEmpty()) {
                        id = 1;
                    }
                    else {
                        id = reservasManager.getReservas().size() + 1;
                    }

                    v.setPlazasLibres(v.getPlazasLibres() - numPlazas);
                    v.setPlazasReservadas(v.getPlazasReservadas() + numPlazas);
                    Reserva reserva = new Reserva(id, this.usuario ,numPlazas);
                    reservasManager.add(reserva);
                    v.addReserva(reserva);

                    if(v instanceof Exclusivo) {
                        v.setPlazasLibres(0);
                        v.setCerrado(true);
                    }

                    GestorIO.print("Reserva realizada con éxito. A continuación se mostrará el ticket de confirmación.");

                    ListadoViajesView vistaReserva = new ListadoViajesView(reserva);
                    vistaReserva.visualizarReserva();
                    return;
                }
                else {
                    GestorIO.print("Reserva rechazada. No quedan suficientes plazas.");
                    return;
                }
            }
        }
        GestorIO.print("No se ha encontrado el viaje.");

    }


}
