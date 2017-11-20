CREATE TABLE paid_order (
  id         BIGINT(20) AUTO_INCREMENT PRIMARY KEY,
  creator_id BIGINT(100)    NULL,
  updater_id BIGINT(20),
  ad_id      BIGINT(20)     NOT NULL,
  total      DECIMAL(17, 2) NOT NULL,
  status     VARCHAR(50)    NOT NULL,

  INDEX creator_indx(creator_id),
  INDEX ad_indx(ad_id),
  FOREIGN KEY (creator_id) REFERENCES user (id),
  FOREIGN KEY (ad_id) REFERENCES ad (id)
);
