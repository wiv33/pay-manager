spec 내용
-
* webflux
* docker-compose: r2dbc - postgres

로직 구현된 모듈
-
- pay-manager:**pay-server**
    - [모듈 경로](https://github.com/wiv33/pay-manager/tree/master/pay-server)
    
- ~~pay-manager:pay-server~~
 
도메인    
-
* PayUser  
* PaySprinkle  
* PayReceive  
* PayToken   
    * TokenNode  

schema.sql
-
[경로](https://github.com/wiv33/pay-manager/blob/master/pay-server/src/main/resources/schema.sql)

---
PayRouterFunction
-
[경로](https://github.com/wiv33/pay-manager/blob/master/pay-server/src/main/java/org/psawesome/payserver/domain/common/PayRouterFunction.java)   

PayHandler
-

- PaySprinkleHandler
    -
    - [경로](https://github.com/wiv33/pay-manager/blob/master/pay-server/src/main/java/org/psawesome/payserver/domain/sprinkle/handler/SprinkleHandler.java)
    
    
- PayReceiveHandler
    -
    - [경로](https://github.com/wiv33/pay-manager/blob/master/pay-server/src/main/java/org/psawesome/payserver/domain/receive/handler/ReceiveHandler.java)

- PayTokenHandler
    -
    - [경로](https://github.com/wiv33/pay-manager/blob/master/pay-server/src/main/java/org/psawesome/payserver/domain/token/handler/TokenHandler.java)    
    



    
---
요구 사항  
+ 뿌리기, 받기, 조회 기능을 수행하는 REST API 를 구현합니다.  
    - ~~요청한 사용자의 식별값은 숫자 형태이며 "X-USER-ID" 라는 HTTP Header로 전달됩니다.~~
    - ~~요청한 사용자가 속한 대화방의 식별값은 문자 형태이며 "X-ROOM-ID" 라는 HTTP Header로 전달됩니다.~~
    
+ 작성하신 어플리케이션이 다수의 서버에 다수의 인스턴스로 동작하더라도 기능에 문제가 없도록 설계되어야 합니다.

+ 각 기능 및 제약사항에 대한 단위테스트를 반드시 작성합니다.

상세 구현 요건 및 제약사항

1. 뿌리기 API


[뿌리기 Test code](https://github.com/wiv33/pay-manager/blob/master/pay-server/src/test/java/org/psawesome/payserver/domain/sprinkle/handler/SprinkleHandlerTest.java)   


[토큰 Test code](https://github.com/wiv33/pay-manager/blob/master/pay-server/src/test/java/org/psawesome/payserver/domain/token/entity/PayTokenTest.java)   

+ 다음 조건을 만족하는 뿌리기 API를 만들어 주세요.
    - ~~뿌릴 금액, 뿌릴 인원을 요청값으로 받습니다.~~
    - ~~뿌리기 요청건에 대한 고유 token을 발급하고 응답값으로 내려줍니다.~~   
    - 뿌릴 금액을 인원수에 맞게 분배하여 **저장**합니다. (분배 로직은 자유롭게 구현해 주세요.)
    - ~~token은 3자리 문자열로 구성되며 예측이 불가능해야 합니다.~~
    
2. 받기 API   

[받기 Test code](https://github.com/wiv33/pay-manager/blob/master/pay-server/src/test/java/org/psawesome/payserver/domain/receive/handler/ReceiveHandlerTest.java)

+ 다음 조건을 만족하는 받기 API를 만들어 주세요.
    - ~~뿌리기 시 발급된 token을 요청값으로 받습니다.~~
    - ~~token에 해당하는 뿌리기 건 중 아직 누구에게도 할당되지 않은 
        분배건 하나를 API를 호출한 사용자에게 할당하고, 그 금액을 응답값으로 내려줍니다.~~
    - 뿌리기 당 한 사용자는 한번만 받을 수 있습니다.
    - 자신이 뿌리기한 건은 자신이 받을 수 없습니다.
    - 뿌린기가 호출된 대화방과 동일한 대화방에 속한 사용자만이 받을 수 있습니다.
    - 뿌린 건은 10분간만 유효합니다. 뿌린지 10분이 지난 요청에 대해서는 받기 실패 응답이 내려가야 합니다.
    
3. 조회 API  

**조회는 현재(6월 27일) 구현된 테스트가 없습니다.**

+ 다음 조건을 만족하는 조회 API를 만들어 주세요.
    - 뿌리기 시 발급된 token을 요청값으로 받습니다.
    - token에 해당하는 뿌리기 건의 현재 상태를 응답값으로 내려줍니다. 현재 상태는 다음의 정보를 포함합니다.
    - ~~뿌린 시각, 뿌린 금액, 받기 완료된 금액, 받기 완료된 정보~~ ([받은 금액, 받은 사용자 아이디] 리스트)
    - 뿌린 사람 자신만 조회를 할 수 있습니다. 다른사람의 뿌리기건이나 유효하지 않은 token에 대해서는 조회 실패 응답이 내려가야 합니다.
    - 뿌린 건에 대한 조회는 7일 동안 할 수 있습니다.
    
    
---

- 미처리 건
    + makeToken() 의 loss 개선 필요 - 현재 5% 미만
        - findByTokenAndUseYn 후 조회되면 다시 만들도록?
    + 토큰이 없을 때 empty 200이 응답 -> Error Code 분류로 적절한 오류 문구 출력
    + NodeToken 동일 회원이 받지 못하도록 로직 추가
    + NodeToken 같은 Room 여부 다시 확인 - 한 것 같지만 정확하지 않음
    + NodeToken 이 전부 팔리면 
        + PayToken.USE_YN을 Y 로 변경
        + PayToken.SOLD_OUT_DATE update
    + 토큰 조회 - 테스트를 위한 (손으로 바꿔서 확인하는 행위를 자동화)
    + 유저 정보 저장과 조회 필요
    
    + 뿌린 결과 조회 API 로직이 거의 없어서 구현의 범위를 전체로 봐야함.
    