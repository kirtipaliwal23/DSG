package RegistrationModule;

import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import HelperPages.CommonTestData;
import PageObjects.RegistrationPage;
import TestComponents.Base;

public class RegistrationUI extends Base{

	
	@Test
	public void verifyRegistrationUI() throws Exception {
		CommonTestData commonTestData = ctd.get();
		driver = commonTestData.getDriver();
		RegistrationPage registrationPage = new RegistrationPage(driver);
		Logger log = commonTestData.getLogger();
		log.info("Header displayed");
		launchApplication();
		registrationPage.getHeaderText();
	}
	
	
	
}
