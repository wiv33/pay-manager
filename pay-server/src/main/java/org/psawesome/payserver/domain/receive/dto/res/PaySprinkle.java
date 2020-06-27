package org.psawesome.payserver.domain.receive.dto.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * author: ps [https://github.com/wiv33/pay-manager]
 * DATE: 20. 6. 27. Saturday
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaySprinkle {

  private Integer id;

  private Integer tokenId;

  private Integer sprinkleUser;

  private String roomName;

  private Integer sprinklePrice;

  private Integer divide;

  private String startDate;

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

