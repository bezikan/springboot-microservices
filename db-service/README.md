# README


docker mysql 
```bash
docker run -d --name mysql101 -p 3306:3306 -e MYSQL_ROOT_PASSWORD=db-password -v /my/own/datadir:/var/lib/mysql mysql
docker exec -it mysql101 mysql -uroot -p
```

```
mysql> create database db_example; -- Create the new database
mysql> create user 'springuser'@'localhost' identified by 'ThePassword'; -- Creates the user
mysql> grant all on db_example.* to 'springuser'@'localhost'; -- Gives all the privileges to the new user on the newly created database
```