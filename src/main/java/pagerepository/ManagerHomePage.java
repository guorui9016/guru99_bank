package pagerepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import templet.PageTemplet;
import util.JsonDataLoader;

/**
 * @author Rui Guo
 *
 * Guru99 bank demo: Home page object class
 *
 */
public class ManagerHomePage extends PageTemplet {
    //WebElements
    @FindBy(css = ".menusubnav > li:nth-child(11) > a")
    @CacheLookup
    private WebElement changePasswordLink;

    @FindBy(css = ".heading3 td")
    @CacheLookup
    private WebElement managerID;

    @FindBy(css = ".menusubnav > li:nth-child(2) > a")
    @CacheLookup
    private WebElement newCustomerLink;

    @FindBy(css = ".menusubnav li:nth-child(5) > a:nth-child(1)")
    @CacheLookup
    private WebElement newAccountLink;

    public ManagerHomePage(WebDriver driver) {
        super(driver);
    }

    public ManagerHomePage(WebDriver driver, String expTitle) {
        super(driver, expTitle);
    }

    /**
     * navChangePasswordLink method to navigate change password page
     *
     * @return
     */
    public ChangePasswordPage navChangePasswordLink() {
        changePasswordLink.click();
        return new ChangePasswordPage(driver);
    }

    public NewCustomerPage navNewCustomerLink() {
        newCustomerLink.click();
        return new NewCustomerPage(driver);
    }

    public NewAccountPage navNewAccountLink() {
        newAccountLink.click();
        return new NewAccountPage(driver);
    }

    /**
     *  verifyHeader method to verify page title
     */
    public void verifyTitle() {
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.titleContains("Manager"));
        Assert.assertEquals(driver.getTitle(), JsonDataLoader.getExpectContent(this.getClass(), "expectTitle"));
    }

    /**
     *   verifyManagerID method to verify Manager ID.
     *
     * @param userID
     */
    public void verifyManagerID(String userID) {
        Assert.assertEquals(managerID.getText(), userID);
    }
}
