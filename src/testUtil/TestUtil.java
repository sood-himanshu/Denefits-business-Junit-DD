package testUtil;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.ScreenshotException;

import com.google.common.io.Files;

import dataTable.Xls_Reader;
import test.baseTest;

public class TestUtil extends baseTest
{
	
	public static void doLogin(String username, String password) throws InterruptedException
	{
			
			if(isLoggedIn)
			{
				//return;
				logout();
			}
			
			Thread.sleep(8000);
			
			//getObject("signin_link").click();
			getObject("username_signin_input").sendKeys(username);
			getObject("password_signing_input").sendKeys(password);
			getObject("signin_button").click();
			Thread.sleep(2000);
			takeScreenShot("Check-Login");
			
			
			Thread.sleep(5000L);
			try
			{
				// Logic to check login successfull or not
				String displayedUserName=driver.findElement(By.xpath(OR.getProperty("start_payment_button"))).getText();
			
				if(displayedUserName.equals("Start a Payment")){
					isLoggedIn=true;
				}else{
				isLoggedIn =false;
				}
			}
			catch(Throwable t)
			{
				isLoggedIn =false;
			}	
		}
	


    public static void logout() throws InterruptedException
    {
    	if(isLoggedIn)
    	{
    		WebElement prof_image = getObject("profile_image_link");
    		Actions act = new Actions(driver);
    		act.moveToElement(prof_image).build().perform();
    		Thread.sleep(2000);
    		driver.findElements(By.xpath("//a[text()='Logout']")).get(1).click();
    		Thread.sleep(2000);
    		getObject("confirm_logout_button").click();
			isLoggedIn=false;
			Thread.sleep(3000);
	    }
    }
    
    
    
    // get the skip condition
 	// true - N
 	// false - Y
 	public static boolean isSkip(String testCase)
 	{ 		
 		for(int rowNum=2 ; rowNum<=datatable.getRowCount("Test Cases");rowNum++)
 		{
 			if(testCase.equals(datatable.getCellData("Test Cases", "TCID", rowNum)))
 			{
 				System.out.println(datatable.getCellData("Test Cases", "TCID", rowNum));
 				if(datatable.getCellData("Test Cases", "Runmode", rowNum).equalsIgnoreCase("Y"))
 					return false;
 				else
 					return true;
 			}
 		}
 		
 		return false;
 	}
 	
 // get the data from xls file
 	public static Object[][] getData(String testName){
 		// return test data;
 		// read test data from xls
 		if(datatable == null){
 			// load the suite 1 sheet
 			datatable = new Xls_Reader(System.getProperty("user.dir")+"//src//config//data.xlsx");
 			
 		}
 		
 		int rows=datatable.getRowCount(testName)-1;
 		if(rows <=0){
 			Object[][] testData =new Object[1][0];
 			return testData;
 			
 		}	
 	    rows = datatable.getRowCount(testName);  // 3
 		int cols = datatable.getColumnCount(testName);
 		System.out.println("Test Name -- "+testName);
 		System.out.println("total rows -- "+ rows);
 		System.out.println("total cols -- "+cols);
 		Object data[][] = new Object[rows-1][cols];
 		
 		for(int rowNum = 2 ; rowNum <= rows ; rowNum++){
 			
 			for(int colNum=0 ; colNum< cols; colNum++){
 				data[rowNum-2][colNum]=datatable.getCellData(testName, colNum, rowNum);
 			}
 		}
 		
 		return data;
 		
 	}
 	
 	// store screenshots
 	public static void takeScreenShot(String fileName) {
 		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
 	    try {
 			Files.copy(scrFile, new File(System.getProperty("user.dir")+"\\screenshots\\"+fileName+".jpg"));
 		} catch (IOException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}	   
 	    
 	}

}
