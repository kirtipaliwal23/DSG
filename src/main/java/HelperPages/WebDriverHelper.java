package HelperPages;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverHelper {
	public WebDriver driver;  
	public TestDataHelper testDataHelper;
	String BROWSER;
	String host;
	MutableCapabilities dc;
	
		
	public WebDriver getWebDriver() throws Exception {
		testDataHelper = new TestDataHelper();
		BROWSER = testDataHelper.getValue("browser");

		if (BROWSER.equalsIgnoreCase("chrome")) {
			// With the help of web driver manager you dont need to have driver setup file in your frameworks
			
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();		

		} else if(BROWSER.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver =new FirefoxDriver();
		}
		else if (BROWSER.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver =new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}
      
	  public WebDriver getRemoteWebDriver() throws Exception {
			
			  if(System.getProperty("BROWSER")!=null && System.getProperty("BROWER").equalsIgnoreCase("firefox"))
			  { 
				  dc= new FirefoxOptions(); 
			  } 
			  else { 
				  dc = new ChromeOptions(); }
			  
			  if(System.getProperty("host")!=null)
			  { 
				  host = System.getProperty("host"); 
			  }
			  else { 
				  host = "localhost"; 
				  
			  } 
			  String completeURL = "http://" + host + ":4040";
			  System.out.println(completeURL); 
			  driver = new RemoteWebDriver(new URL(completeURL), dc);
			  driver = ((RemoteWebDriver) driver);

			  return driver;
			
	  }
	

	


	
}
