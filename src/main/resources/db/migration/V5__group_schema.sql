CREATE TABLE ad_group (
  id      BIGINT(20) AUTO_INCREMENT PRIMARY KEY,
  user_id BIGINT(20)   NOT NULL,
  name    VARCHAR(200) NOT NULL,

  INDEX user_indx(user_id),
  FOREIGN KEY (user_id) REFERENCES user (id)
)