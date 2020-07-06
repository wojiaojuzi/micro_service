create database springcloud;
use springcloud;
#user
CREATE TABLE user (
  username varchar(255) DEFAULT NULL,
  password varchar(255) DEFAULT NULL,
  create_at varchar(255) DEFAULT NULL,
  token_create_at varchar(255) DEFAULT NULL,
  id int(11) NOT NULL AUTO_INCREMENT,
  LoginToken varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);
INSERT INTO user(username,password,create_at,token_create_at,LoginToken) VALUES('guoxidong',1111111,null,null,null);
INSERT INTO user(username,password,create_at,token_create_at,LoginToken) VALUES('duanyingwen',1111111,null,null,null);
INSERT INTO user(username,password,create_at,token_create_at,LoginToken) VALUES('郭熙东',1111111,null,null,null);

#device
CREATE TABLE device (
  device_no int(11) NOT NULL,
  device_status boolean DEFAULT false,
  uid varchar(20) DEFAULT NULL,
  device_type varchar(20) DEFAULT NULL,
  create_at varchar(45) DEFAULT NULL,
  user_name varchar(45) DEFAULT NULL,
  PRIMARY KEY (`device_no`)
);
INSERT INTO device(device_no,device_status,uid,device_type,create_at,user_name) VALUES(1,false,1,'pad',null,'郭熙东');
INSERT INTO device(device_no,device_status,uid,device_type,create_at,user_name) VALUES(2,false,2,'phone',null,'段应文');

#prisoner
CREATE TABLE prisoner (
  id int(11) NOT NULL AUTO_INCREMENT,
  prisoner_id varchar(20) DEFAULT NULL,
  prisoner_name varchar(20) DEFAULT NULL,
  create_at varchar(255) DEFAULT NULL,
  age varchar(20) DEFAULT NULL,
  gender varchar(20) DEFAULT NULL,
  height varchar(20) DEFAULT NULL,
  weight varchar(20) DEFAULT NULL,
  crime varchar(20) DEFAULT NULL,
  criminal_record varchar(20) DEFAULT NULL,
  comment varchar(20) DEFAULT NULL,
  user_name varchar(20) DEFAULT NULL,
  feature varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
);
INSERT INTO prisoner(prisoner_id,prisoner_name,age,gender,height,weight,crime,user_name) VALUES(1,'马加爵',18,'man',180,180,'虾仁','郭熙东');
INSERT INTO prisoner(prisoner_id,prisoner_name,age,gender,height,weight,crime,user_name) VALUES(2,'宋春亮',18,'woman',180,180,'commit','段应文');

#task
CREATE TABLE task (
  task_no int(11) NOT NULL AUTO_INCREMENT,
  car_no varchar(45) DEFAULT NULL,
  prisoner_name varchar(45) DEFAULT NULL,
  user_name varchar(45) DEFAULT NULL,
  detail varchar(255) DEFAULT NULL,
  PRIMARY KEY (`task_no`)
);
INSERT INTO task(task_no,car_no,prisoner_name,user_name,detail) VALUES(1,'京A0000','马加爵','郭熙东','送到北邮');
INSERT INTO task(task_no,car_no,prisoner_name,user_name,detail) VALUES(2,'豫A1111','宋春亮','段应文','送到武汉');

#bracelet
CREATE TABLE bracelet (
  bracelet_no varchar(20) NOT NULL,
  prisoner_id varchar(20) DEFAULT NULL,
  device_no varchar(20) DEFAULT NULL,
  device_status boolean DEFAULT false,
  PRIMARY KEY (`bracelet_no`)
);
INSERT INTO bracelet(bracelet_no,prisoner_id,device_no) VALUES(1,1,1);
INSERT INTO bracelet(bracelet_no,prisoner_id,device_no) VALUES(2,2,2);
INSERT INTO bracelet(bracelet_no,prisoner_id,device_no) VALUES(3,3,3);

#vervel
CREATE TABLE vervel (
  vervel_no varchar(20) NOT NULL,
  device_status boolean DEFAULT false,
  device_no varchar(20) DEFAULT NULL,
  prisoner_id varchar(20) DEFAULT NULL,
  PRIMARY KEY (`vervel_no`)
);
INSERT INTO vervel(vervel_no,prisoner_id,device_no) VALUES(1,1,1);
INSERT INTO vervel(vervel_no,prisoner_id,device_no) VALUES(2,2,2);
INSERT INTO vervel(vervel_no,prisoner_id,device_no) VALUES(3,3,3);
