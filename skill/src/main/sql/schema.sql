CREATE DATABASE seckill;

use seckill;


CREATE TABLE seckill(
  `seckill_id` BIGINT NOT NULL AUTO_INCREMENT COMMIT 'good id',
  `name` VARCHAR(120) NOT NULL COMMIT 'good name',
  `number` INT NOT NULL COMMIT 'good number',
  `start_time` TIMESTAMP NOT NULL 'skill start time',
  `end_time` TIMESTAMP NOT NULL 'skill end time',
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMIT 'create time',
  PRIMARY KEY (seckill_id)
  KEY idx_start_time(start_time),
  KEY idx_end_time(end_time),
  KEY idx_create_time(create_time)
)ENGINE=InnoDB AUTO_INCREMENT= 1000 DEFAULT CHARSET=utf8 COMMIT ='skill datebase table';


INSERT INTO
      seckill(name, number, start_time, end_time)
VALUES
  ('1000 iphone6', 100, '2005-11-01 00:00:00', '2026-11-02 00:00:00'),
  ('500 ipad2', 200, '2005-11-01 00:00:00', '2026-11-02 00:00:00'),
  ('300 mi4', 300, '2005-11-01 00:00:00', '2026-11-02 00:00:00'),
  ('200 mi note', 300, '2005-11-01 00:00:00', '2026-11-02 00:00:00');



CREATE TABLE success_kill(
 `seckill_id` bigint  NOT NULL COMMIT 'kill good',
 `user_phone` bigint NOT NULL 'user phone',
 `state` tinyint NOT NULL DEFAULT -1 COMMIT '-1 invaild; 0 success; 1 havepayment',
 `create_time` TIMESTAMP NOT NULL COMMIT 'create time',
 PRIMARY KEY (seckill_id, user_phone),
 KEY idx_create_time(create_time)
)ENGINE=InnoDB AUTO_INCREMENT= 1000 DEFAULT CHARSET=utf8 COMMIT ='skill datebase table success';



