CREATE TABLE customer (
                          id INT NOT NULL AUTO_INCREMENT,
                          email VARCHAR(50) NOT NULL,
                          pwd VARCHAR(200) NOT NULL,
                          role VARCHAR(50) NOT NULL,
                          PRIMARY KEY (id)
);
/
INSERT INTO Customer (email,pwd,role)
 VALUES('test@gmail.com', '1234','admin')