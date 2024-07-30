package com.hashTagMIS.Test.EmployeeUC.c1Login;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.HashtagMIS.EmployeeUC.ME1Login.EmLogin;

import LibraryFiles.BaseClass;
import LibraryFiles.UtilityClass;
import net.bytebuddy.utility.RandomString;

public class c1LoginTC extends BaseClass {
	SoftAssert soft;
	EmLogin lp;
//	SideMenu sm;
	String msg;
	Logger log= LogManager.getLogger(c1LoginTC.class);
	@BeforeMethod
	public void openBrowser() throws IOException, InterruptedException {
		initialiseBrowser();
		lp = new EmLogin(driver);
	//	sm = new SideMenu(driver);
	
		soft = new SoftAssert();
		driver.get(UtilityClass.getPFData("URL"));
		WebElement error = driver.findElement(By.xpath("//body"));
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].setAttribute('style', 'border: 2px solid red; background-color: #0078d4; background-image: none;')",
				error);
	}

	@Test(enabled = true, dataProvider = "LoginPageDS1", dataProviderClass = DataProviders.C1DSLoginPage.class)
	public void adminLogin(String Scenario, String email, String pwd, String toastmsg, String msg1) throws IOException {
		lp.inpEmLoginPageEmail(email);
		lp.inpEmLoginPagePwd(pwd);
		lp.clickStaffLoginPageLoginBtn();
		String tm = lp.getEmLoginPageToastMsg(driver);
		Reporter.log(Scenario + " ==> " +tm  + " = " + toastmsg, true);
		soft.assertEquals(tm, toastmsg, "Toast msg is not matched");
		soft.assertAll();
	}

/*	@Test(enabled = false)
	public void forgotPwdLink() throws IOException, InterruptedException {
		lp.clickAdLoginPageForgotPwdLink();
		Reporter.log(ee.getAdForgotPwdEnterEmailPageTitle(driver) + " = " + "Enter Email Id", true);
		soft.assertEquals(ee.getAdForgotPwdEnterEmailPageTitle(driver), "Enter Email Id",
				"Enter Email Page Not Working");
		soft.assertAll();
	}*/

	@AfterMethod
	public void FailedTCSS(ITestResult s1) throws IOException {
		String rs = RandomString.make(2);
		if (s1.getStatus() == ITestResult.FAILURE) {
			UtilityClass.captureSS(driver, rs);
		}
	}

	@AfterClass
	public void closeBrowser() {
	// driver.close();
	}
}

