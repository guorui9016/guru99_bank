package pagerepository;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import base.PageBase;
import util.JsonDataLoader;

/**
 * @author Rui Guo
 *
 * Guru99 bank demo: change password object class
 *
 */
public class ChangePasswordPage extends PageBase {

    //init webElement
    @FindBy(name = "oldpassword")
    @CacheLookup
    private WebElement weOldPassword;

    @FindBy(name = "newpassword")
    @CacheLookup
    private WebElement weNewpassword;

    @FindBy(name = "confirmpassword")
    @CacheLookup
    private WebElement weConfirmpassword;

    @FindBy(name = "sub")
    @CacheLookup
    private WebElement weSubmit;

    @FindBy(className = "heading3")
    @CacheLookup
    private WebElement weTitle;

    public ChangePasswordPage(WebDriver driver) {
        super(driver);
    }

    public ChangePasswordPage(WebDriver driver, String expTitle) {
        super(driver, expTitle);
    }

    /**
     * Fill up all password fields then change password
     *
     * @param oldPwd
     * @param newPwd
     * @param confPwd
     */
    public void changePassword(String oldPwd, String newPwd, String confPwd) {
        sendKey(weOldPassword, oldPwd);
        sendKey(weNewpassword, newPwd);
        sendKey(weConfirmpassword, confPwd);
        weSubmit.click();
    }

    /**
     * Compare the expect and actual alert message
     *
     * @param expectMessage
     */
    public void verifyAlert(String expectMessage) {
        Alert alert = driver.switchTo().alert();
        String text = alert.getText();
        alert.accept();
        Assert.assertEquals(text, expectMessage);
    }

    /**
     * Verify the page title
     */
    public void verifyHeader() {
        String text = weTitle.getText();
        Assert.assertEquals(text, JsonDataLoader.getExpectContent(this.getClass(),"expectHeader"));
    }
}
