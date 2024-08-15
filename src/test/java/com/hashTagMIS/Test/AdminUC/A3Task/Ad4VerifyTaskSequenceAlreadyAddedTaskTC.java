package com.hashTagMIS.Test.AdminUC.A3Task;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

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
import com.HashtagMIS.AdminUC.MA5Tasks.AdAddTask;
import com.HashtagMIS.AdminUC.MA5Tasks.AdTaskDashboard;
import com.HashtagMIS.EmployeeUC.ME1Login.EmLogin;
import com.HashtagMIS.EmployeeUC.ME2sideMenubar.EmSideMenu;
import com.HashtagMIS.EmployeeUC.ME3Report.EmReportForm;

import LibraryFiles.BaseClass;
import LibraryFiles.UtilityClass;
import net.bytebuddy.utility.RandomString;

public class Ad4VerifyTaskSequenceAlreadyAddedTaskTC extends BaseClass {
	SoftAssert soft;
	AdLogin1 lp1;
	AdLogin2 lp2;
	AdSideMenu asm;

	AdTaskDashboard td;
	AdAddTask at;
	EmSideMenu esm;
	EmReportForm erp;
	String msg;
	EmLogin lp;
	int i = 1;
	Logger log = LogManager.getLogger(Ad4VerifyTaskSequenceAlreadyAddedTaskTC.class);
	String dept = "Incident";

	@BeforeClass
	public void openBrowser() throws IOException, InterruptedException {
		initialiseBrowser();
		lp1 = new AdLogin1(driver);
		lp2 = new AdLogin2(driver);
		asm = new AdSideMenu(driver);
		esm = new EmSideMenu(driver);
		erp = new EmReportForm(driver);
		lp = new EmLogin(driver);
		td = new AdTaskDashboard(driver);
		at = new AdAddTask(driver);
		soft = new SoftAssert();
		adminSignIn();
		WebElement error = driver.findElement(By.xpath("//body"));
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].setAttribute('style', 'border: 2px solid red; background-color: #0078d4; background-image: none;')",
				error);
		asm.clickAdSideMenuTasksBtn();
		Thread.sleep(1000);
		td.clickAdTaskDashboardTaskFormBtn();
		Thread.sleep(1000);
		at.selAdAddTaskDept(dept);
		Thread.sleep(1000);
	}

	@BeforeMethod
	public void setUp() {
		// Clear or reset the state before each test
		soft = new SoftAssert();

	}

	@Test(priority = 1, enabled = false, dataProvider = "AddTaskDS", dataProviderClass = DataProviders.A2DSAddTask.class)
	public void addTaskFromSheetTest(String number, String task,String placeholder, String taskType) throws IOException, InterruptedException {
		Thread.sleep(200);
		at.inpAdAddTaskTaskRandomly(driver, dept);
		//i++;
	}

//	@BeforeMethod
//	public void verifyTaskFromSheet(ITestResult result) throws IOException, InterruptedException {
//	    if (result.getMethod().getMethodName().equals("ViewDragDropTask")) {
//	        Thread.sleep(1000);			
//			at.clickAdAddTaskSubmitBtn();
//			soft.assertEquals(at.getAdAddTaskToastMsg(driver), "Task added successfully");
//			Thread.sleep(3000);
//	    }
//	}

	@Test(priority = 2, enabled = false)
	public void viewDragDropTaskTest() throws IOException, InterruptedException {
		
		asm.clickAdSideMenuTasksBtn();
		td.selAdTaskDashboardDepartmentName(dept);
		Thread.sleep(1000);
		LinkedHashMap<String, String> actTaskLmpInAdmnTaskDashboard = td.getAdTaskDashboardAllTaskAndTaskType(driver);
		Thread.sleep(1000);
		employeeSignIn();
		log.info("Report Form Opening by Selecting Department and Date...");
		esm.clickEmSideMenuDailyReportBtn();
		erp.selEmReportPageDepartmentName(driver,dept);
		Thread.sleep(2000);
		LinkedHashMap<String, String> actTaskLmpInEmReportForm = erp.getEmReportFormTaskAndPlaceholder(driver);

		System.out.println(actTaskLmpInAdmnTaskDashboard.toString());
		System.out.println(actTaskLmpInEmReportForm.toString());

		// Check if the sizes are the same

		soft.assertEquals(actTaskLmpInAdmnTaskDashboard.size(), actTaskLmpInEmReportForm.size(),
				actTaskLmpInAdmnTaskDashboard + "<=>" + actTaskLmpInEmReportForm.size() + "The task maps sizes are not equal!");
		// Compare entry by entry

		Iterator<Map.Entry<String, String>> it2 = actTaskLmpInAdmnTaskDashboard.entrySet().iterator();
		Iterator<Map.Entry<String, String>> it3 = actTaskLmpInEmReportForm.entrySet().iterator();

		int SrNo = 1;
		while (it2.hasNext() && it3.hasNext()) {

			Map.Entry<String, String> entry2 = it2.next();
			Map.Entry<String, String> entry3 = it3.next();

			String m2 = SrNo + "] TD" + entry2.getKey() + "<=>" + entry2.getValue();
			String m3 = SrNo + "] RF" + entry3.getKey() + "<=>" + entry3.getValue();

			Reporter.log(m2, true);
			Reporter.log(m3, true);

			soft.assertEquals(entry2.getKey(), entry3.getKey(), "Keys at SrNo. " + SrNo + " are not equal!");
			soft.assertEquals(entry2.getValue().toLowerCase(), entry3.getValue(),
					"Values at SrNo. " + SrNo + " are not equal!");

			SrNo++;
		}
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
	public void closeBrowser() throws InterruptedException {
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
