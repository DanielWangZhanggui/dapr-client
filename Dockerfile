FROM openjdk:11.0.13-jre
ARG JAR_FILE=target/*.jar
COPY ./target/dapr-blob-exec.jar app.jar
ENTRYPOINT ["java" ,"-jar","/app.jar","--add-opens","java.base/jdk.internal.misc=ALL-UNNAMED", "-Dio.netty.tryReflectionSetAccessible=true"]