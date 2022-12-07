
-- INSERT INTO user (name,password,role) VALUES
-- ('Erin Carr','ironhack','USER'),
-- ('Naomi Barr','ironhack1','USER'),
-- ('Elinor Mcgee','ironhack2','USER'),
-- ('Hana Jennings','ironhack3','USER'),
-- ('Luisa Foley','ironhack4','USER'),
-- ('Hamzah Mejia','ironhack5','USER'),
-- ('Administrador1','Administrador1','ADMIN'),
-- ('Administrador2','Administrador2','ADMIN'),
-- ('UsuarioExterno1','UsuarioExterno1','CONTRIBUTOR'),
-- ('UsuarioExterno2','UsuarioExterno2','CONTRIBUTOR'),
-- ('UsuarioExterno3','UsuarioExterno3','CONTRIBUTOR');

-- INSERT INTO account_holder (date_of_birth,mailing_city,mailing_house,mailing_steet,city,house_address,street,name) VALUES
-- ('1990-01-01','Barcelona','1º 2ª','Aribau','Barcelona','1º 2ª','Aribau','Erin Carr'),
-- ('1992-12-17',NULL,NULL,NULL,'Zaragoza','principal','Zamora','Naomi Barr'),
-- ('1987-07-13','Barcelona','6º 2ª','Mallorca','Barcelona','2º 2ª','Diagonal','Elinor Mcgee'),
-- ('1965-09-22','Barcelona','12º 1ª','Bailén','Barcelona','3º 4ª','Av. Sarrià','Hana Jennings'),
-- ('1994-05-05',NULL,NULL,NULL,'Valencia','1º 1ª','Diagonal','Luisa Foley'),
-- ('1995-07-23',NULL,NULL,NULL,'Girona','1º 4ª','Girona','Hamzah Mejia');

-- INSERT INTO admin (name) VALUES
-- ('Administrador1'),
-- ('Administrador2');

-- INSERT INTO third_party (hashed_key,name) VALUES
-- ('ironhack','UsuarioExterno2'),
-- ('123abc','UsuarioExterno3'),
-- ('root','UsuarioExterno1');


-- INSERT INTO account (amount,currency,creation_date,penalty_fee,holder_id,secondary_holder_id) VALUES
-- (1000,'USD','2001-01-21',40,'Erin Carr','Hana Jennings'),
-- (1000,'USD','2001-01-21',40,'Naomi Barr','Luisa Foley'),
-- (1000,'USD','2001-01-21',40,'Elinor Mcgee','Hamzah Mejia'),
-- (1000,'USD','2001-01-21',40,'Hana Jennings',null),
-- (1000,'USD','2001-01-21',40,'Luisa Foley',null),
-- (1000,'USD','2001-01-21',40,'Hamzah Mejia',null);

-- INSERT INTO checking (minimum_balance,monthly_maintenance_fee,secret_key,status,id) VALUES
-- (250,12,'ironhack','ACTIVE',1),
-- (250,12,'123abc','ACTIVE',2);

-- INSERT INTO credit_card (credit_limit,interest_rate,id) VALUES
-- (100,0.2,3),
-- (100,0.2,4);

-- INSERT INTO  savings(interest_rate,minimum_balance,secret_key,status,id) VALUES
-- (0.0025,1000,'root','ACTIVE',5),
-- (0.0025,1000,'ironhack1','ACTIVE',6);


-- Ampliación de interest_rate para poder añadir 4 decimales
-- ALTER TABLE savings MODIFY COLUMN interest_rate decimal(19,4) DEFAULT NULL;

-- UPDATE savings
-- SET interest_rate = 0.0025
-- WHERE status = 'ACTIVE';


-- SET SQL_SAFE_UPDATES = 0;
-- DELETE FROM project_database.student_checking WHERE id = 15;
-- SELECT * FROM student_checking;

-- SET SQL_SAFE_UPDATES = 0;
-- DELETE FROM project_database.user WHERE name = 'Juan Rodriguez';
-- SELECT * FROM user;

-- SET SQL_SAFE_UPDATES = 0;
-- DELETE FROM project_database.account WHERE id = 15;
-- SELECT * FROM account;

-- hashed Keys -------------
-- UPDATE user
-- SET password = '$2a$10$59iLMyW4idSHRYhCmT7XWuqSAifxdgs1L83K9GkSdU9EepokO/Bwe'
-- WHERE name = 'Erin Carr';

-- UPDATE user
-- SET password = '$2a$10$fCfTguY1kqyVhCqW/30Ld.YCRCUDwa81YjFQ51oKkYrUEQFRAHHDu'
-- WHERE name = 'Naomi Barr';

-- UPDATE user
-- SET password = '$2a$10$A4F3lhvNMwXrC5O0dvldCO8Y2tRR/fV9PPDxWPBhn2wyrVC4K1Jii'
-- WHERE name = 'Elinor Mcgee';

-- UPDATE user
-- SET password = '$2a$10$UL9/nfLr.1Pt7mpwBBiIluTDjEQ6ds/44PwJO8v2n3mquLA7PtLUS'
-- WHERE name = 'Hana Jennings';

-- UPDATE user
-- SET password = '$2a$10$7qPNDprFGa7SteLUSSasGum6FBOTarKTVnDn.zf9ELoxhNO0ux6o2'
-- WHERE name = 'Luisa Foley';

-- UPDATE user
-- SET password = '$2a$10$mk59gmke3LOCUELBeowbj.2EtcXX6POriTyKFaB8XKTWJEIt/2UN2'
-- WHERE name = 'Hamzah Mejia';

-- UPDATE user
-- SET password = '$2a$10$aNbrPfc4HN.aiaCJcWNLzuL4mrtebb8c828X0RL0UStAIAAaWNCRO'
-- WHERE name = 'Administrador1';

-- UPDATE user
-- SET password = '$2a$10$QHGf2H2SlH4X2Huf9vQx3e4LDvtetWYYGJslu6QuNrWCNvAoaw8hC'
-- WHERE name = 'Administrador2';

-- UPDATE user
-- SET password = '$2a$10$AxUCPszLtqLpv7Gh0/VH5eodHmh0Q.kANC5SUT72rvg9LUhfNqS3W'
-- WHERE name = 'UsuarioExterno1';

-- UPDATE user
-- SET password = '$2a$10$.x.Dxo0igRQebJXuqcHgMecJScWsaxwtp41gOcMJKu6ZnJyzB/Yc6'
-- WHERE name = 'UsuarioExterno2';

-- UPDATE user
-- SET password = '$2a$10$Jh5tdfXa83J0qnnq45Q17eXlUfW9y1WRUCwvKyqlFb1t9uIcO6G5S'
-- WHERE name = 'UsuarioExterno3';
