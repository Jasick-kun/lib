create table if not exists book
(
    id            serial primary key,
    name          varchar(255),
    year_of_issue integer,
    author        varchar(255),
    location_id   integer references location (id)
);

CREATE table if not exists location
(
    id           serial primary key,
    shelf_rack   integer,
    shelf        integer,
    order_number integer
);


insert into location (shelf_rack, shelf, order_number)
VALUES (4, 3, 6);
insert into location (shelf_rack, shelf, order_number)
VALUES (7, 3, 15);
insert into location (shelf_rack, shelf, order_number)
VALUES (9, 7, 26);
insert into location (shelf_rack, shelf, order_number)
VALUES (15, 4, 23);
insert into location (shelf_rack, shelf, order_number)
VALUES (23, 6, 21);
insert into location (shelf_rack, shelf, order_number)
VALUES (1, 1, 13);
insert into location (shelf_rack, shelf, order_number)
VALUES (7, 2, 6);
insert into location (shelf_rack, shelf, order_number)
VALUES (4, 1, 24);
insert into location (shelf_rack, shelf, order_number)
VALUES (2, 4, 3);
insert into location (shelf_rack, shelf, order_number)
VALUES (6, 4, 1);
insert into location (shelf_rack, shelf, order_number)
VALUES (14, 6, 12);
insert into location (shelf_rack, shelf, order_number)
VALUES (17, 3, 12);
insert into location (shelf_rack, shelf, order_number)
VALUES (13, 12, 14);
insert into location (shelf_rack, shelf, order_number)
VALUES (1, 6, 4);
insert into location (shelf_rack, shelf, order_number)
VALUES (4, 12, 4);

insert into book (name, year_of_issue, author, location_id)
values ('Хочу и буду', 2021, 'М. Лабковский', 1);
insert into book (name, year_of_issue, author, location_id)
values ('Похищенная невеста для Его Наглейшества', 2022, 'Алисия Эванс', 2);
insert into book (name, year_of_issue, author, location_id)
values ('Твари бермудского треугольника', 2021, 'Катарина Ховард', 3);
insert into book (name, year_of_issue, author, location_id)
values ('Домой на Рождество', 2011, 'Келли Тейлор', 4);
insert into book (name, year_of_issue, author, location_id)
values ('Война и мир', 1869, 'Лев Толстой', 5);
insert into book (name, year_of_issue, author, location_id)
values ('1984', 1949, 'Джордж Оруэлл', 6);
insert into book (author, name, year_of_issue, location_id)
values ('Джеймс Джойс', 'Улисс', 1922, 7);
insert into book (author, name, year_of_issue, location_id)
values ('Владимир Набоков', 'Лолита', 1955, 8);
insert into book (author, name, year_of_issue, location_id)
values ('Уильям Фолкнер', 'Шум и ярость', 1929, 9);
insert into book (author, name, year_of_issue, location_id)
values ('Ральф Эллисон', 'Невидимка', 1952, 10);
insert into book (author, name, year_of_issue, location_id)
values ('Вирджиния Вульф', 'К маяку', 1927, 11);
insert into book (author, name, year_of_issue, location_id)
values ('Джейн Остин', 'Гордость и предубеждение', 1813, 12);
insert into book (author, name, year_of_issue, location_id)
values ('Данте Алигьери', 'Божественная комедия', 1321, 13);
insert into book (author, name, year_of_issue, location_id)
values ('Джоффри Чосер', 'Кентерберийские рассказы', 1400, 14);
insert into book (author, name, year_of_issue, location_id)
values ('Джонатан Свифт', 'Путешествия Гулливера', 1726, 15);
