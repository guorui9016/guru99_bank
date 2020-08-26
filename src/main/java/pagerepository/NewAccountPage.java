package pagerepository;

import com.google.gson.JsonObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import templet.PageTemplet;

/**
 * @author Rui Guo
 *
 * Guru99 bank demo: New Account Page object class
 *
 */
public class NewAccountPage extends PageTemplet {
    @FindBy(name = "cusid")
    @CacheLookup
    private WebElement weCustomerId;

    @FindBy(name = "selaccount")
    @CacheLookup
    private WebElement weAccType;

    @FindBy(name = "inideposit")
    @CacheLookup
    private WebElement weInitDeposit;

    @FindBy(name = "button2")
    @CacheLookup
    private WebElement btnSubmit;

    public NewAccountPage(WebDriver driver) {
        super(driver);
    }

    public NewAccountPage(WebDriver driver, String expTitle) {
        super(driver, expTitle);
    }

    /**
     * add new account in the weCustomerId
     * @param customerId
     * @param testData
     */
    public void addNewAccount(String customerId, JsonObject testData) {
        sendKey(weCustomerId, customerId);
        //select the type from the list
        Select selectType = new Select(weAccType);
        selectType.selectByVisibleText(testData.get("type").getAsString());
        sendKey(weInitDeposit, testData.get("deposit").getAsString());
        btnSubmit.click();
    }
}
