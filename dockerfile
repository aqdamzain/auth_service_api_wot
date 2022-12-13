FROM maven:latest

COPY . /app

EXPOSE 8080

CMD [ "mvn", "-f", "/app/pom.xml", "spring-boot:run"]

# docker build --tag webthing-auth:1.0 .

# docker network create webthing_network

# docker container create --name webthing-auth -p 8080:8080 -e SC_NETWORK=postgres-container -e PG_DATABASE=webthing_db -e DB_USERNAME=noir -e DB_PASSWORD=skripsi20222023 webthing-auth:1.0

# docker network connect webthing_network webthing-auth