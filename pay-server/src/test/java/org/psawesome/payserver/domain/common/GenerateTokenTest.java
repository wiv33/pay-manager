package org.psawesome.payserver.domain.common;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * author: ps [https://github.com/wiv33/pay-manager]
 * DATE: 20. 6. 25. Thursday
 */
@SpringBootTest
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
  @DisplayName("토큰 중복 체크 - 100개")
  void testTokenKey() {
    int size = Stream.generate(() -> token.getToken())
            .limit(100)
            .collect(Collectors.toSet())
            .size();
    assertEquals(100, size);
  }
}