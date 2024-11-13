CREATE TABLE users (
                       username VARCHAR(50) NOT NULL PRIMARY KEY,
                       password VARCHAR(500) NOT NULL,
                       enabled BOOLEAN NOT NULL
);

CREATE TABLE authority (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           name VARCHAR(255) NOT NULL,
                           customer_id BIGINT NOT NULL,
                           FOREIGN KEY (customer_id) REFERENCES customer(customer_id)
);
CREATE INDEX idx_customer_id ON authority (customer_id);
INSERT IGNORE INTO `authority` (name, customer_id) VALUES ('ROLE_USER', 1);
INSERT IGNORE INTO `authority` (name, customer_id) VALUES ('ROLE_ADMIN', 1);
INSERT IGNORE INTO `authority` (name, customer_id) VALUES ('ROLE_USER', 2);

CREATE TABLE customer (
                          customer_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          email VARCHAR(255) NOT NULL,
                          mobile_number VARCHAR(20),
                          pwd VARCHAR(500) NOT NULL,
                          role VARCHAR(50) NOT NULL,
                          create_dt DATETIME DEFAULT CURRENT_TIMESTAMP
);

INSERT IGNORE INTO `customer` (name, email, mobile_number, pwd, role, create_dt)
VALUES ('ali', 'ali@example.com', '1234560', '123', 'USER', '2024-11-12T10:00:00');

INSERT IGNORE INTO `customer` (name, email, mobile_number, pwd, role, create_dt)
VALUES ('admin', 'admin@example.com', '0987654321', '{bcrypt}$2a$12$88.f6upbBvy0okEa7OfHFuorV29qeK.sVbB9VQ6J6dWM1bW6Qef8m', 'ADMIN', '2024-11-12T10:00:00');


COMMIT;


