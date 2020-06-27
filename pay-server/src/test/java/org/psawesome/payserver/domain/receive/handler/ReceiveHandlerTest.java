package org.psawesome.payserver.domain.receive.handler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.psawesome.payserver.domain.common.PayCommonTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.*;

/**
 * author: ps [https://github.com/wiv33/pay-manager]
 * DATE: 20. 6. 27. Saturday
 */
class ReceiveHandlerTest extends PayCommonTest {


  @Test
  void testInit() {
    assertNotNull(super.testClient);
  }

  @Test
  void testReceive() {

  }
}