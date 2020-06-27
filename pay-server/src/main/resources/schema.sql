-- DROP TABLE PAY_USER;
-- DROP TABLE PAY_TOKEN;
-- DROP TABLE TOKEN_NODE;
-- DROP TABLE PAY_SPRINKLE;
-- DROP TABLE PAY_RECEIVE;

CREATE TABLE PAY_USER (id SERIAL PRIMARY KEY, USER_NAME VARCHAR(30));

CREATE TABLE PAY_TOKEN (
    id SERIAL PRIMARY KEY,
    TOKEN VARCHAR(17),
    USE_YN VARCHAR(7),
--     SPRINKLE_ID INTEGER,
    GENERATE_DATE VARCHAR(33),
    EXPIRE_DATE VARCHAR(33)
);


CREATE TABLE TOKEN_NODE (
    id SERIAL PRIMARY KEY,
    PARENT_TOKEN VARCHAR(17),
    RECEIVE_ID INTEGER,
    RECEIVE_DATE VARCHAR(33)
);

CREATE TABLE PAY_SPRINKLE (
    id SERIAL PRIMARY KEY,
    TOKEN_ID INTEGER,
    SPRINKLE_USER INTEGER,
    ROOM_NAME VARCHAR(33),
    SPRINKLE_PRICE INTEGER,
    DIVIDE INTEGER,
    START_DATE VARCHAR(30),
    SOLD_OUT_DATE VARCHAR(33)
);

-- RECEIVE 는 받기 할 때마다 테이블이 생성된다.

CREATE TABLE PAY_RECEIVE (
    id SERIAL PRIMARY KEY,
    RECEIVE_USER INTEGER,
    TOKEN_ID INTEGER,
    ROOM_NAME VARCHAR(33),
    RECEIVE_PRICE INTEGER,
    SPRINKLE_DATE VARCHAR(33),
    RECEIVE_DATE VARCHAR(33)
);


