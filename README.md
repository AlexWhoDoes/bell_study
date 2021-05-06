# Bell study project
### REST-service that provides set of operations for getting, saving, updating organizations, offices and users objects. It uses only two Http methods GET and POST.
### The web service provides support queries to get the list citizenship with the name of the country and its code, as well as to get a list of document types with the name of the document and its code. To run that application it is requred to have Java 11.

___
# API description
- How to start the application
- How to send requests
- Referencebook-related queries
- Organization-related queries
- Office-related queries
- User-related queries

Descriptions below shows with using POSTMAN app as a client app that makes requests.
___
# How to start the application
- The application can be launched through IDE (like Intellij IDEA). Just doqnolad the repository and luanch pom.xml as a project
- The application can be launched as executable fat jar. The repository contains a fat jar bell_study/target/demo-0.0.1-SNAPSHOT.jar. You can download and launch it from source directory with following command:
```
java -jar demo-0.0.1-SNAPSHOT.jar
```
___
# How to send requests
- For GET-requests, the address bar is enough. Example: http://localhost:8888/user/1
- For POST-requests, you may want to use POSTMAN or something similar. It is essential to write body of request in acceptable format.
___
# Referencebook-related queries 
Referencebook-related request to get a list of countries and their codes.

An example of request to get a list of citizenship:
![request countries](https://github.com/AlexWhoDoes/bell_study/blob/master/img/getCountries.jpg)

To request a list of document types just use that link: http://localhost:8888/docs

As a response you will receive Json formatted output:
```
{
    "data": [
        {
            "name": "Russian federation",
            "code": "001"
        },
        {
            "name": "Ukraine",
            "code": "002"
        },
        {
            "name": "USA",
            "code": "003"
        },
        {
            "name": "UK",
            "code": "004"
        }
    ]
}
```
___
