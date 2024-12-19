FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
COPY target/reading-novel-platform-api-0.0.1-SNAPSHOT.jar web-novel-api.jar
EXPOSE 3306
ENTRYPOINT exec java $JAVA_OPTS -jar web-novel-api.jar
# For Spring-Boot project, use the entrypoint below to reduce Tomcat startup time.
#ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar web-novel-api.jar
