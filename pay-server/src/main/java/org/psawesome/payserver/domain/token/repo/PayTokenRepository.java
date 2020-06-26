package org.psawesome.payserver.domain.token.repo;

import org.psawesome.payserver.domain.token.entity.PayToken;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

/**
 * author: ps [https://github.com/wiv33/pay-manager]
 * DATE: 20. 6. 26. Friday
 */
public interface PayTokenRepository extends ReactiveCrudRepository<PayToken, Integer> {
}
