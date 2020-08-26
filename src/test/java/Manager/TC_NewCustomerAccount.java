package Manager;

import com.google.gson.JsonObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import pagerepository.NewAccountPage;
import pagerepository.NewCustomerPage;
import pagerepository.CustomerRegistrationPage;
import pagerepository.ManagerHomePage;
import templet.TestCaseTemplet;
import util.JsonDataLoader;

/**
 * @author Rui Guo
 *
 * Guru99 bank: Add New Customer & Account
 */
public class TC_NewCustomerAccount extends TestCaseTemplet {
    private Logger logger = LogManager.getLogger(TC_NewCustomerAccount.class);
    private WebDriver driver = getDriver();

    /**
     * Verify after Adding New Customer, page redirects to details of added customer
     *
     * @param object
     */
    @Test(dataProvider = "testData", dataProviderClass = JsonDataLoader.class)
    public void sm4_AddNewCustomer(JsonObject object) {
        //Navigate to add new customer page
        String homePageTitle = JsonDataLoader.getExpectContent(ManagerHomePage.class, "expectTitle");
        ManagerHomePage managerHomePage = new ManagerHomePage(driver, homePageTitle);
        NewCustomerPage addNewCustomerPage = managerHomePage.navNewCustomerLink();
        //add a new customer
        addNewCustomerPage.addNewCustomer(object);
        logger.info("A new customer has been added.");
        //check the details of the added customer
        String expectTitle = JsonDataLoader.getExpectContent(CustomerRegistrationPage.class, "expectTitle");
        CustomerRegistrationPage registrationPage = new CustomerRegistrationPage(driver,expectTitle);
        registrationPage.verifyAddedCustomer(object);
        logger.info("Checked the details of the added customer");
        registrationPage.backToHomePage();
    }

    /**
     * Verify a new account can be added to new customer
     *
     * @param testData
     */
    @Test(dataProvider = "testDate")
    public void sm5_AddAccount(JsonObject testData) {
        //Navigate to new account page from homepage
        ManagerHomePage homePage = new ManagerHomePage(driver);
        NewAccountPage newAccountPage = homePage.navNewAccountLink();
        //get customer id from json file
        int no = testData.get("No").getAsInt();
        JsonObject newCustomer = (JsonObject) JsonDataLoader.getTestDataArray("sm4_AddNewCustomer").get(no);
        String customerId = newCustomer.get("customerId").getAsString();
        //create a new account
        newAccountPage.addNewAccount(customerId,testData);
        logger.info("A new customer has been add to customer: " + customerId);
        //verify this account


    }
    @AfterClass
    public void tearDown() {
        driver.quit();
        logger.info("Quit driver");
    }
}