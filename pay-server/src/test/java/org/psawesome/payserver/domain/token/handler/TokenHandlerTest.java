package org.psawesome.payserver.domain.token.handler;

import org.junit.jupiter.api.Test;
import org.psawesome.payserver.domain.common.PayCommonTest;
import org.psawesome.payserver.domain.token.repo.TokenNodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;

/**
 * author: ps [https://github.com/wiv33/pay-manager]
 * DATE: 20. 6. 27. Saturday
 */
class TokenHandlerTest extends PayCommonTest {

  @Autowired
  TokenNodeRepository tokenNodeRepository;

  @Test
  void testNodeAll() {
//    Mono<Long> meA = tokenNodeRepository.findByParentToken("MeA")
//            .count();
//    meA.subscribe();
    System.out.println("meA = " + tokenNodeRepository.findByParentToken("MeA")
            .blockFirst()
            );
  }
}