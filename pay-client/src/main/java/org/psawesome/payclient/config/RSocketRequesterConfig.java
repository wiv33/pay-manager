/*
package org.psawesome.payclient.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.util.MimeTypeUtils;

@Configuration
public class RSocketRequesterConfig {

  @Bean
  @Profile("local")
  RSocketRequester rSocketRequester(RSocketRequester.Builder builder) {
    return builder
            .metadataMimeType(MimeTypeUtils.APPLICATION_JSON)
            .connectTcp("localhost", 7000)
            .block();
  }
  @Bean
  @Profile("prod")
  RSocketRequester rSocketRequesterProd(RSocketRequester.Builder builder) {
    return builder
            .metadataMimeType(MimeTypeUtils.APPLICATION_JSON)
            .connectTcp("pay-rsocket-server", 7000)
            .block();
  }

}
*/
