package org.psawesome.payserver.domain.user.repo;

import org.psawesome.payserver.domain.user.entity.PayUser;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

/**
 * author: ps [https://github.com/wiv33/pay-manager]
 * DATE: 20. 6. 26. Friday
 */
@Repository
public interface PayUserRepository extends ReactiveCrudRepository<PayUser, Integer> {

}
