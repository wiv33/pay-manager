package org.psawesome.payserver.domain.sprinkle.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * author: ps [https://github.com/wiv33/pay-manager]
 * DATE: 20. 6. 25. Thursday
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("PAY_SPRINKLE")
public class PaySprinkle {

  @Id
  private Integer id;

  @Column("TOKEN_ID")
  private Integer tokenId;

  @Column("SPRINKLE_USER")
  private Integer sprinkleUser;

  @Column("ROOM_NAME")
  private String roomName;

  @Column("SPRINKLE_PRICE")
  private Integer sprinklePrice;

  @Column("DIVIDE")
  private Integer divide;

  @Column("START_DATE")
  private String startDate;

  @Column("SOLD_OUT_DATE")
  private String soldOutDate;

  @Builder
  public PaySprinkle(Integer tokenId, Integer sprinkleUser, String roomName, Integer sprinklePrice, Integer divide, String startDate, String soldOutDate) {
    this.tokenId = tokenId;
    this.sprinkleUser = sprinkleUser;
    this.roomName = roomName;
    this.sprinklePrice = sprinklePrice;
    this.divide = divide;
    this.startDate = startDate;
    this.soldOutDate = soldOutDate;
  }
}
