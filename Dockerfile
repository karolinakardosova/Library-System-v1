FROM adoptopenjdk/openjdk11:jre-11.0.8_10-alpine
COPY target/library-system-0.0.1-SNAPSHOT.jar /library-system-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar","/library-system-0.0.1-SNAPSHOT.jar"]