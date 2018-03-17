CREATE TABLE followers (
  id          BIGINT(10) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  user_id     BIGINT(10) NOT NULL,
  followed_on BIGINT(10) NOT NULL
);

CREATE INDEX user_index
  ON followers (user_id);
CREATE INDEX follower_index
  ON followers (followed_on);

ALTER TABLE product
  MODIFY default_image_id BIGINT(10) NULL;