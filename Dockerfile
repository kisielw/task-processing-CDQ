FROM openjdk:18.0.1.1-jdk
ADD target/task-processing-CDQ.jar .
EXPOSE 8000
CMD java -jar task-processing-CDQ.jar