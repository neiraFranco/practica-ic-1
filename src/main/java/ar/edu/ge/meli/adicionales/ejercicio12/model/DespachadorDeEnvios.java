package ar.edu.ge.meli.adicionales.ejercicio12.model;

import ar.edu.ge.meli.adicionales.ejercicio12.exceptions.PaquetesFaltantesExcepcion;

import java.util.ArrayList;
import java.util.List;

public class DespachadorDeEnvios {
    private final CalculadorDeCosto calculadorDeCosto;
    private final AsignadorDeVehiculo asignadorDeVehiculo;
    private Integer propina;
    private List<EnvioDespachado> enviosDespachados;

    public DespachadorDeEnvios(CalculadorDeCosto calculadorDeCosto, AsignadorDeVehiculo asignadorDeVehiculo) {
        this.calculadorDeCosto = calculadorDeCosto;
        this.asignadorDeVehiculo = asignadorDeVehiculo;
        this.propina = Integer.valueOf(System.getProperty("propina"));
        enviosDespachados = new ArrayList<>();
    }

    public EnvioDespachado despachar(List<Paquete> paquetes, String direccionDeEntrega) {
        if (paquetes.size() == 0)
            throw new PaquetesFaltantesExcepcion("El grupo de paquetes recibido está vacío.", 0);
        EnvioADespachar envioADespachar = new EnvioADespachar(paquetes, direccionDeEntrega);
        Integer costo = calculadorDeCosto.calcularCostoPara(envioADespachar);
        String vehiculo = asignadorDeVehiculo.asignarVehiculoPara(envioADespachar);
        return envioDespachado(envioADespachar, costoConValoresExtras(costo, vehiculo), vehiculo);
    }

    private Integer costoConValoresExtras(Integer costo, String vehiculo) {
        Integer costoAdministrativo = enviosDespachados.size() >= 10 ? costo * 10 / 100 : 0;
        Integer propinaASumar = "BICICLETA".equals(vehiculo) ? propina : 0;
        return costo + costoAdministrativo + propinaASumar;
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
