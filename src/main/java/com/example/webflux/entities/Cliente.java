package com.example.webflux.entities;

public class Cliente {
    private String estadoCliente;

    public String getEstadoCliente() {
        return estadoCliente;
    }

    public void setEstadoCliente(String estadoCliente) {
        this.estadoCliente = estadoCliente;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "estadoCliente='" + estadoCliente + '\'' +
                '}';
    }
}
