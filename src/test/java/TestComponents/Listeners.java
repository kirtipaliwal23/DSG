package TestComponents;

import java.sql.Timestamp;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Resources.ExtentReportNG;

public class Listeners extends Base implements ITestListener  {
	ExtentReports extent = ExtentReportNG.getReportObject();
	ExtentTest test;
	ThreadLocal<ExtentTest> extentReport = new ThreadLocal<ExtentTest>();
	
	
	@Override
	public void onTestStart(ITestResult result) {
		 test = extent.createTest(result.getMethod().getMethodName()); //generating unique test id for each test
		 extentReport.set(test); //this step store unique test id for each test
		
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		extentReport.get().log(Status.PASS, "Test Passed"); // By get method we can get that test id and test name mapped along with it.
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		/*
		 * test.log(Status.FAIL, "Test Failed");
		 * 
		 * //To get error message of failure: test.fail(result.getThrowable());
		 * 	
		 */
		test.fail(result.getThrowable());
		String destinationFile = null;
		
		/*
		 * Below step is to get driver object from specific test like this we can get
		 * value of any other variable too We cant use test method to get the driver
		 * because fields are associated with class level but not with method level
		 */
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e1) {
			e1.printStackTrace();
		} 
		
		try {
			destinationFile = getScreenShotPath(result.getMethod().getMethodName(),driver);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		extentReport.get().addScreenCaptureFromPath(destinationFile,result.getMethod().getMethodName());
		
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
	public void onFinish(ITestContext context) {
		
		//This step will wrap all the info and generate the report.
		extent.flush();
	}

}
