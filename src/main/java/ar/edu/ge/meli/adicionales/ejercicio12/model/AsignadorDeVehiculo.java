package ar.edu.ge.meli.adicionales.ejercicio12.model;

import ar.edu.ge.meli.adicionales.ejercicio12.exceptions.PesoExcedidoExcepcion;

import static ar.edu.ge.meli.adicionales.ejercicio12.constants.Constantes.*;

public class AsignadorDeVehiculo {

    public String asignarVehiculoPara(EnvioADespachar envio) {
        Double peso = envio.peso();
        if (peso > 150)
            throw new PesoExcedidoExcepcion("El peso del envío supera el máximo permitido.", peso);
        if (peso > 50) return CAMIONETA;
        if (peso > 5) return AUTO;
        if (peso > 0.5) return BICICLETA;
        return envio.cantidadDePaquetes() > 1 ? BICICLETA : DRON;
    }
}
