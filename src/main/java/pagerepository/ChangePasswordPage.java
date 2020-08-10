package pagerepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import templet.PageTemplet;

public class ChangePasswordPage extends PageTemplet {
    private WebDriver driver;
    private By oldPassword = new By.ByName("oldpassword");
    private By newPassword = new By.ByName("newpassword");
    private By confPassword = new By.ByName("confirmpassword");
    private By submit = new By.ByName("sub");

    public ChangePasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Fill up all password fields then change password
     *
     * @param oldPwd
     * @param newPwd
     * @param confPwd
     */
    public void changePassword(String oldPwd, String newPwd, String confPwd) {
        sendKey(driver.findElement(oldPassword), oldPwd);
        sendKey(driver.findElement(newPassword), newPwd);
        sendKey(driver.findElement(confPassword), confPwd);
        driver.findElement(submit).click();
    }
}
