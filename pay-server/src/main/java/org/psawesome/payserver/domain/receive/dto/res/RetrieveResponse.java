package org.psawesome.payserver.domain.receive.dto.res;

import lombok.*;

/**
 * author: ps [https://github.com/wiv33/pay-manager]
 * DATE: 20. 6. 27. Saturday
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RetrieveResponse {
  private Integer id;
  private Integer tokenId;
  private Integer sprinkleUser;
  private String roomName;
  private Integer sprinklePrice;
  private Integer divide;
  private String startDate;
  private String soldOutDate;
  private Integer resultPrice;

  @Builder
  public RetrieveResponse(Integer id, Integer tokenId, Integer sprinkleUser, String roomName, Integer sprinklePrice, Integer divide, String startDate, String soldOutDate) {
    this.id = id;
    this.tokenId = tokenId;
    this.sprinkleUser = sprinkleUser;
    this.roomName = roomName;
    this.sprinklePrice = sprinklePrice;
    this.divide = divide;
    this.startDate = startDate;
    this.soldOutDate = soldOutDate;
    this.resultPrice = Math.floorDiv(sprinklePrice, divide);
  }
}
