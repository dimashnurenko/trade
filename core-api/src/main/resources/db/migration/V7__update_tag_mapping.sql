ALTER TABLE product_tag_mapping
  ADD COLUMN `created_by` BIGINT(10) NOT NULL,
  ADD COLUMN `created_date` DATETIME NOT NULL;

CREATE INDEX created_by_index
  ON product_tag_mapping (created_by);
