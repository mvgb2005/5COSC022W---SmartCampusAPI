# 5COSC022W - SmartCampusAPI 
Name: Mohul Biswas 
ID: w1979854 

## Contents
- [Overview](#overview)
- [Run](#run)
- [Report](#report)

## Overview 
SmartCampusAPI is a RESTful web service developed using Java 11, JAX-RS (Jersey), and Apache Tomcat 9 to simulate a smart campus management system. The API is designed to manage three core entities: Rooms, Sensors, and Sensor Readings. Rooms represent physical locations on campus, Sensors are devices installed within those rooms (such as temperature or CO2 monitors), and Sensor Readings store historical data collected from each sensor. The system follows RESTful principles, using clear resource-based endpoints and standard HTTP methods (GET, POST, DELETE). It also demonstrates a hierarchical structure through nested resources, where sensor readings are accessed within the context of a specific sensor. To keep the design simple and aligned with the coursework requirements, data is stored in-memory using static ArrayLists rather than a database. A generic DAO pattern is used to separate data handling logic from the API layer, improving code organisation and maintainability. The API includes validation and business rules to ensure data integrity, such as preventing the deletion of rooms that still contain sensors and restricting readings from sensors under maintenance. Custom exceptions and exception mappers are used to return meaningful HTTP status codes and JSON error responses. 

## Run 

### Sample cURL Commands 

## Report 

### 2.1.1 What is the default lifecycle of a JAX-RS resource class, and how does this affect the management and synchronization of in-memory data structures? 
JAX-RS resource classes use a per-request lifecycle by default, meaning a new instance is created for each request rather than reusing the same one. This helps avoid issues with shared state between requests. However, since this project stores data in shared in-memory lists, those still need to be handled carefully to keep things consistent. 

### 2.1.2 Why is hypermedia (HATEOAS) considered an advanced REST principle, and how does it benefit client developers compared to relying on static documentation?
HATEOAS is considered an advanced REST principle because it allows an API to include links within its responses that guide clients on what actions can be performed next. Instead of relying on static documentation, clients can dynamically discover available endpoints through these links. This makes the API more flexible as changes to the API structure do not require clients to be updated manually. 

### 2.2.1 When returning a list of rooms, what are the implications of returning only IDs versus returning the full room objects? 
Returning only room IDs makes the response smaller and faster to transfer, but the client would need to send extra requests to get full room details. Returning full room objects gives the client all the information straight away, but it increases the response size. In this project, returning full room objects is useful because it makes the API easier to test and reduces extra client-side requests. 

### 2.2.2 Is the ```DELETE``` operation idempotent in your implementation? 
The ```DELETE``` operation is idempotent because sending the same delete request multiple times does not keep changing the system after the room has already been removed. If the room still has sensors, the request is blocked with a ```409 Conflict```, so the room remains unchanged. If the room has no sensors and is deleted, repeating the same request does not delete anything further, so the final state of the system stays the same. 

### 2.3.1 What happens if a client sends data in a format other than JSON when the API expects application/json using ```@Consumes```? 
If a client sends data in a different format, JAX-RS will not match it to the expected method. The request should be rejected with ```415 Unsupported Media Type```. 

### 2.3.2 Why is using ```@QueryParam``` generally considered a better design choice for filtering and searching collections in REST APIs compared to embedding filters in the URL path?
Using ```@QueryParam``` is better for filtering because it keeps the URL structure focused on the main resource while allowing flexible, optional filters to be added without creating new endpoints. It also makes the API easier to extend, as multiple filters can be combined, and it avoids overcomplicating the path structure, which improves readability and maintainability. 

### 2.4.1 How does the Sub-Resource Locator pattern improve API architecture by delegating nested resource logic to separate classes instead of handling everything in one large controller? 
The Sub-Resource Locator pattern improves the API structure by keeping nested logic in its own class. In this project, SensorResource handles sensor endpoints, while SensorReadingResource handles readings for a specific sensor. This keeps the code cleaner, easier to understand, and easier to maintain compared to putting every route in one large resource class.

### 2.5.2 Why is HTTP 422 often considered more semantically accurate than a standard 404 when the issue is a missing reference inside a valid JSON payload?
```422 Unprocessable Entity``` is more accurate because the endpoint exists and the JSON structure is valid, but one of the values inside the request is invalid. 

### 2.5.4 What are the cybersecurity risks of exposing internal Java stack traces to external API users, and what sensitive information could an attacker gain from them?
Exposing Java stack traces is risky because it can reveal internal details about the application. An attacker could see class names, method names, package structure, file paths, server information, libraries being used, and possible weaknesses in the code. This information could help them plan more targeted attacks, so the API should return a generic error message instead of exposing internal stack traces. 

### 2.5.5 Why is it advantageous to use JAX-RS filters for cross-cutting concerns like logging, rather than manually inserting ```Logger.info()``` statements inside every single resource method? 
JAX-RS filters are better for logging because they apply to all requests and responses in one central place. This avoids repeating ```Logger.info()``` in every resource method and keeps the resource classes focused on their main job. It also makes logging more consistent, because every endpoint is handled by the same filter logic. 
