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
# Organization-related queries

There are four allowed queries:

- POST request to get a list of all organizations. Address http://localhost:8888/organization/list
![getOrgList](https://github.com/AlexWhoDoes/bell_study/blob/master/img/orgList.jpg)
Attention, it is mandatory to write parameters into body request. At least a request must have parameter "name".

- POST request to save an organizations. Address http://localhost:8888/organization/update

Almost the same as for list of organizations but you can write some more parameters into body request.
Example:
```
{
    "id": "1",
    "name": "TheBestBankEver",
    "fullName": "LLC TheBestBankEver ",
    "inn": "1111111",
    "kpp": "55555",
    "address": "Moscow, Green street, 4",
    "phone": "4959999999",
    "isActive": "true"
}
```
Attention, it is mandatory to write parameters into body request. At least a request must have parameter "id", "name", "fullName", "inn", "kpp", "address".

As a response you will receive Json formatted output:
```
{
    "data": {
        "result": "success"
    }
}
```

- POST request to save an organizations. Address http://localhost:8888/organization/save

Almost the same as for update organizations but body parameters slightly different.
Example:
```
{
    "name": "NotSoBestBankEver",
    "fullName": "LLC NotSoBestBankEver",
    "inn": "99999",
    "kpp": "5555",
    "address": "Moscow, Noname street, 99",
    "phone": "4956666666",
    "isActive": "false"
}
```
Attention, it is mandatory to write parameters into body request. At least a request must have parameter "name", "fullName", "kpp", "kpp", "address".

- GET request to get an organizations by ID. Address http://localhost:8888/organization/1

You can simply put that link into your brawser and you will recive Json formatted output:
```
{
    "data": {
        "id": "2",
        "name": "Secondbank",
        "fullName": "LLC_Second_bank ",
        "inn": "0987654321",
        "kpp": "123456789",
        "address": "Boston, Green street, 4",
        "phone": "9897785432",
        "isActive": "true"
    }
}
```
___
# Office-related queries
There are four allowed queries:

- POST request to get a list of all offices. Address http://localhost:8888/office/list
![getOfficeList]()
Attention, it is mandatory to write parameters into body request. At least a request must have parameter "name".

