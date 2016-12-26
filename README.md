War migration
=============

This is a simple example of migrating a war based webapp to spring-boot


Building and running
--------------------

    ./gradlew bootRun
   
Api calls
---------

### Request made with valid user id

     $ curl -i -H "X_USER_ID: Carol" http://localhost:8080/external/resource
     
     HTTP/1.1 200
     Content-Type: application/json
     Content-Length: 10
     Date: Mon, 26 Dec 2016 10:43:40 GMT
     
     Looks good
    
### Request made with invalid user id

    $ curl -i -H "X_USER_ID: Somebody" http://localhost:8080/external/resource
    
    HTTP/1.1 401
    Content-Length: 15
    Date: Mon, 26 Dec 2016 10:45:51 GMT
    
    Not authorized!%
    
### Request that causes an illegal exception to be thrown in the code

    $ curl -i -H "X_USER_ID: Carol" http://localhost:8080/internal/resource/illegal

    HTTP/1.1 500
    Content-Type: text/plain
    Content-Length: 5
    Date: Mon, 26 Dec 2016 10:46:31 GMT
    Connection: close
    
### Request that does not require auth
This request also demonstrates the use of an injected repository object

    $ curl -i -H "X_USER_ID: Somebody" http://localhost:8080/external/noauth
    
    HTTP/1.1 200
    Content-Type: application/json
    Content-Length: 15
    Date: Mon, 26 Dec 2016 10:47:56 GMT
    
    ["Some","data"]%