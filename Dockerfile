FROM openjdk:8-jre-alpine
COPY target/virtual-anm-1.0.0.jar virtual-anm-1.0.0.jar
ENTRYPOINT ["java","-jar","/virtual-anm-1.0.0.jar"]