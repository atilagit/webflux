package com.example.webflux.controllers;

import com.example.webflux.entities.Estado;
import com.example.webflux.entities.Estados;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

    @GetMapping(value = "/estados")
    public ResponseEntity<Estados> getEstados() {
        var estados = List.of(new Estado("SP"), new Estado("BA"), new Estado("MG"));
        return ResponseEntity.ok(new Estados(estados));
    }
}
