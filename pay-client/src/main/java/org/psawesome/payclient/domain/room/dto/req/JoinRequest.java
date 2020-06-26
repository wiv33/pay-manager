package org.psawesome.payclient.domain.room.dto.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JoinRequest {

  private String roomId;
  private String userId;
}
