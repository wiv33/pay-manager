package org.psawesome.payserver.domain.token.repo;

import org.psawesome.payserver.domain.token.entity.PayToken;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

/**
 * author: ps [https://github.com/wiv33/pay-manager]
 * DATE: 20. 6. 26. Friday
 */
@Repository
public interface PayTokenRepository extends R2dbcRepository<PayToken, Integer> {
}
