CREATE DATABASE STUTEST_YZC;

USE STUTEST_YZC;

CREATE TABLE STUDENT(
SNO NUMERIC(4) PRIMARY KEY IDENTITY(1001,1),    --学号
SNAME VARCHAR(20),  --姓名
GENDER CHAR(2), --性别
BIRTH DATE, --生日
PHOTO_URL VARCHAR(50)   --照片路径
);