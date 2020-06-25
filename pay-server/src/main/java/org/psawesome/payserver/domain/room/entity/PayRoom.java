package org.psawesome.payserver.domain.room.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import org.psawesome.payserver.domain.common.PayAssociation;
import org.psawesome.payserver.domain.sprinkle.entity.PaySprinkle;
import org.psawesome.payserver.domain.user.entity.PayUser;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;
import java.util.Objects;

/**
 * author: ps [https://github.com/wiv33/pay-manager]
 * DATE: 20. 6. 25. Thursday
 */

@Data
@Table("PAY_ROOM")
public class PayRoom {

  @Id
  @Column("ID")
  private Long id;

  private List<PayAssociation<PaySprinkle>> paySprinkles;
  private List<PayAssociation<PayUser>> payUsers;

  @Builder(access = AccessLevel.PUBLIC)
  public PayRoom(List<PayAssociation<PaySprinkle>> paySprinkles, List<PayAssociation<PayUser>> payUsers) {
    this.paySprinkles = paySprinkles;
    this.payUsers = payUsers;
  }

  @Override
  public String toString() {
    return "PayRoom{" +
            "id=" + id +
            ", paySprinkles=" + this.payArrToString(this.paySprinkles) +
            ", payUsers=" + this.payArrToString(this.payUsers) +
            '}';
  }

  private String payArrToString(List<?> arr){
    return Objects.isNull(arr) ? null : arr.toString();
  }

}
