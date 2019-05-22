use korzo;


insert into film (title, year, domestic, storage) values ("film1", 2019, false, "cd1");
insert into film (title, year, domestic, storage) values ("film2", 2010, true, "cd1");
insert into film (title, year, domestic, storage) values ("film3", 1994, true, "cd1");

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