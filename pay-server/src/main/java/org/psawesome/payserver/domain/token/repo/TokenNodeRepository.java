package org.psawesome.payserver.domain.token.repo;

import org.psawesome.payserver.domain.token.entity.TokenNode;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * author: ps [https://github.com/wiv33/pay-manager]
 * DATE: 20. 6. 26. Friday
 */
@Repository
public interface TokenNodeRepository extends R2dbcRepository<TokenNode, Integer> {
  public Flux<TokenNode> findByParentToken(String parentToken);

//  @Query("SELECT * FROM TOKEN_NODE WHERE parent_token = :parentToken AND receive_id is null")
  Flux<TokenNode> findOneByParentTokenAndReceiveIdIsNull(String parentToken);
}
