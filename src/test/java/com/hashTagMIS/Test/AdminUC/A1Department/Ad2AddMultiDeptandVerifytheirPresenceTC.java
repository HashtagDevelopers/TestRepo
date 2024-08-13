package com.hashTagMIS.Test.AdminUC.A1Department;

import java.io.IOException;
import org.apache.logging.log4j.*;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
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
import com.HashtagMIS.AdminUC.MA4EmployeeReg.AdEmpForm;
import com.HashtagMIS.AdminUC.MA5Tasks.AdTaskDashboard;
import com.HashtagMIS.AdminUC.MA6Report.AdReportDashboard;
import com.HashtagMIS.EmployeeUC.ME1Login.EmLogin;
import com.HashtagMIS.EmployeeUC.ME3Report.EmHistory;

import LibraryFiles.BaseClass;
import LibraryFiles.UtilityClass;
import net.bytebuddy.utility.RandomString;
/*Add Multiple Dept and verify their presence in all dropdown*/
public class Ad2AddMultiDeptandVerifytheirPresenceTC extends BaseClass {
	SoftAssert soft;
	AdLogin1 lp1;
	AdLogin2 lp2;
	AdSideMenu sm;
	AdEmpDashboard sd;
	AdEmpForm ae;
	AdAddDepartmentForm ad;
	AdReportDashboard rd;
	AdTaskDashboard td;
	EmHistory hp;
	String msg;
	EmLogin lp;
	AdEditDepartmentForm de;
	Logger log = LogManager.getLogger(Ad2AddMultiDeptandVerifytheirPresenceTC.class);
	@BeforeClass
	public void openBrowser() throws IOException, InterruptedException {
		initialiseBrowser();
		lp1 = new AdLogin1(driver);
		lp2 = new AdLogin2(driver);
		sm = new AdSideMenu(driver);
		sd = new AdEmpDashboard(driver);
		rd = new AdReportDashboard(driver);
		td = new AdTaskDashboard(driver);
		ae = new AdEmpForm(driver);
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
		// soft.assertEquals(otpSent, "OTP has been sent successfully");
		lp2.inpAdLoginPage2Otp(UtilityClass.getPFData("AdPassword"));
		lp2.clickAdLoginPage2SubmitBtn();

	}

	@BeforeMethod
	public void setUp() {
		// Clear or reset the state before each test
		soft = new SoftAssert();
	}

	@Test(enabled = true, dataProvider = "DepartmentMultiDS",priority = 1, dataProviderClass = DataProviders.A1DSAddDeptAndEmp.class)
	public void testAddDepartmentMulti(String Scenario, String Error, String Department, String toastmsg)
			throws IOException, InterruptedException {

		sm.clickAdSideMenuDepartmentBtn();
		ad.inpAdAddDepartmentFormDept(Department);
		Thread.sleep(5000);
		ad.clickAdAddDepartmentFormAddBtn(driver);
		log.info("Department added");
		String tm = ad.getAdAddDepartmentFormToastMsg(driver);
		Thread.sleep(200);
		Reporter.log(tm + "<====>" + toastmsg, true);	
		soft.assertEquals(tm, toastmsg);
		Thread.sleep(200);
		soft.assertTrue(ad.getAdAddDepartmentFormDeptList().contains(Department));
		log.info("assertion added");		
		soft.assertAll();
	}

	@Test(enabled=false,priority = 2)
	public void testVerifyAllDepInAllDropdown() throws IOException, InterruptedException {
		sm.clickAdSideMenuDepartmentBtn();
		List<String> deptList = ad.getAdAddDepartmentFormDeptList();
		Thread.sleep(4000);
		sm.clickAdSideMenuEmpBtn();
		List<String> deptListInEmpSearch = sd.getAdEmpDashboardDeptLstInDrpDwn();
		Thread.sleep(4000);
		sd.clickAdEmpDashboardAddEmpBtn();
		List<String> deptListInEmpForm = ae.getAdEmpFormDeptLstInDrpDwn();
		Thread.sleep(4000);
		sm.clickAdSideMenuReportsBtn();
		List<String> deptListInReport = rd.getAdReportDashboardDeptLstInDrpDwn();
		Thread.sleep(4000);
		sm.clickAdSideMenuTasksBtn();
		List<String> deptListInTask = td.getAdTaskDashboardDeptLstInDrpDwn();
		Thread.sleep(4000);
		Reporter.log(deptList.toString(),true);
		Reporter.log(deptListInEmpSearch.toString(),true);
		Reporter.log(deptListInEmpForm.toString(),true);
		Reporter.log(deptListInReport.toString(),true);
		Reporter.log(deptListInTask.toString(),true);
		soft.assertEquals(deptList, deptListInEmpSearch);
		soft.assertEquals(deptList, deptListInEmpForm);
		soft.assertEquals(deptList, deptListInReport);
		soft.assertEquals(deptList, deptListInTask);
		soft.assertAll();
	}

	@AfterMethod
	public void FailedTCSS(ITestResult s1, Method m) throws IOException {

		LocalDateTime currentDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");
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
}
