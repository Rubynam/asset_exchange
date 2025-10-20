


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









