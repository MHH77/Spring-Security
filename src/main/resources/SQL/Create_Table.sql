CREATE TABLE users (
                       id INT NOT NULL AUTO_INCREMENT,
                       username VARCHAR(50) NOT NULL,
                       password VARCHAR(45) NOT NULL,
                       enabled BOOLEAN NOT NULL,
                       PRIMARY KEY (id)
);

CREATE TABLE authorities (
                             id INT NOT NULL AUTO_INCREMENT,
                             username VARCHAR(50) NOT NULL,
                             authority VARCHAR(45) NOT NULL,
                             enabled BOOLEAN NOT NULL,
                             PRIMARY KEY (id)
);

INSERT INTO users (username, password, enabled)
VALUES ('ali', '1234', true);

INSERT INTO authorities (username, authority, enabled)
VALUES ('ali', 'write', true);

COMMIT;


