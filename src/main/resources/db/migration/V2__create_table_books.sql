CREATE TABLE books
(
    id          bigint         NOT NULL AUTO_INCREMENT,
    name        varchar(255)   NOT NULL,
    price       DECIMAL(10, 2) NOT NULL,
    status      varchar(255)   NOT NULL,
    customer_id bigint         NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (customer_id) references customers (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci

