package Tests;

import Resources.ExtentReportNG;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listeners extends BaseTest implements ITestListener
{
    ExtentReports extent=ExtentReportNG.getReportObject();
    ExtentTest test;
    ThreadLocal<ExtentTest> extentTest=new ThreadLocal();//this will make test thread safe.

    @Override
    public void onTestStart(ITestResult result) {
        test=extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);//we are pushing our create test object in Thread local
        //now every test will have it's own thread
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.get().log(Status.PASS,"Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result)  {
        extentTest.get().fail(result.getThrowable());//it will check thread ID and work accordingly
        try {
            driver= (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //screenshot, attach to report
        String filePath= null;
        try {
            filePath = getScreenshot(result.getMethod().getMethodName(),driver);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        extentTest.get().addScreenCaptureFromPath(filePath,result.getMethod().getMethodName());//screenshot attached to extent Report
    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context)
    {
        extent.flush();//without this no report will generate
    }
}
