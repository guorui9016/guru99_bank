package pagerepository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import templet.PageTemplet;

import java.util.List;

/**
 * @author Rui Guo
 *
 * Guru99 bank demo: Add New Customer page object class
 */
public class AddNewCustomerPage extends PageTemplet {
    Logger logger = LogManager.getLogger(AddNewCustomerPage.class);
    private WebDriver driver;
    private String existAccountErrorMsg = "Email Address Already Exist !!";
    private By txtName = new By.ByName("name");
    private By radioGenderMale = new By.ByXPath("//input[@value='m']");
    private By radioGenderFemale = new By.ByXPath("//input[@value='f']");
    private By txtDOB = new By.ByName("dob");
    private By txtAddress = new By.ByName("addr");
    private By txtCity = new By.ByName("city");
    private By txtState = new By.ByName("state");
    private By txtPIN = new By.ByName("pinno");
    private By txtMobileNum = new By.ByName("telephoneno");
    private By txtEmail = new By.ByName("emailid");
    private By txtPWD = new By.ByName("password");
    private By btnSubmit = new By.ByName("sub");

    public AddNewCustomerPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Fill up all of new customer info then add the customer.
     *
     * @param name
     * @param gender
     * @param dob
     * @param address
     * @param city
     * @param state
     * @param phone
     * @param email
     * @param pwd
     */
    public void addNewCustomer(String name, String gender, String dob,
                               String address,String city, String state,
                               String pin, String phone, String email, String pwd) {
        sendKey(driver.findElement(txtName), name);
        //select a radio button
        if (gender.equalsIgnoreCase("male")) {
            driver.findElement(radioGenderMale).click();
        } else {
            driver.findElement(radioGenderFemale).click();
        }

        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.TAB).perform();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        actions.sendKeys(dob).perform();
        sendKey(driver.findElement(txtAddress), address);
        sendKey(driver.findElement(txtCity), city);
        sendKey(driver.findElement(txtState), state);
        sendKey(driver.findElement(txtPIN), pin);
        sendKey(driver.findElement(txtMobileNum), phone);
        sendKey(driver.findElement(txtEmail), email);
        sendKey(driver.findElement(txtPWD), pwd);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(btnSubmit).click();
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
