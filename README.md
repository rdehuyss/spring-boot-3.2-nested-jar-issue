## Repo to show issue with Nested Jars

How to reproduce:
- mvn clean install
- java -jar target/SpringBoot3.2-1.0-SNAPSHOT.jar
- visit http://localhost:8080/get/from/nested/jar

Exception is console: `java.nio.file.NotDirectoryException` whereas the provided Path is a Directory.