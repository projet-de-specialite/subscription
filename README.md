### Nécessaire : 
- maven
- jdk 17

### Build l'api et la lancer :
- mvn clean install
- mvnw -DskipTests spring-boot:run

### Les routes :
![image](https://github.com/projet-de-specialite/subscription/assets/42963508/43396716-3f42-4125-87b8-7d3ba3d2fdce)

Subscription module
This is a subscription module API that allows users to create and delete subscriptions, as well as retrieve all subscriptions and subscribers from a particular user.

Base URL
The base URL for the API is http://example.com.

Paths
Create a subscription
POST /subscription

Create a subscription with userId and followerId.

Request body
The request body should contain a JSON object with the following properties:

id (integer, format: long, example: 1)
userId (integer, format: int64, example: 2)
followerId (integer, format: int64, example: 4)
Responses
200: Successful operation. Returns a JSON object with the following properties:
subscription: A JSON object representing the created subscription.
message: A string message.
400: UserId and FollowerId are equal or one of the ID is null. Returns a JSON object with the following properties:
subscription: A JSON object representing the failed subscription.
message: A string message.
409: The subscription already exist. Returns a JSON object with the following properties:
subscription: A JSON object representing the failed subscription.
message: A string message.
404: An error has occurred. Returns a JSON object with the following properties:
subscription: A JSON object representing the failed subscription.
message: A string message.
Delete a subscription
DELETE /subscription

Delete a subscription if already exist.

Request body
The request body should contain a JSON object with the following properties:

id (integer, format: long, example: 1)
userId (integer, format: int64, example: 2)
followerId (integer, format: int64, example: 4)
Responses
200: Successful operation. Returns a JSON object with the following properties:
subscription: A JSON object representing the deleted subscription.
message: A string message.
400: UserId and FollowerId are equal or one of the ID is null. Returns a JSON object with the following properties:
subscription: A JSON object representing the failed subscription.
message: A string message.
404: The subscription does not exist. Returns a JSON object with the following properties:
subscription: A JSON object representing the failed subscription.
message: A string message.
Find all subscription from user
GET /subscription/getAllSubscriptions/{userId}

Return all subscriptions from user id.

Parameters
userId (integer, format: int64) - The ID of the user.
Responses
200: Successful operation. Returns a JSON object containing a list of all subscriptions from the specified user.
404: The specified user does not exist.
Find all subscribers from user
GET /subscription/getAllSubscribers/{userId}

Return all subscribers from user id.

Parameters
userId (integer, format: int64) - The ID of the user.
Responses
200: Successful operation. Returns a JSON object containing a list of all subscribers from the specified user.
404: The specified user does not exist.
Schemas
SubscriptionResponse
A JSON object representing a subscription response.

Properties
subscription: A JSON object representing a subscription.
message: A string message.
