DELETE FROM fundsflows;
DELETE FROM budget_user;
DELETE FROM users_roles;
DELETE FROM budgets;
DELETE FROM users;

ALTER SEQUENCE global_seq RESTART WITH 100000;


INSERT INTO users (last_name, email, password) VALUES 
  ('Administrator', 'admin@gmail.com', '$2a$10$WejOLxVuXRpOgr4IlzQJ.eT4UcukNqHlAiOVZj1P/nmc8WbpMkiju'),
  ('User1 test', 'user1@gmail.com', '$2a$10$Sh0ZD2NFrzRRJJEKEWn8l.92ROEuzlVyzB9SV1AM8fdluPR0aC1ni'),
  ('User2 test', 'user2@gmail.com', '$2a$10$Sh0ZD2NFrzSDGBMGDBMijaoiebinlkdnbgADFKBAKDGbpmbgdpadk');

INSERT INTO users_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100000),
  ('ROLE_USER', 100001),
  ('ROLE_USER', 100002);

INSERT INTO budgets(budget_name, budget_creator_id, description) VALUES 
  ('admins budget', 100000, 'budget control of admins money'),
  ('users budget', 100001, 'budget control of users family money');
    
INSERT INTO budget_user (budget_id, user_id) VALUES 
  (100003, 100000), (100004, 100001), (100004, 100002);
    
INSERT INTO fundsflows(description, operation_date_time, amount, budget_id, user_id, funds_flow_type) VALUES 
  ('admins salary', '2017-01-10 10:00:00', 25000, 100003, 100000, 'INCOME'),
  ('user1 salary', '2017-01-10 13:00:00', 15000, 100004, 100001, 'INCOME'),
  ('user2 salary', '2017-01-10 10:00:00', 15000, 100004, 100002, 'INCOME'),
  --admin's outcomes    
  ('fish, potatoe, etc', '2017-01-10 10:00:00', -200, 100003, 100000, 'FOOD'),
  ('Wollies', '2017-02-10 10:00:00', -250, 100003, 100000, 'ENTERTAINMENT'),
  ('gsm mobile phone', '2017-02-10 10:00:00', -100, 100003, 100000, 'SERVICES'),
  ('gamepad', '2017-10-10 10:00:00', -1200, 100003, 100000, 'ENTERTAINMENT'),
    --users outcomes
  ('noodles, meat', '2017-01-10 10:00:00', -100, 100004, 100001, 'INCOME'),
  ('gun', '2017-01-10 10:00:00', -5000, 100004, 100001, 'HOUSHOLD'),
  ('rubber duck', '2017-06-10 10:00:00', -20, 100004, 100002, 'HOUSHOLD');