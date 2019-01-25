INSERT INTO appuser (name,username,password,status,role) VALUES ('user','user','05d49692b755f99c4504b510418efeeeebfd466892540f27acf9a31a326d6504','ACTIVE','ADMIN');

INSERT INTO badge (level,type,icon) VALUES (1,'1','first icon');

INSERT INTO userbadge (userid, badgeid) VALUES(1, 1);

INSERT INTO word (word, status) values ('a', 'ACTIVE');
INSERT INTO word (word, status) values ('b', 'ACTIVE');
INSERT INTO word (word, status) values ('c', 'ACTIVE');

INSERT INTO userword(userid, word, star, attempt, mastery, status, updated) values(1, 'a', 'Y', 4, 2, 'ACTIVE', '20190101');

INSERT INTO entry(word, seqno, category) values ('a', '001', 'a category 1');
INSERT INTO entry(word, seqno, category) values ('a', '002', 'a category 2');

INSERT INTO sense(id, word, seqno, definition) values ('a001', 'a', '001', 'definition of a 001');
INSERT INTO sense(id, word, seqno, definition) values ('a001-2', 'a', '001', 'deifnition of a 001 also');
INSERT INTO sense(id, word, seqno, definition) values ('a002-1', 'a', '002', 'deifnition of a 002');


