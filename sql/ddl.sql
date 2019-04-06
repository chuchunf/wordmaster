CREATE TABLE word (
    word        varchar(50) CONSTRAINT word_pk PRIMARY KEY,
    status      varchar(10) NOT NULL,
    seq         integer,
    alike1      varchar(50),
    alike2      varchar(50),
    alike3      varchar(50),
    definition1 varchar(400),
    definition2 varchar(400)
);

CREATE TABLE matrix (
    id          SERIAL CONSTRAINT matrix_pk PRIMARY KEY,
    word        varchar(50) REFERENCES word (word),
    relation    varchar(50),
    word2       varchar(50)
);

CREATE TABLE entry (
    word        varchar(50) REFERENCES word (word),
    seqno       varchar(50) NOT NULL,
    category    varchar(20) NOT NULL,
    origin      varchar(200),
    ftype       varchar(50),
    fvalue      varchar(50),
    stype       varchar(50),
    svalue      varchar(50),
    PRIMARY KEY (word, seqno)
);

CREATE TABLE sense (
    id          varchar(50) CONSTRAINT sense_pk PRIMARY KEY,
    word        varchar(50) NOT NULL,
    seqno       varchar(50) NOT NULL,
    thesaurus   varchar(50),
    domain      varchar(50),
    definition  varchar(400),
    FOREIGN KEY (word, seqno) REFERENCES entry (word, seqno)
);

CREATE TABLE sample (
    id          SERIAL CONSTRAINT sample_pk PRIMARY KEY,
    senseid     varchar(50) REFERENCES sense (id),
    text        varchar(400)
);

CREATE TABLE synonym (
    id          SERIAL CONSTRAINT synonym_pk PRIMARY KEY,
    linkid      varchar(50), -- this is actual a FK to sense.thesaurus
    synonym     varchar(50) NOT NULL
);

CREATE TABLE antonym (
    id          SERIAL CONSTRAINT antonym_pk PRIMARY KEY,
    linkid      varchar(50), -- this is actual a FK to sense.thesaurus
    antonym     varchar(50) NOT NULL
);

CREATE TABLE appuser (
    id         SERIAL CONSTRAINT appuser_pk PRIMARY KEY,
    name       varchar(100) UNIQUE,
    username   varchar(50) UNIQUE,
    password   varchar(100) NOT NULL,
    status     varchar(20) NOT NULL,
    role       varchar(20) NOT NULL,
    created    timestamp,
    lastlogin  timestamp
);

CREATE TABLE session (
    id         VARCHAR(8),
    userid     integer,
    practiced  integer,
    learned    integer,
    mastered   integer,
    PRIMARY KEY (id, userid),
    FOREIGN KEY (userid) REFERENCES appuser (id)
);

CREATE TABLE badge (
    id         SERIAL CONSTRAINT badge_pk PRIMARY KEY,
    level      integer NOT NULL,
    type       varchar(10) NOT NULL,
    icon       varchar(100) NOT NULL
);

CREATE TABLE userbadge (
    userid    integer,
    badgeid   integer,
    created   timestamp,
    PRIMARY KEY (userid, badgeid),
    FOREIGN KEY (userid) REFERENCES appuser (id),
    FOREIGN KEY (badgeid) REFERENCES badge (id)
);

CREATE TABLE userword (
   userid    integer,
   word      varchar(50),
   star      char(1),
   attempt   integer,
   mastery   integer,
   status    varchar(10),
   created   varchar(8),
   updated   varchar(8),
   PRIMARY KEY (userid, word),
   FOREIGN KEY (word) REFERENCES word(word),
   FOREIGN KEY (userid) REFERENCES appuser (id)
);

CREATE TABLE list (
   id       SERIAL CONSTRAINT list_pk PRIMARY KEY,
   title    varchar(200) UNIQUE,
   author   varchar(100),
   isbn     varchar(20)
);

CREATE TABLE listword (
   id       SERIAL CONSTRAINT listword_pk PRIMARY KEY,
   listid   integer,
   word     varchar(50),
   FOREIGN KEY (listid) REFERENCES list (id)
);
