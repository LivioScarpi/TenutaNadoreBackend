FROM openjdk:17-oracle
VOLUME /tmp
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
COPY build/libs/google-drive-examples-0.0.1-SNAPSHOT.jar googledrive.jar
EXPOSE 8081
#ENTRYPOINT exec java $JAVA_OPTS -jar googledrive.jar
# For Spring-Boot project, use the entrypoint below to reduce Tomcat startup time.
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar googledrive.jar


# FROM openjdk:17-oracle
# EXPOSE 8081

# ARG DEPENDENCY=target
# ADD ${DEPENDENCY}/*.jar googledrive.jar
# ENTRYPOINT ["java","-jar","googledrive.jar"]