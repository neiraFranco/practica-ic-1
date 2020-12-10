package ar.edu.ge.meli.adicionales.ejercicio12.model;

import java.util.List;

public class EnvioADespachar extends Envio {

    public EnvioADespachar(List<Paquete> paquetes, String direccionDeEntrega) {
        super(paquetes, direccionDeEntrega);
    }

    public Integer cantidadDePaquetes() {
        return paquetes.size();
    }

    public Double peso() {
        return paquetes.stream().mapToDouble(Paquete::peso).sum();
    }

}
