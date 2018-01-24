CREATE TABLE `product_image_binary` (
  id               BIGINT(10) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  product_image_id BIGINT(10)             NOT NULL,
  data             MEDIUMBLOB
);

ALTER TABLE `product_image_binary`
  ADD CONSTRAINT `product_image_binary_fk0` FOREIGN KEY (`product_image_id`) REFERENCES `product_image` (`id`);