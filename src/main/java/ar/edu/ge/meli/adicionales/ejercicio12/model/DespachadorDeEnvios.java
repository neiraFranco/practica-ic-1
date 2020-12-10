package ar.edu.ge.meli.adicionales.ejercicio12.model;

import ar.edu.ge.meli.adicionales.ejercicio12.exceptions.PaquetesFaltantesExcepcion;

import java.util.ArrayList;
import java.util.List;

public class DespachadorDeEnvios {
    private final CalculadorDeCosto calculadorDeCosto;
    private final AsignadorDeVehiculo asignadorDeVehiculo;
    private List<EnvioDespachado> enviosDespachados;

    public DespachadorDeEnvios(CalculadorDeCosto calculadorDeCosto, AsignadorDeVehiculo asignadorDeVehiculo) {
        this.calculadorDeCosto = calculadorDeCosto;
        this.asignadorDeVehiculo = asignadorDeVehiculo;
        enviosDespachados = new ArrayList<>();
    }

    public EnvioDespachado despachar(List<Paquete> paquetes, String direccionDeEntrega) {
        if (paquetes.size() == 0)
            throw new PaquetesFaltantesExcepcion("El grupo de paquetes recibido está vacío.", 0);
        EnvioADespachar envioADespachar = new EnvioADespachar(paquetes, direccionDeEntrega);
        Integer costo = calculadorDeCosto.calcularCostoPara(envioADespachar);
        String vehiculo = asignadorDeVehiculo.asignarVehiculoPara(envioADespachar);
        return envioDespachado(envioADespachar, costoConValoresAdministrativos(costo), vehiculo);
    }

    private Integer costoConValoresAdministrativos(Integer costo) {
        return enviosDespachados.size() >= 10 ? costo + costo * 10 / 100 : costo;
    }

    private EnvioDespachado envioDespachado(EnvioADespachar envioADespachar, Integer costo, String vehiculo) {
        EnvioDespachado envioDespachado = new EnvioDespachado(envioADespachar, costo, vehiculo);
        enviosDespachados.add(envioDespachado);
        return envioDespachado;
    }

    public Integer cantidadDeEnviosDespachados() {
        return enviosDespachados.size();
    }
}
