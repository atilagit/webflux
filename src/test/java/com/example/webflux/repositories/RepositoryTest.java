package com.example.webflux.repositories;

import com.example.webflux.entities.EstadosComInfoCliente;
import com.example.webflux.util.Property;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.mockito.Mockito.when;

public class RepositoryTest {

    private static MockWebServer server;
    private static Property property;

    Repository repository;

    @BeforeAll
    static void setup() throws IOException {
        server = new MockWebServer();
        server.start();
        property = Mockito.mock(Property.class);
        when(property.getBaseUrl()).thenReturn(String.format("http://localhost:%s", server.getPort()));
    }

    @BeforeEach
    public void initialize() {
        repository = new Repository(property);
    }

    @AfterAll
    static void teardown() throws IOException {
        server.shutdown();
    }

    @Test
    public void testGetEstadosComInfoCliente() {
        //ARRANGE
        Dispatcher dispatcher = new Dispatcher() {
            @Override
            public MockResponse dispatch (RecordedRequest request) {

                switch (request.getPath()) {
                    case "/info-cliente":
                        return getMockResponseSucesso().setBody("{\"estadoCliente\": \"SP\", \"estados\": []}");
                    case "/estados":
                        return getMockResponseSucesso().setBody("{\"estadoCliente\": \"\", \"estados\": []}");
                }
                return new MockResponse().setResponseCode(404);
            }
        };
        server.setDispatcher(dispatcher);
        when(property.getTimeOutConfig()).thenReturn(60000);

        //ACT
        EstadosComInfoCliente estadosComInfoCliente = repository.getEstadosComInfoCliente();

        //ASSERT
        Assertions.assertNotNull(estadosComInfoCliente);
        Assertions.assertNotNull(estadosComInfoCliente.getEstados());
        Assertions.assertNotNull(estadosComInfoCliente.getEstadoCliente());
        Assertions.assertEquals("SP", estadosComInfoCliente.getEstadoCliente());
    }

    @Test
    public void deveRetornarResultadoSemEstadoDoClienteQuandoAConsultaInfoClienteDemorar() {
        //ARRANGE
        Dispatcher dispatcher = new Dispatcher() {
            @Override
            public MockResponse dispatch (RecordedRequest request) {

                switch (request.getPath()) {
                    case "/info-cliente":
                        return getMockResponseSucesso().setBodyDelay(500, TimeUnit.MILLISECONDS).setBody("{\"estadoCliente\": \"SP\", \"estados\": []}");
                    case "/estados":
                        return getMockResponseSucesso().setBody("{\"estadoCliente\": \"\", \"estados\": []}");
                }
                return new MockResponse().setResponseCode(404);
            }
        };
        server.setDispatcher(dispatcher);
        when(property.getTimeOutConfig()).thenReturn(200);

        //ACT
        EstadosComInfoCliente estadosComInfoCliente = repository.getEstadosComInfoCliente();

        //ASSERT
        Assertions.assertNotNull(estadosComInfoCliente);
        Assertions.assertNotNull(estadosComInfoCliente.getEstados());
        Assertions.assertNull(estadosComInfoCliente.getEstadoCliente());
    }

    @NotNull
    private static MockResponse getMockResponseSucesso() {
        return new MockResponse().setResponseCode(200).setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    }
}
