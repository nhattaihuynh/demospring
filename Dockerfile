FROM openjdk:8u212-jdk-slim

VOLUME /tmp

EXPOSE 80808

ARG JAR_FILE=target/codestatebkend-0.0.1-SNAPSHOT.jar

ADD ${JAR_FILE} codestatebkend.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/codestatebkend.jar"]