#define base docker image
FROM openjdk:17-oracle
LABEL maintainer="mlangford04"
COPY target/DonnieThornberry-0.0.1-SNAPSHOT.jar donnie-bot-app.jar
ENTRYPOINT ["java","-jar","donnie-bot-app.jar"]