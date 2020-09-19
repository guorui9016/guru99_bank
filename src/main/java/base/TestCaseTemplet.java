package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.testng.annotations.BeforeClass;
import pagerepository.LoginPage;
import util.Constants;

import java.util.concurrent.TimeUnit;

/**
 * @author Rui Guo
 * <p>
 * Guru99 bank: base test case class
 */
public class TestCaseTemplet {

    private Logger logger = LogManager.getLogger(TestCaseTemplet.class.getName());
    private WebDriver driver;

    public TestCaseTemplet() {
        this.initializeDriver(false);
    }

    public TestCaseTemplet(boolean headless) {
        this.initializeDriver(headless);
    }

    public WebDriver getDriver() {
        return driver;
    }

    /**
     * Creat WebDriver. The default browser is chrome.
     */
    public void initializeDriver(boolean headless) {
        //default browser is chrome.
        if (Constants.BROWSER.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", Constants.GECKO_DRIVER_PATH);
            //setup fire fox options
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
            //Use head less mode when headless is true.
            firefoxOptions.setHeadless(headless);
            driver = new FirefoxDriver(firefoxOptions);
            driver.manage().timeouts().implicitlyWait(Constants.TIME_OUT, TimeUnit.SECONDS);
            logger.info("Created firefox driver.");
        } else {
            System.setProperty("webdriver.chrome.driver", Constants.CHROME_DRIVER_PATH);
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
            chromeOptions.setHeadless(headless);
            driver = new ChromeDriver(chromeOptions);
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
