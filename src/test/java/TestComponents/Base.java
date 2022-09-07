package TestComponents;

import java.io.File;
import java.sql.Timestamp;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import HelperPages.CommonTestData;
import HelperPages.Log4j;
import HelperPages.TestDataHelper;
import HelperPages.WebDriverHelper;
import org.apache.logging.log4j.Logger;
public class Base {
	public WebDriver driver;
	public TestDataHelper testDataHelper;
	protected ThreadLocal<CommonTestData> ctd = new ThreadLocal<CommonTestData>();

	@Parameters("platform")
	@BeforeMethod(alwaysRun = true)
	public void initializeDriver(String platform) throws Exception {
	  long threadID= Thread.currentThread().getId();
	  Log4j log4j = new Log4j();
	  Logger log = log4j.getLogger();
	  WebDriverHelper webDriverHelper = new WebDriverHelper();
	
	
	   if (platform.equalsIgnoreCase("local")) {
			 driver = webDriverHelper.getWebDriver();
			
		}
		else {
			 driver = webDriverHelper.getRemoteWebDriver();
		}
	   CommonTestData commonTestData = new CommonTestData(driver,threadID,log);
	   ctd.set(commonTestData);
	  
	}

	public void launchApplication() throws Exception {
		testDataHelper = new TestDataHelper();
		driver.get(testDataHelper.getValue("url"));
	}

	@AfterMethod(alwaysRun = true)
	public void closeDriver() {
		driver.close();
	}

	public String getScreenShotPath(String methodName, WebDriver driver) throws Exception {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File ss = ts.getScreenshotAs(OutputType.FILE);
		String destinationFile = System.getProperty("user.dir") + "\\screenshots\\" + methodName +".png";
		FileUtils.copyFile(ss, new File(destinationFile));
		return destinationFile;
	}
	

}
