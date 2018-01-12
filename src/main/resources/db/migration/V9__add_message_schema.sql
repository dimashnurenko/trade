CREATE TABLE message (
  id           BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  created_date DATETIME NOT NULL,
  created_by   BIGINT(20)             DEFAULT NULL,
  updated_date DATETIME               DEFAULT NULL,
  updated_by   BIGINT(20)             DEFAULT NULL,
  content      TEXT
)