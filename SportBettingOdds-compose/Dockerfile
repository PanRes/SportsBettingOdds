FROM openjdk:11
ARG JAR_FILE=./target/*.jar
COPY ${JAR_FILE} SportBetttingOdds.jar
ENTRYPOINT ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8013", "-jar", "/SportBetttingOdds.jar"]
