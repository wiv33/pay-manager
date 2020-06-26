package org.psawesome.payserver.domain.room.repo;

import org.psawesome.payserver.domain.room.entity.PayRoom;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * author: ps [https://github.com/wiv33/pay-manager]
 * DATE: 20. 6. 26. Friday
 */
@Repository
public interface PayRoomRepository  extends ReactiveCrudRepository<PayRoom, Long> {
  Mono<PayRoom> findByRoomName(String roomName);
}
