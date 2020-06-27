package org.psawesome.payserver.domain.sprinkle.handler;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.psawesome.payserver.domain.sprinkle.entity.PaySprinkle;
import org.psawesome.payserver.domain.token.entity.TokenNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.psawesome.payserver.domain.common.PayUtils.X_ROOM_ID;
import static org.psawesome.payserver.domain.common.PayUtils.X_USER_ID;

/**
 * author: ps [https://github.com/wiv33/pay-manager]
 * DATE: 20. 6. 27. Saturday
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SprinkleHandlerTest {

  WebTestClient testClient;

  @Autowired
  ExchangeFilterFunction exchangeFilterFunction;

  @BeforeEach
  void setUp() {
    testClient = WebTestClient
            .bindToServer()
            .filter(exchangeFilterFunction)
            .baseUrl("http://localhost:8080")
            .build();
  }

  @Order(1)
  @Test
  @DisplayName("토큰 생성 테스트")
  void testSprinkleCreate() {
    testClient.get()
            .uri("/sprinkle/{price}/{divide}", 4000, 3)
            .header(X_USER_ID, "1")
            .header(X_ROOM_ID, "flux")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk()
            .expectHeader().exists("X-USER-ID")
            .expectHeader().exists("X-ROOM-ID")
            .expectBody(PaySprinkle.class)
            .consumeWith(entityExChange -> assertAll(
                    () -> assertEquals(HttpMethod.GET, entityExChange.getMethod()),
                    () -> assertTrue(entityExChange.getResponseHeaders().containsValue(List.of("flux"))),
                    () -> assertTrue(entityExChange.getResponseHeaders().containsValue(List.of("1"))),
                    () -> assertAll(() -> {
                              PaySprinkle responseBody = entityExChange.getResponseBody();
                              assertNotNull(responseBody);
                              assertNotNull(responseBody.getTokenId());
                              assertNotNull(responseBody.getSprinkleUser());
                              assertNotNull(responseBody.getSprinklePrice());
                              assertEquals(3, responseBody.getDivide());
                            }
                    )
            ))
    ;
  }

  @Order(2)
  @ParameterizedTest
  @DisplayName("토큰의 수만큼 TOKEN_NODE 있는지 테스트")
  @ValueSource(ints = {3, 4, 5, 6, 7})
  void testExistToken(int divide) {
    testClient.get()
            .uri("/sprinkle/{price}/{divide}", 7000, divide)
            .header(X_USER_ID, "1")
            .header(X_ROOM_ID, "Mono")
            .exchange()
            .expectStatus().isOk()
            .expectBody(PaySprinkle.class)
            .consumeWith(entity -> {
              PaySprinkle response = entity.getResponseBody();

              testClient.get()
                      .uri("/token/node/{tokenId}", Objects.requireNonNull(response).getTokenId())
                      .header(X_ROOM_ID, response.getRoomName())
                      .header(X_USER_ID, response.getSprinkleUser().toString())
                      .accept(MediaType.APPLICATION_JSON)
                      .exchange()
                      .expectBody(TokenNode.class)
                      .consumeWith(tokenNodeEntityExchangeResult -> {
                        assertNotNull(tokenNodeEntityExchangeResult.getResponseBody());
                      })
              ;
            });
  }
}