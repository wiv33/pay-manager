package org.psawesome.payserver.domain.sprinkle.handler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.psawesome.payserver.domain.sprinkle.entity.PaySprinkle;
import org.psawesome.payserver.domain.sprinkle.repo.SprinkleRepository;
import org.psawesome.payserver.domain.token.entity.TokenNode;
import org.psawesome.payserver.domain.token.repo.TokenNodeRepository;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.*;

/**
 * author: ps [https://github.com/wiv33/pay-manager]
 * DATE: 20. 6. 27. Saturday
 */
@WebFluxTest(controllers = {
        PaySprinkle.class,
        TokenNode.class,
        SprinkleHandler.class
})
class SprinkleHandlerTest {

  WebTestClient testClient;

  @BeforeEach
  void setUp() {
    testClient = WebTestClient
            .bindToServer()
            .baseUrl("http://localhost:8080/sprinkle")
            .build();
  }

  @Test
  void testSprinkleCreate() {
    testClient.get()
            .uri("/{price}/{divide}", 3000, 3)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectHeader().exists("X-USER-ID")
            .expectHeader().exists("X-ROOM-ID")
            .expectBody(PaySprinkle.class)
            .consumeWith(entityExChange -> assertAll(
                    () -> assertNotNull(entityExChange.getResponseBody()))
            );
  }

}