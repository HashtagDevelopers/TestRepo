package com.hashTagMIS.Test.EmployeeUC.c1Login;

import java.io.IOException;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.HashtagMIS.EmployeeUC.ME1Login.EmLogin;
import com.HashtagMIS.EmployeeUC.ME2sideMenubar.EmSideMenu;

import LibraryFiles.BaseClass;
import LibraryFiles.UtilityClass;
import net.bytebuddy.utility.RandomString;

public class ForgotPwdTC extends BaseClass {
	SoftAssert soft;
	EmLogin lp;
	
	EmSideMenu sm;
	
//	ForgotPwdEnterEmailPage ee;
	String msg;

	@BeforeMethod
	public void openBrowser() throws IOException, InterruptedException, Throwable {
		initialiseBrowser();
	
		sm = new EmSideMenu(driver);
		lp = new EmLogin(driver);
		
//		ee = new ForgotPwdEnterEmailPage(driver);
		soft = new SoftAssert();
		driver.get("http://localhost:5173/admin");
//		lp.clickAdLoginPageForgotPwdLink();
	}

	@Test(priority = 2, dataProvider = "ForgotPwdEEPageDS", dataProviderClass = DataProviders.C1DSLoginPage.class)
	public void EnterEmail(String Scenario, String EID, String Expmsg) throws IOException, InterruptedException {
//		ee.inpAdForgotPwdEnterEmailPageEmail(EID);
//		ee.clickAdForgotPwdEnterEmailPageSendOTPBtn();
//		String ActText = ee.getAdForgotPwdEnterEmailPageToastMsg(driver);
//		Reporter.log(ActText + "<==>" + Expmsg, true);
//		soft.assertEquals(ActText, Expmsg, "Msg Missmatched");
		soft.assertAll();
	}

	@AfterMethod
	public void CaptureFailedTCSS(ITestResult s1) throws IOException {
		String rs = RandomString.make(2);
		if (s1.getStatus() == ITestResult.FAILURE) {
			UtilityClass.captureSS(driver, rs);
		}
	}

	@AfterClass
	public void logOut() throws IOException {
		// driver.close();
	}
}
/*
 * @Test(priority = 1, enabled = false) public void signInLink() throws
 * IOException, InterruptedException { lp.clickAdLoginPageForgotPwdLink();
 * ee.clickAClForgotPasswordPageSignInLink(); String ActText =
 * lp.getAClLoginPageText(); String ExpText = UtilityClass.getTD(1, 3);
 * Reporter.log(ExpText + "==>" + ActText, true);
 * UtilityClass.drawBorder(driver, lp.rtnAClLoginPageTitle());
 * soft.assertEquals(ActText, ExpText, "Login Page Not Found");
 * soft.assertAll(); Thread.sleep(1000); driver.navigate().back(); }
 */

/*
 * if (Scenario.equals("BlankEmail")) { boolean result =
 * fp.rtnAClForgotPasswordErrorMsg().getText().contains("Invalid email address"
 * ); soft.assertTrue(result, "page dont contain error"); String s =
 * String.valueOf(result); Reporter.log("page contain error => " + s, true); }
 * if (Scenario.equals("VerifiedEmail") || Scenario.equals("UnRegisteredEmail")
 * || Scenario.equals("NonVerifiedMail")) { UtilityClass.drawBorder(driver,
 * ee.getAdForgotPwdEnterEmailPageToastMsg()); Thread.sleep(1000); String
 * ActText = fp.rtnAClForgotPasswordToastMsg().getText();
 * soft.assertEquals(Expmsg, ActText); Reporter.log(Expmsg + "==>" + ActText,
 * true); }
 */