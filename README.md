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
    docker compose up 
    ```

2. The application will be available at `http://localhost:8080`.

## Configuration

The application uses different profiles for different environments. The profiles are defined in the following files:

- `application.properties`: Common properties.
- `application-dev.properties`: Development environment properties.
- `application-local.properties`: Local testing properties.

You can specify the active profile using the `SPRING_PROFILES_ACTIVE` environment variable.

# API

## Swagger
For a quick start , swagger open api documentation is available at `http://localhost:8080/swagger-ui.html`

## Version
The API URL has version prefix. Current version is `v1`.

## Api groups
There are three groups of endpoints:
- **content management** - endpoints to manage data , request to create  or delete X amount of records\ entities. 
- **data endpoints** - endpoints to work with exact data.
- **stream endpoints** - endpoints to entity data without saving to DB.


#### Content Management Endpoints

  | Description    | Method | Endpoint                          | Request payload                                           | Response payload                     | HATEOAS | Description                                                                                                           |
  |----------------|--------|-----------------------------------|-----------------------------------------------------------|--------------------------------------|---------|-----------------------------------------------------------------------------------------------------------------------|
  | Get job status | GET    | /api/**{version}**/content/{id}   | None                                                      | <pre>[{"status": "COMPLETED"}]</pre> | N       | Get status of job that was creating or deleting content                                                               | 
  | Create bulk    | PUT    | /api/**{version}**/content        | <pre> [{"contentName" :"City","contentAmount": 10}]</pre> | <pre>[{ "jobId": 1 }]</pre>          | N       | Returns job id, that serves creation task, where <pre>contentName</pre> should be one of the names of Entity classes. |     
  | Delete bulk    | DELETE | /api/**{version}**/content        | <pre> [{"contentName" :"City","contentAmount": 10}]</pre> | <pre>[{ "jobId": 1 }]</pre>          | N       | Returns job id, that serves deletion task, where <pre>contentName</pre> should be one of the names of Entity classes. |                                                                                                                                                                           


#### Data  Endpoints 

*City*

Random generated real cities with valid ISO-3 country codes

  | Description       | Method | Endpoint                                                                        | Request payload                                                                | Response payload                                                               | HATEOAS |
  |-------------------|--------|---------------------------------------------------------------------------------|--------------------------------------------------------------------------------|--------------------------------------------------------------------------------|---------|
  | Get all           | GET    | /api/**{version}**/data/city                                                    | None                                                                           | <pre> [{ "name": "Berlin", <br>  "countryCode": "DEU" }]</pre>                 | Y       |
  | Get by id         | GET    | /api/**{version}**/data/city/{id}                                               | None                                                                           | <pre> { "name": "Berlin", <br>  "countryCode": "DEU" } </pre>                  | N       |
  | Search            | GET    | /api/**{version}**/data/city?name=Rio&page=0&size=5&sortList=name&sortOrder=ASC | None                                                                           |                                                                                | Y       |
  | Create or replace | PUT    | /api/**{version}**/data/city                                                    | <pre> { "id": "123",<br> "name": "Berlin", <br>  "countryCode": "DEU" } </pre> | <pre> { "id": "123",<br> "name": "Berlin", <br>  "countryCode": "DEU" } </pre> | N       |   
  | Delete by id      | DELETE | /api/**{version}**/data/city/{id}                                               | None                                                                           |                                                                                | N       |                                                                                                                                                                          

*Phone number*

Random non-unique phone numbers with valid ISO-3 country code and prefix

| Description        | Method | Endpoint                                                                                              | Request payload                                                                                | Response payload                                                                               | HATEOAS |
|--------------------|--------|-------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------|---------|
| Get all            | GET    | /api/**{version}**/data/phone-number                                                                  | None                                                                                           | <pre> [{ "phoneNumber": "+971-262-09194292", <br>  "countryCode": "ARE" }]</pre>               | Y       |
| Get by id          | GET    | /api/**{version}**/data/phone-number/{id}                                                             | None                                                                                           | <pre> { "phoneNumber": "+971-262-09194292", <br>  "countryCode": "ARE" } </pre>                | N       |
| Search             | GET    | /api/**{version}**/data/phone-number?phoneNumber=123&page=0&size=5&sortList=phoneNumber&sortOrder=ASC | None                                                                                           |                                                                                                | Y       |
| Create or replace  | PUT    | /api/**{version}**/data/phone-number                                                                  | <pre> { "id": 2 , <br> "phoneNumber": "+971-262-09194292", <br>  "countryCode": "ARE" } </pre> | <pre> { "id": 2 , <br> "phoneNumber": "+971-262-09194292", <br>  "countryCode": "ARE" } </pre> | N       |  
| Delete by id       | DELETE | /api/**{version}**/data/phone-number/{id}                                                             | None                                                                                           |                                                                                                | N       |                                                                                                                                                                        

*Transaction*

Random non-unique transactions with fabricated payment details.

| Description                  | Method | Endpoint                                          | Request payload                                                                                 | Response payload                                                                                                                                                                                                                                                                                                                                                                                                                                         | HATEOAS  |
|------------------------------|--------|---------------------------------------------------|-------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------|
| Get all                      | GET    | /api/**{version}**/data/transaction               | None                                                                                            | <pre>[{ <br>"id": "39ac7c69-063e-49ca-b3f7-36020d322944", <br>"dateTime": "2024-10-14T10:29:04.203+00:00",<br>"entryType": "MANUAL",<br>"type":"PRE_AUTH",<br>"merchant":<br> {<br>"id": "b6877787-61cd-4ca8-885a-7dd2360911a4",<br>"name": "Top shoes NY",<br>"countryCode": "USA",<br>"address": "49th street NY"<br>},<br>"amount": 10,<br>"currency": "USD",<br>"creditCard": <br>{<br>"id": "d8e546be-3df5-45d8-a52b-f6b20d02cd69",<br>"issuer":"Meirl Lynch",<br>"ccn": "2848 890 89 757"<br>}<br>}]</pre> | Y        |
| Get ONE by id                | GET    | /api/**{version}**/data/transaction/one/{id}      | None                                                                                            | <pre>{ <br>"id": "39ac7c69-063e-49ca-b3f7-36020d322944", <br>"dateTime": "2024-10-14T10:29:04.203+00:00",<br>"entryType": "MANUAL",<br>"type":"PRE_AUTH",<br>"merchant":<br> {<br>"id": "b6877787-61cd-4ca8-885a-7dd2360911a4",<br>"name": "Top shoes NY",<br>"countryCode": "USA",<br>"address": "49th street NY"<br>},<br>"amount": 10,<br>"currency": "USD",<br>"creditCard": <br>{<br>"id": "d8e546be-3df5-45d8-a52b-f6b20d02cd69",<br>"issuer":"Meirl Lynch",<br>"ccn": "2848 890 89 757"<br>}<br>}</pre> | N        |
| Get CHAIN by correlation id* | GET    | /api/**{version}**/data/transaction/chain/{id}    | None                                                                                            | <pre>{ <br>"id": "39ac7c69-063e-49ca-b3f7-36020d322944", <br>"dateTime": "2024-10-14T10:29:04.203+00:00",<br>"entryType": "MANUAL",<br>"type":"PRE_AUTH",<br>"merchant":<br> {<br>"id": "b6877787-61cd-4ca8-885a-7dd2360911a4",<br>"name": "Top shoes NY",<br>"countryCode": "USA",<br>"address": "49th street NY"<br>},<br>"amount": 10,<br>"currency": "USD",<br>"creditCard": <br>{<br>"id": "d8e546be-3df5-45d8-a52b-f6b20d02cd69",<br>"issuer":"Meirl Lynch",<br>"ccn": "2848 890 89 757"<br>}<br>}</pre> | N        |
| Search                       | GET    | /api/**{version}**/data/transaction?page=0&size=5 | None                                                                                            |                                                                                                                                                                                                                                                                                                                                                                                                                                                          | Y        |
| Create or replace            | PUT    | /api/**{version}**/data/transaction/{id}          | <pre>{ <br>"entryType": "MANUAL",<br>"type":"PRE_AUTH",<br>"merchant":<br> {<br>"name": "Top shoes NY"}}</pre> |<pre>{ <br>"id": "39ac7c69-063e-49ca-b3f7-36020d322944", <br>"dateTime": "2024-10-14T10:29:04.203+00:00",<br>"entryType": "MANUAL",<br>"type":"PRE_AUTH",<br>"merchant":<br> {<br>"id": "b6877787-61cd-4ca8-885a-7dd2360911a4",<br>"name": "Top shoes NY",<br>"countryCode": "USA",<br>"address": "49th street NY"<br>},<br>"amount": 10,<br>"currency": "USD",<br>"creditCard": <br>{<br>"id": "d8e546be-3df5-45d8-a52b-f6b20d02cd69",<br>"issuer":"Meirl Lynch",<br>"ccn": "2848 890 89 757"<br>}<br>}</pre> | N        |    
| Delete by id                 | DELETE | /api/**{version}**/data/transaction/{id}          | None                                                                                            |                                                                                                                                                                                                                                                                                                                                                                                                                                                          | N        |                                                                                                   

'*'  - returns list of  transactions that are connected by correlation id. Each transaction in this list represents one step in payment flow of one purchase. 

#### Stream Endpoints

Stream endpoints return piece of randomly generated non-unique data without saving this data to DB. They emulate endless stream of data, that can be taken out by portions with a size of a "bucket". 
The size of a "bucket" is represented by a query parameter "limit".
The size of a bucket is capped by 100 records by default or if not provided.
HATEOAS is not supported for stream endpoints, because no data is stored.

*City*

Random generated real cities with valid ISO-3 country codes

| Description       | Method | Endpoint                               | Request payload | Response payload                                                               | HATEOAS |
|-------------------|--------|----------------------------------------|-----------------|--------------------------------------------------------------------------------|---------|
| Get all           | GET    | /api/**{version}**/stream/city?limit=5 | None            | <pre> [{ "name": "Berlin", <br>  "countryCode": "DEU" }]</pre>                 | N       |

*Phone number*

Random non-unique phone numbers with valid ISO-3 country code and prefix

| Description        | Method | Endpoint                                       | Request payload | Response payload                                                                               | HATEOAS |
|--------------------|--------|------------------------------------------------|-----------------|------------------------------------------------------------------------------------------------|---------|
| Get all            | GET    | /api/**{version}**/stream/phone-number?limit=5 | None            | <pre> [{ "phoneNumber": "+971-262-09194292", <br>  "countryCode": "ARE" }]</pre>               | N       |


*Transaction*

Random non-unique transactions with fabricated payment details.

| Description        | Method | Endpoint                                      | Request payload | Response payload                                                                                                                                                                                                                                                                                                                                                                                                                                         | HATEOAS |
|--------------------|--------|-----------------------------------------------|-----------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|---------|
| Get all            | GET    | /api/**{version}**/stream/transaction?limit=5 | None            | <pre>[{ <br>"id": "39ac7c69-063e-49ca-b3f7-36020d322944", <br>"dateTime": "2024-10-14T10:29:04.203+00:00",<br>"entryType": "MANUAL",<br>"type":"PRE_AUTH",<br>"merchant":<br> {<br>"id": "b6877787-61cd-4ca8-885a-7dd2360911a4",<br>"name": "Top shoes NY",<br>"countryCode": "USA",<br>"address": "49th street NY"<br>},<br>"amount": 10,<br>"currency": "USD",<br>"creditCard": <br>{<br>"id": "d8e546be-3df5-45d8-a52b-f6b20d02cd69",<br>"issuer":"Meirl Lynch",<br>"ccn": "2848 890 89 757"<br>}<br>}]</pre> | N       |