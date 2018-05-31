CREATE TABLE IF NOT EXISTS `product` (
  `id`               BIGINT(10)  NOT NULL AUTO_INCREMENT,
  `title`            VARCHAR(50),
  `description`      VARCHAR(1000),
  `created_date`     DATETIME,
  `created_by`       BIGINT(10),
  `price`            DECIMAL(10, 2),
  `default_image_id` BIGINT(10)  NOT NULL,
  `status`           VARCHAR(20) NOT NULL,
  `feed_id`          BIGINT(10)  NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `user` (
  `id`       BIGINT(10)   NOT NULL AUTO_INCREMENT,
  `name`     VARCHAR(150) NOT NULL,
  `phone`    VARCHAR(150) NOT NULL UNIQUE,
  `password` VARCHAR(150) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `product_image` (
  `id`         BIGINT(10)   NOT NULL AUTO_INCREMENT,
  `url`        VARCHAR(200) NOT NULL,
  `product_id` BIGINT(10)   NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `product_tag` (
  `id`   BIGINT(10)  NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `product_tag_mapping` (
  `id`         BIGINT(10) NOT NULL AUTO_INCREMENT,
  `tag_id`     BIGINT(10) NOT NULL,
  `product_id` BIGINT(10) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `user_role` (
  `id`      BIGINT(10)  NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT(10)  NOT NULL,
  `role`    VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `comments` (
  `id`           BIGINT(10) NOT NULL AUTO_INCREMENT,
  `created_date` DATETIME   NOT NULL,
  `updated_date` DATETIME   NOT NULL,
  `updated_by`   BIGINT(10) NOT NULL,
  `created_by`   BIGINT(10) NOT NULL,
  `product_id`   BIGINT(10) NOT NULL,
  `content`      TEXT,
  `comment_id`   BIGINT(10) NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `feed` (
  `id`      BIGINT(10) NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT(10) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `feed_settings` (
  `id`                  BIGINT(10) NOT NULL AUTO_INCREMENT,
  `page_products_count` INT(3)     NOT NULL,
  `feed_id`             BIGINT(10) NOT NULL,
  `primary_feed_id`     BIGINT(10) NOT NULL UNIQUE,
  PRIMARY KEY (`id`)
);

ALTER TABLE `product`
  ADD CONSTRAINT `product_fk0` FOREIGN KEY (`created_by`) REFERENCES `user` (`id`);

ALTER TABLE `product`
  ADD CONSTRAINT `product_fk1` FOREIGN KEY (`feed_id`) REFERENCES `feed` (`id`);

ALTER TABLE `product_image`
  ADD CONSTRAINT `product_image_fk0` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);

ALTER TABLE `product_tag_mapping`
  ADD CONSTRAINT `product_tag_mapping_fk0` FOREIGN KEY (`tag_id`) REFERENCES `product_tag` (`id`);

ALTER TABLE `product_tag_mapping`
  ADD CONSTRAINT `product_tag_mapping_fk1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);

ALTER TABLE `user_role`
  ADD CONSTRAINT `user_role_fk0` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

ALTER TABLE `comments`
  ADD CONSTRAINT `message_fk0` FOREIGN KEY (`updated_by`) REFERENCES `user` (`id`);

ALTER TABLE `comments`
  ADD CONSTRAINT `message_fk1` FOREIGN KEY (`created_by`) REFERENCES `user` (`id`);

ALTER TABLE `comments`
  ADD CONSTRAINT `message_fk2` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);

ALTER TABLE `feed`
  ADD CONSTRAINT `feed_fk0` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

ALTER TABLE `feed_settings`
  ADD CONSTRAINT `feed_settings_fk0` FOREIGN KEY (`feed_id`) REFERENCES `feed` (`id`);
