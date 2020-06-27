package org.psawesome.payserver.domain.token.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.psawesome.payserver.domain.token.dto.req.NodeOneRequest;
import org.psawesome.payserver.domain.token.entity.PayToken;
import org.psawesome.payserver.domain.token.entity.TokenNode;
import org.psawesome.payserver.domain.token.repo.PayTokenRepository;
import org.psawesome.payserver.domain.token.repo.TokenNodeRepository;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.psawesome.payserver.domain.common.PayUtils.X_USER_ID;
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

  @Description("")
  public Mono<ServerResponse> generateToken(ServerRequest request) {
    return ok().body(getBody(request),
            PayToken.class)
            .doOnError(throwable -> ServerResponse.notFound())
            .log();
  }

  @Description("")
  public Mono<ServerResponse> getNodes(ServerRequest request) {
    Integer tokenId = parseNumber(request.pathVariable("tokenId"), Integer.class);
    log.info("getNodes tokenId = {}", tokenId);
    return ok().body(
            payTokenRepository.findById(tokenId)
                    .map(PayToken::getToken)
//                    .log("PayToken::getToken")
                    .map(tokenNodeRepository::findByParentToken)
                    .log("tokenNodeRepository::findByParentToken")
                    .flatMap(Mono::from)
            , TokenNode.class)
            .log("getNodes final log ==> ");
  }

  @Description("")
  public Mono<ServerResponse> getNodeOneByToken(ServerRequest request) {
    String xUserId = request.headers().firstHeader(X_USER_ID);
    return ok().body(
            findOneTokenNode(request)
//                    .doOnSuccess(TokenHandler::notFoundThrow)
//                    .flatMapIterable(Flux::toIterable) // block
//                    .limitRate(1)
                    .map(updateNode(xUserId))
                    .log()
                    .flatMap(this.tokenNodeRepository::save)
                    .log(),
            TokenNode.class)
            .doOnError((throwable) -> ServerResponse.badRequest())
            ;
  }

  // 내부 메서드

  private Mono<TokenNode> findOneTokenNode(ServerRequest request) {
    return request.bodyToMono(NodeOneRequest.class)
            .map(nodeReq -> this.tokenNodeRepository.findOneByParentTokenAndReceiveIdIsNull(nodeReq.getToken()))
            .single()
//                    .checkpoint("has Elements Point")
            .flatMap(Mono::from);
  }

  private Function<TokenNode, TokenNode> updateNode(String xUserId) {
    return tokenNode -> {
      tokenNode.setReceiveDate(LocalDateTime.now().toString());
      tokenNode.setReceiveId(parseNumber(Objects.requireNonNull(xUserId), Integer.class));
      return tokenNode;
    };
  }

  private static void notFoundThrow(TokenNode ele) {
    if (Objects.isNull(ele)) throw new RuntimeException("유효한 토큰이 없습니다.");
  }

  private Mono<PayToken> getBody(ServerRequest request) {
    return payTokenRepository.save(PayToken.builder().build())
            .map(payToken -> {
              saveList(request, payToken);
              return payToken;
            });
  }

  private void saveList(ServerRequest request, PayToken payToken) {
    tokenNodeRepository.saveAll(generateListLimitDivide(request.pathVariable("divide"), payToken))
            .log()
            .subscribe();
  }

  private List<TokenNode> generateListLimitDivide(String limit, PayToken payToken) {
    return IntStream.range(0, parseNumber(limit, Integer.class))
            .mapToObj(value -> TokenNode.builder()
                    .parentToken(payToken.getToken())
                    .build()
            )
//            .peek(System.out::println)
            .collect(Collectors.toList());
  }

  public Mono<ServerResponse> retrieveAll(ServerRequest request) {
    return ok().body(Mono.from(this.payTokenRepository.findAll()), PayToken.class)
            .log("PayToken.all --> ");
  }
}
