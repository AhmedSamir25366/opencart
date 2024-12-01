package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class MyAccountPage extends BasePage {
    public MyAccountPage(WebDriver driver)
    {
    	super(driver);
    }
    
    // Locators
    @FindBy(xpath = "//h2[normalize-space()='My Account']")
    WebElement msgHeading;
    
    @FindBy(xpath = "//a[@class='list-group-item'][normalize-space()='Logout']")
    WebElement lnkLogout;
    
    // Action methods ---> it is not validation 
   public boolean isMyAccountPageExists()
   {
	   try 
	   {
	   return (msgHeading.isDisplayed()); // it will return true
	   }
	   catch (Exception e)
	   {
		   return false;
	   }
   }
   
   public void clickLogout()
   {
	   lnkLogout.click();
   }
}
