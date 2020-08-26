package pagerepository;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import templet.PageTemplet;
import util.JsonDataLoader;

/**
 * @author Rui Guo
 *
 * Guru99 bank demo: Login page object class
 */

public class LoginPage extends PageTemplet {
    @FindBy(name = "uid")
    @CacheLookup
    private WebElement weUserID;

    @FindBy(name = "password")
    @CacheLookup
    private WebElement wePassword;

    @FindBy(name = "btnLogin")
    @CacheLookup
    private WebElement weBtnLogin;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage(WebDriver driver, String expTitle) {
        super(driver, expTitle);
    }

    /**
     * Load account info from Json file then automatic login system.
     */
    public ManagerHomePage autoLogin() {
        JsonArray jsonArray = JsonDataLoader.getTestDataArray("account");
        JsonObject account = (JsonObject) jsonArray.get(0);
        inputUserID(account.get("userId").getAsString());
        inputPassword(account.get("password").getAsString());
        clickSubmit();
        String expectTitle = JsonDataLoader.getExpectContent(ManagerHomePage.class,"expectTitle");
        return new ManagerHomePage(driver, expectTitle);
    }

    public void inputUserID(String id) {
        sendKey(weUserID,id);
    }

    public void inputPassword(String pwd) {
        sendKey(wePassword, pwd);
    }

    public void clickSubmit() {
        weBtnLogin.click();
    }

    public void checkTitle() {
        Assert.assertEquals(driver.getTitle(), JsonDataLoader.getExpectContent(this.getClass(), "expectTitle"));
    }
}
