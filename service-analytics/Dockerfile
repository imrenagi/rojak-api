FROM java:8-jre
MAINTAINER Imre Nagi <imre.nagi2812@gmail.com>

ADD ./target/service-analytics.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/service-analytics.jar"]

EXPOSE 6002