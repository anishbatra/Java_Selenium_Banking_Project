package com.inetbanking.testCases;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.LoginPage;
import com.inetbanking.utilities.XLUtils;

public class TC_LoginDDT_002 extends BaseClass {

	@Test(dataProvider = "logindata") // name should be exactly same as your data provider method
	public void loginDDT(String user, String pwd) throws InterruptedException {
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(user);
		logger.info("user name provided");
		lp.setPassword(pwd);
		logger.info("password provided");
		lp.clickSubmit();
		Thread.sleep(3000);

		if (isAlertPresent() == true) {
			driver.switchTo().alert().accept(); // close alert
			driver.switchTo().defaultContent();
			Assert.assertTrue(false);
			logger.warn("Login failed");
		} else {

			Assert.assertTrue(true);
			logger.warn("Login passed");
			lp.clickLogout();
			Thread.sleep(3000);
			driver.switchTo().alert().accept(); // log out alert
			driver.switchTo().defaultContent(); // focus on same browser

		}

	}

	public boolean isAlertPresent() { // user defined method to check if alert is present or not
		try {
			driver.switchTo().alert();
			return true;

		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	
	
	@DataProvider(name = "logindata") // for data provider we have to give some name
	public static String [][] getData() throws IOException {
		
		String path = System.getProperty("user.dir") + "/src/test/java/com/inetbanking/testData/LoginData.xlsx";
		
		int rownum = XLUtils.getRowCount(path, "Sheet1");
		int columnCount = XLUtils.getCellCount(path, "Sheet1", 1);

		String logindata[][] = new String[rownum][columnCount]; // we are putting excel data into 2-D array

		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < columnCount; j++) {

				logindata[i-1][j] = XLUtils.getCellData(path, "Sheet1", i, j);
			}
		}
		return logindata;
}
	
//	@DataProvider (name ="getData")
//	public Object[][] getLoginData() throws InvalidFormatException{
//		Object data[][] = XLUtils.getTestData("Sheet1");
//		return data;
//	}

	

}
