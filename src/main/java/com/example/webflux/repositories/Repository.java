package com.example.webflux.repositories;

import com.example.webflux.entities.EstadosComInfoCliente;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class Repository {

    WebClient webClient = WebClient.builder()
            .baseUrl("http://localhost:8081")
            //.baseUrl("http://demo6747767.mockable.io")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();

    public EstadosComInfoCliente getEstadosComInfoCliente() {
        Mono<EstadosComInfoCliente> monoCliente = webClient
                .method(HttpMethod.GET)
                .uri("/info-cliente")
                .retrieve()
                .bodyToMono(EstadosComInfoCliente.class);

        Mono<EstadosComInfoCliente> monoEstados = webClient
                .method(HttpMethod.GET)
                .uri("/estados")
                .retrieve()
                .bodyToMono(EstadosComInfoCliente.class);

        return Mono.zip(monoCliente, monoEstados).map(tuple -> {
            tuple.getT2().setEstadoCliente(tuple.getT1().getEstadoCliente());
            return tuple.getT2();
        }).block();
    }
}
