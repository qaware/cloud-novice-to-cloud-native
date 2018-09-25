FROM gradle:jdk8 as builder

COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle --no-daemon shadowJar

FROM openjdk:8-jre

ENV APPLICATION_USER ktor
RUN useradd -ms /bin/bash $APPLICATION_USER

USER $APPLICATION_USER
COPY --from=builder /home/gradle/src/build/libs/ada-lovelace-application.jar ada-lovelace-application.jar

EXPOSE 3000

CMD ["java", "-server", \
"-XX:+UnlockExperimentalVMOptions",\
"-XX:+UseCGroupMemoryLimitForHeap",\
"-XX:InitialRAMFraction=2",\
"-XX:MinRAMFraction=2",\
"-XX:MaxRAMFraction=2",\
"-XX:+UseG1GC",\
"-XX:MaxGCPauseMillis=100",\
"-XX:+UseStringDeduplication",\
"-jar", "ada-lovelace-application.jar"]
