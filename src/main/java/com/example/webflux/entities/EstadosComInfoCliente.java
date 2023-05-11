package com.example.webflux.entities;


public class EstadosComInfoCliente {
    private Cliente cliente;
    private Estados estados;

    public Estados getEstados() {
        return estados;
    }

    public void setEstados(Estados estados) {
        this.estados = estados;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "EstadosComInfoCliente{" +
                "cliente=" + cliente +
                ", estados=" + estados +
                '}';
    }
}
