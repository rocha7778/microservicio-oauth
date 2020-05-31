FROM openjdk:12
VOLUME /temp
EXPOSE 9100
ADD ./target/microservicio-oauth-0.0.1-SNAPSHOT.jar oauth.jar
ENTRYPOINT ["java", "-jar", "-Duser.timezone=America/Bogota",  "/oauth.jar"]docker 