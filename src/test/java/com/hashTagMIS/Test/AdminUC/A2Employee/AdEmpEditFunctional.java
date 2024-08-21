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
import com.HashtagMIS.AdminUC.MA4EmployeeReg.AdEmpEditForm;
import com.HashtagMIS.AdminUC.MA4EmployeeReg.AdEmpForm;


import com.HashtagMIS.EmployeeUC.ME1Login.EmLogin;
import com.HashtagMIS.EmployeeUC.ME3Report.EmHistory;

import DataProviders.A1DSAddDeptAndEmp;
import LibraryFiles.BaseClass;
import LibraryFiles.UtilityClass;
import LibraryFiles.UtilsClass;
import net.bytebuddy.utility.RandomString;

/*Employee Functional*/
public class AdEmpEditFunctional extends BaseClass {
	
	SoftAssert soft;
	AdLogin1 lp1;
	AdLogin2 lp2;
	AdSideMenu sm;
	AdEmpDashboard ed;
	AdEmpForm ef;
	AdEmpEditForm ee;
	EmHistory hp;
	String msg;
	EmLogin lp;
	Logger log = LogManager.getLogger(AdEmpEditFunctional.class);
	ArrayList<String> expEmpDataInDashboard, expEmpDataInEdit;
	public String sheetName;
	@BeforeClass
	public void openBrowser() throws IOException, InterruptedException {
		sheetName="StaffEdit";
		A1DSAddDeptAndEmp.setSheetName(sheetName,1,9);
		initialiseBrowser();
		
		lp1 = new AdLogin1(driver);
		lp2 = new AdLogin2(driver);
		sm = new AdSideMenu(driver);
		ed = new AdEmpDashboard(driver);
		ef = new AdEmpForm(driver);
		ee = new AdEmpEditForm(driver);
		lp = new EmLogin(driver);
		hp = new EmHistory(driver);
		soft = new SoftAssert();
		adminSignIn();
		WebElement error = driver.findElement(By.xpath("//body"));
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].setAttribute('style', 'border: 2px solid red; background-color: #0078d4; background-image: none;')",
				error);
		sm.clickAdSideMenuEmpBtn();
		ed.clickAdEmpDashboardAddEmpBtn();
		ef.helperInpAdEmpForm("Ridham", "john@hashtaginfosystem.com", "Admin@127","Staff", "3D Design", "Software Development", "Game Development", "Incident", "QA", "1245AM", "8302PM","0505");
		ef.clickAdEmpFormCreateBtn();
		sm.clickAdSideMenuEmpBtn();
		ed.clickAdEmpDashboardAddEmpBtn();
		ef.helperInpAdEmpForm("Adam", "adam@gmail.com", "Adam@123","Sub-Admin", "3D Design", "Software Development", "Game Development", "Incident", "QA", "1245AM", "8302PM","0505");
		ef.clickAdEmpFormCreateBtn();
		sm.clickAdSideMenuEmpBtn();
		ed.clickAdEmpDashboardAddEmpBtn();
		ef.helperInpAdEmpForm("deleteduser", "deleteduser@gmail.com", "Adam@123", "Sub-Admin", "3D Design",
				"Software Development", "Game Development", "Incident", "QA", "1245AM", "8302PM", "0505");
		ef.clickAdEmpFormCreateBtn();
		Thread.sleep(4000);
		sm.clickAdSideMenuEmpBtn();
		//ed.clickAdEmpDashboardDeleteBtnForName(driver, "deleteduser");
		

	}

	@BeforeMethod
	public void setUp() throws InterruptedException {
		// Clear or reset the state before each test
		soft = new SoftAssert();
	
		Thread.sleep(500);
		sm.clickAdSideMenuEmpBtn();
		Thread.sleep(300);
		ed.clickAdEmpDashboardEditBtnForName(driver,"Adam");
		Thread.sleep(300);
	}

	@Test(enabled = true, dataProvider = "EmpFuctionalDS", dataProviderClass = DataProviders.A1DSAddDeptAndEmp.class)
	public void editEmpFuctionalTest(String Scenario, String Error, String Name, String Email, String pwd, String acc,
			String Dept1, String Dept2, String Dept3, String Dept4, String Designation, String shiftStart,
			String shiftEnd, String doj, String toastmsg) throws IOException, InterruptedException {	
		Thread.sleep(300);
		ee.inpAdEmpEditFormName(Name);
		Thread.sleep(300);
		ee.inpAdEmpEditFormEmail(Email);
		Thread.sleep(300);
		ee.inpAdEmpEditFormPwd(pwd);
		Thread.sleep(300);
		ee.selAdEmpEditFormAccess(acc);
		Thread.sleep(300);
		ee.inpEmpEditFormDept(Dept1, Dept2, Dept3, Dept4);
		Thread.sleep(300);
		ee.inpAdEmpEditFormDesgni(Designation);
		Thread.sleep(300);
		ee.inpAdEmpEditFormShiftStart(shiftStart);
		Thread.sleep(300);
		ee.inpAdEmpEditFormShiftEnd(shiftEnd);
		Thread.sleep(300);
		ee.inpAdEmpEditFormDoJ(doj);
		Thread.sleep(300);

		ee.clickAdEmpEditFormUpdateBtn();
		String tm = ee.getAdEmpEditFormToastMsg(driver);
		Reporter.log(tm + "<====>" + toastmsg, true);
		soft.assertEquals(tm, toastmsg);
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

	public void employeeSignIn() throws IOException {
		lp.EmLoginPageSignIn(driver, UtilityClass.getPFData("Email"), UtilityClass.getPFData("Password"));
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
