package pagerepository;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import base.PageBase;
import util.Constants;
import util.JsonDataLoader;

/**
 * @author Rui Guo
 *
 * Guru99 bank demo: Balance enquiry page object class
 *
 */
public class BalanceEnquiryPage extends PageBase {
    @FindBy(name = "accountno")
    private WebElement weAccountId;

    @FindBy(name = "AccSubmit")
    private WebElement btnSubmit;

    @FindBy(css = "body > p > a")
    private WebElement weHomeLink;

    public BalanceEnquiryPage(WebDriver driver) {
        super(driver);
    }

    public BalanceEnquiryPage(WebDriver driver, String expTitle) {
        super(driver, expTitle);
    }

    public void balanceEnquiry(String accountId) {
        sendKey(weAccountId, accountId);
        btnSubmit.click();
    }

    public void verifyIncorrectAccountId() {
        String incorrectAccountMsg = JsonDataLoader.getExpectContent(this.getClass(), "expectIncorrectAccountMsg");
        Alert alert = driver.switchTo().alert();
        String text = alert.getText();
        alert.accept();
        Assert.assertEquals(text,incorrectAccountMsg);
    }

    /**
     * Check the page title.
     */
    public void verifyTitle() {
        WebDriverWait wait = new WebDriverWait(driver, Constants.TIME_OUT);
        wait.until(ExpectedConditions.titleContains("Balance"));
        String expTitle = JsonDataLoader.getExpectContent(this.getClass(), Constants.EXPECT_PAGE_TITLE);
        Assert.assertEquals(driver.getTitle(), expTitle);
    }
}
