package pagerepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import templet.PageTemplet;
import util.JsonDataLoader;

/**
 * @author Rui Guo
 *
 * Guru99 bank demo: Home page object class
 *
 */
public class ManagerHomePage extends PageTemplet {
    //WebElements
    @FindBy(css = ".heading3 td")
    private WebElement weManagerID;

    @FindBy(css = ".menusubnav > li:nth-child(2) > a")
    private WebElement weNewCustomerLink;

    @FindBy(css = ".menusubnav > li:nth-child(3) > a")
    private WebElement weEditCustomerLink;

    @FindBy(css = ".menusubnav > li:nth-child(4) > a")
    private WebElement weDelCustomerLink;

    @FindBy(css = ".menusubnav > li:nth-child(5) > a")
    private WebElement weNewAccountLink;

    @FindBy(css = ".menusubnav > li:nth-child(7) > a")
    private WebElement weDelAccountLink;

    @FindBy(css = ".menusubnav > li:nth-child(11) > a")
    private WebElement weChangePasswordLink;

    @FindBy(css = ".menusubnav > li:nth-child(12) > a")
    private WebElement weBalanceEnquiry;

    @FindBy(css = ".menusubnav > li:nth-child(13) > a")
    private WebElement weMiniStatementLink;

    @FindBy(css = ".menusubnav > li:nth-child(14) > a")
    private WebElement weCustomisedStatementLink;

    public ManagerHomePage(WebDriver driver) {
        super(driver);
    }

    public ManagerHomePage(WebDriver driver, String expTitle) {
        super(driver, expTitle);
    }

    /**
     * navChangePasswordLink method to navigate change password page
     *
     * @return
     */
    public ChangePasswordPage navChangePasswordLink() {
        weChangePasswordLink.click();
        return new ChangePasswordPage(driver);
    }

    public NewCustomerPage navNewCustomerLink() {
        weNewCustomerLink.click();
        return new NewCustomerPage(driver);
    }

    public NewAccountPage navNewAccountLink() {
        weNewAccountLink.click();
        return new NewAccountPage(driver);
    }

    public EditCustomerPage navEditCustomerLink() {
        weEditCustomerLink.click();
        return new EditCustomerPage(driver);
    }

    public DeleteCustomerPage navDelCustomerLink() {
        weDelCustomerLink.click();
        return new DeleteCustomerPage(driver);
    }

    public DeleteAccountPage navDelAccountLink() {
        weDelAccountLink.click();
        return new DeleteAccountPage(driver);
    }

    public BalanceEnquiryPage navBalanceEnquiryLink() {
        weBalanceEnquiry.click();
        return new BalanceEnquiryPage(driver);
    }

    public MiniStatementPage navMiniStatementLink() {
        weMiniStatementLink.click();
        return new MiniStatementPage(driver);
    }

    public CustomizedStatementPage navCustomizedStatementLink() {
        weCustomisedStatementLink.click();
        return new CustomizedStatementPage(driver);
    }

    /**
     *  verifyHeader method to verify page title
     */
    public void verifyTitle() {
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.titleContains("Manager"));
        Assert.assertEquals(driver.getTitle(), JsonDataLoader.getExpectContent(this.getClass(), "expectTitle"));
    }

    /**
     *   verifyManagerID method to verify Manager ID.
     *
     * @param userID
     */
    public void verifyManagerID(String userID) {
        Assert.assertEquals(weManagerID.getText(), userID);
    }
}
