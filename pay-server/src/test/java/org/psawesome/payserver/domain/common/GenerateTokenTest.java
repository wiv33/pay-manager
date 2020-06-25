package org.psawesome.payserver.domain.common;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * author: ps [https://github.com/wiv33/pay-manager]
 * DATE: 20. 6. 25. Thursday
 */
@SpringBootTest
@DisplayName("토큰 생성 테스트")
class GenerateTokenTest {

  @Autowired
  GenerateToken token;

  @Test
  void testGenerateToken() {
    assertNotNull(token);
  }

  @Test
  @DisplayName("토큰 세 자리 테스트")
  void testTokenLength() {
    assertEquals(3, token.getToken().length());
  }

  @Test
  @DisplayName("토큰 중복 체크 - 1000개, loss = 2%...")
  void testTokenKey() {
    var expected = 1000;
    int size = Stream.generate(() -> token.getToken())
            .limit(expected)
            .collect(Collectors.toSet())
            .size();
    assertEquals(expected, size);
  }

}