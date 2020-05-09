package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.paulhammant.ngwebdriver.NgWebDriver;

import dataTable.Xls_Reader;
import testUtil.TestUtil;

public class baseTest {
	
	public static Properties CONFIG  = null;
	public static Properties OR = null;
	public static WebDriver dr = null;
	public static EventFiringWebDriver driver = null;
	public static NgWebDriver ngDriver = null;
	public static Xls_Reader datatable = null;
	public static boolean isLoggedIn = false;
	
	public void initialize() throws IOException
	{
		if(driver == null)
		{
			// config property file
			CONFIG= new Properties();
			FileInputStream fn =new FileInputStream(System.getProperty("user.dir")+"//src//config/config.properties");
			CONFIG.load(fn);
			// OR Properties
			OR= new Properties();
			fn =new FileInputStream(System.getProperty("user.dir")+"//src//config/or.properties");
			OR.load(fn);
			// Initialize the webdriver and EventFiringWebDriver
			if(CONFIG.getProperty("browser").equals("mozilla")){
				 dr = new FirefoxDriver();
			}
			else if(CONFIG.getProperty("browser").equals("chrome")){
				 dr = new ChromeDriver();
			}
			
			 driver = new EventFiringWebDriver(dr);
			 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			 
			 
		}
	}
	
	public static WebElement getObject(String xpathKey) {
		try{
			return driver.findElement(By.xpath(OR.getProperty(xpathKey)));
		}catch(Throwable t){
			// report error
			TestUtil.takeScreenShot(xpathKey);
			Assert.assertTrue(t.getMessage(),false);
			
			return null;
		}
		
	}
	
	
	
	

}
