package Manager;

import com.google.gson.JsonObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import pagerepository.ChangePasswordPage;
import pagerepository.ManagerHomePage;
import pagerepository.LoginPage;
import base.TestCaseBase;
import util.JsonDataLoader;
import java.util.HashMap;

/**
 * @author Rui Guo
 *
 * Guru99 bank: Change Password then Login
 */
public class TC_ChangePassword extends TestCaseBase {
    private Logger logger = LogManager.getLogger(TC_ChangePassword.class);
    private WebDriver driver = getDriver();
    private String newPassword;
    private String oldPassword;

    /**
     * Enter incorrect Old Password
     *
     * @param jsonObject test data
     */
    @Test(dataProvider = "testData", dataProviderClass = JsonDataLoader.class)
    public void sm1_IncorrectOldPassword(JsonObject jsonObject) {
        //load test data
        String incorrectPassword = jsonObject.get("oldPassword").getAsString();
        String newPassword = jsonObject.get("newPassword").getAsString();
        String confirmPassword = jsonObject.get("confirmPassword").getAsString();
        //Navigate to change password page
        ManagerHomePage managerHomePage = new ManagerHomePage(driver);
        ChangePasswordPage changePasswordPage = managerHomePage.navChangePasswordLink();
        //input incorrect password
        changePasswordPage.changePassword(incorrectPassword, newPassword, confirmPassword);
        logger.info("oldPwd: " + incorrectPassword + " newPwd: " + newPassword + " confPwd: " + confirmPassword);
        //verify the message
        changePasswordPage.verifyAlert(JsonDataLoader.getExpectContent(ChangePasswordPage.class, "expectIncorrectMsg"));
        logger.info("Check alert message");
        //verify the redirect page
        changePasswordPage.verifyHeader();
        logger.info("Check the redirect page");
    }

    @Test(dependsOnMethods = {"sm1_IncorrectOldPassword"}, dataProvider = "testData", dataProviderClass = JsonDataLoader.class)
    public void sm2_CorrectOldPassword(JsonObject jsonObject) {
        ChangePasswordPage changePasswordPage = new ChangePasswordPage(driver);
        //load test data
        oldPassword = jsonObject.get("oldPassword").getAsString();
        newPassword = jsonObject.get("newPassword").getAsString();
        String confirmPassword = jsonObject.get("confirmPassword").getAsString();
        //input password
        changePasswordPage.changePassword(oldPassword, newPassword, confirmPassword);
        //verify the dialog message
        changePasswordPage.verifyAlert(JsonDataLoader.getExpectContent(ChangePasswordPage.class, "expectCorrectMsg"));
        logger.info("Password has been changed");
        //verify the redirect page
        LoginPage loginPage = new LoginPage(driver, JsonDataLoader.getExpectContent(LoginPage.class, "expectTitle"));
        loginPage.checkTitle();
        logger.info("Redirected to home page!");
        updateTestData(jsonObject);
    }

    @Test(dependsOnMethods = {"sm2_CorrectOldPassword"})
    public void sm3_LoginWithNewPassword() {
        LoginPage loginPage = new LoginPage(driver);
        ManagerHomePage managerHomePage = loginPage.autoLogin();
        managerHomePage.verifyTitle();
        logger.info("Login with new password.");
    }

    @AfterClass
    public void teardown() {
        driver.quit();
        logger.info("Quit driver");
    }

    /**
     * Swap new and old password to json file for next test .
     *
     * Notice: Do not need this step if load data from database.
     *          Can not access guru99 bank database, so I used Json file instead of it.
     */
    private void updateTestData(JsonObject object) {
        HashMap<String, String> updateAccount = new HashMap<>();
        HashMap<String, String> updateTestData = new HashMap<>();
        //update account test data after the change password run successful.
        updateAccount.put("password", newPassword);
        updateTestData.put("oldPassword",newPassword);
        updateTestData.put("newPassword",oldPassword);
        updateTestData.put("confirmPassword",oldPassword);
        //write new data to file
        JsonDataLoader.updateDataToFile("account", updateAccount, 0);
        JsonDataLoader.updateDataToFile(object, updateTestData);
        logger.info("Test data has been update");
    }
}
