package com.example.webflux.entities;

import java.util.List;

public class Estados {
    public Estados(List<Estado> estados) {
        this.estados = estados;
    }

    public Estados() {}

    List<Estado> estados;

    public List<Estado> getEstados() {
        return estados;
    }

    public void setEstados(List<Estado> estados) {
        this.estados = estados;
    }
}
