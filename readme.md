Documentation of recruitment project for HSBC

Author: Bartosz Szymowski

LittleTwitter Application uses:
-Java 1.8, 
-Maven
-Spring Boot

To run application execute from command line:

mvn compile exec:java

Application runs on port 8080. Make sure this port is free and is not used by other application or process

REST API provides the following web-services  (All Requests and Responses are in JSON)

1. /post (POST) Post new message (Editing and Deleting messages was not requested and it is not implemented)
2. /wall (GET) Get messages of user (in reverse chronological order)
3. /follow (POST) Add another user to following users
4. /follow (DELETE) Remove user from following users
5. /follow (GET) Get list of following users (without following or unfollowing anybody)
6. /timeline (GET) Get messages of users being followed (in reverse chronological order)


Examples:

1. /post (POST) - Post new message (Editing and Deleting messages was not requested and it is not implemented)

Request:

POST http://localhost:8080/post
Content-Type: application/json
{"user" : "John", "text" : "Hello I'm John This is my First Message"}

Response:

{"result": true}

true - means Post has been added. false otherwise

===================================================================================
2  /wall (GET) - Get messages of user (in reverse chronological order)

Request:

GET http://localhost:8080/John  (Username passed as Path Variable)

Response:

{
  "messages": [
    {
      "author": "John",
      "text": "Hello I'm John This is my Third Message",
      "dateTime": "2017-04-24T20:41:07.463"
    },
    {
      "author": "John",
      "text": "Hello I'm John This is my Second Message",
      "dateTime": "2017-04-24T20:41:00.319"
    },
    {
      "author": "John",
      "text": "Hello I'm John This is my First Message",
      "dateTime": "2017-04-24T20:39:03.761"
    }
  ]
}

All messages of user in reverse chronological order

===================================================================================
3 /follow POST - Add another user to following users

Request:

POST http://localhost:8080/follow
Content-Type: application/json
{"user" : "John", "following" : "Peter"}

Response:

{
  "following": [
    "Peter",
    "Paul"
  ],
  "result": true
}

following - list of users who are being followed AFTER the operation
true - operation was successfull - false otherwise (We can not follow user who does not exist)

===================================================================================
4 /follow DELETE - Remove user from following users

Request:

DELETE http://localhost:8080/follow
Content-Type: application/json
{"user" : "John", "following" : "Peter"}

Response:

{
  "following": [
    "Kevin",
    "Paul"
  ],
  "result": true
}

following - list of users who are being followed AFTER the operation
true - operation was successfull - false otherwise (We can not unfollow user who is not being followed)

===================================================================================
5 /follow GET  Get list of following users (without following or unfollowing anybody)

Request:

GET http://localhost:8080/follow  (Username passed as Path Variable)

Response

{
  "following": [
    "Kevin",
    "Paul"
  ]
}

following - list of users who are being followed

====================================================================================
6  /timeline - Get messages of users being followed (in reverse chronological order)

Request:

GET http://localhost:8080/timeline/John  GET (Username passed as Path Variable)

Response:

{
  "messages": [
    {
      "author": "Kevin",
      "text": "Hello I'm Kevin This is my Second Message",
      "dateTime": "2017-04-24T20:47:00.445"
    },
    {
      "author": "Kevin",
      "text": "Hello I'm Kevin This is my First Message",
      "dateTime": "2017-04-24T20:46:53.118"
    },
    {
      "author": "Paul",
      "text": "Hello I'm Paul This is my First Message",
      "dateTime": "2017-04-24T20:46:27.413"
    }
  ]
}


All messages of users being followed in reverse chronological order

====================================================================================

