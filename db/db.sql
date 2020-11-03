--  Many To One
--  название базы данных - many_to_one_lesson
CREATE DATABASE many_to_one_lesson ENCODING 'UTF-8';
-- название таблицы в конце - engines
CREATE TABLE IF NOT EXISTS engines (
--  поля в базе данных
  id    SERIAL PRIMARY KEY,
  model VARCHAR(25) NOT NULL,
  power INTEGER     NOT NULL
);

-- строка наполнения таблицы данными
INSERT INTO engines (model, power) VALUES ('model_test', 123);

-- таблица - engines вставлена в таблицу - cars
-- название таблицы в конце - cars
CREATE TABLE IF NOT EXISTS cars (
  id        SERIAL PRIMARY KEY,
  mark      VARCHAR(25) NOT NULL,
  model     VARCHAR(25) NOT NULL,
  engine_id INTEGER     NOT NULL,
  FOREIGN KEY (engine_id) REFERENCES engines (id)
);

-- строка наполнения таблицы данными
INSERT INTO  cars (mark, model, engine_id) VALUES ('car_test', 'test_model', 1);



/*

--Тоже самое но для MySQL
CREATE TABLE `many_to_one_lesson`.`engines` (
  `id`      INT NOT NULL AUTO_INCREMENT,
  `model`   VARCHAR(25) NOT NULL,
  `power`   INT NOT NULL,
  PRIMARY KEY (`id`)
  );

INSERT INTO `many_to_one_lesson`.`engines` (`id`, `model`, `power`) VALUES ('1', 'model_test', '123');

CREATE TABLE `many_to_one_lesson`.`cars` (
  `id`        INT NOT NULL AUTO_INCREMENT,
  `mark`      VARCHAR(25) NOT NULL,
  `model`     VARCHAR(25) NOT NULL,
  engine_id   INTEGER NOT NULL,
  FOREIGN KEY (`engine_id`) REFERENCES engines (`id`)
  );

INSERT INTO `many_to_one_lesson`.`cars` (`id`, `mark`, `model`, `engine_id`) VALUES ('1', 'car_test', 'test_model', '1');

*/