# Sber study project
### REST-service that provides a set of operations for getting, saving, updating organizations, offices, and users' objects. It uses only two Http methods GET and POST.
### The web service provides support queries to get the list citizenship with the name of the country and its code, as well as to get a list of document types with the name of the document and its code. To run that application it is required to have Java 11.

___
# API description
- How to start the application
- How to send requests
- Referencebook-related queries
- Organization-related queries
- Office-related queries
- User-related queries

The descriptions below shows the usage of POSTMAN app as a client app that makes requests.
___
# How to start the application
- The application can be launched through IDE (like Intellij IDEA). Just download the repository and launch pom.xml as a project

___
# How to send requests
- For GET-requests, the address bar is enough. Example: http://localhost:8888/user/1
- For POST-requests, you may want to use POSTMAN or something similar. It is essential to write the body of the request in an acceptable format.
___
# Referencebook-related queries 
Referencebook-related request to get a list of countries and their codes.

An example of a request to get a list of citizenship:
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
Attention, it is mandatory to write parameters into body requests. At least a request must have the parameter "name".

- POST request to update an organization. Address http://localhost:8888/organization/update

Almost the same as for list of organizations but you can write some more parameters into the body request.
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

- POST request to save an organization. Address http://localhost:8888/organization/save

Almost the same as for an update organizations request but body parameters slightly different.
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

As a response you will receive Json formatted output:
```
{
    "data": {
        "result": "success"
    }
}
```

- GET request to get an organization by ID. Address http://localhost:8888/organization/1

You can simply put that link into your browser and you will receive Json formatted output:
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
![getOfficeList](https://github.com/AlexWhoDoes/bell_study/blob/master/img/OfficeList.jpg)
Attention, it is mandatory to write parameters into body requests. At least a request must have the parameter "orgId".

- POST request to update an organization. Address http://localhost:8888/office/update

Almost the same as for list of offices but you can write some more parameters into body request.
Example:
```
{
    "id": "1",
    "name": "UpdatedBank",
    "address": "Moscow, Red square, 1",
    "phone": "849911111",
    "isActive": "true"
}
```
Attention, it is mandatory to write parameters into body requests. At least a request must have parameter "id", "name", "address".

As a response you will receive Json formatted output:
```
{
    "data": {
        "result": "success"
    }
}
```

- POST request to save an office. Address http://localhost:8888/office/save

Almost the same as for update an office but body parameters slightly different.
Example:
```
{
    "orgId": "1",
    "name": "OneMoreOffice",
    "address": "Moscow, Banana square, 1",
    "phone": "84999944",
    "isActive": "true"
}
```
Attention, it is mandatory to write parameters into body requests. At least a request must have the parameter "orgId".

As a response you will receive Json formatted output:
```
{
    "data": {
        "result": "success"
    }
}
```

- GET request to get an organizations by ID. Address http://localhost:8888/office/1

You can simply put that link into your browser and you will receive Json formatted output:

```
{
    "data": {
        "id": "1",
        "name": "Head_office",
        "address": "Boston, Blue street, 5",
        "phone": "4959999958",
        "isActive": "true"
    }
}
```
___
# User-related queries
There are four allowed queries:

- POST request to get a list of all users. Address http://localhost:8888/user/list
![getOfficeList](https://github.com/AlexWhoDoes/bell_study/blob/master/img/userList.jpg)
Attention, it is mandatory to write parameters into body requests. At least a request must have the parameter "officeId".

- POST request to update a user. Address http://localhost:8888/user/update

Almost the same as for a list of user request but you can write some more parameters into the body request.
Example:
```
{ 
  "id" : 4,
  "officeId":"01",
  "firstName":"newName",
  "secondName": "newSecondName",
  "middleName": "newMiddleName",
  "position": "Cgreat position",
  "phone": "899901200",
  "docName":"Passport",
  "docNumber":"77-33",
  "docDate":"2020-01-01",
  "citizenshipCode":"002",
  "isIdentified":true 
}
```
Attention, it is mandatory to write parameters into body requests. At least a request must have parameter "id", "firstName", "position".

As a response you will receive Json formatted output:
```
{
    "data": {
        "result": "success"
    }
}
```
- POST request to save a user. Address http://localhost:8888/user/save

Almost the same as for update an office but body parameters slightly different.
Example:
```
{
    "officeId" : 3,
    "firstName": "Alex",
    "secondName": "Black",
    "middleName": "j",
    "position": "CIO",
    "phone" : "8495396363",
    "docCode" : "02",
    "docName" : "Passport",
    "docNumber" : "773-777",
    "docDate" : "2012-01-25",
    "citizenshipCode" : "001",
    "isIdentified" : false
}
```
Attention, it is mandatory to write parameters into body requests. At least a request must have parameter "officeId", "firstName", "position".

As a response you will receive Json formatted output:
```
{
    "data": {
        "result": "success"
    }
}
```

- GET request to get a user by ID. Address http://localhost:8888/user/1

You can simply put that link into your browser and you will receive Json formatted output:

```
{
    "data": {
        "id": "1",
        "firstName": "Alexander",
        "secondName": "Vinogradov",
        "middleName": "Dmitrievich",
        "position": "CIO",
        "phone": "9999453378",
        "docName": "Passport",
        "docNumber": "778-887",
        "docDate": "2007-01-01",
        "citizenshipName": "Russian federation",
        "citizenshipCode": "001",
        "isIdentified": "true"
    }
}
```
