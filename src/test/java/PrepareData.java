import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pagerepository.*;
import templet.TestCaseTemplet;
import util.Constants;
import util.JsonDataLoader;

/**
 * Initialize two customers and two accounts for testing such as transfer and deposit.
 */
public class PrepareData {

    private static WebDriver driver;

    //add customers and account
    public static void main(String[] args) {
        //run in headless mode
        TestCaseTemplet testCase = new TestCaseTemplet(true);
        testCase.login();
        driver = testCase.getDriver();
        //get data from Json file
        JsonArray jsonArray = JsonDataLoader.prepareData();
        //Creat new customer and account
        for (JsonElement jsonElement : jsonArray) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            addCustomerAndAccount(jsonObject);
        }
    }

    private static void addCustomerAndAccount(JsonObject jsonObject) {
        //load home page
        ManagerHomePage homePage = new ManagerHomePage(driver);
        //goto add new customer page
        NewCustomerPage newCustomerPage = homePage.navNewCustomerLink();
        //fill up all field and submit
        newCustomerPage.addNewCustomer(jsonObject);
        //get the customer id from the customer details
        String expectTitle = JsonDataLoader.getExpectContent(CustomerRegistrationPage.class, Constants.EXPECT_PAGE_TITLE);
        CustomerRegistrationPage registrationPage = new CustomerRegistrationPage(driver, expectTitle);
        String customerId = registrationPage.getCustomerId();
        registrationPage.updateCustomerId2JsonFile(jsonObject);
        registrationPage.back2HomePage();
        //goto new account page
        WebDriverWait wait = new WebDriverWait(driver, Constants.TIME_OUT);
        wait.until(ExpectedConditions.titleContains("Home"));
        NewAccountPage newAccountPage = homePage.navNewAccountLink();
        //create new account
        newAccountPage.addNewAccount(customerId, jsonObject);
        //get the account id from the details page
        AccountRegPage accountRegPage = new AccountRegPage(driver, JsonDataLoader.getExpectContent(AccountRegPage.class, Constants.EXPECT_PAGE_TITLE));
        //save customer id and account id to Json file
        accountRegPage.updateAccount2JsonFile(customerId,jsonObject);
        //back to home page
        accountRegPage.back2HomePage();
    }

    /*
            String name = jsonObject.get("customerName").getAsString();
        String gender = jsonObject.get("gender").getAsString();
        String dob = jsonObject.get("dob").getAsString();
        String address = jsonObject.get("address").getAsString();
        String city = jsonObject.get("city").getAsString();
        String state = jsonObject.get("state").getAsString();
        String pin = jsonObject.get("pin").getAsString();
        String mobileNum = jsonObject.get("mobileNum").getAsString();
        String email = jsonObject.get("email").getAsString();
        String pwd = jsonObject.get("pwd").getAsString();
        String typeValue = jsonObject.get("typeValue").getAsString();
        String deposit = jsonObject.get("deposit").getAsString();
     */
}