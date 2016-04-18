CREATE TABLE IF NOT EXISTS ratings (
  `user_id` INT NOT NULL,
  `movie_id` VARCHAR(45) NOT NULL,
  `rating` FLOAT NULL,
  `timestamp` BIGINT NULL,
  PRIMARY KEY (`user_id`, `movie_id`));


insert into ratings(user_id,movie_id,rating,timestamp) values(1,11,8.0,1460944234);
