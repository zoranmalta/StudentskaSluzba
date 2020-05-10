-- Skripta koja se pokrece automatski pri pokretanju aplikacije
-- password je 123
-- Obe lozinke su hesovane pomocu BCrypt algoritma https://www.dailycred.com/article/bcrypt-calculator
set foreign_key_checks = 0;

-- delete all rows
truncate table user_authority;
truncate table authority;
truncate table enrollment;
truncate table engagement;
truncate table academic;
truncate table student;
truncate table staff;
truncate table course;
truncate table user;

INSERT INTO user ( username, password)
VALUES ('user', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra'),
       ('user2', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra'),
       ('user3', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra'),
       ('user4', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra'),
       ('user5', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra'),
       ('user6', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra'),
       ('user7', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra'),
       ('user8', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra'),
       ('user9', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra'),
       ('user10', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra'),
       ('user11', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra'),
       ('user12', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra'),
       ('user13', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra'),
       ('user14', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra'),
       ('user15', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra'),
       ('admin', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra'),
       ('prof', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra'),
       ('prof2', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra'),
       ('prof3', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra'),
       ('prof4', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra'),
       ('prof5', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra'),
       ('prof6', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra'),
       ('prof7', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra'),
       ('prof8', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra'),
       ('prof9', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra'),
       ('prof10', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra');

INSERT INTO authority (name) VALUES ('ROLE_USER'),
									('ROLE_ADMIN'),
                                    ('ROLE_PROF');

INSERT INTO user_authority (user_id, authority_id) VALUES (1,1),(2,1),(3,1),(4,1),(5,1),
(6,1),(7,1),(8,1),(9,1),(10,1),(11,1),(12,1),(13,1),(14,1),(15,1),(16,1),(16,2),(16,3),
(17,3),(18,3),(19,3),(20,3),(21,3),(22,3),(23,3),(24,3),(25,3),(26,3);

INSERT INTO academic (name) VALUES("Profesor"),("Asistent");

INSERT INTO student (first_name,last_name,user_id,account_balance,card_number,address,email) VALUES
('Marko','Petrovic',1,0,"sf-001","Kisacka 5 NS","student@hotmail.com"),
('Zoran','Stevanovic',2,0,"sf-002","Kisacka 51 NS","student@hotmail.com"),
('Milan','Milankovic',3,0,"sf-003","rumenacka 5 NS","student@hotmail.com"),
('Jovan','Stevanovic',4,0,"sf-004","Temerinska 20 NS","student@hotmail.com"),
('Petar','Milankovic',5,0,"sf-005","Futoska 6 NS","student@hotmail.com"),
('Dejan','Savic',6,0,"sf-006","Dositejeva 5 NS","student@hotmail.com"),
('Lionel','Milankovic',7,0,"sf-007","Almaska 5 NS","student@hotmail.com"),
('Kristijan','Opacic',8,0,"sf-008","Skerliceva 5 NS","student@hotmail.com"),
('Milan','Brankovic',9,0,"sf-009","Kisacka 11 NS","student@hotmail.com"),
('Zoran','Kostic',10,0,"sf-010","Safarikova 9 NS","student@hotmail.com"),
('Sandra','Caric',11,0,"sf-011","Kisacka 50 NS","student@hotmail.com"),
('Snezana','Kljajic',12,0,"sf-012","Almaska 15 NS","student@hotmail.com"),
('Dusica','Lukic',13,0,"sf-013","Radnicka 10 NS","student@hotmail.com"),
('Luka','Stevanovic',14,0,"sf-014","Kisacka 5 NS","student@hotmail.com"),
('Vuk','Kraljevic',15,0,"sf-015","Uspenska 18 NS","student@hotmail.com");

INSERT into payment (one_payment_change,reason,student_id) VALUES
(600,"Welcome Cash",1),
(600,"Welcome Cash",2),
(600,"Welcome Cash",3),
(600,"Welcome Cash",4),
(600,"Welcome Cash",5),
(600,"Welcome Cash",6),
(600,"Welcome Cash",7),
(600,"Welcome Cash",8),
(600,"Welcome Cash",9),
(600,"Welcome Cash",10),
(600,"Welcome Cash",11),
(600,"Welcome Cash",12),
(600,"Welcome Cash",13),
(600,"Welcome Cash",14),
(600,"Welcome Cash",15);

INSERT INTO staff (academic_id,user_id, first_name, last_name) VALUES (1,17,"Pantelija","Nikolic"),(2,18,"Valentin","Popov"),
(1,19,"Ivica","Nikolic"),(2,20,"Uros","Grizman"),(1,21,"Djordje","Jaksic"),(2,22,"Vanja","Popov"),
(1,23,"Nikola","Vucic"),(2,24,"Urke","Pantelic"),(1,25,"Sasa","Suarez"),(2,26,"Valentin","Lazid");

INSERT INTO course (name) VALUES ("Poslovna Informatika"),("Web Design"),
("Osnovi Informatike"),("Web Programiranje"),("Itil Informatika"),("Osnove Programiranja"),
("Baze Podataka"),("Uvod u Objektno Programiranje");

INSERT INTO enrollment (course_id,student_id) VALUES (1,1),(1,2),(1,3),(1,4),(1,5),(2,1),(2,2),(2,3),(2,6),(2,7),
(3,3),(3,4),(3,5),(3,8),(3,9),(4,10),(4,11),(4,12),(4,13),(4,14),(4,15),(5,15),(5,14),(5,11),(5,12),(5,9),
(6,6),(6,7),(6,8),(6,9),(6,10),(6,11),(7,5),(7,8),(7,1),(8,2),(8,3),(8,4),(8,8),(8,12),(8,13);

INSERT INTO engagement (course_id,staff_id) VALUES (1,1),(1,2),(2,3),(2,4),(3,5),(3,2),(4,2),(4,7),(5,6),(5,7),
(6,8),(6,9),(7,10),(7,9),(8,9),(8,4);

set foreign_key_checks = 1;
