# Server App Using Spring Boot☕ 🍃

Learning Java Spring Boot.

**References:**

- Reason for using DTO (Data Transfer
  Object) [https://stackoverflow.com/a/47715121/13973583](https://stackoverflow.com/a/47715121/13973583)

# Running and Testing The Application Locally

```bash
#  Run all test cases
mvn test
# Run app locally
mvn clean package && java -jar target/serverapp-0.0.1-SNAPSHOT.jar
```

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
    - `Content-Type: application/json`
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
    - `Content-Type: application/json`

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
    - `Content-Type: application/json`
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
    - `Content-Type: application/json`
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
    - `Content-Type: application/json`
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

### Create a Country

Create a new country by sending a POST request to /country.

#### Request

- URL: `/country`
- Method: `POST`
- Headers
    - `Content-Type: application/json`
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

### Get All Country

Retrieve a list of all regions by sending a GET request to /regions.

#### Request

- URL: `/countries`
- Method: `GET`
- Headers:
    - `Content-Type: application/json`

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

### Get Country By ID

Retrieve a specific country by its ID.

#### Request

- URL: `/country/{countryId}`
- Method: `GET`
- Headers:
    - `Content-Type: application/json`
- Path Parameters:
    - `countryId` (Integer): The ID of the country to retrieve.

#### Response: JSON

- Status Code: 200 (OK)
- Headers:
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

### Update Country

Update the details of a country by sending a PUT request.

#### Request

- URL: `/country/{countryId}`
- Method: `PUT`
- Headers
    - `Content-Type: application/json`
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

### Delete Country

Delete a country by sending a DELETE request.

#### Request

- URL: `/country/{countryId}`
- Method: `DELETE`
- Headers
    - `Content-Type: application/json`
- Path Parameters:
    - `countryId (Integer)`: The ID of the country to delete.

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

## Role

### Create a Role

Create a new role by sending a POST request to /role.

#### Request

- URL: `/role`
- Method: `POST`
- Headers
    - `Content-Type: application/json`
- Request Body: JSON
  ```json
  {
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
    "name": "admin"
  }
    ```

### Get All Role

Retrieve a list of all regions by sending a GET request to /regions.

#### Request

- URL: `/roles`
- Method: `GET`
- Headers:
    - `Content-Type: application/json`

#### Response

- Status Code: 200 (OK)
- Body:
  ```json
  [
    {
      "id": 1,
      "name": "admin"
    }
  ]
  ```

### Get Role By ID

Retrieve a specific role by its ID.

#### Request

- URL: `/role/{roleId}`
- Method: `GET`
- Headers:
    - `Content-Type: application/json`
- Path Parameters:
    - `roleId` (Integer): The ID of the role to retrieve.

#### Response: JSON

- Status Code: 200 (OK)
- Headers:
    - `Content-Type: application/json`
- Body:
  ```json
  {
    "id": 1,
    "name": "admin"
  }
  ```

### Update Role

Update the details of a role by sending a PUT request.

#### Request

- URL: `/role/{roleId}`
- Method: `PUT`
- Headers
    - `Content-Type: application/json`
- Path Parameters:
    - `roleId` (Integer): The ID of the role to update.
- Request Body: JSON
  ```json
  {
    "id": 1,
    "name": "admin"
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
    "name": "admin"
  }
  ```

### Delete Role

Delete a role by sending a DELETE request.

#### Request

- URL: `/role/{roleId}`
- Method: `DELETE`
- Headers
    - `Content-Type: application/json`
- Path Parameters:
    - `roleId (Integer)`: The ID of the role to delete.

#### Response: JSON

- Status Code: 200 (OK)
- Body:
  ```json
  {
    "id": 1,
    "name": "admin"
  }
  ```

## User

### Create a User

Create a new user by sending a POST request to /user.

#### Request

- URL: `/user`
- Method: `POST`
- Headers
    - `Content-Type: application/json`
- Request Body: JSON
  ```json
  {
     "username": "mark",
     "password": "mark1234567",
     "roleIds": [5]
  }
  ```

#### Response

- Status Code: 201 (Created)
- Headers
    - `Content-Type: application/json`
- Body:
  ```json
  {
      "id": 6,
      "username": "mark",
      "password": "mark1234567",
      "roles": [
          {
              "id": 5,
              "name": "admin"
          },
          {
              "id": 6,
              "name": "user"
          }
      ]
  }
    ```

### Get All User

Retrieve a list of all regions by sending a GET request to /regions.

#### Request

- URL: `/users`
- Method: `GET`
- Headers:
    - `Content-Type: application/json`

#### Response

- Status Code: 200 (OK)
    - Body:
      ```json
      [
        {
            "id": 6,
            "username": "mark",
            "password": "mark1234567",
            "roles": [
                {
                    "id": 5,
                    "name": "admin"
                },
                {
                    "id": 6,
                    "name": "user"
                }
            ]
        }
      ]
      ```

### Get User By ID

Retrieve a specific user by its ID.

#### Request

- URL: `/user/{userId}`
- Method: `GET`
- Headers:
    - `Content-Type: application/json`
- Path Parameters:
    - `userId` (Integer): The ID of the user to retrieve.

#### Response: JSON

- Status Code: 200 (OK)
- Headers:
    - `Content-Type: application/json`
- Body:
  ```json
  {
      "id": 6,
      "username": "mark",
      "password": "mark1234567",
      "roles": [
          {
              "id": 5,
              "name": "admin"
          },
          {
              "id": 6,
              "name": "user"
          }
      ]
  }
  ```

### Update User

Update the details of a user by sending a PUT request.

#### Request

- URL: `/user/{userId}`
- Method: `PUT`
- Headers
    - `Content-Type: application/json`
- Path Parameters:
    - `userId` (Integer): The ID of the user to update.
- Request Body: JSON
  ```json
  {
     "username": "mark",
     "password": "mark1234567",
     "roleIds": [5,6]
  }
  ```

#### Response

- Status Code: 200 (OK)
- Headers
    - `Content-Type: application/json`
- Body:
  ```json
  {
      "id": 6,
      "username": "mark",
      "password": "mark1234567",
      "roles": [
          {
              "id": 5,
              "name": "admin"
          },
          {
              "id": 6,
              "name": "user"
          }
      ]
  }
  ```

### Delete User

Delete a user by sending a DELETE request.

#### Request

- URL: `/user/{userId}`
- Method: `DELETE`
- Headers
    - `Content-Type: application/json`
- Path Parameters:
    - `userId (Integer)`: The ID of the user to delete.

#### Response: JSON

- Status Code: 200 (OK)
- Body:
  ```json
  {
      "id": 6,
      "username": "mark",
      "password": "mark1234567",
      "roles": [
          {
              "id": 5,
              "name": "admin"
          },
          {
              "id": 6,
              "name": "user"
          }
      ]
  }
  ```

## Employee

### Create an Employee

Create a new employee by sending a POST request to /employee.

#### Request

- URL: `/employee`
- Method: `POST`
- Headers
    - `Content-Type: application/json`
- Request Body: JSON
  ```json
  {
     "name": "Tubagus Saifulloh",
     "email": "tubagus@gmail.com",
     "phone": "083212",
     "userId": 6
  }
  ```

#### Response

- Status Code: 201 (Created)
- Headers
    - `Content-Type: application/json`
- Body:
  ```json
  {
      "id": 7,
      "name": "Tubagus Saifulloh",
      "email": "tubagussaifulloh@gmail.com",
      "phone": "0832578",
      "user": {
          "id": 7,
          "username": "bagus",
          "password": "bagus123456",
          "roles": [
              {
                  "id": 5,
                  "name": "admin"
              }
          ]
      }
  }
    ```

### Get All Employee

Retrieve a list of all regions by sending a GET request to /regions.

#### Request

- URL: `/employees`
- Method: `GET`
- Headers:
    - `Content-Type: application/json`

#### Response

- Status Code: 200 (OK)
    - Body:
      ```json
      [
        {
            "id": 7,
            "name": "Tubagus Saifulloh",
            "email": "tubagussaifulloh@gmail.com",
            "phone": "0832578",
            "user": {
                "id": 7,
                "username": "bagus",
                "password": "bagus123456",
                "roles": [
                    {
                        "id": 5,
                        "name": "admin"
                    }
                ]
            }
        }
      ]
      ```

### Get Employee By ID

Retrieve a specific employee by its ID.

#### Request

- URL: `/employee/{employeeId}`
- Method: `GET`
- Headers:
    - `Content-Type: application/json`
- Path Parameters:
    - `employeeId` (Integer): The ID of the employee to retrieve.

#### Response: JSON

- Status Code: 200 (OK)
- Headers:
    - `Content-Type: application/json`
- Body:
  ```json
    {
        "id": 7,
        "name": "Tubagus Saifulloh",
        "email": "tubagussaifulloh@gmail.com",
        "phone": "0832578",
        "user": {
            "id": 7,
            "username": "bagus",
            "password": "bagus123456",
            "roles": [
                {
                    "id": 5,
                    "name": "admin"
                }
            ]
        }
    }
  ```

### Update Employee

Update the details of an employee by sending a PUT request.

#### Request

- URL: `/employee/{employeeId}`
- Method: `PUT`
- Headers
    - `Content-Type: application/json`
- Path Parameters:
    - `employeeId` (Integer): The ID of the employee to update.
- Request Body: JSON
  ```json
  {
    "name": "Tubagus Saifulloh",
    "email": "tubagussaifulloh@gmail.com",
    "phone": "0832578",
    "userId": 7
  }
  ```

#### Response

- Status Code: 200 (OK)
- Headers
    - `Content-Type: application/json`
- Body:
  ```json
  {
      "id": 7,
      "name": "Tubagus Saifulloh",
      "email": "tubagussaifulloh@gmail.com",
      "phone": "0832578",
      "user": {
          "id": 7,
          "username": "bagus",
          "password": "bagus123456",
          "roles": [
              {
                  "id": 5,
                  "name": "admin"
              }
          ]
      }
  }
  ```

### Delete Employee

Delete an employee by sending a DELETE request.

#### Request

- URL: `/employee/{employeeId}`
- Method: `DELETE`
- Headers
    - `Content-Type: application/json`
- Path Parameters:
    - `employeeId (Integer)`: The ID of the employee to delete.

#### Response: JSON

- Status Code: 200 (OK)
- Body:
  ```json
  {
      "id": 7,
      "name": "Tubagus Saifulloh",
      "email": "tubagussaifulloh@gmail.com",
      "phone": "0832578",
      "user": {
          "id": 7,
          "username": "bagus",
          "password": "bagus123456",
          "roles": [
              {
                  "id": 5,
                  "name": "admin"
              }
          ]
      }
  }
  ```