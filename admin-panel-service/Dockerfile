FROM amazoncorretto:21
WORKDIR /opt/service/admin-service
ADD ./target/*.jar admin-panel-service.jar
ENTRYPOINT java -jar admin-panel-service.jar
