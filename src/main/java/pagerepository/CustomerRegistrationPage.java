package pagerepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import templet.PageTemplet;
import util.Util;

/**
 * @author Rui Guo
 *
 * Guru99 bank: Customer regeistration page object class
 */

public class CustomerRegistrationPage extends PageTemplet {
    private WebDriver driver;

    private By id = new By.ByCssSelector("#customer > tbody > tr:nth-child(4) > td:nth-child(2)");
    private By txtName = new By.ByCssSelector("#customer > tbody > tr:nth-child(5) > td:nth-child(2)");
    private By txtGender = new By.ByCssSelector("#customer > tbody > tr:nth-child(6) > td:nth-child(2)");
    private By txtDob = new By.ByCssSelector("#customer > tbody > tr:nth-child(7) > td:nth-child(2)");
    private By txtAddress = new By.ByCssSelector("#customer > tbody > tr:nth-child(8) > td:nth-child(2)");
    private By txtCity = new By.ByCssSelector("#customer > tbody > tr:nth-child(9) > td:nth-child(2)");
    private By txtState = new By.ByCssSelector("#customer > tbody > tr:nth-child(10) > td:nth-child(2)");
    private By txtPin = new By.ByCssSelector("#customer > tbody > tr:nth-child(11) > td:nth-child(2)");
    private By txtMobileNum = new By.ByCssSelector("#customer > tbody > tr:nth-child(12) > td:nth-child(2)");
    private By txtEmail = new By.ByCssSelector("#customer > tbody > tr:nth-child(13) > td:nth-child(2)");

    public CustomerRegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Verify the details of added customer
     */
    public void verifyAddedCustomer(String name, String gender, String dob,
                                    String address,String city, String state,
                                    String pin, String phone, String email) {
        WebDriverWait wait = new WebDriverWait(driver, 10, 3);
        wait.until(ExpectedConditions.titleContains("Registration Page"));
        Util.screenShot(driver, "verifyAddedCustomer");
        String getName = driver.findElement(txtName).getText();
        Assert.assertEquals(getName, name);
        String getGender = driver.findElement(txtGender).getText();
        Assert.assertEquals(getGender, gender);
        String getDob = driver.findElement(txtDob).getText();
        Assert.assertEquals(getDob, dob);
        String getAddress = driver.findElement(txtAddress).getText();
        Assert.assertEquals(getAddress, address);
        Assert.assertEquals(driver.findElement(txtCity).getText(), city);
        Assert.assertEquals(driver.findElement(txtState).getText(), state);
        Assert.assertEquals(driver.findElement(txtPin).getText(), pin);
        Assert.assertEquals(driver.findElement(txtMobileNum).getText(), phone);
        String getEmail = driver.findElement(txtEmail).getText();
        Assert.assertEquals(getEmail, email);
    }

    /**
     * Get the customer id which produced by bank system
     */
    public String getCustomerID() {
        return driver.findElement(id).getText();
    }
}
