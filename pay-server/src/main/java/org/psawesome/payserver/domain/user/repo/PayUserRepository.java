package org.psawesome.payserver.domain.user.repo;

import org.psawesome.payserver.domain.user.entity.PayUser;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * author: ps [https://github.com/wiv33/pay-manager]
 * DATE: 20. 6. 26. Friday
 */
public interface PayUserRepository extends ReactiveMongoRepository<PayUser, Long> {

}
