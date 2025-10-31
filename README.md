# Spring-Boot-Social-App


> **Not:** This project contains technologies I use for learning Java. You can find more complex projects in my repository.
A social media-like API project built with Spring Boot. To test the project, you can hit the endpoints directly. You can find a ready-to-use Postman collection in the root path.

## Features
- **User Management**: Registration, login, profile
- **Post System**: Create and list posts
- **Comment System**: Comment on posts
- **Like System**: Like posts
- **Activity Tracking**: View user activities

## Technologies
- Spring Boot
- Spring Data JPA
- MySQL
- Docker

## Prerequisites
- Java 21
- Docker (for MySQL)

## Getting Started

### 1. Start MySQL with Docker
```bash
docker run --name mysql-social -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=social-media -e MYSQL_USER=mysql-java -e MYSQL_PASSWORD=password -p 3307:3306 -d mysql:8.0
```

### 2. Run the Application
```bash
./mvnw spring-boot:run
```

## Configuration
1. Copy `application.properties.example` to `application.properties`
2. Update the JWT secrets in `application.properties`:
   ```properties
   questapp.app.secret=YOUR_SECRET_KEY_HERE
   questapp.app.expires-in=604800
   ```

## Database Configuration
The application is configured to connect to MySQL on port 3307 with:
- Database: `social-media`
- Username: `mysql-java`
- Password: `password`

**Note:** The application starts with an empty database. You can create users, posts, and comments through the API endpoints.
