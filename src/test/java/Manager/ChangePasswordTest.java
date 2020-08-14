package Manager;

import com.google.gson.JsonObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pagerepository.ChangePasswordPage;
import pagerepository.HomePage;
import pagerepository.LoginPage;
import templet.TestCaseTemplet;
import util.JsonTestDataLoader;

import java.util.HashMap;

public class ChangePasswordTest extends TestCaseTemplet {
    Logger logger = LogManager.getLogger(ChangePasswordTest.class);
    private WebDriver driver = getDriver();
    private String newPassword;
    private String oldPassword;

    /**
     * Enter incorrect Old Password
     *
     * @param jsonObject test data
     */

    @Test(dataProvider = "testData", dataProviderClass = JsonTestDataLoader.class)
    public void sm1_IncorrectOldPassword(JsonObject jsonObject) {
        //load test data
        String incorrectPassword = jsonObject.get("oldPassword").getAsString();
        String newPassword = jsonObject.get("newPassword").getAsString();
        String confirmPassword = jsonObject.get("confirmPassword").getAsString();
        //Navigate to change password page
        HomePage homePage = new HomePage(driver);
        ChangePasswordPage changePasswordPage = homePage.changePasswordLink();
        //input incorrect password
        changePasswordPage.changePassword(incorrectPassword, newPassword, confirmPassword);
        logger.info("oldPwd: " + incorrectPassword + " newPwd: " + newPassword + " confPwd: " + confirmPassword);
        //verify the message
        changePasswordPage.checkIncorrectPwdMsg();
        logger.info("Check alert message");
        //verify the redirect page
        changePasswordPage.checkTitle();
        logger.info("Check the redirect page");
    }

    @Test(dependsOnMethods = {"sm1_IncorrectOldPassword"}, dataProvider = "testData", dataProviderClass = JsonTestDataLoader.class)
    public void sm2_CorrectOldPassword(JsonObject jsonObject) {
        ChangePasswordPage changePasswordPage = new ChangePasswordPage(driver);
        //load test data
        oldPassword = jsonObject.get("oldPassword").getAsString();
        newPassword = jsonObject.get("newPassword").getAsString();
        String confirmPassword = jsonObject.get("confirmPassword").getAsString();
        //input password
        changePasswordPage.changePassword(oldPassword, newPassword, confirmPassword);
        //verify the dialog message
        changePasswordPage.checkCorrectPwdMsg();
        logger.info("Password has been changed");
        //waiting until the page has been redirected.
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.titleContains("Home Page"));
        //verify the redirect page
        LoginPage loginPage = new LoginPage(driver);
        loginPage.checkTitle();
        logger.info("Redirected to home page!");
        updateTestData();
    }

    @Test(dependsOnMethods = {"sm2_CorrectOldPassword"})
    public void sm3_LoginWithNewPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.autoLogin();
        HomePage homePage = new HomePage(driver);
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.titleContains("Manager"));
        homePage.checkTitle();
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }

    private void updateTestData() {
        HashMap<String, String> updateAccount = new HashMap<>();
        HashMap<String, String> updateTestData = new HashMap<>();
        //update account test data after the change password run successful.
        updateAccount.put("password", newPassword);
        updateTestData.put("oldPassword",newPassword);
        updateTestData.put("newPassword",oldPassword);
        updateTestData.put("confirmPassword",oldPassword);
        //write new data to file
        JsonTestDataLoader.updataJsonObject("account", updateAccount);
        JsonTestDataLoader.updataJsonObject("sm2_CorrectOldPassword", updateTestData);
        logger.info("Test data has been update");
    }
}
