package com.example.webflux.repositories;

import com.example.webflux.entities.EstadosComInfoCliente;
import com.example.webflux.util.Property;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class Repository {

    Property property;

    public Repository(Property property) {
        this.property = property;
    }

    public EstadosComInfoCliente getEstadosComInfoCliente() {
        WebClient webClient = WebClient.builder()
                .baseUrl(property.getBaseUrl())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        Mono<EstadosComInfoCliente> monoCliente = webClient
                .method(HttpMethod.GET)
                .uri("/info-cliente")
                .retrieve()
                .bodyToMono(EstadosComInfoCliente.class)
                .timeout(Duration.ofMillis(property.getTimeOutConfig()))
                .onErrorResume(e -> Mono.empty())
                .defaultIfEmpty(new EstadosComInfoCliente());

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
