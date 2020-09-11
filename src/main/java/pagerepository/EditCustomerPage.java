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
 * Guru99 bank demo: Edit customer page object class
 */
public class EditCustomerPage extends PageTemplet {
    //WebElement
    @FindBy(name = "cusid")
    private WebElement weCustomerId;

    @FindBy(name = "AccSubmit")
    private WebElement btnSubmit;

    public EditCustomerPage(WebDriver driver) {
        super(driver);
    }

    public EditCustomerPage(WebDriver driver, String expTitle) {
        super(driver, expTitle);
    }

    /**
     * Delete customer by id
     *
     * @param customerId
     */
    public void eidtCustomer(String customerId) {
        sendKey(weCustomerId, customerId);
        btnSubmit.click();
    }

    /**
     * Verify the error message when the customer hold a account
     */
    public void verifyDelErrorMsg() {
        String text = getAlertMsg();
        String expMsg = JsonDataLoader.getExpectContent(this.getClass(), "expectDelErrorMsg");
        Assert.assertEquals(text, expMsg);
    }

    public void verifyTitle() {
        String expTitle = JsonDataLoader.getExpectContent(this.getClass(), Constants.EXPECT_PAGE_TITLE);
        WebDriverWait wait = new WebDriverWait(driver, Constants.TIME_OUT);
        wait.until(ExpectedConditions.titleContains("Edit"));
        Assert.assertEquals(driver.getTitle(), expTitle);
    }

    private String getAlertMsg() {
        Alert alert = driver.switchTo().alert();
        String text = alert.getText();
        alert.accept();
        return text;
    }
}
