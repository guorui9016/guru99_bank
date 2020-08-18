package Manager;

import com.google.gson.JsonObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import pagerepository.AddNewCustomerPage;
import pagerepository.CustomerRegistrationPage;
import pagerepository.ManagerHomePage;
import templet.TestCaseTemplet;
import util.JsonTestDataLoader;
import java.util.HashMap;

/**
 * @author Rui Guo
 *
 * Guru99 bank: Add New Customer & Account
 */
public class TC_NewCustomer extends TestCaseTemplet {
    private Logger logger = LogManager.getLogger(TC_NewCustomer.class);
    private WebDriver driver = getDriver();

    /**
     * Verify after Adding New Customer, page redirects to details of added customer
     *
     * @param object
     */
    @Test(dataProvider = "testData", dataProviderClass = JsonTestDataLoader.class)
    public void sm4_AddNewCustomer(JsonObject object) {
        //Navigate to add new customer page
        ManagerHomePage managerHomePage = new ManagerHomePage(driver);
        AddNewCustomerPage addNewCustomerPage = managerHomePage.navNewCustomerLink();
        //get test data
        String name = object.get("customerName").getAsString();
        String gender = object.get("gender").getAsString();
        String dob = object.get("dob").getAsString();
        String verifyDob = object.get("verifyDob").getAsString();
        String address = object.get("address").getAsString();
        String city = object.get("city").getAsString();
        String state = object.get("state").getAsString();
        String pin = object.get("pin").getAsString();
        String mobileNum = object.get("mobileNum").getAsString();
        String email = object.get("email").getAsString();
        String pwd = object.get("pwd").getAsString();
        //add a new customer
        addNewCustomerPage.addNewCustomer(name, gender, dob, address, city, state, pin, mobileNum, email, pwd);
        logger.info("A new customer has been added.");
        //check the details of the added customer
        CustomerRegistrationPage registrationPage = new CustomerRegistrationPage(driver);
        registrationPage.verifyAddedCustomer(name,gender, verifyDob, address, city, state, pin, mobileNum, email);
        logger.info("Checked the details of the added customer");
    }

    /**
     * Put customer id to json file for delete customer test.
     *
     * Notice: Do not need this step if load data from database.
     *          I can not access guru99 bank database, so I used Json file instead of it.
     */
    @Test(dependsOnMethods = "sm4_AddNewCustomer")
    public void setCustomerIdToJsonFile() {
        CustomerRegistrationPage registrationPage = new CustomerRegistrationPage(driver);
        HashMap<String, String> customId = new HashMap<>();
        customId.put("customerId", registrationPage.getCustomerID());
        JsonTestDataLoader.updataJsonObject("customer", customId);
        logger.info("Customer ID has been write to Json file.");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
        logger.info("Quit driver");
    }
}