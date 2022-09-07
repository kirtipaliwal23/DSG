package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import HelperPages.CommonUtilities;

public class RegistrationPage extends CommonUtilities{
	
	WebDriver driver;
	
	/*
	 * driver : we are getting from test class has scope within constructor only so
	 * if further we use driver in this class then that driver will not have any
	 * clue about the driver which we are getting from test class hence we are
	 * creating a global variable of driver and then assigning value to this from
	 * the driver variable which we are getting from test class
	 */
	public RegistrationPage(WebDriver driver) {
		super(driver);	
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//input[@name='firstName']")
	private WebElement firstName_Field;
	
	@FindBy(xpath="//input[@name='lastName']")
	private WebElement lastName_Field;
	
	@FindBy(xpath="//input[@name='phone']")
	private WebElement phoneNumber_Field;
	
	@FindBy(id="userName")
	private WebElement email_Field;
	
	@FindBy(xpath="//input[@name='address1']")
	private WebElement address;
	
	@FindBy(xpath="//input[@name='city']")
	private WebElement city;
	
	@FindBy(xpath="//input[@name='state']")
	private WebElement state;
	
	@FindBy(xpath="//input[@name='city']")
	private WebElement postalCode;
	
	@FindBy(xpath="//select[@name='country']")
	private WebElement country;
	
	@FindBy(id="email")
	private WebElement userName_Field;
	
	@FindBy(xpath="//input[@name='password']")
	private WebElement password_Field;
	
	@FindBy(xpath="//input[@name='confirmPassword']")
	private WebElement confirmPassword_Field;
	
	@FindBy(id="register-btn")
    public WebElement submit_Button;
	
	@FindBy(id="header_14")
	private WebElement headerText;
	


	public void fillRegistrationForm() {
		
		firstName_Field.sendKeys("a");
		lastName_Field.sendKeys("b");
		phoneNumber_Field.sendKeys("123456789");
		email_Field.sendKeys("test@xyz.com");
		address.sendKeys("add");
		city.sendKeys("Delhi");
		state.sendKeys("Delhi");
		postalCode.sendKeys("567889");
		selectCountry();
		userName_Field.sendKeys("Test");
		password_Field.sendKeys("Test@123");
		confirmPassword_Field.sendKeys("Test@123");
		submit_Button.submit();
		
	}
	public void selectCountry() {
		Select s = new Select(country);
		s.selectByValue("INDIA");
	}
	public void getHeaderText() {
		System.out.println(headerText.getText());
	}
}
