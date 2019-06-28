use korzo;


insert into film (title, year, domestic, storage) values ("aabc la", 2019, false, "cd1");
insert into film (title, year, domestic, storage) values ("fbf la", 2010, true, "cd1");
insert into film (title, year, domestic, storage) values ("zzz", 1994, true, "cd1");
insert into film (title, year, domestic, storage) values ("bgr", 1999, false, "cd18");
insert into film (title, year, domestic, storage) values ("bafd", 2018, false, "cd81");
insert into film (title, year, domestic, storage) values ("cfgb", 2010, false, "cd91");

insert into genre (genre) values ("Comedy");
insert into genre (genre) values ("Drama");
insert into genre (genre) values ("Horror");
insert into genre (genre) values ("Animation");
insert into genre (genre) values ("Children's");


insert into filmgenre (film_id, genre_id) values (1, 1);
insert into filmgenre (film_id, genre_id) values (1, 4);
insert into filmgenre (film_id, genre_id) values (1, 5);

insert into filmgenre (film_id, genre_id) values (2, 1);
insert into filmgenre (film_id, genre_id) values (2, 3);

insert into filmgenre (film_id, genre_id) values (3, 2);

insert into filmgenre (film_id, genre_id) values (4, 4);
insert into filmgenre (film_id, genre_id) values (4, 2);

insert into filmgenre (film_id, genre_id) values (6, 1);
insert into filmgenre (film_id, genre_id) values (6, 2);
insert into filmgenre (film_id, genre_id) values (6, 3);
insert into filmgenre (film_id, genre_id) values (6, 4);
insert into filmgenre (film_id, genre_id) values (6, 5);