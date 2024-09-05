CREATE DATABASE checklist
CHARACTER SET = 'utf16'
COLLATE = 'utf16_general_ci';

-- roles
CREATE ROLE 'app_write';
GRANT DELETE, INSERT, UPDATE ON checklist.* TO 'app_write';

CREATE ROLE 'app_read';
GRANT SELECT ON checklist.* TO 'app_read';

-- users
CREATE USER 'app_user'@'%' IDENTIFIED BY '7iMXNMMx6fQeiL';
GRANT 'app_write', 'app_read'  TO 'app_user'@'%';

CREATE USER 'readonly_user'@'%' IDENTIFIED BY 'fpw72PUn4buQGM';
GRANT 'app_read' TO 'readonly_user'@'%';

SET DEFAULT ROLE ALL TO
  'app_user'@'%',
  'readonly_user'@'%';



