package org.psawesome.payserver.domain.receive.dto.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * author: ps [https://github.com/wiv33/pay-manager]
 * DATE: 20. 6. 27. Saturday
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ReceiveInfo {

  private Integer price;
}
