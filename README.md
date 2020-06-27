waiting - after wiv33/rsocket-mongo-vue.git
-

# 페이 뿌리기 기능

## 페이를 뿌리는 유저, 뿌린 페이를 얻을 수 있는 유저

### 상태
- 유저는 로그인한 상태
- 

### 의존성
- DB: mongodb
- protocol: http, tcp
- messaging: rsocket

### API
- **하위 작업은 별도 README**

---
PaySprinkle 뿌리기
- 

- 뿌리기 Create - Response - 뿌리기 내역

    - Token 뿌리기 create
    
- 뿌리기 Retrieve - Response - 뿌리기 내역

- 뿌리기 - isZeroPay?

---
PayReceive 받기
-

- 받기 Create - Response - 뿌리기 Complete 과 체결

---

- pay - manager
    -
    - pay-server
        -
        - 도메인: 유저, 뿌리기, 받기
        - Entity: PayUser, PaySprinkle, PayReceive, PayRoom
      
    - pay-client
        - **REST 호출과 응답 값을 처리하는 layer**
        - 요청과 응답은 DTO 생성
        
        
        
---
흐름
-

1. 뿌리기, 받기 행위 전 방을 호출한다
    - X-ROOM-ID: String 생성 - UUID
    - X-USER-ID: Number - Sequence
    
2. 도메인 별 흐름
    - 뿌리기 @params: 뿌릴 금액:Long, 뿌릴 인원:Integer
        1. 뿌리기 요청 시 token 생성
        
        2. 뿌릴 금액을 분배하여 저장
        
    - 받기 @params: token:String
        1. token으로 받기 시도
            
        2. token과 매칭되는 **잔여 분배건** 하나를 **할당**하고 **응답값으로 금액**을 반환
            - 가능    200: **응답으로 금액을 내려줌**
                + 자신이 뿌린게 아님
                + 해당 사용자가 처음 받는 것
                + 뿌린 후 10분 이내
                
            - 불가능   401: **응답으로 불가 사유를 내려줌**
                + 자신이 뿌림: 자신이 뿌린 페이는 받을 수 없습니다.
                + 해당 사용자가 두 번 이상 요청: 뿌리기 페이는 한 번만 받을 수 있습니다. 
                + 뿌린 후 10분 초과: 유효하지 않은 코드입니다.
        
