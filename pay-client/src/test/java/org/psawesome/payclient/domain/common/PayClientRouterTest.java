package org.psawesome.payclient.domain.common;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.psawesome.payclient.domain.receive.handler.ReceiveHandler;
import org.psawesome.payclient.domain.room.dto.req.JoinRequest;
import org.psawesome.payclient.domain.room.dto.res.JoinResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.server.RouterFunction;

import java.time.Duration;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

//import org.psawesome.payclient.config.RSocketRequesterConfig;
//import org.psawesome.payclient.domain.room.handler.RoomHandler;

@WebFluxTest(controllers = {
//        RSocketRequesterConfig.class,
        PayClientRouter.class,
        ReceiveHandler.class,
//        RoomHandler.class
}
)
class PayClientRouterTest {

  @Autowired
  WebTestClient testClient;

  @Autowired
  RouterFunction<?> routerFunction;

  @Autowired
  ExchangeFilterFunction filterFunction;


  @BeforeEach
  void setUp() {
//    testClient = WebTestClient.bindToRouterFunction(payClientRouter.routerFunction())
//            .build();

  }

  public static final String X_ROOM_ID = "X-ROOM-ID";
  public static final String X_USER_ID = "X-USER-ID";

  @ParameterizedTest
  @MethodSource("makeJoinRoom")
  @DisplayName("1. Room 요청 -> 응답 Header에 roomId, userId 추가")
  void testRoomGetClient(JoinRequest request) {
    testClient.get()
            .uri("/room/{roomId}/{userId}", request.getRoomId(), request.getUserId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .returnResult(JoinResponse.class)
            .consumeWith(result -> assertAll(
                    () -> {
                      assertEquals(HttpStatus.OK, result.getStatus());
                      assertTrue(result.getResponseHeaders().containsKey(X_ROOM_ID));
                      assertTrue(result.getResponseHeaders().containsKey(X_USER_ID));
                      JoinResponse joinResponse = result.getResponseBody().blockFirst(Duration.ofSeconds(5));
                      assertNotNull(joinResponse);
                      assertEquals(request.getUserId(), joinResponse.getUserId());
                      assertEquals(request.getRoomId(), joinResponse.getRoomId());

//                      assertTrue(false); // is success?
                    }
            ));

  }

  private static Stream<JoinRequest> makeJoinRoom() {
    return Stream.iterate(0, (i) -> i + 1)
            .map(integer -> new JoinRequest(UUID.randomUUID().toString(), String.valueOf(integer + 1)))
            .limit(10);
  }
}