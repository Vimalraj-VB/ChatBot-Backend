FROM openjdk:17
EXPOSE 8080
ADD target/chatbot-demo.jar chatbot-demo.jar
ENTRYPOINT [ "java","-jar","/chatbot-demo.jar" ]
