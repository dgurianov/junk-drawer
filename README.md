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

The API URL has version prefix. Current version is `v1`.
It consists of two parts :
- **content management** - endpoints to manage data , request to create  or delete X amount of records\ entities. 
- **data endpoints** - endpoints to work with exact data.


#### Content Management Endpoints

  | Description    | Method | Endpoint                          | Request payload                                           | Response payload                     | Description                                                                                                           |
  |----------------|--------|-----------------------------------|-----------------------------------------------------------|--------------------------------------|-----------------------------------------------------------------------------------------------------------------------|
  | Get job status | GET    | /api/**{version}**/content/{id}   | None                                                      | <pre>[{"status": "COMPLETED"}]</pre> | Get status of job that was creating or deleting content                                                               | 
  | Create bulk    | PUT    | /api/**{version}**/content        | <pre> [{"contentName" :"City","contentAmount": 10}]</pre> | <pre>[{ "jobId": 1 }]</pre>          | Returns job id, that serves creation task, where <pre>contentName</pre> should be one of the names of Entity classes. |     
  | Delete bulk    | DELETE | /api/**{version}**/content        | <pre> [{"contentName" :"City","contentAmount": 10}]</pre> | <pre>[{ "jobId": 1 }]</pre>          | Returns job id, that serves deletion task, where <pre>contentName</pre> should be one of the names of Entity classes. |                                                                                                                                                                           


#### Data  Endpoints by Entity

*City*

Random generated real cities with valid ISO-3 country codes

  | Description       | Method | Endpoint                                       | Request payload                                                                | Response payload                                                               |
  |-------------------|--------|------------------------------------------------|--------------------------------------------------------------------------------|--------------------------------------------------------------------------------|
  | Get all           | GET    | /api/**{version}**/data/city                   | None                                                                           | <pre> [{ "name": "Berlin", <br>  "countryCode": "DEU" }]</pre>                 |
  | Get by id         | GET    | /api/**{version}**/data/city/{id}              | None                                                                           | <pre> { "name": "Berlin", <br>  "countryCode": "DEU" } </pre>                  |
  | Search            | GET    | /api/**{version}**/data/city?q=x&page=y&size=z | None                                                                           |                                                                                |
  | Create or replace | PUT    | /api/**{version}**/data/city                   | <pre> { "id": "123",<br> "name": "Berlin", <br>  "countryCode": "DEU" } </pre> | <pre> { "id": "123",<br> "name": "Berlin", <br>  "countryCode": "DEU" } </pre> |    
  | Delete by id      | DELETE | /api/**{version}**/data/city/{id}              | None                                                                           |                                                                                |                                                                                                                                                                          

*Phone number*

Random non-unique phone numbers with valid ISO-3 country code and prefix

| Description        | Method | Endpoint                                               | Request payload                                                                                | Response payload                                                                               |
|--------------------|--------|--------------------------------------------------------|------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------|
| Get all            | GET    | /api/**{version}**/data/phone-number                   | None                                                                                           | <pre> [{ "phoneNumber": "+971-262-09194292", <br>  "countryCode": "ARE" }]</pre>               |
| Get by id          | GET    | /api/**{version}**/data/phone-number/{id}              | None                                                                                           | <pre> { "phoneNumber": "+971-262-09194292", <br>  "countryCode": "ARE" } </pre>                |
| Search             | GET    | /api/**{version}**/data/phone-number?q=x&page=y&size=z | None                                                                                           |                                                                                                |
| Create or replace  | PUT    | /api/**{version}**/data/phone-number                   | <pre> { "id": 2 , <br> "phoneNumber": "+971-262-09194292", <br>  "countryCode": "ARE" } </pre> | <pre> { "id": 2 , <br> "phoneNumber": "+971-262-09194292", <br>  "countryCode": "ARE" } </pre> |    
| Delete by id       | DELETE | /api/**{version}**/data/phone-number/{id}              | None                                                                                           |                                                                                                |                                                                                                                                                                         

*Transaction*

Random non-unique transactions with valid fabricated payment details.

| Description        | Method | Endpoint                                              | Request payload                                                                                 | Response payload                                                                                                                                                                                                                                                                                                                                                                                                                                         |
|--------------------|--------|-------------------------------------------------------|-------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Get all            | GET    | /api/**{version}**/data/transaction                   | None                                                                                            | <pre>[{ <br>"id": "39ac7c69-063e-49ca-b3f7-36020d322944", <br>"dateTime": "2024-10-14T10:29:04.203+00:00",<br>"entryType": "MANUAL",<br>"type":"PRE_AUTH",<br>"merchant":<br> {<br>"id": "b6877787-61cd-4ca8-885a-7dd2360911a4",<br>"name": "Top shoes NY",<br>"countryCode": "USA",<br>"address": "49th street NY"<br>},<br>"amount": 10,<br>"currency": "USD",<br>"creditCard": <br>{<br>"id": "d8e546be-3df5-45d8-a52b-f6b20d02cd69",<br>"issuer":"Meirl Lynch",<br>"ccn": "2848 890 89 757"<br>}<br>}]</pre> |
| Get by id          | GET    | /api/**{version}**/data/transaction/{id}              | None                                                                                            | <pre>{ <br>"id": "39ac7c69-063e-49ca-b3f7-36020d322944", <br>"dateTime": "2024-10-14T10:29:04.203+00:00",<br>"entryType": "MANUAL",<br>"type":"PRE_AUTH",<br>"merchant":<br> {<br>"id": "b6877787-61cd-4ca8-885a-7dd2360911a4",<br>"name": "Top shoes NY",<br>"countryCode": "USA",<br>"address": "49th street NY"<br>},<br>"amount": 10,<br>"currency": "USD",<br>"creditCard": <br>{<br>"id": "d8e546be-3df5-45d8-a52b-f6b20d02cd69",<br>"issuer":"Meirl Lynch",<br>"ccn": "2848 890 89 757"<br>}<br>}</pre> |
| Search             | GET    | /api/**{version}**/data/transaction?q=x&page=y&size=z | None                                                                                            |                                                                                                                                                                                                                                                                                                                                                                                                                                                          |
| Create or replace* | PUT    | /api/**{version}**/data/transaction/{id}              | <pre>{ <br>"entryType": "MANUAL",<br>"type":"PRE_AUTH",<br>"merchant":<br> {<br>"name": "Top shoes NY"}}</pre> |<pre>{ <br>"id": "39ac7c69-063e-49ca-b3f7-36020d322944", <br>"dateTime": "2024-10-14T10:29:04.203+00:00",<br>"entryType": "MANUAL",<br>"type":"PRE_AUTH",<br>"merchant":<br> {<br>"id": "b6877787-61cd-4ca8-885a-7dd2360911a4",<br>"name": "Top shoes NY",<br>"countryCode": "USA",<br>"address": "49th street NY"<br>},<br>"amount": 10,<br>"currency": "USD",<br>"creditCard": <br>{<br>"id": "d8e546be-3df5-45d8-a52b-f6b20d02cd69",<br>"issuer":"Meirl Lynch",<br>"ccn": "2848 890 89 757"<br>}<br>}</pre> |    
| Delete by id       | DELETE | /api/**{version}**/data/transaction/{id}              | None                                                                                            |                                                                                                                                                                                                                                                                                                                                                                                                                                                          |                                                                                                                                                                         


