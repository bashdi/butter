create table if not exists tblArtikel(
created timestamp default current_timestamp,
modified timestamp default current_timestamp on update current_timestamp,
id int auto_increment,
bezeichnung varchar(200) unique,
preis int DEFAULT 0,
bestand int DEFAULT 0,
mindestbestand int DEFAULT 0,
bestellbestand int DEFAULT 0,
primary key (id)
);