package com.example.webflux.services;

import com.example.webflux.entities.Estado;
import com.example.webflux.entities.Estados;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadosService {
    public Estados getEstados() {
        var estados = List.of(new Estado("SP"), new Estado("BA"), new Estado("MG"), new Estado("AC"));
        return new Estados(estados);
    }
}
