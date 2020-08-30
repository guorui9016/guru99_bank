package pagerepository;

import com.google.gson.JsonObject;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import templet.PageTemplet;
import util.Constants;
import util.JsonDataLoader;

/**
 * @author Rui Guo
 *
 * Guru99 bank demo: Customized statement page object class
 *
 */
public class CustomizedStatementPage extends PageTemplet {
    @FindBy(name = "accountno")
    private WebElement weAccountId;

    @FindBy(name = "fdate")
    private WebElement weFromDate;

    @FindBy(name = "tdate")
    private WebElement weToDate;

    @FindBy(name = "amountlowerlimit")
    private WebElement weMinimumValue;

    @FindBy(name = "numtransaction")
    private WebElement weNumOfTransaciton;

    @FindBy(name = "AccSubmit")
    private WebElement btnSubmit;

    public CustomizedStatementPage(WebDriver driver) {
        super(driver);
    }

    public CustomizedStatementPage(WebDriver driver, String expTitle) {
        super(driver, expTitle);
    }

    public void customizedStatement(JsonObject testData) {
        //for fill up all of field.
    }

    public void customizedStatement(String accountId) {
        sendKey(weAccountId, accountId);
        btnSubmit.click();
    }

    /**
     * Verify the alert message when use deleted account id
     */
    public void verifyIncorrectAccNoMessage() {
        String expIncorrectMsg = JsonDataLoader.getExpectContent(this.getClass(), "expectIncorrectAccountMsg");
        Alert alert = driver.switchTo().alert();
        String text = alert.getText();
        alert.accept();
        Assert.assertEquals(text, expIncorrectMsg);
    }

    /**
     * Check the page title.
     */
    public void verifyTitle() {
        WebDriverWait wait = new WebDriverWait(driver, Constants.TIME_OUT);
        wait.until(ExpectedConditions.titleContains("Statement"));
        String expTitle = JsonDataLoader.getExpectContent(this.getClass(), Constants.EXPECT_PAGE_TITLE);
        Assert.assertEquals(driver.getTitle(), expTitle);
    }
}
