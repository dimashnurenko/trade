CREATE TABLE user_role (
  id      BIGINT(20)           AUTO_INCREMENT PRIMARY KEY,
  user_id BIGINT(100) NOT NULL,
  role    VARCHAR(20) NOT NULL DEFAULT 'USER',

  INDEX user_indx(user_id),
  FOREIGN KEY (user_id) REFERENCES user (id)
);