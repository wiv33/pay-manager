package org.psawesome.payserver.domain.receive.handler;

import lombok.RequiredArgsConstructor;
import org.psawesome.payserver.domain.receive.dto.req.NodeOneRequest;
import org.psawesome.payserver.domain.receive.dto.res.TokenNode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.psawesome.payserver.domain.common.PayUtils.X_ROOM_ID;
import static org.psawesome.payserver.domain.common.PayUtils.X_USER_ID;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

/**
 * author: ps [https://github.com/wiv33/pay-manager]
 * DATE: 20. 6. 26. Friday
 */
@Component
@RequiredArgsConstructor
public class ReceiveHandler {

  private final WebClient webClient;

  public Mono<ServerResponse> takePay(ServerRequest request) {
    return ok().body(webClient.post()
                    .uri("/token/node")
                    .header(X_USER_ID, request.headers().firstHeader(X_USER_ID))
                    .header(X_ROOM_ID, request.headers().firstHeader(X_ROOM_ID))
                    .bodyValue(NodeOneRequest.builder()
                            .roomId(request.headers().firstHeader(X_USER_ID))
                            .token(request.pathVariable("token"))
                            .useYn("Y")
                            .build()
                    )
                    .retrieve()
                    .bodyToMono(TokenNode.class),
            TokenNode.class);
  }
}
