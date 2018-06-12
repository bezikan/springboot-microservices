# README

spring boot microservice experiments

db-service collects user data
stock-service queries yahooAPI
eureka registers other services
zuul acts as a proxy
ui talks to proxy and is routed accordingly


# Deploying

## Build UI

```
cd ui-v1
cat > .env
#where spring boot zuul router-service is located
REACT_APP_SBM_SERVERNAME=<server name > e.g. http://api.example.com
yarn run build
```

## Launch Docker Java JarsRun docker compose file

```

./builddockerfiles.sh

#where your index.html from previous build step is being served
export FRONTEND_HOSTNAME=<server name>  http://example.com

docker-compose up -d
```



# Local Dev in IDE...
```
start db2-service
start eureka-service
start stock service
start routing service

eureka service
http://localhost:8302/
```
