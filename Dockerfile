#FROM openjdk:17-alpine
#
#WORKDIR /opt
#
#ENV PORT 8080
#
#EXPOSE 8080
#
#COPY target/*.jar /opt/app.jar
#
#ENTRYPOINT exec java $JAVA_OPTS -jar app.jar


FROM openjdk:17-alpine
EXPOSE 8080
ADD target/ spring-boot-docker.jar
ENTRYPOINT ["java", "-jar", "/spring-boot-docker.jar"]