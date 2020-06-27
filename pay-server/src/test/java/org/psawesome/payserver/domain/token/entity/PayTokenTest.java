package org.psawesome.payserver.domain.token.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.psawesome.payserver.domain.common.PayCommonTest;
import org.psawesome.payserver.domain.token.repo.PayTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * author: ps [https://github.com/wiv33/pay-manager]
 * DATE: 20. 6. 26. Friday
 */
class PayTokenTest extends PayCommonTest {

  @Autowired
  PayTokenRepository payTokenRepository;

  @Test
  void testPayTokenAll() {
    payTokenRepository.findAll()
            .map(PayToken::getToken)
            .log()
            .subscribe(System.out::println);
  }

  @Test
  void testPrintToken() {
    testClient.get()
            .uri("/token/all")
            .header(X_ROOM_ID, "2")
            .header(X_USER_ID, "2")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectBody(PayToken.class)
            .consumeWith(payTokenEntityExchangeResult -> {
              System.out.println(payTokenEntityExchangeResult.getResponseBody().getToken());
            });
  }

  @ParameterizedTest
  @MethodSource({"getTypePaySprinkle_1000"})
  @DisplayName("토큰 세 자리 테스트")
  void testTokenLength(PayToken payTo) {
    assertEquals(3, payTo.getToken().length());
  }

  @ParameterizedTest
//  @MethodSource("getTypePaySprinkle_1000")
  @ValueSource(ints = {10000})
  @DisplayName("토큰 중복 체크 - 10000개, loss = 5%...")
  void testTokenKey(int expected) {
    System.out.println("expected = " + expected);
    int size = getTypePaySprinkle_param(expected)
            .map(PayToken::getToken)
//            .peek(System.out::println)
            .limit(expected)
            .collect(Collectors.toSet())
            .size();
    int v = expected - (Math.floorDiv(expected, 100) * 5);
    System.out.println("v = " + v + ", size = " + size);
    assertTrue(v < size);
//    assertEquals(expected, size);
  }

  private static Stream<PayToken> getTypePaySprinkle_1000() {
    return Stream.generate(() -> PayToken.builder()
            .build())
            .limit(1000);
  }

  private static Stream<PayToken> getTypePaySprinkle_param(int limit) {
    return Stream.generate(() -> PayToken.builder()
            .build())
            .limit(limit);
  }
}