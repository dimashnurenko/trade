CREATE TABLE auth_token (
  id              BIGINT(20) AUTO_INCREMENT PRIMARY KEY,
  user_id         BIGINT(20)  NOT NULL,
  token           VARCHAR(20) NOT NULL,
  expiration_date DATETIME    NOT NULL,

  INDEX user_indx(user_id)
);