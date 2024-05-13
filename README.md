# Capital Gains

This project does not use frameworks such as spring boot, however in some components of the operation one of the dependency injection pattern is used in order to facilitate the encapsulation of the logic associated with the different functionalities of the tax calculation process in the sale of shares.

In summary, the program will receive two kinds of operations ( buy and sell ) to calculate the capital gains
tax. The tax is fixed at 20% of the overall profit obtained by trading stocks, and operations with a total amount
equal to or below $ 20,000.00 do not pay taxes. The rules to calculate the tax are detailed below:

## requirements
The following items are required to run the application:

- Java 17
- Maven
- JUnit 5

## running project

- **mvn clean install**
- **java -jar target/backend-services-capital-gains-1.0.0-SNAPSHOT.jar**
- input use cases
- to tun test cases: **mvn test**

## components

The service package stores transversal components that behave like business logic, each component has a specific function and does not alter the operation of the others.
Below is the explanation of each service exposed.

- CapitalGainTaxService: It is the main component where the tax calculation is made according to the purchase or sale of stocks.
- ConsoleReaderService: is responsible for obtaining the information that is entered through the console.
- PropertiesReaderService: As a solution to the proposed problem, a configuration file is proposed to update elements with the percentage of taxes to be collected, minimum amount and validations of the input format. This proposal is proposed in such a way that in future implementations the code should not be interfered with, but rather the parameterized variables should be updated.
- StockMarketValidatorService: It is a service that allows you to validate the structure of the elements that are entered through the console, in order to evaluate whether it is a valid entry or not and avoid resource consumption by processing invalid data.