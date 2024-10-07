# Junk Drawer

## Overview

The Junk Drawer is a Spring Boot backed application that provides a RESTful API for  randomly generated non-unique data.

## Project Structure

The project is structured as follows:

- **controller**: Contains REST controllers.
- **model**: Contains entity classes.
- **dto**: Contains data transfer objects.
- **persistance\repository**: Contains Spring Data JPA repositories.
- **persistance\model**: Contains Spring Data JPA repositories.
- **service**: Contains service classes.
- **service\batch**: Contains batch configuration, for bulk data generation.
- **configuration**: Contains configuration files.
- **util**: Contains different utilities used in data generation.

## Prerequisites

- Java 17
- Maven
- Docker and Docker Compose

## Running the Application

### Using Docker Compose

1. Build and start the application using Docker Compose:

    ```sh
    docker-compose up --build
    ```

2. The application will be available at `http://localhost:8080`.

### Running Locally

1. Start the MySQL database:

    ```sh
    docker-compose up mysql
    ```

2. Build the application:

    ```sh
    mvn clean install
    ```

3. Run the application:

    ```sh
    mvn spring-boot:run
    ```

4. The application will be available at `http://localhost:8080`.

## Configuration

The application uses different profiles for different environments. The profiles are defined in the following files:

- `application.properties`: Common properties.
- `application-dev.properties`: Development environment properties.
- `application-local.properties`: Local testing properties.

You can specify the active profile using the `SPRING_PROFILES_ACTIVE` environment variable.

# API

The API  has version prefix in URL and consists of two parts :
- **content management** - endpoints to manage data , request to create  or delete X amount of records\ entities. 
- **data endpoints** - endpoints to work with exact data.


#### Content Management Endpoints

  | Description      | Method | Endpoint             | Request payload                                           | Response payload                     | Description                                                                                                             |
  |------------------|--------|----------------------|-----------------------------------------------------------|--------------------------------------|-------------------------------------------------------------------------------------------------------------------------|
  | Get job status   | GET    | /api/v1/content/{id} | None                                                      | <pre>[{"status": "COMPLETED"}]</pre> | Get status of job that was creating or deleting content                                                                 | 
  | Create bulk      | PUT    | /api/v1/content      | <pre> [{"contentName" :"City","contentAmount": 10}]</pre> | <pre>[{ "jobId": 1 }]</pre>          | Returns job id, that serves creation task, where <pre>contentName</pre> should be one of the names of Entity classes.   |     
  | Delete bulk      | DELETE | /api/v1/content      | <pre> [{"contentName" :"City","contentAmount": 10}]</pre> | <pre>[{ "jobId": 1 }]</pre>          | Returns job id, that serves deletion task, where <pre>contentName</pre> should be one of the names of Entity classes.   |                                                                                                                                                                           


#### Data  Endpoints by Entity

*City*

  | Description       | Method | Endpoint                            | Payload                                                                                                                                                                                   | 
  |-------------------|--------|-------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
  | Get all           | GET    | /api/v1/data/city                   | None                                                                                                                                                                                      | 
  | Get by id         | GET    | /api/v1/data/city/{id}              | None                                                                                                                                                                                      |
  | Search            | GET    | /api/v1/data/city?q=x&page=#&size=# | None                                                                                                                                                                                      |
  | Create or replace | PUT    | /api/v1/data/city                   | <pre> [{ "commitId": "1234", <br>  "userName": "mark",  <br>  "repoId": "important-app", <br>  "tags": [{"id": "dev"}, {"id":"PROD"}], <br>  "description": "Lorem ipsum." <br>  }]</pre> |     
  | Delete by id      | DELETE | /api/v1/data/city/{id}              | None                                                                                                                                                                                      |                                                                                                                                                                           



