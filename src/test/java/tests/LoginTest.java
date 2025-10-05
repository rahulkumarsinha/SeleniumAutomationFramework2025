package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import utils.ExcelUtils;
import utils.ExtentReportManager;
import utils.Log;

public class LoginTest extends BaseTest {
	
	@DataProvider(name = "LoginData")
	public Object[][] getLoginData() throws IOException{
		String filePath = System.getProperty("user.dir")+"/testdata/TestData.xlsx";
		ExcelUtils.loadExcel(filePath, "Sheet1");
		int rowCount = ExcelUtils.getRowCount();
		Object[][] data = new Object[rowCount-1][2];
		
		for(int i =1; i<rowCount; i++) {
			
			data[i-1][0] = ExcelUtils.getCellData(i, 0);//username
			data[i-1][1] = ExcelUtils.getCellData(i, 1);//password
			
		}
		ExcelUtils.closeExcel();
		return data;
	}
	
	
	@DataProvider(name="LoginData2")
	public Object[][] getData(){
		
		return new Object[][] {
			{"user1","pass1"},
			{"user2","pass2"},
			{"user3","pass3"}
			
		};
	}
	
	
//	@Test(dataProvider = "LoginData2")
//	@Test
//	@Parameters({"username", "password"})
	@Test
	public void testValidLogin() {
		Log.info("Starting Login Test...");
		
		test = ExtentReportManager.createTest("Login Test - ");
		
		test.info("Navigating to URL");
		
		LoginPage loginPage = new LoginPage(driver);
		
		test.info("Adding credentials");
		Log.info("Adding Credentials");
		loginPage.enterUsername("admin@yourstore.com");
		loginPage.enterPassword("admin");
//		loginPage.enterUsername(username);
//		loginPage.enterPassword(password);
		test.info("click on Login button");
		loginPage.clickLogin();
		System.out.println("Title of the page is : " + driver.getTitle());
		
		test.info("Verifying page title...");
		Log.info("Verifying page title...");
		Assert.assertEquals(driver.getTitle(), "Dashboard / nopCommerce administration123");
		
		test.pass("Login Successful");
	}
	
//	@Test
//	public void testLoginWithInvalidCredentials() {
//		Log.info("Starting Login Test...");
//		
//		test = ExtentReportManager.createTest("Login Test with Invalid credentials");
//		
//		test.info("Navigating to URL");
//		
//		LoginPage loginPage = new LoginPage(driver);
//		
//		test.info("Adding credentials");
//		Log.info("Adding Credentials");
//		loginPage.enterUsername("admin1234@yourstore.com");
//		loginPage.enterPassword("admin");
//		loginPage.clickLogin();
//		System.out.println("Title of the page is : " + driver.getTitle());
//		
//		test.info("Verifying page title...");
//		Log.info("Verifying page title...");
//		test.info("Verifying page title");
//		test.info("Verifying page title");
//		Assert.assertEquals(driver.getTitle(), "Dashboard / nopCommerce administration");
//		
//		test.pass("Login Successful");
//	}

}
