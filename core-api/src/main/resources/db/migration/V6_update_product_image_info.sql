ALTER TABLE product
  DROP COLUMN default_image_id;

ALTER TABLE product_image
  ADD COLUMN main TINYINT(1) DEFAULT FALSE;