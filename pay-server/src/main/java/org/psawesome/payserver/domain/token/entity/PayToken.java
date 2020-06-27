package org.psawesome.payserver.domain.token.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * author: ps [https://github.com/wiv33/pay-manager]
 * DATE: 20. 6. 26. Friday
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("PAY_TOKEN")
public class PayToken {

  @Id
  private Integer id;

  @Column("TOKEN")
  private String token;

  @Column("USE_YN")
  private String useYn;

//  @Column("SPRINKLE_ID")
//  private Integer sprinkleId;

  @Column("GENERATE_DATE")
  private String generateDate;

  @Column("EXPIRE_DATE")
  private String expireDate;

  @Builder
  public PayToken(String useYn) {
    this.token = this.makeToken();
    this.useYn = useYn;
    LocalDateTime now = LocalDateTime.now();
    this.generateDate = now.toString();
    this.expireDate = now.plusMinutes(10).toString();
  }

  public String makeToken() {
    return makeResult(
            getInts(48, 122)
                    .filter(value -> (value > 48 && value < 58) ||
                            (value > 64 && value < 91) ||
                            (value > 96 && value < 123))
                    .parallel()
                    .mapToObj(Character::toString)
//                    .peek(System.out::println)
                    .collect(Collectors.toList()))
            ;
  }

  private String makeResult(List<String> strings) {
    strings.addAll(
            ThreadLocalRandom.current().ints(0, 9)
                    .limit(3)
                    .parallel()
                    .mapToObj(String::valueOf)
//                    .peek(System.out::println)
                    .collect(Collectors.toList())
    )
    ;
    // TODO PayToken.findAll().count() > 1 ? makeToken() : currentToken
    return strings
            .parallelStream()
            .limit(3)
            .reduce((acc, str) -> acc + str)
            .get();
  }

  private IntStream getInts(int i, int i2) {
    return ThreadLocalRandom.current().ints(i, i2)
            .limit(3);
  }

}
