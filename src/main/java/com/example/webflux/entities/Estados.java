package com.example.webflux.entities;

import java.util.List;

public class Estados {
    List<Estado> estados;

    public Estados(List<Estado> estados) {
        this.estados = estados;
    }

    public Estados() {}

    public List<Estado> getEstados() {
        return estados;
    }

    public void setEstados(List<Estado> estados) {
        this.estados = estados;
    }

    @Override
    public String toString() {
        return "Estados{" +
                "estados=" + estados +
                '}';
    }
}
