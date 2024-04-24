package es.progcipfpbatoi.menu;

import es.progcipfpbatoi.controller.UsuariosController;
import es.progcipfpbatoi.controller.ViajesController;
import es.progcipfpbatoi.exceptions.*;
import es.progcipfpbatoi.model.entidades.types.Opcion;
import es.progcipfpbatoi.views.ExceptionView;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase que gestiona el menú de opciones. A partir de esta clase se ejecutan
 * las diferentes opciones del menú (casos de uso).
 * @author batoi
 */

public class Menu {

    private ViajesController viajesController;
    private UsuariosController usuariosController;
    private String titulo;
    private ArrayList<Opcion> opciones;

    public Menu(String titulo) {
        this.viajesController = new ViajesController();
        this.usuariosController = new UsuariosController();
        this.titulo = titulo;
        this.opciones = new ArrayList<>();
    }

    public void anyadir(Opcion opcion) {
        this.opciones.add(opcion);
    }

    public void mostrar() {
        System.out.print("\n" + this.titulo + "\n====================");
        for (int i = 0; i < opciones.size(); i++) {
            opciones.get(i).mostrar(i + 1);
        }
    }

    public Opcion getOpcion() {
        Scanner teclado = new Scanner(System.in);

        do {
            System.out.print("\nSeleccione una opción [1-" + opciones.size() + "]: ");
            if (teclado.hasNextInt()) {
                int opcion = teclado.nextInt();
                if (opcion >= 1 && opcion <= opciones.size()) {
                    return this.opciones.get(opcion - 1);
                }
            }
            System.out.println("¡Error! La opción seleccionada no existe");
            teclado.nextLine();
        } while (true);
    }

    public void ejecutar() {
        Opcion opcion = null;
        do {
            try {
                mostrar();
                opcion = getOpcion();
                opcion.ejecutar(viajesController);
            } catch (UsuarioSinEstablecerException | FechaPasadaException | ViajeNoValidoException | ReservaNoValidaException |
                 ReservaNoCancelableException | CredencialesInvalidasException | MaximoIntentosAlcanzadosException e) {
                System.out.println(new ExceptionView(e.getMessage()));
            }
        } while (!opcion.finalizar());
    }

}
