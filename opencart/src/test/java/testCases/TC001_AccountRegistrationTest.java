package testCases;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {
	public WebDriver driver;
	
	
	// Test methods

	@Test(groups= {"Regression", "Master"})
	public void verify_account_registration()
	{
		logger.info("*********** Starting TC001_AccountRegistrationTest ************");
		try {
		HomePage hp = new HomePage(driver); // Here we pass the driver to the HomePage object class
		logger.info("clicked on MyAccount Link");
		hp.clickMyAccount();
		logger.info("clicked on Register Link");
		hp.clickRegister();
		
		AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
		logger.info("Providing customer details");
		regpage.setFirstName(randomeString().toUpperCase());
		regpage.setLastName(randomeString().toUpperCase());
		regpage.setEmail(randomeString() + "@gmail.com");
		regpage.setTelephone(randomeNumber());
		String password = randomeAlphaNumberic();
		regpage.setPassword(password);
		regpage.setPrivacyPolicy();
		regpage.clickContinue();
		
		logger.info("Validating expected message...");
		String confmsg = regpage.getConfirmationMsg();
		if(confmsg.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		}
		else 
		{
			logger.error("Test failed...");
			logger.debug("Debug logs..");
			Assert.assertTrue(false);
		}
		} catch (Exception e) {
			logger.error("Test failed..");
			logger.debug("Debug logs..");
			Assert.fail();
		}
		
		logger.info("*********** Finished TC001_AccountRegistrationTest ***********");
		
	
		
			
	}
	

	

}
