CREATE TABLE url_store (
                           url_store_id BIGINT PRIMARY KEY,
                           original     VARCHAR(4000) UNIQUE NOT NULL,
                           encoded      VARCHAR(4000) UNIQUE
);

CREATE SEQUENCE SEQ_URL_STORE
    MINVALUE 1
    MAXVALUE 999999999
    INCREMENT BY 1
    START WITH 1;