/*
package org.psawesome.payclient.testConfig;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.util.MimeTypeUtils;

@Configuration
public class RSocketServerConfig {

  @Bean
  RSocketRequester rSocketRequest(RSocketRequester.Builder builder) {
    return builder.dataMimeType(MimeTypeUtils.APPLICATION_JSON)
            .connectTcp("localhost", 7000)
            .block();
  }
}
*/
