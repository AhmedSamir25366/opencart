package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {
	// Validations only
	@Test(groups= {"Sanity", "Master"})
	public void verify_login()
	{
		logger.info("****** Starting TC002_LoginTest ************");
		try
		{
		// Home page 
		HomePage hp = new HomePage(driver); // to be able to access locators and action methods from HomePage page object class
		hp.clickMyAccount();
		hp.clickLogin();
		
		
		// Login page 
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(p.getProperty("email")); // we get email key from config.properties file
		lp.setPassword(p.getProperty("password")); // we get password key from config.properties file
		
		// MyAccount Page
		MyAccountPage macc = new MyAccountPage(driver);
		boolean targetPage = macc.isMyAccountPageExists(); // it will return true or false
		//Assert.assertEquals(targetPage, true, "Login passed");
		Assert.assertTrue(targetPage);
		}
		catch (Exception e)
		{
			Assert.fail(); // if any exception occurs that means that test case is failed
		}
		logger.info("****** Finished TC002_LoginTest ************");
		
		
		
	}

}
