drop table if exists book;
drop table if exists dialog;
drop table if exists library;
create table if not exists library
(
    id      serial primary key,
    name    varchar(255),
    address varchar(255)
);
create table if not exists book
(
    id            serial primary key,
    name          varchar(255),
    year_of_issue integer,
    author        varchar(255),
    status        varchar(50),
    booked_by     varchar(50),
    library_id    bigint references library (id)

);

create table if not exists dialog
(
    id        serial primary key,
    chat_id   bigint,
    dialog_id int
);

insert into library(name, address)
values ('Библиотека №1', 'ул. Пушкина 11');
insert into library(name, address)
values ('Библиотека №2', 'ул. Иманова 37');
insert into library(name, address)
values ('Библиотека №3', 'ул. Кабанбай 45');
insert into library(name, address)
values ('Библиотека №4', 'ул. Кенесары 69');
insert into library(name, address)
values ('Библиотека №5', 'ул. Янушкеевича 1');

insert into book (library_id, status, name, year_of_issue, author)
values (1, 'IN_STOCK', 'Хочу и буду', 2021, 'М. Лабковский');
insert into book (library_id, status, name, year_of_issue, author)
values (2, 'IN_STOCK', 'Похищенная невеста для Его Наглейшества', 2022, 'Алисия Эванс');
insert into book (library_id, status, name, year_of_issue, author)
values (3, 'IN_STOCK', 'Похищенная невеста для Его Наглейшества', 2022, 'Алисия Эванс');
insert into book (library_id, status, name, year_of_issue, author)
values (4, 'IN_STOCK', 'Похищенная невеста для Его Наглейшества', 2022, 'Алисия Эванс');
insert into book (library_id, status, name, year_of_issue, author)
values (5, 'IN_STOCK', 'Похищенная невеста для Его Наглейшества', 2022, 'Алисия Эванс');
insert into book (library_id, status, name, year_of_issue, author)
values (2, 'IN_STOCK', 'Твари бермудского треугольника', 2021, 'Катарина Ховард');
insert into book (library_id, status, name, year_of_issue, author)
values (4, 'IN_STOCK', 'Домой на Рождество', 2011, 'Келли Тейлор');
insert into book (library_id, status, name, year_of_issue, author)
values (5, 'IN_STOCK', 'Домой на Рождество', 2011, 'Келли Тейлор');
insert into book (library_id, status, name, year_of_issue, author)
values (1, 'IN_STOCK', 'Война и мир', 1869, 'Лев Толстой');
insert into book (library_id, status, name, year_of_issue, author)
values (2, 'IN_STOCK', 'Война и мир', 1869, 'Лев Толстой');
insert into book (library_id, status, name, year_of_issue, author)
values (1, 'IN_STOCK', 'Война и мир', 1869, 'Лев Толстой');
insert into book (library_id, status, name, year_of_issue, author)
values (4, 'IN_STOCK', '1984', 1949, 'Джордж Оруэлл');
insert into book (library_id, status, author, name, year_of_issue)
values (5, 'IN_STOCK', 'Джеймс Джойс', 'Улисс', 1922);
insert into book (library_id, status, author, name, year_of_issue)
values (3, 'IN_STOCK', 'Владимир Набоков', 'Лолита', 1955);
insert into book (library_id, status, author, name, year_of_issue)
values (2, 'IN_STOCK', 'Владимир Набоков', 'Лолита', 1955);
insert into book (library_id, status, author, name, year_of_issue)
values (4, 'IN_STOCK', 'Уильям Фолкнер', 'Шум и ярость', 1929);
insert into book (library_id, status, author, name, year_of_issue)
values (3, 'IN_STOCK', 'Ральф Эллисон', 'Невидимка', 1952);
insert into book (library_id, status, author, name, year_of_issue)
values (1, 'IN_STOCK', 'Вирджиния Вульф', 'К маяку', 1927);
insert into book (library_id, status, author, name, year_of_issue)
values (2, 'IN_STOCK', 'Вирджиния Вульф', 'К маяку', 1927);
insert into book (library_id, status, author, name, year_of_issue)
values (5, 'IN_STOCK', 'Джейн Остин', 'Гордость и предубеждение', 1813);
insert into book (library_id, status, author, name, year_of_issue)
values (4, 'IN_STOCK', 'Данте Алигьери', 'Божественная комедия', 1321);
insert into book (library_id, status, author, name, year_of_issue)
values (3, 'IN_STOCK', 'Джоффри Чосер', 'Кентерберийские рассказы', 1400);
insert into book (library_id, status, author, name, year_of_issue)
values (2, 'IN_STOCK', 'Джоффри Чосер', 'Кентерберийские рассказы', 1400);
insert into book (library_id, status, author, name, year_of_issue)
values (1, 'IN_STOCK', 'Джоффри Чосер', 'Кентерберийские рассказы', 1400);
insert into book (library_id, status, author, name, year_of_issue)
values (1, 'IN_STOCK', 'Джонатан Свифт', 'Путешествия Гулливера', 1726);
