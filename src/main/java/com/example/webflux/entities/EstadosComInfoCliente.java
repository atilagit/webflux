package com.example.webflux.entities;


import java.util.List;

public class EstadosComInfoCliente {
    private String estadoCliente;
    private List<Estado> estados;

    public String getEstadoCliente() {
        return estadoCliente;
    }

    public void setEstadoCliente(String estadoCliente) {
        this.estadoCliente = estadoCliente;
    }

    public List<Estado> getEstados() {
        return estados;
    }

    public void setEstados(List<Estado> estados) {
        this.estados = estados;
    }

    @Override
    public String toString() {
        return "EstadosComInfoCliente{" +
                "estadoCliente='" + estadoCliente + '\'' +
                ", estados=" + estados +
                '}';
    }
}
