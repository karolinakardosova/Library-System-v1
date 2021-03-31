[![Java11](https://img.shields.io/badge/java-11-blue)](https://img.shields.io/badge/java-11-blue)
[![Maven](https://img.shields.io/badge/maven-3.6-blue)](https://img.shields.io/badge/maven-3.6-blue)
![Build and Test](https://github.com/jveverka/java-boot-camp/workflows/Build%20and%20Test/badge.svg)

This project is simple REST API for managing a library system where it is possible to search, insert, edit and delete books stored in the database. Every book has its author/s and tags which can be used to search for a specific book.

###IDE required :
* IntelliJ IDEA
* Docker Desktop
* PostgreSQL

###Minimal software requirements:
* OpenJDK 11 
* Maven 3.6.3 (or later)

###OpenAPI v3 docs:
* http://localhost:8100/v3/api-docs
* http://localhost:8100/v3/api-docs.yaml

###Swagger UI
* http://localhost:8100/swagger-ui/index.html?url=/v3/api-docs



###Commands for building and running this project:
- it is required to have Pg Admin localhost running
```
mvn clean install -Dmaven.test.skip=true
java -jar library-system-0.0.1-SNAPSHOT.jar
```

###Commands for building an image and running a docker:
```
docker pull postgres
docker run  --name postgres-cont -e POSTGRES_PASSWORD=hhbs77 -e POSTGRES_USER=postgres -e POSTGRES_DB=postgres -d -p 5432:5432 postgres:latest 
docker build -t library-system:1.0 .
docker run -p 8100:8100 library-system:1.0 --link postgres-cont1
docker-compose up --build
```

####Post Author example:
* http://localhost:8100/authors
```
{"name": "J. R. R. Tolkien"}
```

####Update Author example:
* http://localhost:8100/authors/1
```
{"name": "John Ronald Reuel Tolkien"}
```

####Post Book example:
* http://localhost:8100/books
```
{"title" : "Lord of the Rings", "authorsId" : [1] }
```

####Update Book example:
* http://localhost:8100/books/1
```
{"title" : "Lord of the Rings : The Fellowship of the Ring", "authorsId" : [1] }
```
