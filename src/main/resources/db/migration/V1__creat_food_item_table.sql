CREATE TABLE Food_Item
(
    id         BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome       VARCHAR(255) NOT NULL,
    categoria  VARCHAR(100) NOT NULL,
    quantidade INT          NOT NULL,
    validade   TIMESTAMP    NOT NULL
);