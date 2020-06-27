package org.psawesome.payserver.domain.token.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.psawesome.payserver.domain.token.entity.PayToken;
import org.psawesome.payserver.domain.token.entity.TokenNode;
import org.psawesome.payserver.domain.token.repo.PayTokenRepository;
import org.psawesome.payserver.domain.token.repo.TokenNodeRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.springframework.util.NumberUtils.parseNumber;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

/**
 * author: ps [https://github.com/wiv33/pay-manager]
 * DATE: 20. 6. 26. Friday
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class TokenHandler {

  private final PayTokenRepository payTokenRepository;
  private final TokenNodeRepository tokenNodeRepository;

  public Mono<ServerResponse> getToken(ServerRequest request) {
    return ok().body(
            getBody(request),
            PayToken.class)
            .doOnError(throwable -> ServerResponse.notFound())
            .log();
  }


  private Mono<PayToken> getBody(ServerRequest request) {
    return payTokenRepository.save(PayToken.builder().build())
            .map(payToken -> {
              createTokenNodes(request, payToken);
              return payToken;
            });
  }

  private void createTokenNodes(ServerRequest request, PayToken payToken) {
    List<TokenNode> saveList = IntStream.range(0, parseNumber(request.pathVariable("divide"), Integer.class))
            .mapToObj(value -> TokenNode.builder()
                    .parentToken(payToken.getToken())
                    .build()
            )
            .collect(Collectors.toList());
//    log.info("saveList size = {}", saveList.size());
//    saveList.forEach(System.out::println);
    tokenNodeRepository.saveAll(saveList)
            .log()
            .subscribe();
  }

  public Mono<ServerResponse> getNodes(ServerRequest request) {
    Integer tokenId = parseNumber(request.pathVariable("tokenId"), Integer.class);
    log.info("getNodes tokenId = {}", tokenId);
    return ok().body(
            payTokenRepository.findById(tokenId)
                    .map(PayToken::getToken)
                    .log("PayToken::getToken")
                    .map(tokenNodeRepository::findByParentToken)
                    .log("tokenNodeRepository::findByParentToken")
                    .flatMap(Mono::from)
            , TokenNode.class)
            .log("getNodes final log ==> ");
  }
}
