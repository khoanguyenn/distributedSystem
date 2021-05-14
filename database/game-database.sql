USE distributedsystem;
CREATE TABLE IF NOT EXISTS auth (
	auth_id int,
    username VARCHAR(255),
    passwort VARCHAR(255)
);
INSERT INTO auth (auth_id, username, passwort) VALUES 
(1, "khoa", "khoa123"), (2, "nhu", "nhu123");