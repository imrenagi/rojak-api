FROM java:8-jre

MAINTAINER Imre Nagi <imre.nagi2812@gmail.com>

EXPOSE 8080

ADD ./target/backend.jar /app/
CMD ["java","-Xmx1024m", "-jar", "/app/backend.jar"]


