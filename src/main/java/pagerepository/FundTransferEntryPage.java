package pagerepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import base.PageBase;

/**
 * @author Rui Guo
 *
 * Guru99 bank: Fund Transfer Entry Page object class
 */
public class FundTransferEntryPage extends PageBase {
    @FindBy(name = "payersaccount")
    @CacheLookup
    private WebElement wePayerAccNo;

    @FindBy(name = "payeeaccount")
    @CacheLookup
    private WebElement wePayeeAcctNo;

    @FindBy(name = "ammount")
    @CacheLookup
    private WebElement weAmount;

    @FindBy(name = "desc")
    @CacheLookup
    private WebElement weDesc;

    @FindBy(name = "AccSubmit")
    @CacheLookup
    private WebElement weSubmit;

    public FundTransferEntryPage(WebDriver driver) {
        super(driver);
    }

    public FundTransferEntryPage(WebDriver driver, String expTitle) {
        super(driver, expTitle);
    }

    /**
     *  Transfer money between diff account
     *
     * @param payerAcc
     * @param payeeAcc
     * @param amount
     * @param desc
     */
    public void fundTransfer(String payerAcc, String payeeAcc, String amount, String desc) {
        sendKey(wePayerAccNo, payerAcc);
        sendKey(wePayeeAcctNo, payeeAcc);
        sendKey(weAmount, amount);
        sendKey(weDesc, desc);
        weSubmit.click();
    }
}
