package org.psawesome.payserver.domain.receive.handler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.psawesome.payserver.domain.common.PayCommonTest;
import org.psawesome.payserver.domain.receive.dto.res.TokenNode;
import org.psawesome.payserver.domain.receive.repo.ReceiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.*;
import static org.psawesome.payserver.domain.common.PayUtils.X_ROOM_ID;
import static org.psawesome.payserver.domain.common.PayUtils.X_USER_ID;

/**
 * author: ps [https://github.com/wiv33/pay-manager]
 * DATE: 20. 6. 27. Saturday
 */
class ReceiveHandlerTest extends PayCommonTest {

  @Autowired
  ReceiveRepository receiveRepository;

  @Test
  void testInit() {
    assertNotNull(super.testClient);
    assertNotNull(receiveRepository);
  }

  @Test
  @DisplayName("자신 방에 있는 뿌리기를 아무거나 찾아서 receive")
  void testFindFirstTokenReceive() {
    testClient.get()
            .uri("/receive/{token}", "MeA")
            .header(X_USER_ID, "2")
            .header(X_ROOM_ID, "Mono")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectBody(TokenNode.class)
            .consumeWith(tokenNodeEntityExchangeResult -> {
              System.out.println(tokenNodeEntityExchangeResult.getStatus().toString());
            })
    ;
  }
}