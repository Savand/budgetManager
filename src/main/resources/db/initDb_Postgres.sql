DROP TABLE IF EXISTS fundsflows;
DROP TABLE IF EXISTS users_roles;
DROP TABLE IF EXISTS budget_user;
DROP TABLE IF EXISTS budgets;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START 100000;

CREATE TABLE users
(
  id                      INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  creation_date           TIMESTAMP  DEFAULT now(),
  last_update             TIMESTAMP,
  first_name              text,
  last_name               text,
  email                   text NOT NULL,
  password                text NOT NULL,
  enabled                 BOOL DEFAULT TRUE,
  
  CONSTRAINT unique_email UNIQUE (email)
);

CREATE TABLE users_roles
(
  user_id          INTEGER NOT NULL,
  role             text,
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE budgets (
  id                      INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  creation_date           TIMESTAMP  DEFAULT now(),
  last_update             TIMESTAMP,
  budget_name             text NOT NULL,
  budget_creator_id       INTEGER NOT NULL,
  description             TEXT,
  CONSTRAINT budget_user_creator_idx UNIQUE (budget_creator_id, budget_name),
  FOREIGN KEY (budget_creator_id) REFERENCES users (id)
);

-- many to many relationship, additional table
CREATE TABLE budget_user (
  budget_id               INTEGER NOT NULL,
  user_id                 INTEGER NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
  FOREIGN KEY (budget_id) REFERENCES budgets (id) ON DELETE CASCADE,
  CONSTRAINT budget_user_idx UNIQUE (user_id, budget_id)
);

CREATE TABLE fundsflows (
  id                      INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  creation_date           TIMESTAMP DEFAULT now(),
  last_update             TIMESTAMP,
  description             TEXT,
  operation_date_time     TIMESTAMP NOT NULL DEFAULT now(),
  amount                  INTEGER NOT NULL,
  budget_id               INTEGER NOT NULL,
  user_id                 INTEGER,
  funds_flow_type         TEXT NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE SET NULL,
  FOREIGN KEY (budget_id) REFERENCES budgets (id) ON DELETE CASCADE
);