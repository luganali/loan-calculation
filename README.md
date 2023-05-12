Loan Calculation Application

This application is using for different types of  Loan Calculations.

Run the spring boot application directly with Application class.

With using Postman, you can use this URL; localhost:8081/loan/calculateLoan

Here is the request example;

{ "type":"HOUSING_LOAN", "term": 12, "amount": 120000 }

and expected response;

{ "monthlyPayment": 1021.74}

Or 

You can use Swagger interface to send your request :

http://localhost:8081/swagger-ui/
