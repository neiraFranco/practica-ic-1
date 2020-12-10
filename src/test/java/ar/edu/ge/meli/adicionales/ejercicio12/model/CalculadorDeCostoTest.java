package ar.edu.ge.meli.adicionales.ejercicio12.model;

import ar.edu.ge.meli.adicionales.ejercicio12.exceptions.PaquetesFaltantesExcepcion;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class CalculadorDeCostoTest {

    private final CalculadorDeCosto calculadorDeCosto = new CalculadorDeCosto();
    private EnvioADespachar envio;
    private Integer costoTotal;

    @Test
    public void unEnvioConUnPaqueteTieneCostoCincuenta() {
        dadoUnEnvioConCantidadDePaquetes(1);
        cuandoSeCalculaElCostoTotal();
        entoncesElCostoEs(50);
    }

    @Test
    public void unEnvioConCuatroPaquetesTieneCostoCincuenta() {
        dadoUnEnvioConCantidadDePaquetes(4);
        cuandoSeCalculaElCostoTotal();
        entoncesElCostoEs(50);
    }

    @Test
    public void unEnvioConCincoPaquetesTieneCostoOchenta() {
        dadoUnEnvioConCantidadDePaquetes(5);
        cuandoSeCalculaElCostoTotal();
        entoncesElCostoEs(80);
    }

    @Test
    public void unEnvioConDiezPaquetesTieneCostoOchenta() {
        dadoUnEnvioConCantidadDePaquetes(10);
        cuandoSeCalculaElCostoTotal();
        entoncesElCostoEs(80);
    }

    @Test
    public void unEnvioConOncePaquetesTieneCostoNoventaYCinco() {
        dadoUnEnvioConCantidadDePaquetes(11);
        cuandoSeCalculaElCostoTotal();
        entoncesElCostoEs(95);
    }

    @Test
    public void unEnvioConVeintePaquetesTieneCostoDoscientosTreinta() {
        dadoUnEnvioConCantidadDePaquetes(20);
        cuandoSeCalculaElCostoTotal();
        entoncesElCostoEs(230);
    }

    @Test
    public void noDebePermitirCalcularCostoSinPaquetes() {
        dadoUnEnvioConCantidadDePaquetes(0);

        try {
            cuandoSeCalculaElCostoTotal();
            fail("Debe lanzar excepción al intentar calcular costo sin paquetes.");
        } catch (PaquetesFaltantesExcepcion excepcion) {
            laCantidadDePaquetesRecibidosEs(0, excepcion.cantidadDePaquetes());
        }
    }

    private void dadoUnEnvioConCantidadDePaquetes(Integer cantidad) {
        List<Paquete> paquetes = grupoDePaquetesConCantidad(cantidad);
        envio = new EnvioADespachar(paquetes, "Av.Cordoba 3000");
    }

    private void cuandoSeCalculaElCostoTotal() {
        costoTotal = calculadorDeCosto.calcularCostoPara(envio);
    }

    private void entoncesElCostoEs(Integer costoEsperado) {
        assertEquals(costoEsperado, costoTotal);
    }

    private void laCantidadDePaquetesRecibidosEs(Integer cantidadDePaquetesEsperada, Integer cantidadDePaquetesRecibidos) {
        assertEquals(cantidadDePaquetesEsperada, cantidadDePaquetesRecibidos);
    }

    private List<Paquete> grupoDePaquetesConCantidad(Integer cantidad) {
        List<Paquete> paquetes = new ArrayList<>();
        while (cantidad > 0) {
            paquetes.add(new Paquete(1.0));
            cantidad--;
        }
        return paquetes;
    }
}
