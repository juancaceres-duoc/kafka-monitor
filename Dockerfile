FROM eclipse-temurin:22-jre
WORKDIR /app

COPY target/*.jar app.jar

COPY wallet /opt/oracle/wallet

EXPOSE 8080

ENV TNS_ADMIN=/opt/oracle/wallet
ENTRYPOINT ["java","-jar","app.jar"]