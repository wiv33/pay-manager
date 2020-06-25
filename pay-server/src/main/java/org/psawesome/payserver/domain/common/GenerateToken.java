package org.psawesome.payserver.domain.common;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * author: ps [https://github.com/wiv33/pay-manager]
 * DATE: 20. 6. 25. Thursday
 */
@Component
public class GenerateToken {

  public String getToken() {
    return makeResult(
            IntStream.concat(
                    getInts(65, 97),
                    getInts(97, 122)
            )
                    .parallel()
                    .mapToObj(Character::toString)
                    .limit(2)
                    .collect(Collectors.toList()))
            ;
  }

  private String makeResult(List<String> strings) {
    strings.add(String.valueOf(ThreadLocalRandom.current().nextInt(0, 9)));
    return strings.parallelStream()
            .limit(3)
            .reduce((acc, str) -> acc + str)
            .get();
  }

  private IntStream getInts(int i, int i2) {
    return ThreadLocalRandom.current().ints(i, i2)
            .limit(ThreadLocalRandom.current().nextInt() % 3 == 0 ? 1 : 2);
  }

}

