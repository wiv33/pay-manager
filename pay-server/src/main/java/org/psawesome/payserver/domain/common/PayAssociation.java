package org.psawesome.payserver.domain.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * author: ps [https://github.com/wiv33/pay-manager]
 * DATE: 20. 6. 25. Thursday
 */
public class PayAssociation<T> {

  private final List<Long> uuidList;

  public PayAssociation(Long uuid) {
    this.uuidList = new ArrayList<>();
    this.uuidList.add(uuid);
  }

  public String getUuids() {
    return Objects.isNull(this.uuidList) ?
            null : this.uuidList
            .stream()
            .map(String::valueOf)
            .reduce((acc, uuid) -> acc  + ","+ uuid)
            .orElse(null);
  }

  public PayAssociation<T> setUuidList(Long targetUuid){
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
