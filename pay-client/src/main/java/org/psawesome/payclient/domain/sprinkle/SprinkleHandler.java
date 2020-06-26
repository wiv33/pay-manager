package org.psawesome.payclient.domain.sprinkle;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public class SprinkleHandler {
  public <T extends ServerResponse> Mono<T> sprinklePay(ServerRequest request) {
    return null;
  }
}
