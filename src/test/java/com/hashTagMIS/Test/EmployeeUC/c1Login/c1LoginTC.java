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

import com.HashtagMIS.AdminUC.MA1Login.AdLogin1;
import com.HashtagMIS.AdminUC.MA1Login.AdLogin2;
import com.HashtagMIS.EmployeeUC.ME1Login.EmForgotPassword;
import com.HashtagMIS.EmployeeUC.ME1Login.EmLogin;
import com.HashtagMIS.EmployeeUC.ME2sideMenubar.EmSideMenu;
import com.HashtagMIS.EmployeeUC.ME3Report.EmHistory;

import DataProviders.C1DSLoginPage;
import LibraryFiles.BaseClass;
import LibraryFiles.UtilityClass;
import net.bytebuddy.utility.RandomString;

public class c1LoginTC extends BaseClass {
	SoftAssert soft;
	EmLogin lp;
	EmSideMenu esm;
	String msg;
	EmForgotPassword efp;
	EmHistory ehp;
	AdLogin1 alp1;
	AdLogin2 alp2;
	Logger log = LogManager.getLogger(c1LoginTC.class);

	@BeforeMethod
	public void openBrowser() throws IOException, InterruptedException {
		initialiseBrowser();
		lp = new EmLogin(driver);
		ehp = new EmHistory(driver);
		efp = new EmForgotPassword(driver);
		esm = new EmSideMenu(driver);
		alp1 = new AdLogin1(driver);
		alp2 = new AdLogin2(driver);
		soft = new SoftAssert();
		WebElement error = driver.findElement(By.xpath("//body"));
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].setAttribute('style', 'border: 2px solid red; background-color: #0078d4; background-image: none;')",
				error);
	}

	@Test(enabled = false, dataProvider = "LoginPageDS", dataProviderClass = DataProviders.C1DSLoginPage.class)
	public void employeeLoginTest(String Scenario, String email, String pwd, String toastmsg, String msg1)
			throws IOException, InterruptedException {
		lp.inpEmLoginPageEmail(email);
		lp.inpEmLoginPagePwd(pwd);
		lp.clickEmLoginPageLoginBtn();
		
		if (Scenario.equals("BothTrue")) {
			soft.assertTrue(ehp.getEmHistoryPageTitle(driver), "title not Displayed");
			soft.assertFalse(esm.getEmSideMenuTeamReportBtnDisplayed(), "Team Report is Displayed");
		} 
		else if(Scenario.equals("SubAdmin")) {
			soft.assertTrue(esm.getEmSideMenuTeamReportBtnDisplayed(), "Team Report is not Displayed");
		}
		else {
			String tm = lp.getEmLoginPageToastMsg(driver);
			Reporter.log(Scenario + " ==> " + tm + " = " + toastmsg, true);
			soft.assertEquals(tm, toastmsg, "Toast msg is not matched");
		}
		soft.assertAll();
	}

	@Test(enabled = false,dataProvider = "ForgotPwdPageDS",dataProviderClass = C1DSLoginPage.class )
	public void employeeForgotPwdTest(String Scenario, String email, String toastmsg) throws IOException, InterruptedException {
		lp.clickEmLoginPageForgotPwdLink();
		Thread.sleep(1000);
		efp.inpEmForgotPasswordPageEmail(email);
		efp.clickEmForgotPasswordPageTitleSendPwdBtn();
		soft.assertEquals(efp.getEmForgotPasswordPageToastMsg(driver), toastmsg);
		soft.assertAll();
	}
	@Test(enabled = true,dataProvider = "AdminLoginDS",dataProviderClass = C1DSLoginPage.class )
	public void adminForgotPwdTest(String Scenario, String email, String toastmsg) throws IOException, InterruptedException {
		driver.get(UtilityClass.getPFData("AdminURL"));
		alp1.inpAdLoginPage1Email(email);
		alp1.clickAdLoginPage1LoginBtn();
		Thread.sleep(1000);
		soft.assertEquals(efp.getEmForgotPasswordPageToastMsg(driver), toastmsg);
		soft.assertAll();
	}

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
