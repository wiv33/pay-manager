package org.psawesome.payserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.ClientCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import static org.psawesome.payserver.domain.common.PayUtils.X_ROOM_ID;
import static org.psawesome.payserver.domain.common.PayUtils.X_USER_ID;

/**
 * author: ps [https://github.com/wiv33/pay-manager]
 * DATE: 20. 6. 27. Saturday
 */
@Component
public class WebClientConfig {

  @Bean
  WebClient webClient() {
    return WebClient.builder()
            .codecs(ClientCodecConfigurer::defaultCodecs)
            .build();
  }
}
