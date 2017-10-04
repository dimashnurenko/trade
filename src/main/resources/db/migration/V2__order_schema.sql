CREATE TABLE paid_order (
  id      BIGINT(20)   NOT NULL         AUTO_INCREMENT PRIMARY KEY,
  user_id BIGINT(20)   NOT NULL,
  link    VARCHAR(200) NULL,
  color   VARCHAR(50)  NULL,
  size    VARCHAR(20)  NULL,
  status  VARCHAR(10)  NOT NULL         DEFAULT 'NEW',

  FOREIGN KEY (user_id) REFERENCES user (id)
);