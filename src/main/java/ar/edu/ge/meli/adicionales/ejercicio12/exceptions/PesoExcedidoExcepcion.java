package ar.edu.ge.meli.adicionales.ejercicio12.exceptions;

public class PesoExcedidoExcepcion extends RuntimeException {
    private final Double peso;

    public PesoExcedidoExcepcion(String mensaje, Double peso) {
        super(mensaje);
        this.peso = peso;
    }

    public Double peso() {
        return peso;
    }
}
