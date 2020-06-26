package org.psawesome.payserver.domain.token.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * author: ps [https://github.com/wiv33/pay-manager]
 * DATE: 20. 6. 26. Friday
 */
@Data
@Table("PAY_TOKEN")
public class PayToken {

  @Id
  @Column("ID")
  private Integer id;

  @Column("TOKEN")
  private String token;

  @Column("USE_YN")
  private String useYn;

  @Column("SPRINKLE_ID")
  private Integer sprinkleId;

  @Column("GENERATE_DATE")
  private String generateDate;

  @Column("EXPIRE_DATE")
  private String expireDate;

  @Builder
  public PayToken(String useYn, Integer sprinkleId, String generateDate, String expireDate) {
    this.token = this.makeToken();
    this.useYn = useYn;
    this.sprinkleId = sprinkleId;
    this.generateDate = generateDate;
    this.expireDate = expireDate;
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
