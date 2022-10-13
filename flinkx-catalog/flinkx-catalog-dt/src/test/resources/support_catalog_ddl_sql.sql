CREATE
CATALOG catalog1
WITH (
      'type' = 'DT',
      'default-database' = 'default_database',
      'driver' = 'com.mysql.cj.jdbc.Driver',
      'url' = 'jdbc:mysql://172.16.100.186:3306/catalog_default?autoReconnect=true&failOverReadOnly=false',
      'username' = 'drpeco',
      'password' = 'DT@Stack#123',
      'project-id' = '1',
      'tenant-id' = '1'
    );


create database if not exists catalog1.database1;
drop database if exists catalog1.database1;


CREATE TABLE if not exists catalog1.default_database.table1
(
    id      int,
    name    string,
    age     bigint,
    primary key ( id) not enforced
) with (
    'connector' = 'jdbc',
    'url' = 'jdbc:mysql://172.16.83.218:3306/wujuan?useSSL=false',
    'table-name' = 't2',
    'username' = 'drpeco',
    'password' = 'DT@Stack#123'
);
drop table if exists catalog1.default_database.table1;
ALTER TABLE catalog1.default_database.table1 RENAME TO table222;
ALTER TABLE catalog1.default_database.table1 SET ('table-name'='t2', 'url'='dbc:mysql://172.16.83.218:3306/wujuan?useSSL=false');


create database if not exists catalog1.database2;
CREATE TABLE if not exists catalog1.database2.table2
(
    id      int,
    name    string,
    age     bigint,
    primary key ( id) not enforced
) with (
 'connector' = 'print'
);

insert into catalog1.database2.table2 select * from catalog1.database1.table1