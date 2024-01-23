FROM openjdk:21


CMD ["mvn", "clean", "install"]

COPY target/*jar /app/yallaShoot.jar

COPY wait-for-it.sh /app/wait-for-it.sh

RUN chmod +x /app/wait-for-it.sh

WORKDIR /app

CMD ["java", "-jar", "yallaShoot.jar"]
