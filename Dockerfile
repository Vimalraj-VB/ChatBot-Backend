FROM openjdk:17
EXPOSE 8080
ADD target/chatbot-demo.war chatbot-demo.war
ENTRYPOINT ["java", "-jar", "/chatbot-demo.war"]
