package org.psawesome.payserver.domain.room.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PayRoomController {

/*  private final PayRoomRepository repository;

  @MessageMapping("room.create")
  public Mono<PayRoom> createRoom(@Payload JoinRequest joinRequest) {
    return this.repository.findByRoomName(joinRequest.getRoomId())
            .flatMap(findRoom -> {
              if (Objects.isNull(findRoom)) {
                return this.repository.save(PayRoom.builder()
                        .roomName(joinRequest.getRoomId())
                        .payUsers(List.of(new PayAssociation<>(parseNumber(joinRequest.getUserId(), Long.class))))
                        .build());
              }

              findRoom.getPayUsers().add(new PayAssociation<>(parseNumber(joinRequest.getUserId(), Long.class)));
              return this.repository.save(findRoom);
            })
    .log("create room --> ")
    ;
  }*/



}
