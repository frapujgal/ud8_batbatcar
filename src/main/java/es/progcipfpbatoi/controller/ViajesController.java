package es.progcipfpbatoi.controller;

import es.progcipfpbatoi.exceptions.*;
import es.progcipfpbatoi.model.entidades.Reserva;
import es.progcipfpbatoi.model.entidades.Usuario;
import es.progcipfpbatoi.model.entidades.types.ViajeCancelable;
import es.progcipfpbatoi.model.entidades.types.ViajeExclusivo;
import es.progcipfpbatoi.model.entidades.types.ViajeFlexible;
import es.progcipfpbatoi.model.entidades.types.Viaje;
import es.progcipfpbatoi.model.managers.ViajesManager;
import es.progcipfpbatoi.utils.GestorIO;
import es.progcipfpbatoi.views.ListadoReservasUsuarioView;
import es.progcipfpbatoi.views.ListadoViajesView;
import es.progcipfpbatoi.views.ReservaView;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

public class ViajesController {

    private Usuario usuario;
    private ViajesManager viajesManager;

    public ViajesController() {
        this.viajesManager = new ViajesManager();
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

    public void listarViajes(List<Viaje> viajes) {
        System.out.println(new ListadoViajesView(viajes));
    }

    public void listarViajesCancelables() {
        List<Viaje> viajes = viajesManager.getViajesCancelables(this.usuario);
        System.out.println(new ListadoViajesView(viajes));
    }

    public void listarViajesReservables() {
        List<Viaje> viajes = viajesManager.getViajesReservables(this.usuario);
        System.out.println(new ListadoViajesView(viajes));
    }

    public void listarViajesFlexibles() {
        List<Viaje> viajes = viajesManager.getViajesFlexibles(this.usuario);
        System.out.println(new ListadoViajesView(viajes));
    }

    public void listarReservas(Usuario usuario) {
        List<Reserva> reservas = getReservasUsuario(usuario);
        System.out.println(new ListadoReservasUsuarioView(reservas));
    }

    public void listarReservasModificables(Usuario usuario) {
        List<Reserva> reservas = new ArrayList<>();

        for (Reserva r : getReservasUsuario(this.usuario)) {
            if(r.getViaje() instanceof ViajeFlexible) {
                reservas.add(r);
            }
        }
        System.out.println(new ListadoReservasUsuarioView(reservas));
    }

    public void listarReservasCancelables(Usuario usuario) {
        List<Reserva> reservas = new ArrayList<>();

        for (Reserva r : getReservasUsuario(this.usuario)) {
            if(r.getViaje() instanceof ViajeCancelable) {
                reservas.add(r);
            }
        }
        System.out.println(new ListadoReservasUsuarioView(reservas));
    }

    public List<Reserva> getReservasUsuario(Usuario usuario) {
        List<Reserva> reservasUsuario = new ArrayList<>();

        for (Viaje v : viajesManager.findAll()) {
            for (Reserva r : v.getReservas()) {
                if (r.getCliente().getUsername().equals(usuario.getUsername())) {
                    reservasUsuario.add(r);
                }
            }
        }
        return reservasUsuario;
    }

    /**
     * Añade un viaje al sistema, preguntando previamente por toda la información necesaria para crearlo.
     */
    public void anyadirViaje() throws UsuarioSinEstablecerException, FechaPasadaException {
        if(this.usuario == null) {
            throw new UsuarioSinEstablecerException("Por favor, ¡identifícate primero!");
        }

        GestorIO.print("1- Viaje Estándar");
        GestorIO.print("2- Viaje Cancelable");
        GestorIO.print("3- Viaje Exclusivo");
        GestorIO.print("4- Viaje Flexible");

        int seleccion = GestorIO.getInt("Seleccione el tipo de viaje", 1, 4);
        String ruta = GestorIO.getString("Introduzca la ruta a realizar (Ej: Alcoy-Alicante)");
        String fecha = GestorIO.getString("Introduzca la fecha (Ej. 10-10-2024)");
        String hora = GestorIO.getString("Introduzca la hora (Ej. 23:15)");
        int duracion = GestorIO.getInt("Introduce la duración del viaje en minutos");
        double precio = GestorIO.getFloat("Introduce el precio de cada plaza");
        int plazasDisponibles = GestorIO.getInt("Introduce el número de plazas disponibles");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy'T'HH:mm");
        LocalDateTime fechaHora = LocalDateTime.parse(fecha + "T" + hora, formatter);

        if (fechaHora.isBefore(LocalDateTime.now())) {
            throw new FechaPasadaException("Error, fecha no válida.");
        }

        Viaje viaje = null;
        if(seleccion == 1) {
            viaje = new Viaje(this.usuario, ruta, duracion, plazasDisponibles, precio, fechaHora);
            this.viajesManager.add(viaje);
        }
        else if(seleccion == 2) {
            viaje = new ViajeCancelable(this.usuario, ruta, duracion, plazasDisponibles, precio, fechaHora);
            this.viajesManager.add(viaje);
        }
        else if(seleccion == 3) {
            viaje = new ViajeExclusivo(this.usuario, ruta, duracion, plazasDisponibles, precio, fechaHora);
            this.viajesManager.add(viaje);
        }
        else if(seleccion == 4) {
            viaje = new ViajeFlexible(this.usuario, ruta, duracion, plazasDisponibles, precio, fechaHora);
            this.viajesManager.add(viaje);
        }
        GestorIO.print("Viaje de tipo " + viaje.getTipoViaje() +  " de " + viaje.getPropietario().getUsername() + " código " + viaje.getId() + " ruta " + viaje.getRuta() + " añadido con éxito");
    }

    public void cancelarViaje() throws UsuarioSinEstablecerException, ViajeNoValidoException {
        if(this.usuario == null) {
            throw new UsuarioSinEstablecerException("Por favor, ¡identifícate primero!");
        }

        listarViajesCancelables();
        int seleccion = GestorIO.getInt("Introduce el código de viaje a seleccionar");

        for(Viaje v : viajesManager.getViajesCancelables(this.usuario)) {
            if(v.getId() == seleccion) {
                viajesManager.cancel(v);
                GestorIO.print("El viaje se ha cancelado correctamente.");
                return;
            }
        }
        throw new ViajeNoValidoException("No se ha encontrado el viaje.");
    }

    public void realizarReserva() throws UsuarioSinEstablecerException, ReservaNoValidaException, ViajeNoValidoException, FechaPasadaException {
        if(this.usuario == null) {
            throw new UsuarioSinEstablecerException("Por favor, ¡identifícate primero!");
        }

        listarViajesReservables();
        int idViaje = GestorIO.getInt("Introduzca el código de viaje a seleccionar");
        int numPlazas = GestorIO.getInt("Introduzca el número de plazas a reservar");

        for(Viaje v : viajesManager.getViajesReservables(this.usuario)) {
            if(v.getId() == idViaje) {

                if (v.getFecha().isBefore(LocalDateTime.now())) {
                    throw new FechaPasadaException("Error, el viaje ya se ha realizado");
                }

                // comprobamos que el usuario no tenga una reserva en ese viaje
                for (Reserva r : v.getReservas()) {
                    if(r.getCliente().getUsername().equals(this.usuario.getUsername())) {
                        throw new ReservaNoValidaException("El usuario " + this.usuario.getUsername() + " ya tiene una reserva en este viaje");
                    }
                }

                // comprobamos que haya plazas suficientes en el viaje
                if(v.getPlazasOfertadas() >= numPlazas) {
                    v.setPlazasReservadas(v.getPlazasReservadas() + numPlazas);
                    Reserva reserva = new Reserva(this.usuario ,numPlazas, v);
                    v.addReserva(reserva);

                    // si es exclusivo, ponemos plazas libres a 0 y cerramos viaje
                    if(v instanceof ViajeExclusivo) {
                        v.setPlazasReservadas(v.getPlazasOfertadas());
                        v.setCerrado(true);
                    }

                    // si ya no quedan plazas libres, cerramos viaje
                    if(v.getPlazasReservadas() == v.getPlazasOfertadas()) {
                        v.setCerrado(true);
                    }

                    GestorIO.print("Reserva realizada con éxito. A continuación se mostrará el ticket de confirmación.");

                    ReservaView vistaReserva = new ReservaView(reserva);
                    vistaReserva.visualizar();
                    return;
                }
                else {
                    throw new ReservaNoValidaException("Reserva rechazada. No quedan suficientes plazas.");
                }
            }
        }
        throw new ViajeNoValidoException("No se ha encontrado el viaje.");
    }

    public void realizarReserva(List<Viaje> viajes) throws UsuarioSinEstablecerException, ReservaNoValidaException, ViajeNoValidoException, FechaPasadaException {
        if(this.usuario == null) {
            throw new UsuarioSinEstablecerException("Por favor, ¡identifícate primero!");
        }

        int idViaje = GestorIO.getInt("Introduzca el código de viaje a seleccionar");
        int numPlazas = GestorIO.getInt("Introduzca el número de plazas a reservar");

        for(Viaje v : viajes) {
            if(v.getId() == idViaje) {

                if (v.getFecha().isBefore(LocalDateTime.now())) {
                    throw new FechaPasadaException("Error, el viaje ya se ha realizado");
                }

                // comprobamos que el usuario no tenga una reserva en ese viaje
                for (Reserva r : v.getReservas()) {
                    if(r.getCliente().getUsername().equals(this.usuario.getUsername())) {
                        throw new ReservaNoValidaException("El usuario " + this.usuario.getUsername() + " ya tiene una reserva en este viaje");
                    }
                }

                // comprobamos que haya plazas suficientes en el viaje
                if(v.getPlazasOfertadas() >= numPlazas) {
                    v.setPlazasReservadas(v.getPlazasReservadas() + numPlazas);
                    Reserva reserva = new Reserva(this.usuario ,numPlazas, v);
                    v.addReserva(reserva);

                    // si es exclusivo, ponemos plazas libres a 0 y cerramos viaje
                    if(v instanceof ViajeExclusivo) {
                        v.setPlazasReservadas(v.getPlazasOfertadas());
                        v.setCerrado(true);
                    }

                    // si ya no quedan plazas libres, cerramos viaje
                    if(v.getPlazasReservadas() == v.getPlazasOfertadas()) {
                        v.setCerrado(true);
                    }

                    GestorIO.print("Reserva realizada con éxito. A continuación se mostrará el ticket de confirmación.");

                    ReservaView vistaReserva = new ReservaView(reserva);
                    vistaReserva.visualizar();
                    return;
                }
                else {
                    throw new ReservaNoValidaException("Reserva rechazada. No quedan suficientes plazas.");
                }
            }
        }
        throw new ViajeNoValidoException("No se ha encontrado el viaje.");
    }

    public void modificarReserva() throws UsuarioSinEstablecerException, ReservaNoValidaException {
        if(this.usuario == null) {
            throw new UsuarioSinEstablecerException("Por favor, ¡identifícate primero!");
        }

        listarReservasModificables(this.usuario);
        int seleccion = GestorIO.getInt("Introduzca el código de reserva a modificar");
        int plazas = GestorIO.getInt("Introduzca el número de plazas a reservar");

        for (Viaje v : viajesManager.getViajesFlexibles(this.usuario)) {
            for (Reserva r : v.getReservas()) {
                if(r.getId() == seleccion) {
                    if (r.getViaje().getPlazasOfertadas() < plazas) {
                        throw new ReservaNoValidaException("No hay suficientes plazas.");
                    }

                    r.getViaje().getReservas().remove(r);
                    r.getViaje().setPlazasReservadas(r.getViaje().getPlazasReservadas() - r.getNumPlazasSolicitadas() + plazas);

                    Reserva nuevaReserva = new Reserva(this.usuario, plazas, v);
                    r.getViaje().getReservas().add(nuevaReserva);

                    if(r.getViaje().getPlazasReservadas() == r.getViaje().getPlazasOfertadas()) {
                        r.getViaje().setCerrado(true);
                    }
                    else {
                        r.getViaje().setCerrado(false);
                    }
                    GestorIO.print("Reserva realizada con éxito. A continuación se mostrará el ticket de confirmación.");

                    ReservaView vistaReserva = new ReservaView(nuevaReserva);
                    vistaReserva.visualizar();

                    GestorIO.print("Reserva del viaje de tipo Viaje " + r.getViaje().getTipoViaje() + " de " + r.getViaje().getPropietario().getUsername() + " código " + r.getViaje().getId() + " ruta " + r.getViaje().getRuta() + " modificada con éxito.");
                    return;
                }
            }
        }
    }

    public void cancelarReserva() throws UsuarioSinEstablecerException, ReservaNoCancelableException {
        if(this.usuario == null) {
            throw new UsuarioSinEstablecerException("Por favor, ¡identifícate primero!");
        }

        listarReservas(this.usuario);
        int seleccion = GestorIO.getInt("Introduzca el código de reserva a modificar");

        for (Reserva r : getReservasUsuario(this.usuario)) {
            if (r.getViaje() instanceof ViajeCancelable || r.getViaje() instanceof ViajeFlexible) {
                if (r.getId() == seleccion) {
                    r.getViaje().getReservas().remove(r);
                    r.getViaje().setPlazasReservadas(r.getViaje().getPlazasReservadas() - r.getNumPlazasSolicitadas());
                    r.getViaje().setCerrado(false);

                    GestorIO.print("Reserva del viaje de tipo Viaje " + r.getViaje().getTipoViaje() + " de " + r.getViaje().getPropietario().getUsername() + " código " + r.getViaje().getId() + " ruta " + r.getViaje().getRuta() + " cancelada con éxito.");
                    return;
                }
            }
        }
        throw new ReservaNoCancelableException("Error, la reserva seleccionada no puede ser cancelada");
    }

    public void buscarViaje() throws UsuarioSinEstablecerException, ViajeNoValidoException, ReservaNoValidaException, FechaPasadaException {
        if(this.usuario == null) {
            throw new UsuarioSinEstablecerException("Por favor, ¡identifícate primero!");
        }

        String ciudad = GestorIO.getString("Introduzca la ciudad a la que desea viajar");
        List<Viaje> viajes = new ArrayList<>();

        for (Viaje v : viajesManager.findAll()) {
            if (v.getRuta().contains("-" + ciudad) && !v.getPropietario().getUsername().equals(this.usuario.getUsername())) {
                viajes.add(v);
            }
        }

        listarViajes(viajes);
        if(viajes.isEmpty()) {
            throw new ViajeNoValidoException("Error, no hay viajes a " + ciudad);
        }

        String seleccion;
        do {
            seleccion = GestorIO.getString("¿Quiere realizar una reserva?[S/N]");
            if(seleccion.equals("S")) {
                realizarReserva(viajes);
            }
            else if (seleccion.equals("N")) {
                return;
            }
            else {
                GestorIO.print("Selección incorrecta, la opción debe ser S/N");
            }
        } while (!seleccion.equals("S"));
    }

}
