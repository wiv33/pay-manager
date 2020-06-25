package org.psawesome.payserver.domain.room.entity;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

/**
 * author: ps [https://github.com/wiv33/pay-manager]
 * DATE: 20. 6. 25. Thursday
 */

@Data
@Builder
@Document("PAY_ROOM")
public class PayRoom {

  @Id
  private UUID uuid;


}
