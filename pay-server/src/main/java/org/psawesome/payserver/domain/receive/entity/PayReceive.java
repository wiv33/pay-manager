package org.psawesome.payserver.domain.receive.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

/**
 * author: ps [https://github.com/wiv33/pay-manager]
 * DATE: 20. 6. 26. Friday
 */
@Data
@AllArgsConstructor
@Table("PAY_RECEIVE")
public class PayReceive {

  @Id
  private Integer id;

  @Column("RECEIVE_USER")
  private Integer receiveUser;

  @Column("TOKEN_ID")
  private Integer tokenId;

  @Column("ROOM_NAME")
  private String roomName;

  @Column("RECEIVE_PRICE")
  private Integer receivePrice;

  @Column("SPRINKLE_DATE")
  private String sprinkleDate;

  @Column("RECEIVE_DATE")
  private String receiveDate;

  @Column("DIVIDE")
  private Integer divide;

  public PayReceive() {
    this.receiveDate = LocalDateTime.now().toString();
  }

  @Builder
  public PayReceive(Integer receiveUser, Integer tokenId, String roomName, Integer receivePrice, String sprinkleDate, Integer divide) {
    this.receiveUser = receiveUser;
    this.tokenId = tokenId;
    this.roomName = roomName;
    this.receivePrice = receivePrice;
    this.sprinkleDate = sprinkleDate;
    this.divide = divide;
  }

}
