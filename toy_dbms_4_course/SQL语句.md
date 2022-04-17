```SQL
1.创建数据库
create database testDb;
create database testDb2;
2.切换数据库
use testDb;
3.创建数据表
create table testTable (name varchar(10) notNull, age int notNull);
4.插入数据
insert into testTable (name, age) values (zhangsan, 23);
insert into testTable (name, age) values (lisi, 20);
insert into testTable (name, age) values (wangwu, 21);
5.查询数据
select * from testTable;
select name from testTable;
select age from testTable;
select age, name from testTable;
6.更新数据
update testTable set age = 20 where name = zhangsan;
7.删除数据
delete from testTable where name = zhangsan;
delete from testTable where age = 20;
8.统计数据库
show databases;
9.统计数据表
show tables;
10.显示数据表头、类型及约束条件
describe testTable;
11.删除数据表
drop table testTable;
12.删除数据库
drop database testDb;
13.帮助界面
help;
14.退出
quit;
```

