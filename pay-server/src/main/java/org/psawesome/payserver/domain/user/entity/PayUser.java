package org.psawesome.payserver.domain.user.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

/**
 * author: ps [https://github.com/wiv33/pay-manager]
 * DATE: 20. 6. 25. Thursday
 */
@Data
@Document("PAY_USER")
@AllArgsConstructor
public class PayUser {

  @Id
  private Long id;
  private String name;

}
