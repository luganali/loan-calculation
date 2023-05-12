Loan Calculation Application

This application is using for different types of  Loan Calculations.

There are two ways for running the application:

Run the spring boot application directly with Application class

Use docker with following instruction

a) docker build --tag=loan-calculator:latest .

b) docker run -it -p 8081:8081 loan-calculator:latest

With using Postman, you can use this URL; localhost:8081/loan/calculate


Here is the request example;

{ "type":"HOUSING_LOAN", "term": 12, "amount": 120000 }

and expected response;

{ "monthlyPayment": 1021.74}

Or 

You can use Swagger interface to send your request :

http://localhost:8081/swagger-ui/
