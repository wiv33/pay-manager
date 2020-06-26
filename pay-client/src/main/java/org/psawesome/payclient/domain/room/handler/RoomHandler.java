/*
package org.psawesome.payclient.domain.room.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.psawesome.payclient.domain.room.dto.res.PayRoom;
import org.psawesome.payclient.domain.room.dto.req.JoinRequest;
import org.psawesome.payclient.domain.room.dto.res.JoinResponse;
import org.springframework.http.MediaType;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
@RequiredArgsConstructor
@Slf4j
public class RoomHandler {

  private final RSocketRequester requester;

  public Mono<ServerResponse> joinRoom(ServerRequest req) {
    String roomId = req.pathVariable("roomId");
    String userId = req.pathVariable("userId");
    log.info("pathVariable user: [{}], room: [{}]", userId, roomId);
    return ok()
            .header("X-ROOM-ID", roomId)
            .header("X-USER-ID", userId)
            .contentType(MediaType.APPLICATION_JSON)
            .body(this.requester.route("room.create")
                    .data(JoinRequest.builder()
                            .roomId(roomId)
                            .userId(userId)
                            .build(),
                            JoinRequest.class)
                    .retrieveMono(PayRoom.class),
                    JoinResponse.class)
            .log("joinRoom --> ")
            .doOnError(throwable -> {
              log.info(throwable.getMessage());
              ServerResponse.notFound();
            });
  }

}
*/
