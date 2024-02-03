FROM openjdk:17

CMD ["mvn", "clean", "install"]

COPY target/yallaShoot-0.0.1-SNAPSHOT.jar /app/yallaShoot-0.0.1-SNAPSHOT.jar

WORKDIR /app

CMD ["java", "-jar", "yallaShoot-0.0.1-SNAPSHOT"]

