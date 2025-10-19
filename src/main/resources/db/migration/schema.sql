
CREATE TABLE APP_USER (
    username VARCHAR(255) NOT NULL primary key ,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE USER_WALLET (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL ,
    currency VARCHAR(10) NOT NULL,
    balance DECIMAL(18, 8) NOT NULL,
    UNIQUE (username,currency)
);

ALTER TABLE USER_WALLET ADD CONSTRAINT fk_user_wallet_user FOREIGN KEY (username) REFERENCES APP_USER (username) ON DELETE CASCADE;

CREATE TABLE CURRENCY_PAIR (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    base_currency VARCHAR(10) NOT NULL,
    quote_currency VARCHAR(10) NOT NULL,
    UNIQUE (base_currency, quote_currency)
);


CREATE TABLE IF NOT EXISTS price (
     id BIGINT AUTO_INCREMENT PRIMARY KEY,
     base_currency  TEXT NOT NULL,
     quote_currency TEXT NOT NULL,
     close_time     TIMESTAMP         NOT NULL,
     average_bid    TEXT,
     average_ask    TEXT,
     high_bid       TEXT,
     high_ask       TEXT,
     low_bid        TEXT,
     low_ask        TEXT,
     updated_at     TIMESTAMP NOT NULL DEFAULT NOW()
);

create table if not exists metadata (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    metadata text not null,
    updated_at TIMESTAMP not null default now()
);

CREATE INDEX IF NOT EXISTS idx_price_base_quote ON price (base_currency, quote_currency);









