# README

```
start db2-service
start eureka-service
start stock service
start routing service

eureka service
http://localhost:8302/
```



cd db2-service
./mvnw package
docker build . -t sbm-db2-service:v1  --build-arg JAR_FILE=target/db2-service-0.0.1-SNAPSHOT.jar



db-service

docker mysql

```
docker run  --name mysql101 -p 3306:3306 -e MYSQL_ROOT_PASSWORD=db-password -d mysql/mysql-server:latest

docker run --name mysql101 -p 3306:3306 -e MYSQL_ROOT_PASSWORD=db-password -d mysql/mysql-server:5.7

docker exec -it mysql101 mysql -uroot -p
mysql> create database db_example; -- Create the new database
mysql> create user 'springuser'@'localhost' identified by 'ThePassword'; -- Creates the user
mysql> grant all on db_example.* to 'springuser'@'localhost'; -- Gives all the privileges to the new user on the newly created database
```

```
#weird case where ubuntu 16.04 docker container
uses 172.17.0.1

docker run -p 3306:3306 -v /tmp:/tmp --name db --detach -e MYSQL_ROOT_PASSWORD="db-password" -e MYSQL_ROOT_HOST=% -e MYSQL_DATABASE=db_example -d mysql/mysql-server:5.6 --lower_case_table_names=1 --init-connect='GRANT CREATE USER ON *.* TO 'root'@'%';FLUSH PRIVILEGES;'

```


# Build UI

```
cd ui-v1
export SBM_UI_V1_SERVERNAME=<server name > e.g. http://example.com
yarn run build
```