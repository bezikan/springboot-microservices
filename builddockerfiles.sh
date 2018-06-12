## declare an array variable
declare -a arr=("db2-service" "eureka-service" "routing-service" "stock-service")

## now loop through the above array
for i in "${arr[@]}"
do
   echo "$i"
   cd "$i"
   ./mvnw package
   docker build . -t sbm/"$i":v1  --build-arg JAR_FILE=target/"$i"-0.0.1-SNAPSHOT.jar
   cd ..

done