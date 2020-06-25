package org.psawesome.payclient.domain.receive.router;

import io.netty.handler.ssl.ApplicationProtocolNames;
import lombok.RequiredArgsConstructor;
import org.psawesome.payclient.domain.receive.handler.ReceiveHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

/**
 * author: ps [https://github.com/wiv33/pay-manager]
 * DATE: 20. 6. 26. Friday
 */
@Component
@RequiredArgsConstructor
public class ReceiveRouter {

  private final ReceiveHandler handler;

  @Bean
  RouterFunction<ServerResponse> receiveRouterFunction() {
    return RouterFunctions.route(GET("/receive")
            .and(accept(MediaType.APPLICATION_JSON)), handler::takePay);
  }

}
