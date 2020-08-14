package pagerepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class HomePage {
    //init variable
    private WebDriver driver;
    private String expectTitle = "Guru99 Bank Manager HomePage";
    private By changePassword = new By.ByCssSelector(".menusubnav > li:nth-child(11) > a");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public ChangePasswordPage changePasswordLink() {
        driver.findElement(changePassword).click();
        return new ChangePasswordPage(driver);
    }

    public void checkTitle() {
        Assert.assertEquals(driver.getTitle(), expectTitle);
    }
}
