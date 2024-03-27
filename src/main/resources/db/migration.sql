CREATE TABLE products (
  id INTEGER PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  description VARCHAR(255) NOT NULL,
  price numeric NOT NULL,
  category_id int,
  stock_quantity numeric,
  image_url varchar,
  is_deleted boolean,
  status varchar,
  created_at timestamp,
  created_by varchar,
  modified_at timestamp,
  modified_by varchar,
  deleted_at timestamp,
  deleted_by varchar
);


CREATE TABLE product_categories (
  id INTEGER PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  description VARCHAR(255) NOT NULL,
  image_url varchar,
  is_deleted boolean,
  status varchar,
  created_at timestamp,
  created_by varchar,
  modified_at timestamp,
  modified_by varchar,
  deleted_at timestamp,
  deleted_by varchar
);

