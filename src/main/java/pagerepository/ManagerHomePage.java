package pagerepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import templet.PageTemplet;

/**
 * @author Rui Guo
 *
 * Guru99 bank demo: Home page object class
 *
 */

public class ManagerHomePage extends PageTemplet {
    //init variable
    private WebDriver driver;
    private String expectTitle = "Guru99 Bank Manager ManagerHomePage";
    private By changePasswordLink = new By.ByCssSelector(".menusubnav > li:nth-child(11) > a");
    private By newCustomerLikn = new By.ByCssSelector(".menusubnav > li:nth-child(2) > a");
    private By managerID = new By.ByCssSelector(".heading3 td");

    public ManagerHomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * navChangePasswordLink method to navigate change password page
     *
     * @return
     */
    public ChangePasswordPage navChangePasswordLink() {
        driver.findElement(changePasswordLink).click();
        return new ChangePasswordPage(driver);
    }

    public AddNewCustomerPage navNewCustomerLink() {
        driver.findElement(newCustomerLikn).click();
        return new AddNewCustomerPage(driver);
    }

    /**
     *  verifyTitle method to verify page title
     */
    public void verifyTitle() {
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.titleContains("Manager"));
        Assert.assertEquals(driver.getTitle(), expectTitle);
    }

    /**
     *   verifyManagerID method to verify Manager ID.
     *
     * @param userID
     */
    public void verifyManagerID(String userID) {
        Assert.assertEquals(driver.findElement(managerID).getText(), userID);
    }
}
