package org.psawesome.payserver.domain.common;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.psawesome.payserver.domain.sprinkle.entity.PaySprinkle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.junit.jupiter.api.Assertions.*;

/**
 * author: ps [https://github.com/wiv33/pay-manager]
 * DATE: 20. 6. 28. Sunday
 */
class PayRouterFunctionTest extends PayCommonTest<PaySprinkle> {

  WebTestClient router;

  @BeforeEach
  void setUp(@Autowired RouterFunction<ServerResponse> routerFunction) {
    this.router = WebTestClient.bindToRouterFunction(routerFunction)
            .build();
  }

  @Test
  void testInit() {
    assertNotNull(super.testClient);
    assertNotNull(this.router);
  }

  @Test
  void testPaySprinkleFindAll() {
    router.get()
            .uri(SPRINKLE + "/retrieve/all");
//    testClient
//            .get()
  }
}