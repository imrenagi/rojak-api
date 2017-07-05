FROM java:8-jre
MAINTAINER Imre Nagi <imre.nagi2812@gmail.com>

ADD ./target/service-election.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/service-election.jar"]

EXPOSE 6001