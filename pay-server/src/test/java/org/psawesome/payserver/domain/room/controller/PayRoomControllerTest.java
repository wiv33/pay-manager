package org.psawesome.payserver.domain.room.controller;

import org.junit.jupiter.api.Test;
import org.psawesome.payserver.domain.room.repo.PayRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@DataJpaTest
class PayRoomControllerTest {

  @Autowired
  PayRoomRepository repository;

  @Test
  void testInit() {
    assertNotNull(repository);
  }

}