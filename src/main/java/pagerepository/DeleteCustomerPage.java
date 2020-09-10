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
 * <p>
 * Guru99 bank demo: Delete customer page object class
 */

public class DeleteCustomerPage extends PageTemplet {
    //WebElement
    @FindBy(name = "cusid")
    private WebElement weCustomerId;

    @FindBy(name = "AccSubmit")
    private WebElement btnSubmit;

    public DeleteCustomerPage(WebDriver driver) {
        super(driver);
    }

    public DeleteCustomerPage(WebDriver driver, String expTitle) {
        super(driver, expTitle);
    }

    /**
     * Delete customer by id
     *
     * @param customerId
     */
    public void delCustomer(String customerId) {
        sendKey(weCustomerId, customerId);
        btnSubmit.click();
    }

    /**
     * Verify the notice message after click the submit button.
     */
    public void verifyNoticeMsg() {
        String text = getAlertMsg();
        String expMsg = JsonDataLoader.getExpectContent(this.getClass(), "expectNoticeMsg");
        Assert.assertEquals(text, expMsg);
    }

    /**
     * Verify the error message when the customer hold a account
     */
    public void verifyDelErrorMsg() {
        String text = getAlertMsg();
        String expMsg = JsonDataLoader.getExpectContent(this.getClass(), "expectDelErrorMsg");
        Assert.assertEquals(text, expMsg);
    }

    /**
     * Verify the error message when the customer hold a account
     */
    public void verifyDelSuccessfulMsg() {
        String text = getAlertMsg();
        String expMsg = JsonDataLoader.getExpectContent(this.getClass(), "expectDelSuccessfulMsg");
        Assert.assertEquals(text, expMsg);
    }

    public void verifyTitle() {
        String expTitle = JsonDataLoader.getExpectContent(this.getClass(), Constants.EXPECT_PAGE_TITLE);
        WebDriverWait wait = new WebDriverWait(driver, Constants.TIME_OUT);
        wait.until(ExpectedConditions.titleContains("Delete"));
        Assert.assertEquals(driver.getTitle(), expTitle);
    }

    private String getAlertMsg() {
        Alert alert = driver.switchTo().alert();
        String text = alert.getText();
        alert.accept();
        return text;
    }
}
