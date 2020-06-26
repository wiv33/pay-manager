package org.psawesome.payserver.domain.user.entity;

import org.junit.jupiter.api.Test;
import org.psawesome.payserver.domain.user.repo.PayUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;

/**
 * author: ps [https://github.com/wiv33/pay-manager]
 * DATE: 20. 6. 26. Friday
 */
@SpringBootTest
class PayUserTest {

  @Autowired
  PayUserRepository repository;

  @Test
  void testSaveUser() {
    StepVerifier.create(repository.save(PayUser.builder().name("ps").build())
            .log())
            .expectNextCount(1L)
            .expectNext(new PayUser(1L, "ps"))
            .expectComplete()
            .verify()
    ;
  }
}