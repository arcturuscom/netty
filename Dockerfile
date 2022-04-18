FROM intranexus.lexisnexis.fr:8083/lnf/maven:3-jdk-8-alpine as BUILDER
WORKDIR /tmp/
COPY pom.xml /tmp/

#Resolve dependencies to use docker cache
RUN mvn -B dependency:resolve-plugins dependency:resolve
COPY src /tmp/src/
RUN mvn versions:set -DnewVersion=1.0
RUN mvn clean package

FROM openjdk:8-jre-alpine

EXPOSE 8080
COPY --from=BUILDER /tmp/target/netty-1.0.jar app.jar

# assumes that the config will be mounted as a volume at the root of the file system, next to the jar
ENTRYPOINT ["java", "-jar","/app.jar"]
