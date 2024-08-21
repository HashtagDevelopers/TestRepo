package com.hashTagMIS.Test.AdminUC.A1Department;

import java.io.IOException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
import com.HashtagMIS.AdminUC.MA3Department.AdAddDepartmentForm;
import com.HashtagMIS.AdminUC.MA3Department.AdEditDepartmentForm;
import com.HashtagMIS.AdminUC.MA4EmployeeReg.AdEmpDashboard;
import com.HashtagMIS.EmployeeUC.ME1Login.EmLogin;
import com.HashtagMIS.EmployeeUC.ME3Report.EmHistory;

import DataProviders.A1DSAddDeptAndEmp;
import LibraryFiles.BaseClass;
import LibraryFiles.UtilityClass;
import net.bytebuddy.utility.RandomString;

public class Ad3DepartmentEditTC extends BaseClass {
	SoftAssert soft;
	AdLogin1 lp1;
	AdLogin2 lp2;
	AdSideMenu sm;
	AdEmpDashboard sd;
	AdAddDepartmentForm ad;
	EmHistory hp;
	String msg;
	EmLogin lp;
	AdEditDepartmentForm de;
	Logger log = LogManager.getLogger(Ad3DepartmentEditTC.class);
	public String sheetName;
	@BeforeClass
	public void openBrowser() throws IOException, InterruptedException {
		sheetName="DeptEdit";
		A1DSAddDeptAndEmp.setSheetName(sheetName,1,9);
	
		initialiseBrowser();
		lp1 = new AdLogin1(driver);
		lp2 = new AdLogin2(driver);
		sm = new AdSideMenu(driver);
		sd = new AdEmpDashboard(driver);
		ad = new AdAddDepartmentForm(driver);
		lp = new EmLogin(driver);
		hp = new EmHistory(driver);
		de = new AdEditDepartmentForm(driver);

		log.info("for info only");

		adminSignIn();
		WebElement error = driver.findElement(By.xpath("//body"));
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].setAttribute('style', 'border: 2px solid red; background-color: #0078d4; background-image: none;')",
				error);
		
		sm.clickAdSideMenuDepartmentBtn();
		log.info("add Prerequisite Department");
		ad.inpAdAddDepartmentFormDept("Hospitality");
		ad.clickAdAddDepartmentFormAddBtn(driver);

		ad.inpAdAddDepartmentFormDept("Mediclaim");
		ad.clickAdAddDepartmentFormAddBtn(driver);

	}

	@BeforeMethod
	public void setUp() {
		// Clear or reset the state before each test
		soft = new SoftAssert();
	}

	@Test(enabled = true, dataProvider = "DepartmentEditDS", dataProviderClass = DataProviders.A1DSAddDeptAndEmp.class)
	public void editDepartmentFunctionalTest(String Scenario, String Error, String Department)
			throws IOException, InterruptedException {
		ad.clickAdAddDepartmentFormEditBtn();
		de.inpAdEditDepartmentFormDept(Department);
		Thread.sleep(2000);
		de.clickAdEditDepartmentFormOkBtn(driver);
		Thread.sleep(2000);
	
		switch (Scenario) {
		case "AlreadyExistDept":
			Thread.sleep(4000);
			soft.assertTrue(de.rtnEditDepartmentFormOkBtn().isDisplayed());
			de.clickAdEditDepartmentFormOkBtn(driver);
			break;
		case "Empty": // Handle empty department name scenario
			soft.assertTrue(de.rtnEditDepartmentFormOkBtn().isDisplayed());
			de.clickAdEditDepartmentFormCancelBtn(); // Might need adjustment
			break;
		case "Num":
		case "SpecialChar":
			soft.assertFalse(ad.getAdAddDepartmentFormDeptList().contains(Department));
			break;
		default:
			soft.assertTrue(ad.getAdAddDepartmentFormDeptList().contains(Department));
			break;
		}
		soft.assertAll();
	}

	@AfterMethod
	public void FailedTCSS(ITestResult s1, Method m) throws IOException {

		LocalDateTime currentDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		String cntDate = currentDate.format(formatter);
		// System.out.println(m.getName());
		String rs = m.getName() + cntDate + RandomString.make(2);
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
		//soft.assertEquals(otpSent, "OTP has been sent successfully");
		lp2.inpAdLoginPage2Otp(UtilityClass.getPFData("AdPassword"));
		lp2.clickAdLoginPage2SubmitBtn();
	}
}
