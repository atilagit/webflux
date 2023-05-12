package com.example.webflux.services;

import com.example.webflux.entities.EstadosComInfoCliente;
import com.example.webflux.repositories.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EstadosService {

    @Autowired
    Repository repository;

    public EstadosComInfoCliente getEstados() {
        return repository.getEstadosComInfoCliente();
    }
}
