INSERT INTO appuser (name,username,password,status,role) VALUES ('user','user','05d49692b755f99c4504b510418efeeeebfd466892540f27acf9a31a326d6504','ACTIVE','ADMIN');

INSERT INTO badge (level,type,icon) VALUES (1,'1','first icon');

INSERT INTO userbadge (userid, badgeid) VALUES(1, 1);

INSERT INTO word (word, status) values ('a', 'ACTIVE');
INSERT INTO word (word, status) values ('b', 'ACTIVE');
INSERT INTO word (word, status) values ('c', 'ACTIVE');

INSERT INTO userword(userid, word, star, attempt, mastery, status) values(1, 'a', 'Y', 4, 4, 'ACTIVE');
