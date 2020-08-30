package pagerepository;

import com.google.gson.JsonObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import templet.PageTemplet;
import util.JsonDataLoader;
import util.Util;
import java.util.HashMap;

/**
 * @author Rui Guo
 *
 * Guru99 bank: Customer regeistration page object class
 */
public class CustomerRegistrationPage extends PageTemplet {
    private Logger logger = LogManager.getLogger(CustomerRegistrationPage.class);

    @FindBy(css = "#customer > tbody > tr:nth-child(4) > td:nth-child(2)")
    @CacheLookup
    private WebElement weCustomerId;

    @FindBy(css = "#customer > tbody > tr:nth-child(5) > td:nth-child(2)")
    @CacheLookup
    private WebElement weName;

    @FindBy(css = "#customer > tbody > tr:nth-child(6) > td:nth-child(2)")
    @CacheLookup
    private WebElement weGender;

    @FindBy(css = "#customer > tbody > tr:nth-child(7) > td:nth-child(2)")
    @CacheLookup
    private WebElement weDob;

    @FindBy(css = "#customer > tbody > tr:nth-child(8) > td:nth-child(2)")
    @CacheLookup
    private WebElement weAddress;

    @FindBy(css = "#customer > tbody > tr:nth-child(9) > td:nth-child(2)")
    @CacheLookup
    private WebElement weCity;

    @FindBy(css = "#customer > tbody > tr:nth-child(10) > td:nth-child(2)")
    @CacheLookup
    private WebElement weState;

    @FindBy(css = "#customer > tbody > tr:nth-child(11) > td:nth-child(2)")
    @CacheLookup
    private WebElement wePin;

    @FindBy(css = "#customer > tbody > tr:nth-child(12) > td:nth-child(2)")
    @CacheLookup
    private WebElement weMobileNum;

    @FindBy(css = "#customer > tbody > tr:nth-child(13) > td:nth-child(2)")
    @CacheLookup
    private WebElement weEmail;

    public CustomerRegistrationPage(WebDriver driver) {
        super(driver);
    }

    public CustomerRegistrationPage(WebDriver driver, String expTitle) {
        super(driver, expTitle);
    }

    /**
     * Verify the details of added customer
     */
    public void verifyAddedCustomer(JsonObject object) {
        //load test data from json object
        String name = object.get("customerName").getAsString();
        String gender = object.get("gender").getAsString();
        String verifyDob = object.get("verifyDob").getAsString();
        String address = object.get("address").getAsString();
        String city = object.get("city").getAsString();
        String state = object.get("state").getAsString();
        String pin = object.get("pin").getAsString();
        String mobileNum = object.get("mobileNum").getAsString();
        String email = object.get("email").getAsString();

        Util.screenShot(driver, "verifyAddedCustomer"+ name);
        setCustomerIdToJsonFile(object);

        Assert.assertEquals(weName.getText(), name);
        Assert.assertEquals(weGender.getText(), gender);
        Assert.assertEquals(weDob.getText(), verifyDob);
        Assert.assertEquals(weAddress.getText(), address);
        Assert.assertEquals(weCity.getText(), city);
        Assert.assertEquals(weState.getText(), state);
        Assert.assertEquals(wePin.getText(), pin);
        Assert.assertEquals(weMobileNum.getText(), mobileNum);
        Assert.assertEquals(weEmail.getText(), email);
    }

    /**
     * Put customer id to json file for delete customer test.
     *
     * Notice: Do not need this step if load data from database.
     *          I can not access guru99 bank database, so I used Json file instead of it.
     */
    private void setCustomerIdToJsonFile(JsonObject object) {
        if (!weCustomerId.getText().isEmpty()) {
            HashMap<String, String> customerId = new HashMap<>();
            customerId.put("customerId", weCustomerId.getText());
            JsonDataLoader.updateDataToFile(object, customerId);
            logger.info("Customer ID has been write to Json file.");
        }
    }
}
