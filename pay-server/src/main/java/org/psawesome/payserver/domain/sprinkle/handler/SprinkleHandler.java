package org.psawesome.payserver.domain.sprinkle.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

/**
 * author: ps [https://github.com/wiv33/pay-manager]
 * DATE: 20. 6. 25. Thursday
 */
@Component
public class SprinkleHandler {
  public Mono<ServerResponse> sprinklePay(ServerRequest request) {
    return ok().body(s -> );
  }
}
