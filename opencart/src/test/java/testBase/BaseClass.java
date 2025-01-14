package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

// BaseClass contains all reusable methods 

public class BaseClass {
	public WebDriver driver;
	public Logger logger; // Log4j2
	public Properties p; // variable 
	
    
	@BeforeClass(groups= {"Sanity", "Regression", "Master"})
	@Parameters({"os", "browser"})
	public void setup(String os, String br) throws IOException // operating system + browser name 
	{
		// Loading config.properties file
		FileReader file = new FileReader(".//src//test//resources//config.properties"); // location of config.properties file
		p = new Properties(); // object
		p.load(file); 
		
		
		logger = LogManager.getLogger(this.getClass());
		
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		
		switch(br.toLowerCase())
		{
		case "chrome": driver = new ChromeDriver(); break;
		case "edge": driver = new EdgeDriver(); break;
		case "firefox": driver = new FirefoxDriver(); break;
		default: System.out.println("Invalid browser name"); return;
		}
		
		driver.get(p.getProperty("appURL")); // we obtain appURL key from config.properties file which holds URL of Web Application
		driver.manage().window().maximize();
	}
	@AfterClass
	public void tearDown()
	{
		driver.quit();
		
	}
	
	// method for creating a random string
	public String randomeString()
	{
		String generatedstring = RandomStringUtils.randomAlphabetic(5);
		return generatedstring;
	}
	
	// method for creating a random number for phone number field 
	public String randomeNumber()
	{
		String generatednumber = RandomStringUtils.randomNumeric(10);
		return generatednumber;
	}
	
	// method for creating a random alpha number for email field 
	public String randomeAlphaNumberic()
	{
		String generatedstring = RandomStringUtils.randomAlphabetic(3);
		String generatednumber = RandomStringUtils.randomNumeric(3);
		return (generatedstring + "@" + generatednumber);
	}
	
	// method for taking a screenshot
	public String captureScreen(String tname) 
	{
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time stamp
		
		TakesScreenshot takesScreenShot = (TakesScreenshot) driver;
		File sourceFile = takesScreenShot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath = System.getProperty("user.dir")+ "\\screenshots\\" + tname +"_" + timeStamp;
		File targetFile = new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
		return targetFilePath;
		
	}
}
