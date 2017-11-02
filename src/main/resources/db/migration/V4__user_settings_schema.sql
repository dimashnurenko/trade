CREATE TABLE user_settings (
  id            BIGINT(20)              AUTO_INCREMENT PRIMARY KEY,
  user_id       BIGINT(20)     NOT NULL,
  exchange_rate DECIMAL(10, 2) NOT NULL,
  currency      VARCHAR(50)    NOT NULL DEFAULT 'USD',
  percent       DECIMAL(10, 2) NOT NULL,

  INDEX user_indx(user_id),
  FOREIGN KEY (user_id) REFERENCES user (id)
);

CREATE TABLE ad (
  id        BIGINT(20) AUTO_INCREMENT PRIMARY KEY,
  user_id   BIGINT(20)     NOT NULL,
  link      VARCHAR(500)   NOT NULL,
  title     VARCHAR(200)   NULL,
  price     DECIMAL(10, 2) NOT NULL,
  image_url VARCHAR(500)   NOT NULL,

  INDEX user_indx(user_id),
  FOREIGN KEY (user_id) REFERENCES user (id)
);