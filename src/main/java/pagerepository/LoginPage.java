package pagerepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import templet.PageTemplet;
import util.Constants;
import util.Util;

public class LoginPage extends PageTemplet {
    WebDriver driver;
    private By userID = new By.ByName("uid");
    private By password = new By.ByName("password");
    private By btnLogin = new By.ByName("btnLogin");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
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

    public void autoLogin() {
        inputUserID(Constants.USER_ID);
        inputPassword(Constants.PASSWORD);
        clickSubmit();
    }
}
