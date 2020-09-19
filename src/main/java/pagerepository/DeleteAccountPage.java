package pagerepository;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import base.PageBase;
import util.Constants;
import util.JsonDataLoader;

import java.util.HashMap;

/**
 * @author Rui Guo
 *
 * Guru99 bank demo: Delete account page object class
 *
 */

public class DeleteAccountPage extends PageBase {
    //WebElement
    @FindBy(name = "accountno")
    private WebElement weAccountId;

    @FindBy(name = "AccSubmit")
    private WebElement btnSubmit;

    @FindBy(css = "body > p > a")
    private WebElement weHomeLink;

    public DeleteAccountPage(WebDriver driver) {
        super(driver);
    }

    public DeleteAccountPage(WebDriver driver, String expTitle) {
        super(driver, expTitle);
    }

    /**
     * Delete customer by id
     * @param accountId
     */
    public void delAccount(String accountId) {
        sendKey(weAccountId, accountId);
        btnSubmit.click();
    }

    /**
     * Verify the notice message after click the submit button.
     */
    public void verifyNoticeMsg() {
        String text = getAlertMsg();
        String expMsg = JsonDataLoader.getExpectContent(this.getClass(), "expectNoticeMsg");
        Assert.assertEquals(text,expMsg);
    }

    /**
     * Verify the error message when the customer hold a account
     */
    public void verifyDelSuccMsg() {
        String text = getAlertMsg();
        String expMsg = JsonDataLoader.getExpectContent(this.getClass(), "expectDelSuccMsg");
        Assert.assertEquals(text, expMsg);
        //update json file when delete successfully
        update2JsonFile();
    }

    private void update2JsonFile() {
        HashMap<String, String> update = new HashMap<>();
        update.put("customerId", "");
        update.put("accountId", "");
        JsonDataLoader.updateDataToFile("sm5_AddNewAccount", update);
    }

    public void verifyTitle() {
        String expTitle = JsonDataLoader.getExpectContent(this.getClass(), Constants.EXPECT_PAGE_TITLE);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(driver.getTitle(), expTitle);
    }

    public void navHomePageLink() {
        weHomeLink.click();
    }

    private String getAlertMsg() {
        Alert alert = driver.switchTo().alert();
        String text = alert.getText();
        alert.accept();
        return text;
    }
}
