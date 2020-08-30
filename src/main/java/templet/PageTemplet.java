package templet;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import pagerepository.ManagerHomePage;
import util.Constants;
import util.JsonDataLoader;

/**
 * @author Rui Guo
 *
 * Guru99 bank: base page object class
 */
public class PageTemplet {
    protected WebDriver driver;
    @FindBy(css = ".menusubnav > li:nth-child(1) > a")
    private WebElement weHomePage;

    public PageTemplet(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, Constants.TIME_OUT), this);
    }

    /**
     * Check the title before the page object been created
     *
     * @param driver
     * @param expTitle
     */
    public PageTemplet(WebDriver driver, String expTitle) {
        this.driver = driver;
        WebDriverWait wait = new WebDriverWait(driver, Constants.TIME_OUT);
        try {
            wait.until(new ExpectedCondition<Boolean>() {
                @NullableDecl
                @Override
                public Boolean apply(@NullableDecl WebDriver driver) {
                    String actTitle = driver.getTitle();
                    return actTitle.equals(expTitle);
                }
            });
        } catch (TimeoutException e) {
            throw new IllegalStateException("Can not load the page with title: '" + expTitle + "'. The currect page title is: " + driver.getTitle());
        }
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, Constants.TIME_OUT), this);
    }

    /**
     * clear the text field before send context.
     *
     * @param webElement
     * @param context
     */
    public void sendKey(WebElement webElement, String context) {
        webElement.clear();
        webElement.sendKeys(context);
    }

    /**
     * Back to home page from current page
     *
     * @return
     */
    public ManagerHomePage back2HomePage() {
        weHomePage.click();
        String title = JsonDataLoader.getExpectContent(ManagerHomePage.class, Constants.EXPECT_PAGE_TITLE);
        ManagerHomePage homePage = new ManagerHomePage(driver, title);
        return homePage;
    }

}
