package Manager;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import pagerepository.HomePage;
import templet.TestCaseTemplet;

public class ChangePassword extends TestCaseTemplet {
    WebDriver driver = getDriver();

    @Test
    public void sm1_IncorrectOldPassword() {
        HomePage homePage = new HomePage(driver);
        homePage.changePasswordLink();

    }
}
