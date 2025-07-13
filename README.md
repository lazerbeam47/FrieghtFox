# FrieghtFox Assignments

## Assignment 1: Toll Plaza Between 2 Pincodes

This project provides a REST API to find toll plazas between two Indian pincodes.

### How to Run

1. **Prerequisites:**
   - Java 17 or higher
   - Maven 3.8+

2. **Build and Run:**
   ```sh
   cd assignment1
   mvn spring-boot:run
   ```
   The server will start on `http://localhost:8080` by default.

### API Usage

- **Endpoint:**
  - `POST /api/v1/toll-plazas`

- **Request Body (JSON):**
  ```json
  {
    "sourcePincode": "560064",
    "destinationPincode": "110001"
  }
  ```

- **Response (JSON):**
  ```json
  {
    "route": {
      "sourcePincode": "560064",
      "destinationPincode": "110001",
      "sourceCoords": [13.0845, 77.5555],
      "destinationCoords": [28.6358, 77.2245]
    },
    "tollPlazas": [
      {
        "name": "Example Toll Plaza",
        "highway": "NH44",
        "latitude": 13.5,
        "longitude": 77.6
      }
      // ... more toll plazas ...
    ]
  }
  ```

- **Error Response:**
  - Returns HTTP 400 with an error message if pincodes are invalid or missing.

### Notes
- Test the API using Postman or curl.
- All code is in the `assignment1/` folder.
- For more details, see the code comments and Javadoc. 