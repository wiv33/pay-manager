package org.psawesome.payserver.domain.common;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.psawesome.payserver.domain.receive.handler.ReceiveHandler;
import org.psawesome.payserver.domain.sprinkle.handler.SprinkleHandler;
import org.psawesome.payserver.domain.token.handler.TokenHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.server.RouterFunction;

import java.util.Collections;

import static org.psawesome.payserver.domain.common.PayUtils.X_ROOM_ID;
import static org.psawesome.payserver.domain.common.PayUtils.X_USER_ID;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * author: ps [https://github.com/wiv33/pay-manager]
 * DATE: 20. 6. 26. Friday
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class PayRouterFunction {

  // handler 집합
  private final ReceiveHandler receiveHandler;

  private final SprinkleHandler sprinkleHandler;

  private final TokenHandler tokenHandler;

  @Bean
  public RouterFunction<?> routerFunction() {
    return nest(path("/sprinkle"),
            nest(accept(MediaType.APPLICATION_JSON),
                    route(GET("/{price}/{divide}"), sprinkleHandler::sprinklePay))
    )
            .andNest(path("/receive"),
                    nest(accept(MediaType.APPLICATION_JSON),
                            route(GET("/{id}"), receiveHandler::takePay)
                    ))
            .andNest(path("/token"),
                    nest(accept(MediaType.APPLICATION_JSON),
                            route(GET("/{divide}"), tokenHandler::getToken)
                            .andRoute(GET("/node/{tokenId}"), tokenHandler::getNodes)
                    )
            )
            ;
  }

  @Bean
  ExchangeFilterFunction logFilter() {
    return ((request, next) -> {
      log.info("Request: {}, {}", request.method(), request.url());
      request.attributes()
              .forEach((k, v) -> log.info("attributes key = {}, val = {}", k, v));

      HttpHeaders headers = request.headers();
      headers.forEach((k, v) -> log.info("header key = {}, v = {}", k, v));

      return next.exchange(request);
    });
  }
}