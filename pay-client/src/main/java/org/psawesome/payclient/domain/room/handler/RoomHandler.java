package org.psawesome.payclient.domain.room.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.psawesome.payclient.domain.room.dto.res.JoinResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
@RequiredArgsConstructor
@Slf4j
public class RoomHandler {


  public Mono<ServerResponse> joinRoom(ServerRequest req) {
    String roomId = req.pathVariable("roomId");
    String userId = req.pathVariable("userId");
    log.info("pathVariable user: [{}], room: [{}]", userId, roomId);
    return ok()
            .header("X-ROOM-ID", roomId)
            .header("X-USER-ID", userId)
            .contentType(MediaType.APPLICATION_JSON)
            .body(Mono.just(new JoinResponse(roomId, userId)), JoinResponse.class)
            .log("joinRoom --> ")
            .doOnError(throwable -> {
              log.info(throwable.getMessage());
              ServerResponse.notFound();
            });
  }
}
