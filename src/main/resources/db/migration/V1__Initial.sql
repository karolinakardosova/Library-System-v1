drop table if exists BOOK cascade;
drop table if exists AUTHOR cascade;

create table AUTHOR
(
   author_id varchar(42)  primary key,
   name varchar(100) not null
);

create table BOOK
(
   id     varchar(42)  primary key,
   title varchar(100) not null,
   author_id varchar(42) references AUTHOR (author_id)
);



ALTER TABLE "BOOK"
ADD FOREIGN KEY ("author_id") REFERENCES "author" ("author_id");
