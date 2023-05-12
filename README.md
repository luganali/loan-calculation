# Loan Calculation Application

This application is using for different types of  Loan Calculations.

Run the spring boot application directly with Application class .

"com.visma.loan.calculation.LoanCalculationApplication"

!There is two ways to test application : 

> With using Postman, you can use this URL; localhost:8081/loan/calculateLoan


    Here is the request example;
    
    {
    "loanAmount": 120000,
    "loanPeriodYear": 12,
    "type": "HOUSING_LOAN"
    }
    
    and expected response;
    
    { "monthlyPayment": 1021.74}

> You can use Swagger interface to send your request :

http://localhost:8081/swagger-ui/
