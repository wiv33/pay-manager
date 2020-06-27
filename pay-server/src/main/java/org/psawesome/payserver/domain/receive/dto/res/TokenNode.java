package org.psawesome.payserver.domain.receive.dto.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Column;

/**
 * author: ps [https://github.com/wiv33/pay-manager]
 * DATE: 20. 6. 27. Saturday
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TokenNode {
  private Integer id;
  private String parentToken;
  private Integer receiveId;
  private String receiveDate;

}
