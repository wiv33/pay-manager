package org.psawesome.payserver.domain.receive.repo;

import org.psawesome.payserver.domain.receive.entity.PayReceive;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

/**
 * author: ps [https://github.com/wiv33/pay-manager]
 * DATE: 20. 6. 27. Saturday
 */
@Repository
public interface ReceiveRepository extends R2dbcRepository<PayReceive, Integer> {

}
