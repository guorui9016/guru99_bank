package pagerepository;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import templet.PageTemplet;

public class ChangePasswordPage extends PageTemplet {
    //init variable
    private WebDriver driver;
    private String expectHeader = "Change Password";
    private String expectIncorrectMsg = "Old Password is incorrect";
    private String expectCorrectMsg = "Password is Changed";

    private By oldPassword = new By.ByName("oldpassword");
    private By newPassword = new By.ByName("newpassword");
    private By confPassword = new By.ByName("confirmpassword");
    private By submit = new By.ByName("sub");
    private By title = new By.ByClassName("heading3");

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

    public void checkCorrectPwdMsg() {
        checkAlertMessage(expectCorrectMsg);
    }

    public void checkIncorrectPwdMsg() {
        checkAlertMessage(expectIncorrectMsg);
    }

    private void checkAlertMessage(String expectMessage) {
        Alert alert = driver.switchTo().alert();
        String text = alert.getText();
        alert.accept();
        Assert.assertEquals(text, expectMessage);
    }

    public void checkTitle() {
        String text = driver.findElement(title).getText();
        Assert.assertEquals(text, expectHeader);
    }
}
