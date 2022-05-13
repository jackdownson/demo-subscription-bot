FROM adoptopenjdk/openjdk11:alpine-jre 
ARG JAR_FILE=build/libs/demobot-0.0.1-SNAPSHOT.jar
WORKDIR /opt/kube-app-rest
COPY ${JAR_FILE} demobot-0.0.1-SNAPSHOT.jar
EXPOSE 5000
ENTRYPOINT ["java", "-jar", "demobot-0.0.1-SNAPSHOT.jar"]