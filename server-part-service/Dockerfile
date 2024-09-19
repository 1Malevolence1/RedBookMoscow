FROM amazoncorretto:21
WORKDIR /opt/service/server-part-service
ADD ./target/*.jar server-part-service.jar
ENTRYPOINT java -jar server-part-service.jar
