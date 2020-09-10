package Manager;

import com.google.gson.JsonObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import pagerepository.*;
import templet.TestCaseTemplet;
import util.Constants;
import util.JsonDataLoader;

/**
 * @author Rui Guo
 *
 * Guru99 bank: Add New Customer & Account
 */
public class TC_NewCustomerAccount extends TestCaseTemplet {
    private Logger logger = LogManager.getLogger(TC_NewCustomerAccount.class);
    private WebDriver driver = getDriver();
    private String customerId;
    private String expHomePageTitle;
    private String accountId;

    /**
     * Verify after Adding New Customer, page redirects to details of added customer
     *
     * @param object
     */
    @Test(dataProvider = "testData", dataProviderClass = JsonDataLoader.class)
    public void sm4_AddNewCustomer(JsonObject object) {
        //Navigate to add new customer page
        expHomePageTitle = JsonDataLoader.getExpectContent(ManagerHomePage.class, Constants.EXPECT_PAGE_TITLE);
        ManagerHomePage managerHomePage = new ManagerHomePage(driver, expHomePageTitle);
        NewCustomerPage addNewCustomerPage = managerHomePage.navNewCustomerLink();
        //add a new customer
        addNewCustomerPage.addNewCustomer(object);
        logger.info("A new customer has been added.");
        //check the details of the added customer
        String expCusRegPageTitle = JsonDataLoader.getExpectContent(CustomerRegistrationPage.class, Constants.EXPECT_PAGE_TITLE);
        CustomerRegistrationPage registrationPage = new CustomerRegistrationPage(driver,expCusRegPageTitle);
        registrationPage.verifyAddedCustomer(object);
        logger.info("Checked the details of the added customer");
        registrationPage.back2HomePage();
    }

    /**
     * Verify a new account can be added to new customer
     *
     * @param testData
     */
    @Test( dataProvider = "testData", dataProviderClass = JsonDataLoader.class, dependsOnMethods = "sm4_AddNewCustomer")
    public void sm5_AddAccount(JsonObject testData) {
        //Navigate to new account page from homepage
        ManagerHomePage homePage = new ManagerHomePage(driver, expHomePageTitle);
        NewAccountPage newAccountPage = homePage.navNewAccountLink();
        //get customer id from json file
        JsonObject newCustomer = JsonDataLoader.getDataObject("sm4_AddNewCustomer");
        customerId = newCustomer.get("customerId").getAsString();
        //create a new account
        newAccountPage.addNewAccount(customerId,testData);
        logger.info("A new customer has been add to customer: " + customerId);
        //verify this account
        String expAccRegPageTitle = JsonDataLoader.getExpectContent(AccountRegPage.class, Constants.EXPECT_PAGE_TITLE);
        AccountRegPage accountRegPage = new AccountRegPage(driver, expAccRegPageTitle);
        accountRegPage.verifyAccount(customerId, testData);
        logger.info("New account details has been checked");
        accountRegPage.back2HomePage();
    }

    /**
     * Can not delete a customer if it has a account
     */
    @Test(dependsOnMethods = "sm5_AddAccount")
    public void sm11_12_DelCustomerWithAccount() {
        //Navigate to delete customer page from home page
        ManagerHomePage homePage = new ManagerHomePage(driver, expHomePageTitle);
        DeleteCustomerPage deleteCustomerPage = homePage.navDelCustomerLink();
        logger.info("Navigate to delete customer page");
        //delete a customer which hold an account
        deleteCustomerPage.delCustomer(customerId);
        logger.info("Try to delete a customer which hold an account");
        //check the alert message.
        deleteCustomerPage.verifyNoticeMsg();
        deleteCustomerPage.verifyDelErrorMsg();
        logger.info("The alert messages have been checked");
        //Redirect to Customer delete form
        deleteCustomerPage.verifyTitle();
        //Back to home page
        deleteCustomerPage.back2HomePage();
    }

    /**
     * delete a account
     */
    @Test(dependsOnMethods = "sm11_12_DelCustomerWithAccount")
    public void sm6_7_DelAccount() {
        //Goto delete account page
        ManagerHomePage homePage = new ManagerHomePage(driver, expHomePageTitle);
        DeleteAccountPage deleteAccountPage = homePage.navDelAccountLink();
        //Input valid account id and submit the form
        accountId = JsonDataLoader.getDataObject("sm5_AddAccount").get("accountId").getAsString();
        deleteAccountPage.delAccount(accountId);
        //verify the alert message
        deleteAccountPage.verifyNoticeMsg();
        //confirm delete the account
        deleteAccountPage.verifyDelSuccMsg();
        //redirected to manager home page
        homePage.verifyTitle();
    }

    /**
     * Verify mini statement for deleted account
     */
    @Test(groups = "checkDeletedAccunt", dependsOnMethods = "sm6_7_DelAccount")
    public void sm8_MiniStatementOfDeleteAccount() {
        //goto mini statement page
        ManagerHomePage homePage = new ManagerHomePage(driver);
        MiniStatementPage miniStatementPage = homePage.navMiniStatementLink();
        logger.info("Navigate to mini statement page");
        //verify the mini statement is no generated for the deleted account
        miniStatementPage.miniStatement(accountId);
        logger.info("input the deleted account id");
        //verify the alert message
        miniStatementPage.verifyIncorrectAccountId();
        //Redirects to Balance Enquiry page
        miniStatementPage.verifyTitle();
        logger.info("The alert message has been checked and redirects to Mini statement page");
        miniStatementPage.back2HomePage();
    }

    /**
     * Verify balance for deleted account
     */
    @Test(groups = "checkDeletedAccunt", dependsOnMethods = "sm6_7_DelAccount")
    public void sm9_BalanceOfDeletedAccount() {
        //goto balance enquiry page
        ManagerHomePage homePage = new ManagerHomePage(driver);
        BalanceEnquiryPage balanceEnquiryPage = homePage.navBalanceEnquiryLink();
        logger.info("Navigate to Balance Equiry page");
        balanceEnquiryPage.balanceEnquiry(accountId);
        //A pop "Account does not exist"
        balanceEnquiryPage.verifyIncorrectAccountId();
        //Redirects to Balance Enquiry page
        balanceEnquiryPage.verifyTitle();
        balanceEnquiryPage.back2HomePage();
    }

    /**
     * Verify statement of deleted account
     */
    @Test(groups = "checkDeletedAccunt", dependsOnMethods = "sm6_7_DelAccount")
    public void sm10_CustomizedStatement() {
        //goto customized statement page
        ManagerHomePage homePage = new ManagerHomePage(driver);
        CustomizedStatementPage customizedStatementPage = homePage.navCustomizedStatementLink();
        logger.info("Navigate to Customized statement page");
        //fill up the page
        customizedStatementPage.customizedStatement(accountId);
        //verify the alert message
        customizedStatementPage.verifyIncorrectAccNoMessage();
        //redirects to customized statement page
        customizedStatementPage.verifyTitle();
        customizedStatementPage.back2HomePage();
    }

    /**
     * Verify that a Customer can be Deleted
     */
    @Test(dependsOnGroups = "checkDeletedAccunt")
    public void sm13_DelCustomerWithoutAccount() {
        ManagerHomePage homePage = new ManagerHomePage(driver);
        DeleteCustomerPage deleteCustomerPage = homePage.navDelCustomerLink();
        deleteCustomerPage.delCustomer(customerId);
        deleteCustomerPage.verifyDelSuccessfulMsg();
        homePage.verifyTitle();
    }

    //  sm14, sm15 do something with the delete customer

    /**
     * Verify deleted customer cannot be edited
     */
    public void sm14_EditCustomer() {
        //todo
        //Load home page

        //Navigate to edit customer page

        //input deleted customer id

        //verify alert message

        //back to home page
    }

    public void sm15_DelNonExistingCustomer() {
        //load home page

        //Navigate to delete customer page

        //input the delelted customer id

        //verify the alert message

        //all the test should be done.
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
        logger.info("Quit driver");
    }
}