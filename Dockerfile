FROM openjdk:17-jdk

ENV DEFAULTPASSWORD='password'

WORKDIR /app

EXPOSE 8080

COPY build/libs/RecipesMySQL-0.0.1.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]

