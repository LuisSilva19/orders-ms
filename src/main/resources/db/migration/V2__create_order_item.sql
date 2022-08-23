CREATE TABLE order_item (
  id SERIAL PRIMARY KEY,
  description varchar(255) DEFAULT NULL,
  amount int NOT NULL,
  payment_id int,
  order_id int,
  CONSTRAINT fk_order FOREIGN KEY (order_id) REFERENCES orders(id)
)