package org.psawesome.payserver.domain.sprinkle.entity;

import lombok.Builder;
import lombok.Data;
import org.psawesome.payserver.domain.common.PayAssociation;
import org.psawesome.payserver.domain.room.entity.PayRoom;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * author: ps [https://github.com/wiv33/pay-manager]
 * DATE: 20. 6. 25. Thursday
 */
@Data
public class PaySprinkle {

  private Long id;

  private PayAssociation<PayRoom> room;

  private String token;

  public BigDecimal sprinklePay;
  public int sprinkleDivide;

  public LocalDateTime sprinkleDate;
  public LocalDateTime completeDate;

  public boolean isCompletePay;

  @Builder
  public PaySprinkle(PayAssociation<PayRoom> room, BigDecimal sprinklePay, int sprinkleDivide, LocalDateTime sprinkleDate, LocalDateTime completeDate, boolean isCompletePay) {
    this.room = room;
    this.token = makeToken();
    this.sprinklePay = sprinklePay;
    this.sprinkleDivide = sprinkleDivide;
    this.sprinkleDate = sprinkleDate;
    this.completeDate = completeDate;
    this.isCompletePay = isCompletePay;
  }

  public String makeToken() {
    return makeResult(
            IntStream.concat(
                    getInts(65, 97),
                    getInts(97, 122)
            )
                    .parallel()
                    .mapToObj(Character::toString)
//                    .peek(System.out::println)
                    .collect(Collectors.toList()))
            ;
  }

  private String makeResult(List<String> strings) {
    strings.addAll(
            ThreadLocalRandom.current().ints(0, 9)
                    .limit(7)
                    .parallel()
                    .mapToObj(String::valueOf)
                    .collect(Collectors.toList())
    )
    ;
    return strings
            .parallelStream()
            .limit(3)
            .reduce((acc, str) -> acc + str)
            .get();
  }

  private IntStream getInts(int i, int i2) {
    return ThreadLocalRandom.current().ints(i, i2)
            .limit(12);
  }
}
