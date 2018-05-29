# Тестовое задание на Spring MVC Java

Тестовое задание для Силикон Еуроп - реализация веб-приложения, предоставляющее ограниченную функциональность системы управления складом товаров.

## Getting Started

Для запуска проекта:

### Заполнение базы данных

База данных: PostgreSQL

Выполнить скрипт

```
sql-database-create-script.sql
```

Или выполнить следующей код

```
DROP SCHEMA IF EXISTS spring CASCADE;
CREATE SCHEMA spring;

DROP TABLE IF EXISTS spring.categories;
CREATE TABLE spring.categories
(
   name character varying(45) NOT NULL, 
   description text, 
   CONSTRAINT pk_cat_name PRIMARY KEY (name)
) 
WITH (
  OIDS = FALSE
)
;

DROP TABLE IF EXISTS spring.items;
CREATE TABLE spring.items
(
  category character varying(45) NOT NULL,
  img character varying(255),
  name character varying(255) NOT NULL,
  description text,
  cost real NOT NULL DEFAULT 0,
  amount integer NOT NULL,
  CONSTRAINT pk_items_catname PRIMARY KEY (category, name),
  CONSTRAINT fk_items_category FOREIGN KEY (category)
      REFERENCES spring.categories (name) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT chk_negative_amount CHECK (amount >= 0),
  CONSTRAINT chk_negative_cost CHECK (cost >= 0::double precision)
)
WITH (
  OIDS=FALSE
);

INSERT INTO spring.categories(
            name, description)
    VALUES ('Аудиотехника', NULL),
	   ('Игры', NULL),
	   ('Ноутбуки', 'Включает ноутбуки и аксессуары для них'),
	   ('Планшеты', 'Планшет, или планшетный персональный компьютер (Tablet PC) – это новый взгляд на переносные компьютеры, их возможности и удобство. Время громоздких ноутбуков безвозвратно уходит в прошлое. У планшета нет громоздкого корпуса, с открывающейся крышкой-монитором, как у ноутбуков. Минимальные размеры и вес, делают его незаменимым не только для бизнеса и путешествий, планшетный ПК можно смело назвать папкой делового человека, который всегда носит с собой все свои важные документы. А на фоне современного развития беспроводных технологий доступа в Интернет, планшет позволит быть вам всегда в курсе всех новостей, знать о событиях в мире, вести свои дела, участвовать в конференциях, где бы вы ни находились.'),
	   ('Телевизоры',NULL),
	   ('Телефоны',NULL),
	   ('Фотоаппаратура',NULL);
	   
INSERT INTO spring.items(
            category, img, name, description, cost, amount)
    VALUES ('Аудиотехника', NULL, 'ATH-PRO5MK3', NULL, 8000, 6),
	   ('Аудиотехника', NULL, 'SE-MJ771BT-K', NULL, 8300, 12),
	   ('Аудиотехника', NULL, 'SE-MJ771BT-W', NULL, 8300, 7),
	   ('Аудиотехника', NULL, 'HD 559', NULL, 8400, 3),
	   ('Аудиотехника', NULL, 'HD 380 PRO', NULL, 9000, 0),
	   ('Аудиотехника', NULL, 'ATH-SR5', NULL, 9200, 2),
	   ('Аудиотехника', NULL, 'HD 2.30G', NULL, 5300, 20),
	   ('Аудиотехника', NULL, 'HD 200', NULL, 5200, 4),
	   ('Аудиотехника', NULL, 'RS 110 II', NULL, 5300, 7),
	   ('Аудиотехника', NULL, 'T450BT', NULL, 3300, 15),
	   ('Аудиотехника', NULL, 'HD 2.20S', NULL, 4250, 12),
	   ('Аудиотехника', NULL, 'SE-MJ553BT', NULL, 4300, 0),
	   ('Аудиотехника', NULL, 'ATH-M20X', NULL, 4850, 3),
	   ('Игры', NULL, 'Средиземье:Тени войны', NULL, 999, 6),
	   ('Игры', NULL, 'Wolfenstein II: The New Colossus', NULL, 900, 3),
	   ('Игры', NULL, 'Kingdom Come: Deliverance', NULL, 1000, 4),
	   ('Игры', NULL, 'Surviving Mars', NULL, 1700, 3),
	   ('Игры', NULL, 'Call of Duty: WWII', NULL, 1200, 5),
	   ('Игры', NULL, 'Need for Speed Payback', NULL, 2000, 10),
	   ('Ноутбуки', NULL, 'TMB118-R-C9JG', NULL, 24000, 0),
	   ('Ноутбуки', NULL, 'MacBook Air', NULL, 50000, 2),
	   ('Ноутбуки', NULL, '710-11IKB', NULL, 50000, 0),
	   ('Ноутбуки', NULL, 'NB137', NULL, 15000, 1),
	   ('Ноутбуки', NULL, 'UX370UA-C4224T', NULL, 100000, 1),
	   ('Планшеты', NULL, 'Air A7', NULL, 3000,4),
	   ('Планшеты', NULL, 'Z581KL-1A021A', NULL, 18000, 7),
	   ('Планшеты', NULL, 'MUZE 3718', NULL, 6000, 5),
	   ('Телевизоры', NULL, 'Harper 19R470', NULL, 7500, 5), 
	   ('Телевизоры', NULL, 'Thomson T19RTE1060', NULL, 9000,3),
	   ('Телевизоры', NULL, 'LG 28LK480U', NULL, 17500, 1),
	   ('Телевизоры', NULL, 'Akira 32LEC05T2S', NULL, 12500,1),
	   ('Телефоны', NULL, 'Galaxy S9+', NULL, 66999,4),
	   ('Телефоны', NULL, '9 Lite', NULL, 15000,7),
	   ('Телефоны', NULL, 'iPhone X', NULL, 76999,15),
	   ('Фотоаппаратура', NULL, 'D610', NULL, 90000,3),
	   ('Фотоаппаратура', NULL, 'EOS 6D', NULL, 80000,4),
	   ('Фотоаппаратура', NULL, 'D750', NULL, 120000,2),
	   ('Фотоаппаратура', NULL, 'EOS 700D', NULL, 46000,5);
	   



```

### Картинки предметов

Картинки располагаются по пути

```
./resource/imgs/
```

Имеют следующую структуру:
Название категории + название предмета + расширение

```
АудиотехникаMX20.jpg
```
