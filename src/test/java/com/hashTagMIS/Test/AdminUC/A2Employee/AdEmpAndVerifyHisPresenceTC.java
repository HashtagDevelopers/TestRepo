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
import LibraryFiles.UtilsClass;
import net.bytebuddy.utility.RandomString;

/*1]Add one Employee and Check his presence in 1]Employee dashboard 2]Edit Page 3]verify by wether created employee able to login*/
public class AdEmpAndVerifyHisPresenceTC extends BaseClass {
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
	Logger log = LogManager.getLogger(AdEmpAndVerifyHisPresenceTC.class);
	ArrayList<String> expEmpDataInDashboard, expEmpDataInEdit;

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
		driver.get(UtilityClass.getPFData("AdminURL"));
		WebElement error = driver.findElement(By.xpath("//body"));
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].setAttribute('style', 'border: 2px solid red; background-color: #0078d4; background-image: none;')",
				error);
		lp1.inpAdLoginPage1Email(UtilityClass.getPFData("AdEmail"));
		lp1.clickAdLoginPage1LoginBtn();
		String otpSent = lp1.getAdLoginPage1ToastMsg(driver);
		Reporter.log(otpSent + "<===>OTP has been sent successfully", true);
		soft.assertEquals(otpSent, "OTP has been sent successfully");
		Thread.sleep(300);
		lp2.inpAdLoginPage2Otp(UtilityClass.getPFData("AdPassword"));
		Thread.sleep(300);
		lp2.clickAdLoginPage2SubmitBtn();
		Thread.sleep(300);
		sm.clickAdSideMenuEmpBtn();
		Thread.sleep(300);
	}

	@BeforeMethod
	public void setUp() {
		// Clear or reset the state before each test
		soft = new SoftAssert();
		expEmpDataInDashboard = new ArrayList<String>();
		expEmpDataInEdit = new ArrayList<String>();
	}

	@Test(enabled = false)
	public void DeleteAllStaff() throws IOException, InterruptedException {

		int TE = sd.getAdEmpDashboardTotalEmployee();
		for (int i = 1; i <= TE; i++) {
			sd.clickAdEmpDashboardDeleteBtn();
		}
	}

	@Test(enabled = true, dataProvider = "EmpMultipleDS", dataProviderClass = DataProviders.A1DSAddDeptAndEmp.class)
	public void testAddEmpAndVerifyHisPresence(String Scenario, String Error, String Name, String Email, String pwd,
			String acc, String Dept1, String Dept2, String Dept3, String Dept4, String Designation, String shiftStart,
			String shiftEnd, String doj, String toastmsg) throws IOException, InterruptedException {

		expEmpDataInDashboard.addAll(Arrays.asList(Name, Dept1, Dept2, Dept3, Dept4,
				Email.toLowerCase() + "@hashtaginfosystem.com", Designation));
		Collections.sort(expEmpDataInDashboard);
		sd.clickAdEmpDashboardAddEmpBtn();
		Thread.sleep(300);
		ef.inpAdEmpFormName(Name);
		Thread.sleep(300);
		ef.inpAdEmpFormEmail(Name.toLowerCase().trim() + "@hashtaginfosystem.com");
		Thread.sleep(300);
		ef.inpAdEmpFormPwd(Name + "@123");
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

		Thread.sleep(2000);
		String doj1 = ef.getAdEmpFormDate();
		String start = ef.getAdEmpFormShiftStart();
		String end = ef.getAdEmpFormShiftEnd();

		ef.clickAdEmpFormCreateBtn();
		String tm = ef.getAdEmpFormToastMsg(driver);
		Reporter.log(tm + "<====>" + toastmsg, true);
		soft.assertEquals(tm, toastmsg);

		Reporter.log("<== Verify data in Dashboard ==>", true);
		Thread.sleep(1000);
		ArrayList<String> actEmpDataInDashboard = sd.getAdEmpDashboardEmpDataForName(driver, Name);
		UtilsClass.compareTwoList(actEmpDataInDashboard, expEmpDataInDashboard, soft);

		Reporter.log("<== Verify data in Emp edit Page ==>", true);

		expEmpDataInEdit.addAll(Arrays.asList(Name, Email.toLowerCase() + "@hashtaginfosystem.com", pwd, Dept1, Dept2,
				Dept3, Dept4, Designation, start, end, doj1, acc));
		Collections.sort(expEmpDataInEdit);
		sd.clickAdEmpDashboardEditBtnForName(driver, Name);
		List<String> actEmpDataInEdit = ee.getAdEmpFormEditData();
		UtilsClass.compareTwoList(actEmpDataInEdit, expEmpDataInEdit, soft);

		Reporter.log("<== Verify data in Emp Credentials ==>", true);

		driver.get(UtilityClass.getPFData("URL"));
		lp.inpEmLoginPageEmail(Email.toLowerCase() + "@hashtaginfosystem.com");
		lp.inpEmLoginPagePwd(pwd);
		lp.clickStaffLoginPageLoginBtn();
		soft.assertTrue(hp.getEmHistoryPageTitle(driver));

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
