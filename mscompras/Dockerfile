
FROM maven:3.6.3-openjdk-11 as build

COPY src /home/app/src
COPY pom.xml /home/app

RUN mvn -f /home/app/pom.xml clean package -Dmaven.test.skip=true

FROM openjdk:11
COPY --from=build /home/app/target/app.jar /user/local/lib/app.jar
EXPOSE 4000
ENTRYPOINT java -jar -Djava.security.egd=file:/dev/./urandom -jar -XX:MinRAMPercentage=70 -XX:MaxRAMPercentage=90 -server -XX:+OptimizeStringConcat -XX:+UseStringDeduplication /user/local/lib/app.jar
