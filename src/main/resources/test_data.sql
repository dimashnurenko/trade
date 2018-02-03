INSERT INTO user (id, name, phone, password) VALUES (1, 'Dmitry', '0993848428', '$2a$10$.Jv0DWPM/v8eIc/jvGDPQu0x7ZxLfZJwaUDm2PCGjRyUtFuw3dzS.');
INSERT INTO user_role (id, user_id, role) VALUES (1, 1, 'ADMIN');
INSERT INTO feed (id, user_id) VALUES (1, 1);
INSERT INTO feed_settings (id, page_products_count, feed_id, primary_feed_id) VALUES (1, 10, 1, 1);
INSERT INTO product (id, title, description, created_date, created_by, price, default_image_id, status, feed_id)
VALUES (1, 'Product title', 'description', now(), 1, 23.25, 1, 'NEW', 1);
INSERT INTO product_image (id, url, product_id) VALUES (1, 'image-url', 1);
INSERT INTO product_tag (id, name) VALUES (1, 'tagname');
INSERT INTO product_tag_mapping (id, tag_id, product_id) VALUES (1, 1, 1);
INSERT INTO comments (id, created_date, updated_date, updated_by, created_by, product_id, content, comment_id)
VALUES (1, now(), now(), 1, 1, 1, 'content', 1);
INSERT INTO comments (id, created_date, updated_date, updated_by, created_by, product_id, content, comment_id)
VALUES (2, now(), now(), 1, 1, 1, 'content1_child', 1);
INSERT INTO comments (id, created_date, updated_date, updated_by, created_by, product_id, content, comment_id)
VALUES (3, now(), now(), 1, 1, 1, 'content2_child', 2);
INSERT INTO comments (id, created_date, updated_date, updated_by, created_by, product_id, content, comment_id)
VALUES (4, now(), now(), 1, 1, 1, 'content', 3);
