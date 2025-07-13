# Toll Plaza API Assignment

## How to Run

1. Make sure you have Java 17+ and Maven installed.
2. In the `assignment1` directory, run:
   ```bash
   mvn spring-boot:run
   ```
3. The server will start on `http://localhost:8080`.

## API Endpoint

### POST `/api/v1/toll-plazas`

**Request Body:**
```
{
  "sourcePincode": "110001",
  "destinationPincode": "560001"
}
```

**Success Response:**
```
{
  "route": {
    "sourcePincode": "110001",
    "destinationPincode": "560001",
    "distanceInKm": 2100
  },
  "tollPlazas": [
    {
      "name": "Toll Plaza 1",
      "latitude": 28.7041,
      "longitude": 77.1025,
      "distanceFromSource": 200
    },
    {
      "name": "Toll Plaza 2",
      "latitude": 19.076,
      "longitude": 72.8777,
      "distanceFromSource": 1400
    }
  ]
}
```

**Error Responses:**
- Invalid pincode:
```
{"error": "Invalid source or destination pincode"}
```
- Same source and destination:
```
{"error": "Source and destination pincodes cannot be the same"}
```
- No toll plazas:
```
{
  "route": { ... },
  "tollPlazas": []
}
```

## Testing with Postman
- Set method to POST
- URL: `http://localhost:8080/api/v1/toll-plazas`
- Body: raw, JSON (see above)

## Running Tests
```bash
mvn test
``` 