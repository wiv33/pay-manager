package org.psawesome.payserver.domain.sprinkle.handler;

import lombok.RequiredArgsConstructor;
import org.psawesome.payserver.domain.sprinkle.entity.PaySprinkle;
import org.psawesome.payserver.domain.sprinkle.repo.SprinkleRepository;
import org.psawesome.payserver.domain.token.entity.PayToken;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

import static org.psawesome.payserver.domain.common.PayUtils.X_ROOM_ID;
import static org.psawesome.payserver.domain.common.PayUtils.X_USER_ID;
import static org.springframework.util.NumberUtils.parseNumber;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

/**
 * author: ps [https://github.com/wiv33/pay-manager]
 * DATE: 20. 6. 25. Thursday
 */
@Component
@RequiredArgsConstructor
public class SprinkleHandler {

  private final SprinkleRepository sprinkleRepository;
  private final WebClient webClient;

  public Mono<ServerResponse> sprinklePay(ServerRequest request) {
    String xUserId = request.headers().firstHeader(X_USER_ID);
    String xRoomId = request.headers().firstHeader(X_ROOM_ID);
    return ok()
            .header(X_USER_ID, xUserId)
            .header(X_ROOM_ID, xRoomId)
            .body(generateToken(request, xUserId, xRoomId)
                            .map(PayToken::getId)
                            .flatMap(id -> saveSprinkle(request, xUserId, xRoomId, id)),
                    PaySprinkle.class)
            .log();
  }

  private Mono<PayToken> generateToken(ServerRequest request, String xUserId, String xRoomId) {
    return webClient.get()
            .uri("/token/{divide}", request.pathVariable("divide"))
            .header(X_USER_ID, xUserId)
            .header(X_ROOM_ID, xRoomId)
            .retrieve()
            .bodyToMono(PayToken.class)
            .log("generateToken --> ");
  }

  private Mono<PaySprinkle> saveSprinkle(ServerRequest request, String xUserId, String xRoomId, Integer id) {
    return this.sprinkleRepository.save(
            PaySprinkle.builder()
                    .roomName(xRoomId)
                    .tokenId(id)
                    .sprinkleUser(parseNumber(xUserId, Integer.class))
                    .sprinklePrice(parseNumber(request.pathVariable("price"), Integer.class))
                    .divide(parseNumber(request.pathVariable("divide"), Integer.class))
                    .startDate(LocalDateTime.now().toString())
                    .build());
  }

  public Mono<ServerResponse> retrieve(ServerRequest request) {
    return ok().body(
            this.sprinkleRepository.findById(parseNumber(request.pathVariable("tokenId"), Integer.class)),
            PaySprinkle.class)
            .log("SprinkleHandler.retrieve --> ");
  }
}
