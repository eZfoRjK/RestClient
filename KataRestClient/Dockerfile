FROM openjdk:17-jdk-slim-buster
WORKDIR /app
COPY /out/artifacts/KataRestClient_jar/KataRestClient.jar /app/RestClient.jar
ENTRYPOINT ["java", "-jar", "RestClient.jar"]