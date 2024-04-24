package es.progcipfpbatoi.views;

import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import es.progcipfpbatoi.model.entidades.Reserva;

public class ExceptionView {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    private static final int ANCHO_TABLA = 60;
    private String mensaje;

    public ExceptionView(String mensaje) {
        this.mensaje = mensaje;
    }

    private AsciiTable buildASCIITable()  {

        AsciiTable view = new AsciiTable();
        view.addRule();
        view.addRow(mensaje);
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
