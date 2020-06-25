package org.psawesome.payserver.domain.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * author: ps [https://github.com/wiv33/pay-manager]
 * DATE: 20. 6. 25. Thursday
 */
public class PayAssociation<T> {

  private final List<UUID> uuidList;

  public PayAssociation(UUID uuid) {
    this.uuidList = new ArrayList<>();
    this.uuidList.add(uuid);
  }

  public String getUuids() {
    return Objects.isNull(this.uuidList) ?
            null : this.uuidList
            .stream()
            .map(UUID::toString)
            .reduce((acc, uuid) -> acc  + ","+ uuid)
            .orElse(null);
  }

  public PayAssociation<T> setUuidList(UUID targetUuid){
    this.uuidList.add(targetUuid);
    return this;
  }

  @Override
  public String toString() {
    return "PayAssociation{" +
            "uuidList=" + this.getUuids() +
            '}';
  }
}
