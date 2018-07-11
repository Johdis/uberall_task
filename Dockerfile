FROM openjdk:8u141-jdk
MAINTAINER chris.stefani@outlook.com

WORKDIR /app
COPY uberall/build.gradle uberall/gradlew /app/
COPY uberall/gradle/ /app/gradle/
COPY /uberall/ /app/

# Define the ENV variable
ENV BROWSER="chrome" \
    HOST="sandbox.uberall.com/en/app/uberall/login" \
    TIMEOUT="10"

CMD ["./gradlew", "clean", "test"]
