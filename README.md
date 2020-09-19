# Guru99_Bank
This is an automated test project for guru99 bank demo using the page factory framework.

**Notice**:
    There is no relevant authority to access database and API, so this test can only involve black-box test.

Test case:

1. Please see here: [Test Case](https://github.com/guorui9016/guru99_bank/blob/master/SystemTestPlan_v4.xlsx)
2. You can find **SystemTestPlan_v4.xlsx** from root directory of this project.

Development environment:
```
    Java 8
    Selenium 3.141.59
    TestNG 7.1.0
    Log4j 2.13.3
    Extentreports 5.0.1
    json-simple 1.1.1
```
### Before Start

- #### Web driver version
    The defult web driver is **Firefox**. Ensure that you have firefox installed and the gecko driver that matches your firefox version. You may have to update this from time to time.
    Webdriver path:
    ` guru99_bank\src\main\resources\driver\win64\ `

- #### Update login account
    Register your account: [Guru99 bank demo](http://demo.guru99.com/)
    
    To login to guru99 bank demo with your account, please update your **USERID** and **PASSWORD** in your `guru99_bank\src\main\resources\test_data.json`
    ```
    "account": {
    "userId": "YOUR USER ID",
    "password": "YOUR PASSWORD"
    }

    "sm2_CorrectOldPassword": {
    "description": "Enter correct Old Password",
    "oldPassword": "YOUR PASSWORD",
    "newPassword": "@Test002",
    "confirmPassword": "@Test002"
  }
    ```
- #### Inject data before starting the test
    Before testing, we need to inject data into the test account for testing. 
    The code to prepare data is here.  
    `guru99_bank\src\test\java\PrepareData.java`  
    
    Or execute the following command in Terminal.   
    ```
    mvn compile
    mvn exec:java -Dexec.mainClass="PrepareData" 
    ```
### Project Structure
```
    guru99_bank
    │
    │  pom.xml                      
    │  SystemTestPlan_v4.xlsx       #Test Case(From Guru99)
    │  testng.xml                   #TestNG configuration file 
    │
    ├─logs                          #For store test log
    │
    └─src
       ├─main
       │  ├─java
       │  │  ├─listener             #TestNG listener
       │  │  ├─pagerepository       #All of the page objects
       │  │  ├─base                 #Page base and test case base
       │  │  └─util                 #Json loader, Constants and others
       │  └─resources               #Webdrivers, Json files and log4j2 configuation file
       └─test
           └─java                   #Test case
              └─Manager             #Test case about Manager functions
```
 
