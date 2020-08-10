package pagerepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    WebDriver driver;
    private By changePassword = new By.ByCssSelector(".menusubnav > li:nth-child(11) > a");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public void changePasswordLink() {
        driver.findElement(changePassword).click();
    }
}
