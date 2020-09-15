package pagerepository;

import com.google.gson.JsonObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import templet.PageTemplet;

/**
 * @author Rui Guo
 *
 * Guru99 bank demo: Add New Customer page object class
 */
public class NewCustomerPage extends PageTemplet {
    private Logger logger = LogManager.getLogger(NewCustomerPage.class);

    @FindBy(name = "name")
    private WebElement weName;

    @FindBy(xpath = "//input[@value='m']")
    private WebElement weRadioGenderMale;

    @FindBy(xpath = "//input[@value='f']")
    private WebElement weRadioGenderFemale;

    @FindBy(name = "dob")
    private WebElement weDob;

    @FindBy(name = "addr")
    private WebElement weAddress;

    @FindBy(name = "city")
    private WebElement weCity;

    @FindBy(name = "state")
    private WebElement weState;

    @FindBy(name = "pinno")
    private WebElement wePin;

    @FindBy(name = "telephoneno")
    private WebElement weTelephoneno;

    @FindBy(name = "emailid")
    private WebElement weEmail;

    @FindBy(name = "password")
    private WebElement wePassword;

    @FindBy(name = "sub")
    private WebElement weSubmit;

    public NewCustomerPage(WebDriver driver) {
        super(driver);
    }

    public NewCustomerPage(WebDriver driver, String expTitle) {
        super(driver, expTitle);
    }

    /**
     * Fill up all of new customer info then add the customer.
     *
     * @param object
     */
    public void addNewCustomer(JsonObject object) {
        //load test data from json object
        String name = object.get("customerName").getAsString();
        String gender = object.get("gender").getAsString();
        String dob = object.get("dob").getAsString();
        String address = object.get("address").getAsString();
        String city = object.get("city").getAsString();
        String state = object.get("state").getAsString();
        String pin = object.get("pin").getAsString();
        String mobileNum = object.get("mobileNum").getAsString();
        String email = object.get("email").getAsString();
        String pwd = object.get("pwd").getAsString();
        //input data
        sendKey(weName, name);
        //select a radio button
        if (gender.equalsIgnoreCase("male")) {
            weRadioGenderMale.click();
        } else if (gender.equalsIgnoreCase("female")){
            weRadioGenderFemale.click();
        }
        //input time to date picker
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.TAB).perform();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        actions.sendKeys(dob).perform();
        sendKey(weAddress, address);
        sendKey(weCity, city);
        sendKey(weState, state);
        sendKey(wePin, pin);
        sendKey(weTelephoneno, mobileNum);
        sendKey(weEmail, email);
        sendKey(wePassword, pwd);

        weSubmit.click();
    }

    /**
     * verify the alert message.
     */
    private void verifyAlertMsg(String message) {
        Alert alert = driver.switchTo().alert();
        String text = alert.getText();
        alert.accept();
        Assert.assertEquals(text, message);
    }
}
