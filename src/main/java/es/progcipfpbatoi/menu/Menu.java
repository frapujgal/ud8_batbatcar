package es.progcipfpbatoi.menu;

import es.progcipfpbatoi.controller.UsuariosController;
import es.progcipfpbatoi.controller.ViajesController;
import es.progcipfpbatoi.exceptions.FechaPasadaException;
import es.progcipfpbatoi.exceptions.UsuarioSinEstablecerException;
import es.progcipfpbatoi.model.managers.UsuariosManager;
import es.progcipfpbatoi.utils.GestorIO;
import es.progcipfpbatoi.views.ExceptionView;
import es.progcipfpbatoi.views.ListadoViajesView;

/**
 * Clase que gestiona el menú de opciones. A partir de esta clase se ejecutan
 * las diferentes opciones del menú (casos de uso).
 * @author batoi
 */

public class Menu {

    private static final int OPCION_SALIR = 9;
    
    private ViajesController viajesController;
    private UsuariosController usuariosController;
    
    public Menu() {
        this.viajesController = new ViajesController();
        this.usuariosController = new UsuariosController();
    }

    public void iniciar(){

        int opcionSeleccionada = 0;

        // Ampliar método para que se soliciten las opciones hasta que se indique la opción salir
        do {
            System.out.println("BatBatCar");
            System.out.println("=========");
            mostrarOpciones();

            opcionSeleccionada = solicitarOpcion();

            ejecutarOpcion(opcionSeleccionada);
            System.out.println();
        } while (opcionSeleccionada != OPCION_SALIR);
    }

    private void mostrarOpciones() {
        System.out.println("1) Establecer usuario (login)");
        System.out.println("2) Listar todos los viajes");
        System.out.println("3) Añadir viaje");
        System.out.println("4) Cancelar viaje");
        System.out.println("5) Realizar reserva");
        System.out.println("6) Modificar reserva");
        System.out.println("7) Cancelar reserva");
        System.out.println("8) Buscar viaje y realizar reserva");
        System.out.println("9) Salir");
    }
    
    private int solicitarOpcion() {
        return GestorIO.getInt("Selecciona una opción [1-9]", 1, 9);
    }
    
    private void ejecutarOpcion(int opcionSeleccionada) {

        try {
            switch (opcionSeleccionada) {
                case 1 -> viajesController.setUsuario(usuariosController.login());
                case 2 -> viajesController.listarViajes();
                case 3 -> viajesController.anyadirViaje();
                case 4 -> viajesController.cancelarViaje();
                case 5 -> viajesController.realizarReserva();
                case 6 -> viajesController.modificarReserva();
                case 7 -> viajesController.cancelarReserva();
                case 8 -> viajesController.buscarViaje();
                case 9 -> GestorIO.print("¡Hasta pronto!");
            }
        } catch (UsuarioSinEstablecerException | FechaPasadaException e) {
            System.err.println(new ExceptionView(e.getMessage()));
        }
        System.out.println();
    }

}
