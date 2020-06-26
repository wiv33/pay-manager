package org.psawesome.payserver.domain.user.entity;

import org.junit.jupiter.api.Test;
import org.psawesome.payserver.domain.user.repo.PayUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
    PayUser maria = PayUser.builder()
            .userName("maria")
            .build();
    StepVerifier.create(repository.save(maria)
            .log()
    )
            .expectNext(new PayUser(2, "maria"))
            .expectComplete()
            .verify()
    ;
  }

  @Test
  void testFindByUser() {
    StepVerifier.create(this.repository.findById(1).log())
            .consumeNextWith(payUsers -> assertAll(
                    () -> assertEquals(1, payUsers.getId()),
                    () -> assertEquals("ps", payUsers.getUserName())
            ))
            .verifyComplete()
    ;
  }
}