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
 * <p>
 * Guru99 bank demo: Delete customer page object class
 */

public class DeleteCustomerPage extends PageBase {
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
        verifyAlertMsg("expectNoticeMsg");
    }

    /**
     * Verify the error message when the customer hold a account
     */
    public void verifyDelErrorMsg() {
        verifyAlertMsg("expectDelErrorMsg");
    }

    /**
     * Verify the error message that the customer is not exist
     */
    public void verifyDelNoExistMsg() {
        verifyAlertMsg("expectDelNoExistCustomerMsg");
    }

    /**
     * Verify the success message after delete a customer
     */
    public void verifyDelSuccessfulMsg() {
        verifyAlertMsg("expectDelSuccessfulMsg");
    }

    public void verifyTitle() {
        String expTitle = JsonDataLoader.getExpectContent(this.getClass(), Constants.EXPECT_PAGE_TITLE);
        WebDriverWait wait = new WebDriverWait(driver, Constants.TIME_OUT);
        wait.until(ExpectedConditions.titleContains("Delete"));
        Assert.assertEquals(driver.getTitle(), expTitle);
    }

    private void verifyAlertMsg(String jsonKey){
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        alert.accept();
        String expMsg = JsonDataLoader.getExpectContent(this.getClass(), jsonKey);
        Assert.assertEquals(alertText, expMsg);
    }
}
