package ar.edu.ge.meli.adicionales.ejercicio12.model;

import ar.edu.ge.meli.adicionales.ejercicio12.exceptions.PaquetesFaltantesExcepcion;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class DespachadorDeEnviosMockingTest {

    private final AsignadorDeVehiculo asignadorDeVehiculo = mock(AsignadorDeVehiculo.class);
    private final CalculadorDeCosto calculadorDeCosto = mock(CalculadorDeCosto.class);
    private final DespachadorDeEnvios despachadorDeEnvios = new DespachadorDeEnvios(calculadorDeCosto, asignadorDeVehiculo);
    private EnvioDespachado envio;
    private List<Paquete> paquetes;
    private String direccionDeEntrega;

    @Test
    public void unEnvioEsRealizadoPorElDespachador() {
        dadosUnGrupoDePaquetesYUnaDireccionDeEntregaParaEnvio();
        cuandoSeDespachaUnTotalDeEnviosIgualA(1);
        entoncesSeAsignoCostoUnNumeroDeVecesIgualA(1);
        entoncesElVehiculoAsignadoEs("BICICLETA");
        entoncesSeAsignoVehiculoUnNumeroDeVecesIgualA(1);
        entoncesElTotalDeEnviosDespachadosEs(1);
        entoncesElCostoDelUltimoEnvioEs(50); // Envio con 1 paquete: costo base $50
    }

    @Test
    public void diezEnviosSonRealizadosPorElDespachador() {
        dadosUnGrupoDePaquetesYUnaDireccionDeEntregaParaEnvio();
        cuandoSeDespachaUnTotalDeEnviosIgualA(10);
        entoncesSeAsignoCostoUnNumeroDeVecesIgualA(10);
        entoncesElVehiculoAsignadoEs("BICICLETA");
        entoncesSeAsignoVehiculoUnNumeroDeVecesIgualA(10);
        entoncesElTotalDeEnviosDespachadosEs(10);
        entoncesElCostoDelUltimoEnvioEs(50);
    }

    @Test
    public void onceEnviosSonRealizadosPorElDespachadorYElUltimoTieneUnCostoExtra() {
        dadosUnGrupoDePaquetesYUnaDireccionDeEntregaParaEnvio();
        cuandoSeDespachaUnTotalDeEnviosIgualA(11);
        entoncesSeAsignoCostoUnNumeroDeVecesIgualA(11);
        entoncesElVehiculoAsignadoEs("BICICLETA");
        entoncesSeAsignoVehiculoUnNumeroDeVecesIgualA(11);
        entoncesElTotalDeEnviosDespachadosEs(11);
        entoncesElCostoDelUltimoEnvioEs(55); // Se agrega 10% del costo base
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
            entoncesSeAsignoCostoUnNumeroDeVecesIgualA(0);
            entoncesSeAsignoVehiculoUnNumeroDeVecesIgualA(0);
        }
    }

    private void dadosUnGrupoDePaquetesYUnaDireccionDeEntregaParaEnvio() {
        paquetes = Collections.singletonList(new Paquete(1.0));
        direccionDeEntrega = "Av.Cordoba 3000";
    }

    private void dadosUnGrupoDePaquetesVacioYUnaDireccionDeEntregaParaEnvio() {
        paquetes = new ArrayList<>();
        direccionDeEntrega = "Av.Cordoba 3000";
    }

    private void cuandoSeDespachaUnTotalDeEnviosIgualA(Integer cantidad) {
        when(calculadorDeCosto.calcularCostoPara(any(EnvioADespachar.class))).thenReturn(50);
        when(asignadorDeVehiculo.asignarVehiculoPara(any(EnvioADespachar.class))).thenReturn("BICICLETA");
        while (cantidad > 0) {
            envio = despachadorDeEnvios.despachar(paquetes, direccionDeEntrega);
            cantidad--;
        }
    }

    private void entoncesElVehiculoAsignadoEs(String vehiculoEsperado) {
        assertEquals(vehiculoEsperado, envio.vehiculo());
    }

    private void entoncesSeAsignoCostoUnNumeroDeVecesIgualA(Integer cantidadEsperada) {
        verify(calculadorDeCosto, times(cantidadEsperada)).calcularCostoPara(any(EnvioADespachar.class));
    }

    private void entoncesSeAsignoVehiculoUnNumeroDeVecesIgualA(Integer cantidadEsperada) {
        verify(asignadorDeVehiculo, times(cantidadEsperada)).asignarVehiculoPara(any(EnvioADespachar.class));
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
