create database Mobilebanking;
use Mobilebanking;
create table user(
phoneno varchar (25) primary key ,
firstname varchar(50) ,
lastname varchar(50) ,
accno varchar(50)
);
insert into user values ("0934627307","Eyob","Kefale","101010","2020"),
("0962807299","Eyader","Tsehayu","101011","2021"),
("0902914386","Betsegaw","Abebe","101012","2022"),
("0934670767","Bereket","Tadele","101013","2023");
alter table user
add column pin varchar(10);

create table account(
accno varchar (50) primary key ,
balance varchar(50) 
);
insert into account values ("101010","12060"),
("101011","8765"),
("101012","9876"),
("101013","9000");
create table exchangerate(
id varchar (50) primary key ,
currency varchar(50) ,
buy varchar (50),
sell varchar(50)
);
ALTER TABLE user 
ADD 
CONSTRAINT FK_user FOREIGN KEY (accno) REFERENCES account (accno) ;

select * from account;
select* from user;
SELECT CONCAT(firstname, ' ', lastname) AS FullName
FROM user
WHERE phoneno =0902914386;

SELECT CONCAT(firstname, ' ', lastname) AS FullName FROM user WHERE phoneno =0962807299;

SELECT balance  
FROM account   
INNER JOIN user  
ON account.accno = user.accno
where phoneno = "0962807299" ;
SELECT firstname  FROM user  
                    INNER JOIN account ON (account.accno = user.accno)
                     WHERE user.accno= "101010";
                     
                     
   --  UPDATE account 
--     SET tutorial_title = 'Learning JAVA' 
-- WHERE tutorial_id = 3;

UPDATE account
SET balance=balance+10
where accno = "101010";

alter table account
modify column balance int; 

create table exchangerate(
name varchar (25) primary key ,
buy  varchar(50) ,
sell varchar(50) 
);
select * from exchangerate;
