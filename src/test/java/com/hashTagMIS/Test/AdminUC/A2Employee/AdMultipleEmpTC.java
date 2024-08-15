package com.hashTagMIS.Test.AdminUC.A2Employee;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.HashtagMIS.AdminUC.MA1Login.AdLogin1;
import com.HashtagMIS.AdminUC.MA1Login.AdLogin2;
import com.HashtagMIS.AdminUC.MA2SideMenu.AdSideMenu;

import com.HashtagMIS.AdminUC.MA4EmployeeReg.AdEmpDashboard;
import com.HashtagMIS.AdminUC.MA4EmployeeReg.AdEmpForm;
import com.HashtagMIS.AdminUC.MA4EmployeeReg.AdEmpFormEdit;

import com.HashtagMIS.EmployeeUC.ME1Login.EmLogin;
import com.HashtagMIS.EmployeeUC.ME3Report.EmHistory;

import LibraryFiles.BaseClass;
import LibraryFiles.UtilityClass;
import net.bytebuddy.utility.RandomString;
/*Add Multiple Employee*/
public class AdMultipleEmpTC extends BaseClass {
	SoftAssert soft;
	AdLogin1 lp1;
	AdLogin2 lp2;
	AdSideMenu sm;
	AdEmpDashboard sd;
	AdEmpForm ef;
	AdEmpFormEdit ee;
	EmHistory hp;
	String msg;
	EmLogin lp;
	Logger log = LogManager.getLogger(AdMultipleEmpTC.class);

	@BeforeClass
	public void openBrowser() throws IOException, InterruptedException {
		initialiseBrowser();
		lp1 = new AdLogin1(driver);
		lp2 = new AdLogin2(driver);
		sm = new AdSideMenu(driver);
		sd = new AdEmpDashboard(driver);
		ef = new AdEmpForm(driver);
		ee = new AdEmpFormEdit(driver);
		lp = new EmLogin(driver);
		hp = new EmHistory(driver);
		soft = new SoftAssert();
		adminSignIn();
		WebElement error = driver.findElement(By.xpath("//body"));
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].setAttribute('style', 'border: 2px solid red; background-color: #0078d4; background-image: none;')",
				error);
		Thread.sleep(300);
		sm.clickAdSideMenuEmpBtn();
		Thread.sleep(300);
	}

	@BeforeMethod
	public void setUp() {
		// Clear or reset the state before each test
		soft = new SoftAssert();
	}

	@Test(enabled = false)
	public void DeleteAllStaff() throws IOException, InterruptedException {

		int TE = sd.getAdEmpDashboardTotalEmployee();
		for (int i = 1; i <= TE; i++) {
			sd.clickAdEmpDashboardDeleteBtn();
		}
	}

	@Test(enabled = true, dataProvider = "EmpMultipleDS", dataProviderClass = DataProviders.A1DSAddDeptAndEmp.class)
	public void adminLoginTest(String Scenario, String Error, String Name, String Email, String pwd, String acc,String Dept1,
			String Dept2, String Dept3, String Dept4, String Designation, String shiftStart, String shiftEnd,
			String doj,  String toastmsg) throws IOException, InterruptedException {
		
		String al[] = { Name, Dept1, Dept2, Dept3, Dept4, Email, Designation };
		sd.clickAdEmpDashboardAddEmpBtn();
		Thread.sleep(300);
		ef.inpAdEmpFormName(Name);
		Thread.sleep(300);
		ef.inpAdEmpFormEmail(Name.toLowerCase().trim()+"@hashtaginfosystem.com");
		Thread.sleep(300);
		ef.inpAdEmpFormPwd(Name+"@123");
		Thread.sleep(300);	
		ef.selAdEmpFormAccess(acc);
		Thread.sleep(300);
		ef.seleAdEmpFormDept(Dept1, Dept2, Dept3, Dept4);
		Thread.sleep(300);
		ef.inpAddEmpFormDesgni(Designation);
		Thread.sleep(300);
		ef.inpAdEmpFormShiftStart(shiftStart);
		Thread.sleep(300);
		ef.inpAdEmpFormShiftEnd(shiftEnd);
		Thread.sleep(300);
		ef.inpAdEmpFormDtOJ(doj);
		
		Thread.sleep(300);
		ef.clickAdEmpFormCreateBtn();
		Thread.sleep(500);

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
	public void adminSignIn() throws IOException {
		driver.get(UtilityClass.getPFData("AdminURL"));
		lp1.inpAdLoginPage1Email(UtilityClass.getPFData("AdEmail"));
		lp1.clickAdLoginPage1LoginBtn();
		String otpSent = lp1.getAdLoginPage1ToastMsg(driver);
		Reporter.log(otpSent + "<===>OTP has been sent successfully", true);
		soft.assertEquals(otpSent, "OTP has been sent successfully");
		lp2.inpAdLoginPage2Otp(UtilityClass.getPFData("AdPassword"));
		lp2.clickAdLoginPage2SubmitBtn();
	}
}
