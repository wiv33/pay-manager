package org.psawesome.payclient.domain.router;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.psawesome.payclient.domain.receive.handler.ReceiveHandler;
import org.psawesome.payclient.domain.room.handler.RoomHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.server.RouterFunction;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Slf4j
@Component
@RequiredArgsConstructor
public class PayClientRouter {

  private final ReceiveHandler receiveHandler;

  private final RoomHandler payRoomHandler;

  @Bean
  RouterFunction<?> routerFunction() {
    return nest(path("/receive"),
            nest(accept(MediaType.APPLICATION_JSON),
                    route(GET("/{id}"), receiveHandler::takePay)
//                    .route(POST("/{id}"), receiveHandler::)
            ))
            .andNest(path("/room"),
                    nest(accept(MediaType.APPLICATION_JSON),
                            route(GET("/{roomId}/{userId}"), payRoomHandler::joinRoom)
//                                    .and()
                    )
            );
  }

  @Bean
  ExchangeFilterFunction logFilter() {
    return ((request, next) -> {
      log.info("Request: {}, {}", request.method(), request.url());
      request.attributes()
              .forEach((k, v) -> log.info("attributes key = {}, val = {}", k, v));
      request.headers()
              .forEach((k, v) -> log.info("header key = {}, v = {}", k, v));
      return next.exchange(request);
    });
  }
}
