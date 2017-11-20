CREATE TABLE user (
  id    BIGINT(20)                  AUTO_INCREMENT PRIMARY KEY,
  name  VARCHAR(100)       NULL,
  phone VARCHAR(20) UNIQUE NOT NULL DEFAULT '',

  INDEX phone_indx(phone(8))
);