package test.suite;



import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.JavascriptExecutor;

import com.paulhammant.ngwebdriver.NgWebDriver;

import test.baseTest;
import testUtil.TestUtil;

@RunWith(Parameterized.class)
public class loginTest extends baseTest
{
	public String username;
	public String password;
	public String positiveData;
	
	public loginTest(String username,String password,String positiveData){
		System.out.println("Enter constructor");
		this.username=username;
		this.password=password;
		this.positiveData=positiveData;
	}
	
	@Before
	public void beforeTest() throws IOException{
		initialize();
		// xlsx file
		if(TestUtil.isSkip("loginTest"))
			Assume.assumeTrue(false);
		
	}
	
	
	@Test
	public void loginTest() throws InterruptedException{
		System.out.println("Executing login test");
		// selenium code
		driver.get(CONFIG.getProperty("siteURL"));
		ngDriver = new NgWebDriver((JavascriptExecutor) driver);
		 ngDriver.waitForAngularRequestsToFinish();
		// login
		TestUtil.doLogin(username, password);
		if(!isLoggedIn & positiveData.equals("Y")){
			// report error
			TestUtil.takeScreenShot("loginerror_1");
			System.err.println("Not able to login with right cred - "+username +" -- "+password);
			Assert.assertTrue("Not able to login with right cred - "+username +" -- "+password,false);
		}else if(isLoggedIn & positiveData.equals("N")){
			TestUtil.takeScreenShot("loginerror_2");
			// report error - able to login with wrong cred
			System.err.println("Able to login with wrong cred - "+username +" -- "+password);
			Assert.assertTrue("Able to login with wrong cred - "+username +" -- "+password,false);
		}
		
		Thread.sleep(5000);
		
		TestUtil.logout();
	}
	
	@Parameters
	public static Collection<Object[]> dataSupplier(){
		System.out.println("entered in datasupplier");
		// read data from xls file and write in into Object array.
		Object[][] data = TestUtil.getData("loginTest");
		return Arrays.asList(data);

	}
	
	@After
	public void quit()
	{
		driver.quit();
	}

}

