package org.psawesome.payserver.domain.sprinkle.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.psawesome.payserver.domain.common.PayAssociation;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * author: ps [https://github.com/wiv33/pay-manager]
 * DATE: 20. 6. 25. Thursday
 */
@SpringBootTest
class PaySprinkleTest {

  @Test
  void testSprinkleEntityState() {

  }

  @ParameterizedTest
  @MethodSource({"getTypePaySprinkle_1000"})
  @DisplayName("토큰 세 자리 테스트")
  void testTokenLength(PaySprinkle paySprinkle) {
    assertEquals(3, paySprinkle.getToken().length());
  }

  @ParameterizedTest
//  @MethodSource("getTypePaySprinkle_1000")
  @ValueSource(ints = {1000})
  @DisplayName("토큰 중복 체크 - 1000개, loss = 2%...")
  void testTokenKey(int expected) {
    int size = getTypePaySprinkle_1000()
            .map(PaySprinkle::getToken)
            .limit(expected)
            .collect(Collectors.toSet())
            .size();
    assertTrue(expected * 0.02 < size);
  }

  private static Stream<PaySprinkle> getTypePaySprinkle_1000() {
    return Stream.generate(() -> PaySprinkle.builder()
            .room(new PayAssociation<>(1L))
            .sprinkleDate(LocalDateTime.now())
            .sprinklePay(BigDecimal.valueOf(500))
            .sprinkleDivide(3)
            .build())
            .limit(1000);
  }
}