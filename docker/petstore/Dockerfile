FROM openjdk:17

COPY target/springdoc-context-path-1.0-SNAPSHOT.jar /opt

CMD java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5050 -jar /opt/springdoc-context-path-1.0-SNAPSHOT.jar