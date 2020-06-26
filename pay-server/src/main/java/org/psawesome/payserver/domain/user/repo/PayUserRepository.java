package org.psawesome.payserver.domain.user.repo;

import org.psawesome.payserver.domain.user.entity.PayUser;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

/**
 * author: ps [https://github.com/wiv33/pay-manager]
 * DATE: 20. 6. 26. Friday
 */
@Repository
public interface PayUserRepository extends R2dbcRepository<PayUser, Integer> {

}
