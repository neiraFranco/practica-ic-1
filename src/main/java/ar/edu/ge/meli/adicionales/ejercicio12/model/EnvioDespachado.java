package ar.edu.ge.meli.adicionales.ejercicio12.model;

public class EnvioDespachado extends Envio {
    private final Integer costo;
    private final String vehiculo;

    public EnvioDespachado(EnvioADespachar envioADespachar, Integer costo, String vehiculo) {
        super(envioADespachar.paquetes, envioADespachar.direccionDeEntrega);
        this.costo = costo;
        this.vehiculo = vehiculo;
    }

    public Integer costo() {
        return costo;
    }

    public String vehiculo() {
        return vehiculo;
    }
}
