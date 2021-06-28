FROM adoptopenjdk/openjdk14:ubuntu-jre
RUN apt-get update
RUN apt-get install -y maven
WORKDIR /Users/patrick/Desktop/scrapeBot2/
COPY pom.xml /Users/patrick/Desktop/scrapeBot2/
COPY src /Users/patrick/Desktop/scrapeBot2/src/
RUN mvn clean package
CMD ["java" ,"-jar","target/JAVABOT-1.0-SNAPSHOT-jar-with-dependencies.jar"]
