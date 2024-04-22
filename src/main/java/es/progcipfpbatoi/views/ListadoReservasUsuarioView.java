package es.progcipfpbatoi.views;

import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import es.progcipfpbatoi.model.entidades.Reserva;
import es.progcipfpbatoi.model.entidades.types.Viaje;

import java.util.List;

public class ListadoReservasUsuarioView {

    private static final int ANCHO_TABLA = 100;
    private final List<Reserva> reservas;

    public ListadoReservasUsuarioView(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    private AsciiTable buildASCIITable()  {

        AsciiTable view = new AsciiTable();
        view.addRule();
        view.addRow("*", "*", "*", "*", "*", "*");
        view.addRule();
        view.addRow(null, null, null, null, null, "Reservas de viajes");
        view.addRule();
        view.addRow("Cod.Reserva", "Cod.Viaje", "Propietario Viaje", null, "Plazas Reservadas", "Fecha");
        view.addRule();
        generarFilasReservas(view);
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

    private void generarFilasReservas (AsciiTable tabla){
        // Implementa este método usando un bucle que itere sobre la lista de viajes y mostrando uno por fila.
        for (Reserva r : reservas) {
            tabla.addRow(r.getId(), r.getViaje().getId(), r.getViaje().getPropietario().getUsername(), null, r.getNumPlazasSolicitadas(), r.getStringFecha());
            tabla.addRule();
        }
    }
}
