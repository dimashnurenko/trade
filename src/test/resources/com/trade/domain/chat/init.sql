SET FOREIGN_KEY_CHECKS = 0;

INSERT INTO user (name, phone, password) VALUES ('Dmitry', '0993848428', '$2a$10$ptxLsYT35d1wp.A/A5mE5u2NiyWIUadzIVmDVJC5LEh0QzkuWX/aq');

INSERT INTO user_role (user_id, role) VALUES (1, 'ADMIN');