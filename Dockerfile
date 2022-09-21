FROM openjdk:17-jdk

ENV DEFAULTPASSWORD='admin'
ENV URLDB=''

WORKDIR /app

COPY build/libs/RecipesMySQL-0.0.1.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]

