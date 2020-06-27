package org.psawesome.payserver.domain.receive.dto.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * author: ps [https://github.com/wiv33/pay-manager]
 * DATE: 20. 6. 27. Saturday
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NodeOneRequest {
  private String roomId;
  private String token;
  private String useYn;
}
