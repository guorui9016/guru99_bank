package Manager;

import com.google.gson.JsonObject;
import org.openqa.selenium.WebDriver;
import base.TestCaseBase;
import org.testng.annotations.Test;
import pagerepository.FundTransferDetailsPage;
import pagerepository.FundTransferPage;
import pagerepository.ManagerHomePage;
import util.JsonDataLoader;

/**
 * @author Rui Guo
 *
 * Guru99 bank: Add New Customer & Account
 */
public class TC_FundTransfer extends TestCaseBase {
    WebDriver driver = getDriver();

    @Test(dataProvider = "testData", dataProviderClass = JsonDataLoader.class)
    public void sm16_FundTransfer(JsonObject jsonObject) {
        //loading home page
        ManagerHomePage homePage = new ManagerHomePage(driver);
        //Navigate to fund transfer page
        FundTransferPage transferPage = homePage.navFundTransfer();
        //fill up fund transfer form
        String[] accouts = JsonDataLoader.getTestAccouts();
        String amount = jsonObject.get("amount").getAsString();
        String description = jsonObject.get("description").getAsString();
        transferPage.fundTransfer(accouts[0], accouts[1], amount, description);
        //jump to transfer etails page
        FundTransferDetailsPage detailsPage = new FundTransferDetailsPage(driver, "statement");
        //check the details
        detailsPage.verifyDetails(accouts[0], accouts[1], amount, description);
        //back to home page
        detailsPage.back2HomePage();
    }
}
