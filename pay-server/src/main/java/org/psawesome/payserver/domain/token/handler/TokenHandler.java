package org.psawesome.payserver.domain.token.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * author: ps [https://github.com/wiv33/pay-manager]
 * DATE: 20. 6. 26. Friday
 */
@Component
public class TokenHandler {
  public <T extends ServerResponse> Mono<T> getToken(ServerRequest request) {
    return null;
  }
}
