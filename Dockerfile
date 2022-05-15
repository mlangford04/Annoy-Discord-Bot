FROM openjdk:17-oracle
ARG JAR_FILE=target/*.jar
COPY target/DonnieThornberry-0.0.1-SNAPSHOT.jar donnie-bot-app.jar
ENTRYPOINT ["java","-jar","/donnie-bot-app.jar"]