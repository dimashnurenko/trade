CREATE TABLE IF NOT EXISTS auth_token (
  id              BIGINT(10) AUTO_INCREMENT NOT NULL,
  token           VARCHAR(100),
  expiration_date DATETIME                  NOT NULL,
  user_id         BIGINT(10)                NOT NULL,

  PRIMARY KEY (id)
);

ALTER TABLE `auth_token`
  ADD CONSTRAINT `auth_token_fk1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

CREATE INDEX token_index
  ON auth_token (token(8));