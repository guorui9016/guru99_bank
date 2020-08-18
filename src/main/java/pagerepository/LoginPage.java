package pagerepository;

import com.google.gson.JsonObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import templet.PageTemplet;
import util.JsonTestDataLoader;

/**
 * @author Rui Guo
 *
 * Guru99 bank demo: Login page object class
 */

public class LoginPage extends PageTemplet {
    private WebDriver driver;
    private String expectTitle = "Guru99 Bank Home Page";
    private By userID = new By.ByName("uid");
    private By password = new By.ByName("password");
    private By btnLogin = new By.ByName("btnLogin");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Load account info from Json file then automatic login system.
     */
    public void autoLogin() {
        JsonObject account = JsonTestDataLoader.getJsonObject("account");

        inputUserID(account.get("userId").getAsString());
        inputPassword(account.get("password").getAsString());
        clickSubmit();
    }

    public void inputUserID(String id) {
        sendKey(driver.findElement(userID),id);
    }

    public void inputPassword(String pwd) {
        sendKey(driver.findElement(password), pwd);
    }

    public void clickSubmit() {
        driver.findElement(btnLogin).click();
    }

    public void checkTitle() {
        Assert.assertEquals(driver.getTitle(), expectTitle);
    }
}
