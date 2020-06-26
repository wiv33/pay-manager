package org.psawesome.payserver.domain.room.dto.req;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class JoinRequest {
  private String roomId;
  private String userId;
}
