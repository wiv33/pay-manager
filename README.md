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
PayRoom 방
-

- 방 안에 유저 기준으로 행위가 이루어짐. 

- Room이 필요한가?


- pay - manager
    -
    - pay-server
        -
        - 도메인: 유저, 뿌리기, 받기
        - Entity: PayUser, PaySprinkle, PayReceive, PayRoom
      
    - pay-client
        - **REST 호출과 응답 값을 처리하는 layer**
        - 요청과 응답은 DTO 생성
        
        
        
