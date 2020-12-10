package ar.edu.ge.meli.adicionales.ejercicio12.model;

import java.util.List;

public abstract class Envio {
    protected List<Paquete> paquetes;
    protected String direccionDeEntrega;

    public Envio(List<Paquete> paquetes, String direccionDeEntrega) {
        this.paquetes = paquetes;
        this.direccionDeEntrega = direccionDeEntrega;
    }
}
