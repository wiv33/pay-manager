package org.psawesome.payserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * author: ps [https://github.com/wiv33/pay-manager]
 * DATE: 20. 6. 27. Saturday
 */
@Component
public class WebClientConfig {

  @Bean
  WebClient webClient() {
    return WebClient.builder()
            .build();
  }
}
