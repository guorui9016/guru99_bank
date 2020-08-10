import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import templet.TestCaseTemplet;
import util.Constants;
import util.Util;

import java.io.IOException;

public class DemoTestCase extends TestCaseTemplet {
    Logger logger = LogManager.getLogger(TestCaseTemplet.class.getName());
    WebDriver driver = getDriver();

    @Test
    public void passedtest() {
        Assert.assertTrue(true);
    }

    @Test
    public void failedtest() {
        driver.get("https://www.google.com");
        Assert.assertTrue(false);
    }

    public void tearDown() {
        driver.quit();
    }
}
