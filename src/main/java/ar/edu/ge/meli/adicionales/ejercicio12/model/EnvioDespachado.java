package ar.edu.ge.meli.adicionales.ejercicio12.model;

import java.time.LocalDateTime;

public class EnvioDespachado extends Envio {
    private final Integer costo;
    private final String vehiculo;
    private final LocalDateTime fechaDespacho;

    public EnvioDespachado(EnvioADespachar envioADespachar, Integer costo, String vehiculo, LocalDateTime fechaDespacho) {
        super(envioADespachar.paquetes, envioADespachar.direccionDeEntrega);
        this.costo = costo;
        this.vehiculo = vehiculo;
        this.fechaDespacho = fechaDespacho;
    }

    public Integer costo() {
        return costo;
    }

    public String vehiculo() {
        return vehiculo;
    }

    public LocalDateTime fechaDespacho() {return fechaDespacho;}

    public Double peso() {
        return paquetes.stream().mapToDouble(Paquete::peso).sum();
    }
}
