package ar.edu.ge.meli.adicionales.ejercicio12.model;

import ar.edu.ge.meli.adicionales.ejercicio12.exceptions.PaquetesFaltantesExcepcion;

public class CalculadorDeCosto {

    public Integer calcularCostoPara(EnvioADespachar envio) {
        int cantidad = envio.cantidadDePaquetes();
        if (cantidad == 0)
            throw new PaquetesFaltantesExcepcion("El grupo de paquetes recibido está vacío.", cantidad);
        if (cantidad > 10) return 80 + (cantidad - 10) * 15;
        return cantidad < 5 ? 50 : 80;
    }
}
