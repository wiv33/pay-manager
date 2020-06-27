package org.psawesome.payserver.domain.receive.handler;

import lombok.RequiredArgsConstructor;
import org.psawesome.payserver.domain.receive.dto.req.NodeOneRequest;
import org.psawesome.payserver.domain.receive.dto.res.PaySprinkle;
import org.psawesome.payserver.domain.receive.dto.res.RetrieveResponse;
import org.psawesome.payserver.domain.receive.dto.res.ReceiveInfo;
import org.psawesome.payserver.domain.receive.dto.res.TokenNode;
import org.psawesome.payserver.domain.receive.entity.PayReceive;
import org.psawesome.payserver.domain.receive.repo.ReceiveRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.psawesome.payserver.domain.common.PayUtils.X_ROOM_ID;
import static org.psawesome.payserver.domain.common.PayUtils.X_USER_ID;
import static org.springframework.util.NumberUtils.parseNumber;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

/**
 * author: ps [https://github.com/wiv33/pay-manager]
 * DATE: 20. 6. 26. Friday
 */
@Component
@RequiredArgsConstructor
public class ReceiveHandler {

  private final WebClient webClient;

  private final ReceiveRepository receiveRepository;

  public Mono<ServerResponse> takePay(ServerRequest request) {
    ServerRequest.Headers headers = request.headers();
    String xUserId = headers.firstHeader(X_USER_ID);
    String xRoomId = headers.firstHeader(X_ROOM_ID);
    return ok().body(
            retrieveTokenNode(request, xUserId, xRoomId)
                    .map(TokenNode::getId)
                    .flatMap(this::retrieveSprinkle)
                    .flatMap(id -> saveReceive(xUserId, xRoomId, id))
                    .log("payReceive log -->>> ")
                    .map(this::makeResponse)
                    .log()
                    .doOnError(throwable -> ServerResponse.notFound()),
            ReceiveInfo.class)
            .doOnError(throwable -> ServerResponse.notFound())
            ;
  }

  private ReceiveInfo makeResponse(PayReceive payReceive) {
    return ReceiveInfo.builder()
            .price(payReceive.getReceivePrice())
            .build();
  }

  private Mono<PayReceive> saveReceive(String xUserId, String xRoomId, RetrieveResponse id) {
    return receiveRepository.save(
            PayReceive.builder()
                    .divide(id.getDivide())
                    .receivePrice(id.getResultPrice())
                    .receiveUser(parseNumber(xUserId, Integer.class))
                    .sprinkleDate(id.getStartDate())
                    .roomName(xRoomId)
                    .tokenId(id.getTokenId())
                    .build()
    );
  }

  private Mono<TokenNode> retrieveTokenNode(ServerRequest request, String xUserId, String xRoomId) {
    return webClient.post()
            .uri("/token/node")
            .header(X_USER_ID, xUserId)
            .header(X_ROOM_ID, xRoomId)
            .bodyValue(NodeOneRequest.builder()
                    .roomId(xUserId)
                    .token(request.pathVariable("token"))
                    .useYn("Y")
                    .build())
            .retrieve()
            .bodyToMono(TokenNode.class);
  }

  private Mono<RetrieveResponse> retrieveSprinkle(Integer tokenId) {
    return webClient.get()
            .uri("/sprinkle/retrieve/{tokenId}", tokenId)
            .retrieve()
            .bodyToMono(RetrieveResponse.class);
  }
}
