package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

/*
 Data is valid - login success - test case is passed - logout
 Data is valid - login failed - test case is failed 
 
 Data is invalid - login success - test case is failed - logout
 Data is invalid - login failed - test case is passed 

 */

public class TC003_LoginDDT extends BaseClass {
	
	// Validation only
	// LoginData is the name of the DataProvider method and DataProviders is the class name in which the dataProvider method is present
	@Test(dataProvider= "LoginData", dataProviderClass=DataProviders.class)
	public void verify_loginDDT(String email, String pwd, String exp) throws InterruptedException // we get these 3 variables from excel sheet (email, password, expected result)
	{
		
		logger.info("************* Starting TC_003_LoginDDT ****************");
		
		try
		{
		// HomePage Page
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		// Login Page
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(null); // we will get the data from Excel file  
		lp.setPassword(null); // we will get the data from Excel file 
		lp.clickLogin();
		
		// MyAccount Page 
		MyAccountPage macc = new MyAccountPage(driver);
		boolean targetPage = macc.isMyAccountPageExists(); // true means login success and false means login failed
		if(exp.equalsIgnoreCase("Valid"))
		{
			if(targetPage == true)
			{
				macc.clickLogout(); // Action always should be before Assertion 
				Assert.assertTrue(true); // test case is passed & Assertion should be always after the Action because after assertion no statement will be executed
			
			} else 
			{
				Assert.assertTrue(false); // test case is failed
			}
			
		}
		if(exp.equalsIgnoreCase("Invalid"))
		{
			if(targetPage == true)
			{
				macc.clickLogout(); 
				Assert.assertTrue(false); // test case is failed
			}
			else 
			{
				Assert.assertTrue(true); // test case is passed
			}
		}
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		Thread.sleep(3000);
		logger.info("************* Finished TC_003_LoginDDT ****************");
		
	}

}
