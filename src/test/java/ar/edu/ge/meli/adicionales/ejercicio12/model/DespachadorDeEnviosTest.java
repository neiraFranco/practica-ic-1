package ar.edu.ge.meli.adicionales.ejercicio12.model;

import ar.edu.ge.meli.adicionales.ejercicio12.exceptions.PaquetesFaltantesExcepcion;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class DespachadorDeEnviosTest {

    private final AsignadorDeVehiculo asignadorDeVehiculo = new AsignadorDeVehiculo();
    private final CalculadorDeCosto calculadorDeCosto = new CalculadorDeCosto();
    private final DespachadorDeEnvios despachadorDeEnvios = new DespachadorDeEnvios(calculadorDeCosto, asignadorDeVehiculo);
    private EnvioDespachado envio;
    private List<Paquete> paquetes;
    private String direccionDeEntrega;

    @Test
    public void unEnvioEsRealizadoPorElDespachadorEnBicicleta() {
        dadosUnGrupoDePaquetesYUnaDireccionDeEntregaParaEnvioConPeso(4.0);
        cuandoSeDespachaUnTotalDeEnviosIgualA(1);
        entoncesElVehiculoAsignadoEs("BICICLETA");
        entoncesElTotalDeEnviosDespachadosEs(1);
        entoncesElCostoDelUltimoEnvioEs(60); // Envio con 1 paquete: costo base $50, CON propina
    }

    @Test
    public void unEnvioEsRealizadoPorElDespachadorEnAuto() {
        dadosUnGrupoDePaquetesYUnaDireccionDeEntregaParaEnvioConPeso(30.0);
        cuandoSeDespachaUnTotalDeEnviosIgualA(1);
        entoncesElVehiculoAsignadoEs("AUTO");
        entoncesElTotalDeEnviosDespachadosEs(1);
        entoncesElCostoDelUltimoEnvioEs(50); // Envio con 1 paquete: costo base $50, SIN propina
    }

    @Test
    public void diezEnviosSonRealizadosPorElDespachador() {
        dadosUnGrupoDePaquetesYUnaDireccionDeEntregaParaEnvioConPeso(4.0);
        cuandoSeDespachaUnTotalDeEnviosIgualA(10);
        entoncesElVehiculoAsignadoEs("BICICLETA");
        entoncesElTotalDeEnviosDespachadosEs(10);
        entoncesElCostoDelUltimoEnvioEs(60);
    }

    @Test
    public void onceEnviosSonRealizadosPorElDespachadorYElUltimoTieneUnCostoExtra() {
        dadosUnGrupoDePaquetesYUnaDireccionDeEntregaParaEnvioConPeso(4.0);
        cuandoSeDespachaUnTotalDeEnviosIgualA(11);
        entoncesElVehiculoAsignadoEs("BICICLETA");
        entoncesElTotalDeEnviosDespachadosEs(11);
        entoncesElCostoDelUltimoEnvioEs(65); // Se agrega 10% del costo base
    }

    @Test
    public void noDebePermitirDespacharEnvioSinPaquetes() {
        dadosUnGrupoDePaquetesVacioYUnaDireccionDeEntregaParaEnvio();

        try {
            cuandoSeDespachaUnTotalDeEnviosIgualA(1);
            fail("Debe lanzar excepción al intentar hacer un envío sin paquetes.");
        } catch (PaquetesFaltantesExcepcion excepcion) {
            laCantidadDePaquetesRecibidosEs(0, excepcion.cantidadDePaquetes());
            entoncesElTotalDeEnviosDespachadosEs(0);
        }
    }

    private void dadosUnGrupoDePaquetesYUnaDireccionDeEntregaParaEnvioConPeso(Double peso) {
        paquetes = Collections.singletonList(new Paquete(peso));
        direccionDeEntrega = "Av.Cordoba 3000";
    }

    private void dadosUnGrupoDePaquetesVacioYUnaDireccionDeEntregaParaEnvio() {
        paquetes = new ArrayList<>();
        direccionDeEntrega = "Av.Cordoba 3000";
    }

    private void cuandoSeDespachaUnTotalDeEnviosIgualA(Integer cantidad) {
        while (cantidad > 0) {
            envio = despachadorDeEnvios.despachar(paquetes, direccionDeEntrega);
            cantidad--;
        }
    }

    private void entoncesElVehiculoAsignadoEs(String vehiculoEsperado) {
        assertEquals(vehiculoEsperado, envio.vehiculo());
    }

    private void entoncesElTotalDeEnviosDespachadosEs(Integer cantidadDeEnviosEsperada) {
        assertEquals(cantidadDeEnviosEsperada, despachadorDeEnvios.cantidadDeEnviosDespachados());
    }

    private void entoncesElCostoDelUltimoEnvioEs(Integer costoEsperado) {
        assertEquals(costoEsperado, envio.costo());
    }

    private void laCantidadDePaquetesRecibidosEs(Integer cantidadDePaquetesEsperada, Integer cantidadDePaquetesRecibidos) {
        assertEquals(cantidadDePaquetesEsperada, cantidadDePaquetesRecibidos);
    }
}
