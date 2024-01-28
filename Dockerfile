FROM openjdk:21
ADD /target/insuranceService-0.0.1-SNAPSHOT.jar backend.jar
ENTRYPOINT ["java", "-jar", "backend.jar"]