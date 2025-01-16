FROM openjdk:17
#VOLUME /tmp
#EXPOSE 8080
COPY target/tickets-0.0.1-SNAPSHOT.jar tickets.jar
ENTRYPOINT ["java","-jar","/tickets.jar"]
