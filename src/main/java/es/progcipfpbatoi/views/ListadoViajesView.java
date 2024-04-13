package es.progcipfpbatoi.views;

/**
 * Vista dedicada a los listados de viajes. De cada viaje se muestra su código,
 * ruta, precio, propietario, tipo de viaje, plazas disponibles y si está cancelado.
 */

import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import es.progcipfpbatoi.model.entidades.Reserva;
import es.progcipfpbatoi.model.entidades.types.Viaje;
import org.w3c.dom.ls.LSOutput;

import java.util.List;


public class ListadoViajesView {

    private final List<Viaje> viajes;
//    private final Reserva reserva;

    private static final int ANCHO_TABLA = 100;
    private static final int ANCHO_TABLA_RESERVA = 70;

    public ListadoViajesView(List<Viaje> viajes) {
        this.viajes = viajes;
//        this.reserva = null;
    }

//    public ListadoViajesView(Reserva reserva) {
//        this.viajes = null;
//        this.reserva = reserva;
//    }

    private AsciiTable buildASCIITable()  {
        
        AsciiTable view = new AsciiTable();
        view.addRule();
        view.addRow("*", "*", "*", "*", "*", "*", "*", "*");
        view.addRule();
        view.addRow(null, null, null, null, null, null, null, "Listado Viajes");
        view.addRule();
        view.addRow("Cod. Viaje", null, "Ruta", "Precio", "Propietario", "Tipo", "Plazas Disponibles", "Cancelado");
        view.addRule();
        generarFilasViajes(view);
        view.setTextAlignment(TextAlignment.CENTER);
        return view;
    }

    private AsciiTable buildASCIITable2(Reserva reserva)  {

        AsciiTable view = new AsciiTable();
        view.addRule();
        view.addRow("*", "*");
        view.addRule();
        view.addRow(null, "Reserva con código " + reserva.getId());
        view.addRule();
        view.addRow("Usuario", reserva.getCliente());
        view.addRule();
        view.addRow("Plazas", reserva.getNumPlazasSolicitadas());
        view.addRule();

        view.setTextAlignment(TextAlignment.CENTER);
        return view;
    }

    @Override
    public String toString() {
        return buildASCIITable().render(ANCHO_TABLA);
    }

    public void visualizar() {
        System.out.println(buildASCIITable().render(ANCHO_TABLA));
    }

//    public void visualizarReserva() {
//        System.out.println(buildASCIITable2(this.reserva).render(ANCHO_TABLA_RESERVA));
//    }

    private void generarFilasViajes (AsciiTable tabla){
        
        // Implementa este método usando un bucle que itere sobre la lista de viajes y mostrando uno por fila.
        for (Viaje v : viajes) {
            tabla.addRow(v.getId(), null, v.getRuta(), v.getPrecio(), v.getPropietario().getUsuario(), v.getTipoViaje(), v.getPlazasLibres(), v.isCanceladoString());
            tabla.addRule();
        }


    }
}
