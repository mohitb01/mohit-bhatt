# mohit-bhatt

This framework is based on Serenity-BDD. Its a Maven based project where all the dependencies are supplied on POM.xml. 

Please check design pattern of the framework below.

<b>Resources</b> :

Package configFiles : Here we have store our configuration file and test data.
Package feature : Here we would be adding our test cases in form of Gherkin language.

<b>Java</b> :

SerenityBase : This is the initilization place of our framework.This class initialize all the require configuration and load to memory for future use.

Endpoint : This package contains endpoint for the APIs.

RunnerCukes : This is to start out test cases. Here we can add tags which needs to be executed.

Stepdefinition : This is the glue code for the test features.

Utility : This package contains all the utilities required for the execution of the project. As SerenityServices with CRUD operations, Xls,Respons validation etc.

<b>How to execute</b>

Step 1 : Run maven command <b>clean verify</b> : This will run all the test cases with tag mentioned on Runner file.
Step 2 : After the execution go to target->Serenity->site->Search for index.html
