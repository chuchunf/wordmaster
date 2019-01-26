INSERT INTO appuser (name,username,password,status,role) VALUES ('user','user','05d49692b755f99c4504b510418efeeeebfd466892540f27acf9a31a326d6504','ACTIVE','ADMIN');

INSERT INTO badge (level,type,icon) VALUES (1,'1','first icon');

INSERT INTO userbadge (userid, badgeid) VALUES(1, 1);

INSERT INTO word (word, status, seq) values ('a', 'ACTIVE', 1);
INSERT INTO word (word, status, seq) values ('b', 'ACTIVE', 2);
INSERT INTO word (word, status, seq) values ('c', 'ACTIVE', 3);

INSERT INTO userword(userid, word, star, attempt, mastery, status, updated) values(1, 'a', 'Y', 4, 2, 'ACTIVE', '20190101');

INSERT INTO entry(word, seqno, category) values ('a', '001', 'a category 1');
INSERT INTO entry(word, seqno, category) values ('a', '002', 'a category 2');
INSERT INTO entry(word, seqno, category) values ('b', '001', 'b category 1');

INSERT INTO sense(id, word, seqno, definition, thesaurus) values ('a001', 'a', '001', 'definition of a 001', 'link-sym-a1');
INSERT INTO sense(id, word, seqno, definition, thesaurus) values ('a001-2', 'a', '001', 'deifnition of a 001 also', 'link-sym-a2');
INSERT INTO sense(id, word, seqno, definition, thesaurus) values ('a002-1', 'a', '002', 'deifnition of a 002','link-sym-a21');
INSERT INTO sense(id, word, seqno, definition, thesaurus) values ('b001', 'b', '001', 'deifnition of b 001', 'link-sym-b1');

INSERT INTO synonym(linkid,synonym) VALUES ('link-sym-a1', 'sym-a1');
INSERT INTO synonym(linkid,synonym) VALUES ('link-sym-a2', 'sym-a2');

INSERT INTO antonym(linkid,antonym) VALUES ('link-sym-a1', 'antoym-a1');
INSERT INTO antonym(linkid,antonym) VALUES ('link-sym-a2', 'antoym-a2');

INSERT INTO matrix(word,relation,word2) VALUES ('a','derived','a-derived1');
INSERT INTO matrix(word,relation,word2) VALUES ('a','derived','a-derived2');
