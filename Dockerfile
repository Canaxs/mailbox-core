FROM openjdk:17-oracle

COPY mailbox-0.0.1-SNAPSHOT.jar mailbox.jar

ENTRYPOINT ["java","-jar","/mailbox.jar"]