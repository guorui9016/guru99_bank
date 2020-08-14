package util;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;

/**
 * @author Rui Guo
 *
 * General utility Class
 *
 */

public class Util {
    private static ExtentReports extentReports;
    private static Logger logger = LogManager.getLogger(Util.class.getName());

    /**
     * Take a screen shot and return the file path.
     *
     * @param driver For take a screen shot
     * @param fileName Screen shot file name
     * @return relative path of the screen shot file.
     * @throws IOException
     */
    public static String screenShot(WebDriver driver, String fileName) {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String targetDir = Constants.SCREEN_SHOT_PATH + fileName + ".png";
        try {
            FileUtils.copyFile(screenshot, new File(targetDir));
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e);
        }
        return Constants.SCREEN_SHOT_RETURN_PATH + fileName + ".png";
    }

    /**
     * Initialization Extent Reports
     *
     * @return
     */

    public static ExtentReports initExtentReporter() {
        ExtentSparkReporter reporter = new ExtentSparkReporter(Constants.REPORT_PATH);
        reporter.config().setReportName("Guru99 bank test results report");
        reporter.config().setDocumentTitle("Test Results");
        extentReports = new ExtentReports();
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Tester", "Rui Guo");
        extentReports.setSystemInfo("Host", "Guru99 Bank");
        extentReports.setSystemInfo("OS", "Windows 10");
        extentReports.setSystemInfo("Java Version", "1.8");
        return extentReports;
    }
}
