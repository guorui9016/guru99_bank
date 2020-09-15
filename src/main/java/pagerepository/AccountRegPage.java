package pagerepository;

import com.google.gson.JsonObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import templet.PageTemplet;
import util.JsonDataLoader;

import java.time.LocalDate;
import java.util.HashMap;

/**
 * @author Rui Guo
 *
 * Guru99 bank demo: Account Reg Page object class
 *
 */
public class AccountRegPage extends PageTemplet {
    Logger logger = LogManager.getLogger(AccountRegPage.class);

    @FindBy(css = "#account tr:nth-child(4) > td:nth-child(2)")
    private WebElement weAccountId;

    @FindBy(css = "#account tr:nth-child(5) > td:nth-child(2)")
    private WebElement weCustomerId;

    @FindBy(css = "#account tr:nth-child(6) > td:nth-child(2)")
    private WebElement weCustomerName;

    @FindBy(css = "#account tr:nth-child(7) > td:nth-child(2)")
    private WebElement weEmail;

    @FindBy(css = "#account tr:nth-child(8) > td:nth-child(2)")
    private WebElement weAccountType;

    @FindBy(css = "#account tr:nth-child(9) > td:nth-child(2)")
    private WebElement weDoo; //Date of Opening

    @FindBy(css = "#account tr:nth-child(10) > td:nth-child(2)")
    private WebElement weCurrAmount;

    public AccountRegPage(WebDriver driver) {
        super(driver);
    }

    public AccountRegPage(WebDriver driver, String expTitle) {
        super(driver, expTitle);
    }

    /**
     * Check the account details after add new account
     * @param customerId
     * @param testdata
     */
    public void verifyAccount(String customerId, JsonObject testdata) {
        //get information from the page
        String customerName = testdata.get("customerName").getAsString();
        String email = testdata.get("Email").getAsString();
        String accountType = testdata.get("typeValue").getAsString();
        String amount = testdata.get("deposit").getAsString();
        //get the local system time
        String doo = LocalDate.now().toString();
        //save account info to json file
        updateAccount2JsonFile(customerId, testdata);
        //check all info
        Assert.assertEquals(weCustomerId.getText(), customerId);
        Assert.assertEquals(weCustomerName.getText(), customerName);
        Assert.assertEquals(weEmail.getText(), email);
        Assert.assertEquals(weAccountType.getText(), accountType);
        Assert.assertEquals(weDoo.getText(), doo);
        Assert.assertEquals(weCurrAmount.getText(), amount);
    }

    /**
     * Update account info into Json file
     *
     * @param customerId
     * @param testdata
     */
    public void updateAccount2JsonFile(String customerId, JsonObject testdata) {
        if (!weAccountId.getText().isEmpty()) {
            HashMap<String, String> update = new HashMap<>();
            update.put("customerId", customerId);
            update.put("accountId", weAccountId.getText());
            update.put("dateOfOpen", LocalDate.now().toString());
            JsonDataLoader.updateDataToFile(testdata, update);
            logger.info("Account ID has been write to Json file.");
        }
    }


}


