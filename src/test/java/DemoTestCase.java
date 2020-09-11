import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import pagerepository.*;
import templet.TestCaseTemplet;
import util.JsonDataLoader;

public class DemoTestCase extends TestCaseTemplet {

    @Test
    public void sm9_BalanceOfDeletedAccount() {
        //goto balance enquiry page
        WebDriver driver = getDriver();
        ManagerHomePage homePage = new ManagerHomePage(driver);
        //Navigate to edit customer page
        //input deleted customer id

        //verify alert message

        //back to home page
    }

}


