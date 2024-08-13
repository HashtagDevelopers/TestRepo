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

import LibraryFiles.BaseClass;
import LibraryFiles.UtilityClass;
import net.bytebuddy.utility.RandomString;

/*Check Datatype and duplicate dept*/
public class Ad1DepartmentFunctionalTC extends BaseClass {
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
	Logger log = LogManager.getLogger(Ad1DepartmentFunctionalTC.class);

	@BeforeClass
	public void openBrowser() throws IOException, InterruptedException {
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

		driver.get(UtilityClass.getPFData("AdminURL"));
		WebElement error = driver.findElement(By.xpath("//body"));
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].setAttribute('style', 'border: 2px solid red; background-color: #0078d4; background-image: none;')",
				error);
		lp1.inpAdLoginPage1Email(UtilityClass.getPFData("AdEmail"));
		lp1.clickAdLoginPage1LoginBtn();
		String otpSent = lp1.getAdLoginPage1ToastMsg(driver);
		Reporter.log(otpSent + "<===>OTP has been sent successfully", true);
		lp2.inpAdLoginPage2Otp(UtilityClass.getPFData("AdPassword"));
		lp2.clickAdLoginPage2SubmitBtn();
		sm.clickAdSideMenuDepartmentBtn();
		log.info("add Prerequisite Department");
		ad.inpAdAddDepartmentFormDept("Hospital");
		ad.clickAdAddDepartmentFormAddBtn(driver);

		ad.inpAdAddDepartmentFormDept("Medical");
		ad.clickAdAddDepartmentFormAddBtn(driver);
		Thread.sleep(5000);
	}
	@BeforeMethod
	public void setUp() {
	    // Clear or reset the state before each test
		soft = new SoftAssert();
	}
	@Test(enabled = true, dataProvider = "DepartmentFunctionalDS", dataProviderClass = DataProviders.A1DSAddDeptAndEmp.class)
	public void AddDepartmentFunctional(String Scenario, String Error, String Department, String toastmsg)
			throws IOException, InterruptedException {
		sm.clickAdSideMenuDepartmentBtn();
		ad.inpAdAddDepartmentFormDept(Department);
		ad.clickAdAddDepartmentFormAddBtn(driver);
		String tm = ad.getAdAddDepartmentFormToastMsg(driver);
		//log.info("Department added");
		Reporter.log(Scenario+"=>"+tm + "<====>" + toastmsg, true);
		// Assert the toast message
	    soft.assertEquals(tm, toastmsg, Scenario+" => Toast message does not match the expected message.");

	    // Verify the department list based on the scenario
	    if (Scenario.equals("Num") || Scenario.equals("SpecialChar") || Scenario.equals("Negative Min")) {
	        soft.assertFalse(ad.getAdAddDepartmentFormDeptList().contains(Department), "Department list should not contain: " + Department);
	    } else if (Scenario.equals("Duplicate")) {
	        // For duplicate scenario, ensure the department was not added again
	        long occurrences = ad.getAdAddDepartmentFormDeptList().stream().filter(dept -> dept.equals(Department)).count();
	        soft.assertEquals(occurrences, 1, "Duplicate department should not be added again: " + Department);
	    } else {
	        soft.assertTrue(ad.getAdAddDepartmentFormDeptList().contains(Department), "Department list should contain: " + Department);
	    }

		Thread.sleep(5000);
		soft.assertAll();
	}

	@AfterMethod
	public void FailedTCSS(ITestResult s1, Method m) throws IOException {

		LocalDateTime currentDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		String cntDate = currentDate.format(formatter);
		// System.out.println(m.getName());
		String rs = m.getName() + cntDate+RandomString.make(2);
		if (s1.getStatus() == ITestResult.FAILURE) {
			UtilityClass.captureSS(driver, rs);
		}
	}

	@AfterClass
	public void closeBrowser() {

		// driver.close();
	}
}
