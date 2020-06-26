package org.psawesome.payserver.domain.user.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


/**
 * author: ps [https://github.com/wiv33/pay-manager]
 * DATE: 20. 6. 25. Thursday
 */
@Data
@AllArgsConstructor
@Table("PAY_USER")
public class PayUser {

  @Id
  private Long id;
  private String name;

  @Builder
  public PayUser(String name) {
    this.name = name;
  }
}
