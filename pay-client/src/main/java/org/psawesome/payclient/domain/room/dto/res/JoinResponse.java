package org.psawesome.payclient.domain.room.dto.res;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JoinResponse {

  private String roomId;
  private String userId;
}