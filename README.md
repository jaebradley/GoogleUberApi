# Google Uber Api

### Introduction
API that returns Uber estimates for a defined start and end address, represented as strings. 

This API uses the [Google Maps Geolocation API](https://developers.google.com/maps/documentation/geolocation/intro) to translate start and end address values into latitude/longitude coordinates.

These latitude and longitude values are then used when making requests to [Uber's API](https://developer.uber.com/docs/api-overview), in particular, the [price](https://developer.uber.com/docs/v1-estimates-price) and [time](https://developer.uber.com/docs/v1-estimates-time) endpoints.

### Example Request
* [http://trip-estimates.herokuapp.com/estimates?startAddress=2 cANaL paRK cmbridge ma&endAddress=harvard university cambridge ma](http://trip-estimates.herokuapp.com/estimates?startAddress=2 cANaL paRK cmbridge ma&endAddress=harvard university cambridge ma)

```json
{
  "startLocation": {
    "address": "2 Canal Park, Cambridge, MA 02141, USA",
    "latitude": 42.37016819999999,
    "longitude": -71.0763161
  },
  "endLocation": {
    "address": "Agassiz, Cambridge, MA, USA",
    "latitude": 42.3800977,
    "longitude": -71.1166286
  },
  "vendorEstimates": [
    {
      "vendor": "UBER",
      "tripEstimates": [
        {
          "productName": "uberX",
          "price": {
            "lowEstimate": 8,
            "highEstimate": 11,
            "surgeMultiplier": 1,
            "currencyCode": "USD"
          },
          "trip": {
            "durationSeconds": 720,
            "waitSeconds": 120,
            "distanceMiles": 3.08
          }
        },
        {
          "productName": "uberXL",
          "price": {
            "lowEstimate": 15,
            "highEstimate": 19,
            "surgeMultiplier": 1,
            "currencyCode": "USD"
          },
          "trip": {
            "durationSeconds": 720,
            "waitSeconds": 480,
            "distanceMiles": 3.08
          }
        },
        {
          "productName": "UberBLACK",
          "price": {
            "lowEstimate": 22,
            "highEstimate": 29,
            "surgeMultiplier": 1,
            "currencyCode": "USD"
          },
          "trip": {
            "durationSeconds": 720,
            "waitSeconds": 420,
            "distanceMiles": 3.08
          }
        },
        {
          "productName": "UberSUV",
          "price": {
            "lowEstimate": 31,
            "highEstimate": 38,
            "surgeMultiplier": 1,
            "currencyCode": "USD"
          },
          "trip": {
            "durationSeconds": 720,
            "waitSeconds": 420,
            "distanceMiles": 3.08
          }
        },
        {
          "productName": "uberTAXI",
          "price": {
            "lowEstimate": 0,
            "highEstimate": 0,
            "surgeMultiplier": 1,
            "currencyCode": null
          },
          "trip": {
            "durationSeconds": 720,
            "waitSeconds": 360,
            "distanceMiles": 3.08
          }
        }
      ]
    }
  ]
}
```
