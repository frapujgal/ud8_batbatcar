package es.progcipfpbatoi.views;

import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import es.progcipfpbatoi.model.entidades.Reserva;
import es.progcipfpbatoi.model.entidades.types.Viaje;

public class ReservaView {

    private static final int ANCHO_TABLA = 70;
    private final Reserva reserva;

    public ReservaView(Reserva reserva) {
        this.reserva = reserva;
    }

    private AsciiTable buildASCIITable()  {

        AsciiTable view = new AsciiTable();
        view.addRule();
        view.addRow("*", "*");
        view.addRule();
        view.addRow(null, "Reserva con código " + reserva.getId());
        view.addRule();
        view.addRow("Usuario", reserva.getCliente().getUsername());
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

}
