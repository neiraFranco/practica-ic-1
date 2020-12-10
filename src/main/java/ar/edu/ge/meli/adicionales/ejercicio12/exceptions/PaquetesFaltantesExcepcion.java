package ar.edu.ge.meli.adicionales.ejercicio12.exceptions;

public class PaquetesFaltantesExcepcion extends RuntimeException {

    private final Integer cantidadDePaquetes;

    public PaquetesFaltantesExcepcion(String mensaje, Integer cantidadDePaquetes) {
        super(mensaje);
        this.cantidadDePaquetes = cantidadDePaquetes;
    }

    public Integer cantidadDePaquetes() {
        return cantidadDePaquetes;
    }
}
