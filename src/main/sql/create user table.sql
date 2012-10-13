DROP TABLE IF EXISTS USER;

CREATE TABLE USER
(
  USER_ID     VARCHAR(20) NOT NULL PRIMARY KEY,
  USER_LEVEL  INT NOT NULL,
  LOG_IN_COUNT  INT NOT NULL,
  LAST_LOGIN_DATE TIMESTAMP DEFAULT NOW(),
  PASSWORD    VARCHAR(256) NOT NULL,
  FULL_NAME   VARCHAR(30) NOT NULL,
  EMAIL       VARCHAR(30),
  DATE_CREATED  DATE DEFAULT NULL,
  ADDR_ID     INT NOT NULL,
  USER_IDX    INT(11) DEFAULT NULL,
  FOREIGN KEY (ADDR_ID) REFERENCES ADDRESS(ADDR_ID)
);

DROP TABLE IF EXISTS ADDRESS;
CREATE TABLE ADDRESS
(
  ADDR_ID       INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  ADDR_STREET   VARCHAR(40) NOT NULL,
  ADDR_CITY     VARCHAR(20) NOT NULL,
  ADDR_STATE    VARCHAR(3) NOT NULL,
  ADDR_ZIP_CODE VARCHAR(4) NOT NULL
);

DROP TABLE IF EXISTS ADMIN;
CREATE TABLE ADMIN
(
  ADMIN_ID     VARCHAR(20) NOT NULL PRIMARY KEY,
  PASSWORD    VARCHAR(256) NOT NULL,
  EMAIL       VARCHAR(30)
);
INSERT ADMIN values ("admin", "0DPiKuNIrrVmD8IUCuw1hQxNqZc=", "otto185@naver.com");

DROP TABLE IF EXISTS SELLER;
CREATE TABLE SELLER
(
  SELLER_ID     VARCHAR(20) NOT NULL PRIMARY KEY,
  SELLER_LEVEL  INT NOT NULL,
  LOG_IN_COUNT  INT NOT NULL,
  LAST_LOGIN_DATE DATE DEFAULT NULL,
  PASSWORD    VARCHAR(256) NOT NULL,
  FULL_NAME   VARCHAR(30) NOT NULL,
  EMAIL       VARCHAR(30),
  DATE_CREATED DATE DEFAULT NULL
);

DROP TABLE IF EXISTS ITEM;
CREATE TABLE ITEM
(
  ITEM_ID             INTEGER(10) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  ITEM_NAME           VARCHAR(20) NOT NULL ,
  STOCK_QUANTITY      INTEGER(10) NOT NULL ,
  PRICE               INTEGER(10) NOT NULL ,
  DISCOUNT_RATE       INTEGER(2) DEFAULT 0,
  DESCRIPTION         TEXT DEFAULT NULL ,
  PICTURE_PATH        VARCHAR(40) DEFAULT NULL
);

DROP TABLE IF EXISTS SELLER_ITEM_JOIN;
CREATE TABLE SELLER_ITEM_JOIN
(
  SELLER_ID     VARCHAR(20) NOT NULL,
  ITEM_ID       INTEGER(10) NOT NULL,
  FOREIGN KEY (SELLER_ID) REFERENCES SELLER(SELLER_ID),
  FOREIGN KEY (ITEM_ID) REFERENCES ITEM(ITEM_IDD)
);

DROP TABLE IF EXISTS ORDERS;
CREATE TABLE ORDERS
(
  ORDER_ID        INTEGER(10) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  ORDER_QUANTITY  INTEGER(4) NOT NULL,
  ITEM_ID         INTEGER(10) NOT NULL,
  ORDER_DATE      DATE DEFAULT NULL,
  FOREIGN KEY (ITEM_ID) REFERENCES ITEM(ITEM_IDD)
);

DROP TABLE IF EXISTS USER_ORDER_JOIN;
CREATE TABLE USER_ORDER_JOIN
(
  USER_ID     VARCHAR(20) NOT NULL,
  ORDER_ID    INTEGER(10) NOT NULL,
  FOREIGN KEY (USER_ID) REFERENCES USER(USER_ID),
  FOREIGN KEY (ORDER_ID) REFERENCES ORDERS(ORDER_ID)
);
