package templet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import pagerepository.LoginPage;
import util.Constants;
import java.util.concurrent.TimeUnit;

/**
 * @author Rui Guo
 *
 * Guru99 bank: base test case class
 */
public class TestCaseTemplet {

    private Logger logger = LogManager.getLogger(TestCaseTemplet.class.getName());
    private WebDriver driver;

    public TestCaseTemplet() {
        initializeDriver();
    }

    public WebDriver getDriver() {
        return driver;
    }

    /**
     * Creat WebDriver. The default browser is chrome.
     */
    public void initializeDriver() {

        //default browser is chrome.
        if (Constants.BROWSER.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", Constants.GECKO_DRIVER_PATH);
            DesiredCapabilities dc = new DesiredCapabilities();
            dc.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
            driver = new FirefoxDriver(dc);
            driver.manage().timeouts().implicitlyWait(Constants.TIME_OUT, TimeUnit.SECONDS);
            logger.info("Created firefox driver.");
        } else {
            System.setProperty("webdriver.chrome.driver", Constants.CHROME_DRIVER_PATH);
            WebDriver driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(Constants.TIME_OUT, TimeUnit.SECONDS);
            logger.info("Created chrome driver.");
        }
    }

    /**
     * Login system before start test
     */
    @BeforeClass
    public void login() {
        driver.get(Constants.INDEX_URL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.autoLogin();
        logger.info("Login system");
    }



}
