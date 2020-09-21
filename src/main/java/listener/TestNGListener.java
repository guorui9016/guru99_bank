package listener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import base.TestCaseBase;
import util.Util;

public class TestNGListener implements ITestListener {
    Logger logger = LogManager.getLogger(TestNGListener.class.getName());
    // Initializing ExtentReport
    ExtentReports extentReports = Util.initExtentReporter();
    ExtentTest test;
    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult iTestResult) {
        //Crate a test
        test = extentReports.createTest(iTestResult.getMethod().getMethodName());
        extentTest.set(test);
        logger.info("Start run test case: " + iTestResult.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        extentTest.get().log(Status.PASS, iTestResult.getMethod().getMethodName() + " Passed");
        logger.info(iTestResult.getMethod().getMethodName() + " Passed");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        String methodName = iTestResult.getMethod().getMethodName();
        extentTest.get().fail(iTestResult.getThrowable());
        logger.error(methodName + "Failed");
        //get driver from test result
        TestCaseBase currentClass = (TestCaseBase) iTestResult.getInstance();
        WebDriver driver = currentClass.getDriver();
        String path = Util.screenShot(driver,methodName);
        extentTest.get().addScreenCaptureFromPath(path, methodName);
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        extentTest.get().log(Status.SKIP, iTestResult.getMethod().getMethodName() + "Skipped");
        logger.warn(iTestResult.getMethod().getMethodName() + "Skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        extentReports.flush();
        logger.info("All test cases has been done");
    }
}
