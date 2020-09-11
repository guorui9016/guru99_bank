package pagerepository;

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
 * Guru99 bank demo: Mini Statement page object class
 *
 */
public class MiniStatementPage extends PageTemplet {
    @FindBy(name = "accountno")
    private WebElement weAccountId;

    @FindBy(name = "AccSubmit")
    private WebElement btnSubmit;

    public MiniStatementPage(WebDriver driver) {
        super(driver);
    }

    public MiniStatementPage(WebDriver driver, String expTitle) {
        super(driver, expTitle);
    }

    public void miniStatement(String accountID) {
        sendKey(weAccountId, accountID);
        btnSubmit.click();
    }

    public void verifyIncorrectAccountIdMsg() {
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
        wait.until(ExpectedConditions.titleContains("Statement"));
        String expTitle = JsonDataLoader.getExpectContent(this.getClass(), Constants.EXPECT_PAGE_TITLE);
        Assert.assertEquals(driver.getTitle(), expTitle);
    }
}
