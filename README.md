# Server App Using Spring Boot☕ 🍃

Learning Java Spring Boot.

  ```
  com
   +- bagus2x
       +- serverapp
           +- ServerAppAplication.java
           |
           +- country
           |   +- dto // Data Transfer Object folders
           |   +- Country.java // Entity
           |   +- CountryController.java
           |   +- CountryService.java
           |   +- CountryRepository.java
           |
           +- region
           |   +- dto // Data Transfer Object folders
           |   +- Region.java // Entity
           |   +- RegionController.java
           |   +- RegionService.java
           |   +- RegionRepository.java
           |
           +- utils
           |   +- ErrorController.java // Handle error response
           |   +- ErrorDto.java // Object for error response
           |   +- Mapper.java // Map entity object to its DTO
           |
           
  ```

**References:**

- Reason for using this code
  structure [https://docs.spring.io/spring-boot/docs/current/reference/html/using.html#using.structuring-your-code](https://docs.spring.io/spring-boot/docs/current/reference/html/using.html#using.structuring-your-code)
- Reason for using DTO (Data Transfer
  Object) [https://stackoverflow.com/a/47715121/13973583](https://stackoverflow.com/a/47715121/13973583)

# REST API Specification

This documentation provides information about the REST API endpoints to handle CRUD (Create, Read, Update, Delete)
operations for regions and countries.

## Region

### Create a Region

Create a new region by sending a POST request to /region.

#### Request

- URL: `/region`
- Method: `POST`
- Headers
    - `Accept: application/json`
- Request Body: JSON
  ```json
  {
    "name": "Asia"
  }
  ```

#### Response

- Status Code: 201 (Created)
- Headers
    - `Content-Type: application/json`
- Body:
  ```json
  {
    "id": 1,
    "name": "Asia",
    "countries": [
      {
        "id": 1,
        "code": "ID",
        "name": "Indonesia"
      }
    ]
  }
    ```

### Get All Regions

Retrieve a list of all regions by sending a GET request to /regions.

#### Request

- URL: `/regions`
- Method: `GET`
- Headers:
    - `Accept: application/json`

#### Response

- Status Code: 200 (OK)
- Body:
  ```json
  [
    {
      "id": 1,
      "name": "Region 1",
      "countries": []
    },
    {
      "id": 2,
      "name": "Region 2",
      "countries": []
    }
  ]
  ```

### Get Region by ID

Retrieve a specific region by its ID.

#### Request

- URL: `/region/{regionId}`
- Method: `GET`
- Headers:
    - `Accept: application/json`
- Path Parameters:
    - `regionId` (Integer): The ID of the region to retrieve.

#### Response: JSON

- Status Code: 200 (OK)
- Headers:
    - `Content-Type: application/json`
- Body:
  ```json
  {
    "id": 1,
    "name": "Asia",
    "countries": [
      {
        "id": 1,
        "code": "ID",
        "name": "Indonesia"
      }
    ]
  }
  ```

### Update Region

Update the details of a region by sending a PUT request.

#### Request

- URL: `/region/{regionId}`
- Method: `PUT`
- Headers
    - `Accept: application/json`
- Path Parameters:
    - `regionId` (Integer): The ID of the region to update.
- Request Body: JSON
  ```json
  {
    "name": "Asia"
  }
  ```

#### Response

- Status Code: 200 (OK)
- Headers
    - `Content-Type: application/json`
- Body:
  ```json
  {
    "id": 1,
    "name": "Asia",
    "countries": [
      {
        "id": 1,
        "code": "ID",
        "name": "Indonesia"
      }
    ]
  }
    ```

### Delete Region

Delete a region by sending a DELETE request.

#### Request

- URL: `/region/{regionId}`
- Method: `DELETE`
- Headers
    - `Accept: application/json`
- Path Parameters:
    - `regionId (Integer)`: The ID of the region to delete.

#### Response: JSON

- Status Code: 200 (OK)
- Body:
  ```json
  {
    "id": 1,
    "name": "Asia",
    "countries": [
      {
        "id": 1,
        "code": "ID",
        "name": "Indonesia"
      }
    ]
  }
    ```

## Country

## Create a Country

Create a new country by sending a POST request to /country.

#### Request

- URL: `/country`
- Method: `POST`
- Headers
    - `Accept: application/json`
- Request Body: JSON
  ```json
  {
    "regionId": 1,
    "code": "ID",
    "name": "Indonesia"
  }
  ```

#### Response

- Status Code: 201 (Created)
- Headers
    - `Content-Type: application/json`
- Body:
  ```json
  {
    "id": 1,
    "region": {
      "id": 1,
      "name": "Asia"
    },
    "code": "ID",
    "name": "Indonesia"
  }
    ```

## Get All Country

Retrieve a list of all regions by sending a GET request to /regions.

#### Request

- URL: `/countries`
- Method: `GET`
- Headers:
    - `Accept: application/json`

#### Response

- Status Code: 200 (OK)
- Body:
  ```json
  [
    {
      "id": 1,
      "region": {
        "id": 1,
        "name": "Asia"
      },
      "code": "ID",
      "name": "Indonesia"
    }
  ]
  ```

## Get Country By ID

Retrieve a specific country by its ID.

#### Request

- URL: `/country/{countryId}`
- Method: `GET`
- Headers:
    - `Accept: application/json`
- Path Parameters:
    - `countryId` (Integer): The ID of the country to retrieve.

#### Response: JSON

- Status Code: 200 (OK)
- Headers:
    - `Content-Type: application/json`
- Body:
  ```json
  [
    {
      "id": 1,
      "region": {
        "id": 1,
        "name": "Asia"
      },
      "code": "ID",
      "name": "Indonesia"
    }
  ]
  ```

## Update Country

Update the details of a country by sending a PUT request.

#### Request

- URL: `/country/{countryId}`
- Method: `PUT`
- Headers
    - `Accept: application/json`
- Path Parameters:
    - `countryId` (Integer): The ID of the country to update.
- Request Body: JSON
  ```json
  {
    "regionId": 1,
    "code": "ID",
    "name": "Indonesia"
  }
  ```

#### Response

- Status Code: 200 (OK)
- Headers
    - `Content-Type: application/json`
- Body:
  ```json
  [
    {
      "id": 1,
      "region": {
        "id": 1,
        "name": "Asia"
      },
      "code": "ID",
      "name": "Indonesia"
    }
  ]
  ```

## Delete Country

Delete a country by sending a DELETE request.

#### Request

- URL: `/country/{countryId}`
- Method: `DELETE`
- Headers
    - `Accept: application/json`
- Path Parameters:
    - `countryId (Integer)`: The ID of the region to delete.

#### Response: JSON

- Status Code: 200 (OK)
- Body:
  ```json
  [
    {
      "id": 1,
      "region": {
        "id": 1,
        "name": "Asia"
      },
      "code": "ID",
      "name": "Indonesia"
    }
  ]
  ```