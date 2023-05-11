package com.example.webflux.services;

import com.example.webflux.entities.Estados;
import com.example.webflux.repositories.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EstadosService {

    @Autowired
    Repository repository;

    public Estados getEstados() {
        var estadosComInfoCliente = repository.getEstadosComInfoCliente();
        System.out.println(estadosComInfoCliente);
        return new Estados();
    }
}
