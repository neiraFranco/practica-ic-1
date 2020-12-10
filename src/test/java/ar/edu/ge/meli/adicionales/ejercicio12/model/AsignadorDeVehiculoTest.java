package ar.edu.ge.meli.adicionales.ejercicio12.model;

import ar.edu.ge.meli.adicionales.ejercicio12.exceptions.PesoExcedidoExcepcion;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class AsignadorDeVehiculoTest {

    private final AsignadorDeVehiculo asignadorDeVehiculo = new AsignadorDeVehiculo();
    private EnvioADespachar envio;
    private String vehiculoAsignado;

    @Test
    public void unEnvioConUnPaqueteConPesoMedioKgTieneAsignadoDron() {
        dadoUnEnvioConPesoEnKg(0.5);
        conCantidadDePaquetes(1);
        cuandoSeEvaluaElPesoParaAsignarVehiculo();
        entoncesElVehiculoAsignadoEs("DRON");
    }

    @Test
    public void unEnvioConCincoPaquetesConPesoMedioKgTieneAsignadoBicicleta() {
        dadoUnEnvioConPesoEnKg(0.5);
        conCantidadDePaquetes(5);
        cuandoSeEvaluaElPesoParaAsignarVehiculo();
        entoncesElVehiculoAsignadoEs("BICICLETA");
    }

    @Test
    public void unEnvioConPeso5KgTieneAsignadoBicicleta() {
        dadoUnEnvioConPesoEnKg(5.0);
        cuandoSeEvaluaElPesoParaAsignarVehiculo();
        entoncesElVehiculoAsignadoEs("BICICLETA");
    }

    @Test
    public void unEnvioConPeso5KgYMedioTieneAsignadoAuto() {
        dadoUnEnvioConPesoEnKg(5.5);
        cuandoSeEvaluaElPesoParaAsignarVehiculo();
        entoncesElVehiculoAsignadoEs("AUTO");
    }

    @Test
    public void unEnvioConPeso50KgTieneAsignadoAuto() {
        dadoUnEnvioConPesoEnKg(50.0);
        cuandoSeEvaluaElPesoParaAsignarVehiculo();
        entoncesElVehiculoAsignadoEs("AUTO");
    }

    @Test
    public void unEnvioConPeso50KgYMedioTieneAsignadoCamioneta() {
        dadoUnEnvioConPesoEnKg(50.5);
        cuandoSeEvaluaElPesoParaAsignarVehiculo();
        entoncesElVehiculoAsignadoEs("CAMIONETA");
    }

    @Test
    public void unEnvioConPeso150KgTieneAsignadoCamioneta() {
        dadoUnEnvioConPesoEnKg(150.0);
        cuandoSeEvaluaElPesoParaAsignarVehiculo();
        entoncesElVehiculoAsignadoEs("CAMIONETA");
    }

    @Test
    public void noDebePermitirAsignarVehiculoConPesoExcedido() {
        dadoUnEnvioConPesoEnKg(150.5);

        try {
            cuandoSeEvaluaElPesoParaAsignarVehiculo();
            fail("Debe lanzar excepción al intentar asignar vehículo a envío con peso excedido.");
        } catch (PesoExcedidoExcepcion excepcion) {
            elPesoDelPedidoEsMayorAlMaximoPermitido(150, excepcion.peso());
        }
    }


    private void dadoUnEnvioConPesoEnKg(Double peso) {
        List<Paquete> paquetes = paquetesConPesoEnKg(peso);
        envio = new EnvioADespachar(paquetes, "Av.Cordoba 3000");
    }

    private void conCantidadDePaquetes(Integer cantidad) {
        Double pesoPorPaquete = envio.peso() / cantidad;
        envio.vaciar();
        while (envio.cantidadDePaquetes() < cantidad)
            envio.agregarPaquete(new Paquete(pesoPorPaquete));
    }

    private void cuandoSeEvaluaElPesoParaAsignarVehiculo() {
        vehiculoAsignado = asignadorDeVehiculo.asignarVehiculoPara(envio);
    }

    private void entoncesElVehiculoAsignadoEs(String vehiculoEsperado) {
        assertEquals(vehiculoEsperado, vehiculoAsignado);
    }

    private void elPesoDelPedidoEsMayorAlMaximoPermitido(Integer pesoMaximoPermitido, Double peso) {
        assertTrue(pesoMaximoPermitido < peso);
    }

    private List<Paquete> paquetesConPesoEnKg(Double peso) {
        return Collections.singletonList(new Paquete(peso));
    }
}
