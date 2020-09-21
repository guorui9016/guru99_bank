package pagerepository;

import base.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class FundTransferDetailsPage extends PageBase {
    @FindBy(css = "table table > tbody > tr:nth-child(1) > td:nth-child(2)")
    private WebElement weFromAccoutNo;

    @FindBy(css = "table table > tbody > tr:nth-child(2) > td:nth-child(2)")
    private WebElement weToAccoutNo;

    @FindBy(css = "table table > tbody > tr:nth-child(3) > td:nth-child(2)")
    private WebElement weAmount;

    @FindBy(css = "table table > tbody > tr:nth-child(4) > td:nth-child(2)")
    private WebElement weDescription;

    public FundTransferDetailsPage(WebDriver driver) {
        super(driver);
    }

    public FundTransferDetailsPage(WebDriver driver, String expTitle) {
        super(driver, expTitle);
    }

    public void verifyDetails(String payer, String payee, String amount, String description) {
        Assert.assertEquals(weFromAccoutNo.getText(), payer);
        Assert.assertEquals(weToAccoutNo.getText(), payee);
        Assert.assertEquals(weAmount.getText(), amount);
        Assert.assertEquals(weDescription.getText(), description);
    }



}
