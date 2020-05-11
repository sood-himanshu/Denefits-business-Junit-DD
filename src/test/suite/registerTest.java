package test.suite;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.Select;

import com.paulhammant.ngwebdriver.NgWebDriver;

import test.baseTest;
import testUtil.TestUtil;

@RunWith(Parameterized.class)
public class registerTest extends baseTest 
{
	
	public String firstName;
	public String lastName;
	public String phone;
	public String email;
	public String password;
	public String address;
	public String positiveData;

	
	public registerTest(String firstName,String lastName,String phone, String email, String password, String address, String positiveData){
		System.out.println("Enter constructor");
		this.firstName=firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.email = email;
		this.password=password;
		this.address=address;
		this.positiveData=positiveData;
	}
	
	@Before
	public void beforeTest() throws IOException
	{
		initialize();
		// xlsx file
		if(TestUtil.isSkip("registerTest"))
			Assume.assumeTrue(false);	
	}
	
	@Test
	public void registerTest() throws InterruptedException
	{
		System.out.println("Executing Register Test");
		
		driver.get(CONFIG.getProperty("siteURL"));
		ngDriver = new NgWebDriver((JavascriptExecutor) driver);
		 ngDriver.waitForAngularRequestsToFinish();
		 
		 Thread.sleep(8000);
		 
		 getObject("sign_up_link").click();
		 Thread.sleep(5000);
		 
		 getObject("register_first_name_input").sendKeys(firstName);
		 getObject("register_last_name_input").sendKeys(lastName);
		 getObject("register_phone_input").click();
		 getObject("register_phone_input").sendKeys(Keys.HOME, phone);
		 getObject("register_email_input").sendKeys(email);
		 getObject("register_password_input").sendKeys(password);
		 
		 Select s = new Select(getObject("register_industry_drop_down"));
		 Thread.sleep(2000);
		 s.selectByIndex(1);
		 Thread.sleep(3000);
		 s= new Select(getObject("register_sub_industry_drop_down"));
		 s.selectByIndex(1);
		 getObject("register_address_input").sendKeys(address);
		 
		 getObject("register_zip_code_drop_down").click();
		 Thread.sleep(2000);
		 getObject("register_zip_input").sendKeys("12345");
		 Thread.sleep(4000);
		 getObject("register_zip_input").sendKeys(Keys.ENTER);
		 getObject("sign_up_button").click();
		 Thread.sleep(2000);
		 TestUtil.takeScreenShot("Check-Register");
		 Thread.sleep(7000);
		 
		 
		 try
			{
				// Logic to check register successful or not
				String signUpButton=driver.findElement(By.xpath(OR.getProperty("sign_up_button"))).getText();
			
				if(signUpButton.equals("Sign Up")){
					System.out.println("Not able to register");
				}else{
					System.out.println("Able to register");
				}
			}
			catch(Throwable t)
			{
				TestUtil.takeScreenShot("Registered");
				System.out.println("Able to Register");
			}	
	}
	
	@Parameters
	public static Collection<Object[]> dataSupplier(){
		System.out.println("Collecting data");
		// read data fromthkYou xls file and write in into Object array.
		Object[][] data = TestUtil.getData("registerTest");
		// 1st row
		/*data[0][0]="qtpseleniumtest1";
		data[0][1]="qtpseleniumtest1";
		data[0][2]="password1234";
		data[0][3]="qtpseleniumsample@gmail.com";
		data[0][4]="Delhi";
		
		// 2nd row
		data[1][0]="qtpseleniumtest2";
		data[1][1]="qtpseleniumtest2";
		data[1][2]="password1234";
		data[1][3]="qtpseleniumsample@gmail.com";
		data[1][4]="Delhi";*/
		
		return Arrays.asList(data);
	}
	
	/*@After
	public void quit()
	{
		driver.quit();
	}*/
}
