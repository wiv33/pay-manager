package org.psawesome.payserver.domain.token.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.internal.stubbing.defaultanswers.GloballyConfiguredAnswer;
import org.psawesome.payserver.domain.sprinkle.entity.PaySprinkle;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * author: ps [https://github.com/wiv33/pay-manager]
 * DATE: 20. 6. 26. Friday
 */
class PayTokenTest {

  @ParameterizedTest
  @MethodSource({"getTypePaySprinkle_1000"})
  @DisplayName("토큰 세 자리 테스트")
  void testTokenLength(PayToken payTo) {
    assertEquals(3, payTo.getToken().length());
  }

  @ParameterizedTest
//  @MethodSource("getTypePaySprinkle_1000")
  @ValueSource(ints = {1000})
  @DisplayName("토큰 중복 체크 - 1000개, loss = 2%...")
  void testTokenKey(int expected) {
    int size = getTypePaySprinkle_1000()
            .map(PayToken::getToken)
            .limit(expected)
            .collect(Collectors.toSet())
            .size();
    assertTrue(expected * 0.02 < size);
//    assertEquals(expected, size);
  }

  private static Stream<PayToken> getTypePaySprinkle_1000() {
    return Stream.generate(() -> PayToken.builder()
            .generateDate(LocalDateTime.now().toString())
            .expireDate(LocalDateTime.now().plusMinutes(10).toString())
            .build())
            .limit(1000);
  }
}