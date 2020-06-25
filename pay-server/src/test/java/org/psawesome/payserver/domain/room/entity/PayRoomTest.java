package org.psawesome.payserver.domain.room.entity;

import org.junit.jupiter.api.Test;
import org.psawesome.payserver.domain.common.PayAssociation;
import org.psawesome.payserver.domain.user.entity.PayUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

/**
 * author: ps [https://github.com/wiv33/pay-manager]
 * DATE: 20. 6. 25. Thursday
 */

@SpringBootTest
class PayRoomTest {

  @Autowired
//  PayRoomRepository repository;
          ReactiveMongoOperations operations;

  @Test
  void testRepositoryState() {
    ArrayList<PayAssociation<PayUser>> objects = new ArrayList<>();
    objects.add(new PayAssociation<>(UUID.randomUUID()) {
    });

    operations.save(PayRoom.builder().payUsers(objects).build())
            .log()
            .subscribe(System.out::println);

  }
}
