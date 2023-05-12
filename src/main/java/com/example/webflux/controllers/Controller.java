package com.example.webflux.controllers;

import com.example.webflux.entities.EstadosComInfoCliente;
import com.example.webflux.services.EstadosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    EstadosService estadosService;

    @GetMapping(value = "/estados")
    public ResponseEntity<EstadosComInfoCliente> getEstados() {
        EstadosComInfoCliente estados = estadosService.getEstados();
        return ResponseEntity.ok(estados);
    }
}
