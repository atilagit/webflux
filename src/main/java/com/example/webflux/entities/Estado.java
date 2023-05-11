package com.example.webflux.entities;

public class Estado {
    private String id;

    private String uf;

    public Estado(String uf) {
        this.uf = uf;
    }
    public Estado() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    @Override
    public String toString() {
        return "Estado{" +
                "id='" + id + '\'' +
                ", uf='" + uf + '\'' +
                '}';
    }
}
