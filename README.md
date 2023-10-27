MP4 Analyzer Coded by Asad Ahmed

This application is designed to analyze MP4 files from a provided URL and return a list of boxes found within the MP4 file.

Prerequisites
Ensure you have the following installed:

- Java 8 or higher
- Maven

To build the project, navigate to the project's root directory and run:
- mvn clean install

After building the project, you can start the application by running:
- java -jar target/mp4analyzer-0.0.1-SNAPSHOT.jar

To run and test the mp4 file you can open command prompt and run below command
- curl -X GET "http://localhost:8080/analyze?url=http://demo.castlabs.com/tmp/text0.mp4"