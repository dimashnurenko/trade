CREATE TABLE orders (
  id           BIGINT(10) PRIMARY KEY AUTO_INCREMENT,
  product_id   BIGINT(10)  NOT NULL,
  quantity     INT         NOT NULL,
  status       VARCHAR(10) NOT NULL   DEFAULT 'NEW',
  created_date DATETIME,
  created_by   BIGINT(10)
);

ALTER TABLE `orders`
  ADD CONSTRAINT `orders_fk1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);

CREATE TABLE order_summary (
  id       BIGINT(10) PRIMARY KEY AUTO_INCREMENT,
  order_id BIGINT(10)     NOT NULL,
  price    DECIMAL(10, 2) NULL,
  shipping DECIMAL(10, 2) NULL,
  total    DECIMAL(10, 2) NULL
);

ALTER TABLE `order_summary`
  ADD CONSTRAINT `order_summary_fk1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`);

CREATE TABLE recipients (
  id         BIGINT(10) PRIMARY KEY AUTO_INCREMENT,
  order_id   BIGINT(10),
  first_name VARCHAR(30),
  last_name  VARCHAR(30),
  address1   VARCHAR(200),
  address2   VARCHAR(200),
  phone      VARCHAR(20),
  email      VARCHAR(320),
  country    VARCHAR(50),
  zip        VARCHAR(20)
);

CREATE INDEX recipients_index
  ON recipients (order_id);
