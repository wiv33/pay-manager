package org.psawesome.payserver.domain.common;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.psawesome.payserver.domain.receive.dto.res.PaySprinkle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;

import java.lang.reflect.Type;

/**
 * author: ps [https://github.com/wiv33/pay-manager]
 * DATE: 20. 6. 27. Saturday
 */
@SpringBootTest
public abstract class PayCommonTest<T> {

  public final String X_USER_ID = "X-USER-ID";
  public final String X_ROOM_ID = "X-ROOM-ID";

  // TODO 하위 Type 을 전달받고 baseUrl 을 만들 로직 정의

  public static final String SPRINKLE = "/sprinkle";
  public static final String RECEIVE = "/receive";
  public static final String TOKEN = "/token";
  public static final String TOKEN_NODE = "/token/node";
  public static final String USER = "/user";

  @Autowired
  ExchangeFilterFunction exchangeFilterFunction;

  public WebTestClient testClient;

  @BeforeAll
  static void beforeAll() {

  }

  @BeforeEach
  void setUp() {
    Type genericSuperclass = this.getClass().getGenericSuperclass();
    testClient = WebTestClient
            .bindToServer()
            .filter(exchangeFilterFunction)
            .baseUrl("http://localhost:8080")
            .build();
  }
}
