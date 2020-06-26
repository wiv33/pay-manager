package org.psawesome.payserver.domain.sprinkle.entity;

import org.junit.jupiter.api.Test;
import org.psawesome.payserver.domain.sprinkle.repo.SprinkleRepository;
import org.psawesome.payserver.domain.token.repo.PayTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * author: ps [https://github.com/wiv33/pay-manager]
 * DATE: 20. 6. 25. Thursday
 */
@SpringBootTest
class PaySprinkleTest {

  @Autowired
  PayTokenRepository tokenRepository;

  @Autowired
  SprinkleRepository sprinkleRepository;

  WebTestClient testClient;

  @Test
  void testInit() {
    assertNotNull(tokenRepository);
    assertNotNull(sprinkleRepository);

    testClient = WebTestClient
            .bindToServer()
//            .defaultHeader("X-USER-ID", "1")
//            .defaultHeader("X-ROOM-ID", "flux")
            .baseUrl("http://localhost:8080/sprinkle")
            .build();
  }

  @Test
  void testSprinkleCreate() {
    testClient.get()
            .uri("/{price}/{divide}")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectHeader().exists("X-USER-ID")
            .expectHeader().exists("X-ROOM-ID")
            .expectBody()
  }

}