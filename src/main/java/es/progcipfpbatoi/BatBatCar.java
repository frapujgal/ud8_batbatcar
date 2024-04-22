package es.progcipfpbatoi;

import es.progcipfpbatoi.exceptions.FechaPasadaException;
import es.progcipfpbatoi.exceptions.UsuarioSinEstablecerException;
import es.progcipfpbatoi.menu.Menu;
import es.progcipfpbatoi.model.entidades.types.*;

public class BatBatCar {

    public static void main(String[] args) throws UsuarioSinEstablecerException, FechaPasadaException {
        Menu menu = new Menu("BatBatCar");
        menu.anyadir(new OpcionLogin());
        menu.anyadir(new OpcionListarViajes());
        menu.anyadir(new OpcionAddViaje());
        menu.anyadir(new OpcionCancelViaje());
        menu.anyadir(new OpcionAddReserva());
        menu.anyadir(new OpcionModReserva());
        menu.anyadir(new OpcionCancelReserva());
        menu.anyadir(new OpcionBuscarViaje());
        menu.anyadir(new OpcionSalir());
        menu.ejecutar();
    }
}
