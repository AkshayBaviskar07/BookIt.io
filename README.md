# BookIt.io - A Ticket Booking Service

## Introduction
> BookIt.io is a train ticket booking application based on Spring Boot based microservice web application designed for Book, cancel tickets. It can enroll Passengers and shows details about trains.

## Prerequisites

- Java 8 or later
- Maven
- Basic understanding of Spring Boot and microservices architecture
- Testing tools Junit and Mockito, Postman (API's testing)
- Docker

## Microservices

1. **Api-Gateway**: Acts as the single entry point for all API requests, routing them to the appropriate microservices.
2. **Service-Registry**: Enables service discovery, allowing microservices to locate each other dynamically.
3. **Passengers**: Handles CRUD (create, read, update and delete) operations on passengers and provide RESTful service for managing data.
4. **Train**: Manages Train data and performs CRUD operations on them to display details.
5. **Ticket**: Service provides operation to manage ticket related operations.

## Installation

**Clone this repository:**
```Bash
git clone https://github.com/AkshayBaviskar07/BookIt.io
```

**Install dependencies:**
```Bash
mvn install
```

### Running the Application

1. Start the service-registry (Eureka Server):
```Bash
mvn spring-boot:run -p service-reg
```

2. Start the gateway:
```Bash
mvn spring-boot:run -p gateway
```

3. Start the passenger-service:
```Bash
mvn spring-boot:run -p passenger
```

4. Start the train-service:
```Bash
mvn spring-boot:run -p train
```

5. Start the ticket-service:
```Bash
mvn spring-boot:run -p ticket
```

Once all services are running, you can interact with the application through the API gateway using tools like Postman.

## Health Checks:

Spring Actuator exposes health endpoints at the following paths:

- `/health`: Overall application health
- `/info`: This endpoint provides information about your application, such as the version and build details.
-  `/metrics`: This endpoint provides metrics about your application, such as CPU usage and memory usage.

## Development

1. Clone the repository as mentioned above.
2. Install dependencies using `mvn install`.
3. Use an IDE like Spring Tool Suite or IntelliJ IDEA for development.
4. Start the services as shown in the "Running the Application" section.
5. Make code changes and test them using JUnit and Mockito.
6. Commit your changes and push them to your remote repository.

## API Testing

The application's APIs are thoroughly tested using Postman, a popular API client. Postman allows for sending various HTTP requests (GET, POST, PUT, DELETE) with customizable headers, body payloads, and query parameters. This ensures that the APIs function as intended and handle diverse input scenarios.

### API Documentation

***For documentation api please refer to following links:***

1. [Gateway](https://web.postman.co/workspace/Ticket-Booking~b125440a-1729-4a9d-8c27-15feb09ab6f1/collection/31938115-c0075ab8-9260-497b-860e-229650eead96?action=share&source=copy-link&creator=31938115): Provides documentation about gateway services.
2. [Passenger](https://web.postman.co/workspace/Ticket-Booking~b125440a-1729-4a9d-8c27-15feb09ab6f1/collection/31938115-d5ceafd6-fa18-42b6-a2de-ee0597079f3e?action=share&source=copy-link&creator=31938115): Provides documentation about passenger-service.
3. [Train](https://web.postman.co/workspace/Ticket-Booking~b125440a-1729-4a9d-8c27-15feb09ab6f1/collection/31938115-50d999c6-56c8-4d30-89be-a7aed0457e25?action=share&source=copy-link&creator=31938115): Provides documentation about train-service.
4. [Ticket](https://web.postman.co/workspace/Ticket-Booking~b125440a-1729-4a9d-8c27-15feb09ab6f1/collection/31938115-ca63ce9b-5b74-4b08-8d49-636cf0d47258?action=share&source=copy-link&creator=31938115): Provides documentation about ticket-service.


