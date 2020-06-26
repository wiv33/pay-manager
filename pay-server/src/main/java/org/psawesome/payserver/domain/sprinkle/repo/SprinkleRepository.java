package org.psawesome.payserver.domain.sprinkle.repo;

import org.psawesome.payserver.domain.sprinkle.entity.PaySprinkle;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.stereotype.Repository;

/**
 * author: ps [https://github.com/wiv33/pay-manager]
 * DATE: 20. 6. 27. Saturday
 */
@Repository
public interface SprinkleRepository extends R2dbcRepository<PaySprinkle, Integer> {

}
