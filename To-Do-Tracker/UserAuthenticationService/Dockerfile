#openjdk is the docker image for Java Jdk application
FROM openjdk

#creating a working directory inside the docker container of the application
WORKDIR usr/lib

#copy the executable jar file that is created into the target folder and add it to the working directory created in the previous
ADD ./target/UserAuthenticationService-0.0.1-SNAPSHOT.jar /usr/lib/UserAuthenticationService-0.0.1-SNAPSHOT.jar

#Run the jar file using the java -jar command
ENTRYPOINT ["java","-jar","UserAuthenticationService-0.0.1-SNAPSHOT.jar"]

