package org.psawesome.payserver.domain.token.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * author: ps [https://github.com/wiv33/pay-manager]
 * DATE: 20. 6. 26. Friday
 */
@Data
@Table("TOKEN_NODE")
public class TokenNode {

  @Id
  private Integer id;

  @Column("PARENT_TOKEN")
  private String parentToken;

  @Column("RECEIVE_ID")
  private Integer receiveId;

  @Column("RECEIVE_DATE")
  private String receiveDate;

  @Builder
  public TokenNode(String parentToken, Integer receiveId, String receiveDate) {
    this.parentToken = parentToken;
    this.receiveId = receiveId;
    this.receiveDate = receiveDate;
  }


}
