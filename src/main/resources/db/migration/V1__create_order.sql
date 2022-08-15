CREATE TABLE orders (
  id SERIAL PRIMARY KEY,
  date_time timestamp NOT NULL,
  status varchar(255) NOT NULL
)