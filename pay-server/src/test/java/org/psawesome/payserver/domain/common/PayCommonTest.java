package org.psawesome.payserver.domain.common;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;

/**
 * author: ps [https://github.com/wiv33/pay-manager]
 * DATE: 20. 6. 27. Saturday
 */
@SpringBootTest
public class PayCommonTest {
  @Autowired
  ExchangeFilterFunction exchangeFilterFunction;

  public WebTestClient testClient;

  @BeforeEach
  void setUp() {
    testClient = WebTestClient
            .bindToServer()
            .filter(exchangeFilterFunction)
            .baseUrl("http://localhost:8080")
            .build();
  }
}
