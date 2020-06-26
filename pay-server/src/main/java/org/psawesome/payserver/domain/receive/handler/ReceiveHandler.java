package org.psawesome.payserver.domain.receive.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * author: ps [https://github.com/wiv33/pay-manager]
 * DATE: 20. 6. 26. Friday
 */
@Component
public class ReceiveHandler {
  public <T extends ServerResponse> Mono<T> takePay(ServerRequest request) {
    return null;
  }
}
