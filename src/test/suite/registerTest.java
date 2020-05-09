package test.suite;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;

import com.paulhammant.ngwebdriver.NgWebDriver;

import test.baseTest;
import testUtil.TestUtil;

@RunWith(Parameterized.class)
public class registerTest extends baseTest 
{
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
		 
		 getObject("register_first_name_input").sendKeys("Test Doc");
		 getObject("register_last_name_input").sendKeys("9 May");
		 getObject("register_phone_input").click();
		 getObject("register_phone_input").sendKeys(Keys.HOME, "7788441199");
		 getObject("register_email_input").sendKeys("testdoc9may@mailinator.com");
		 getObject("register_password_input").sendKeys("tester1234");
		 
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
}
