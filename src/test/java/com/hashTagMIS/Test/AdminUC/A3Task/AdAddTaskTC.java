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

public class AdAddTaskTC extends BaseClass {
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
	Logger log = LogManager.getLogger(AdAddTaskTC.class);
	String dept = "E-Commerce";

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
		lp2.inpAdLoginPage2Otp(UtilityClass.getPFData("AdPassword"));
		lp2.clickAdLoginPage2SubmitBtn();
		asm.clickAdSideMenuTasksBtn();
		td.clickAdTaskDashboardTaskFormBtn();
		Thread.sleep(800);
		at.selAdAddTaskDept(dept);

	}

	@BeforeMethod
	public void setUp() {
		// Clear or reset the state before each test
		soft = new SoftAssert();

	}

	@Test(priority = 1, enabled = true, dataProvider = "AddTaskDS", dataProviderClass = DataProviders.A2DSAddTask.class)
	public void addTaskFromSheet(String number, String task, String taskType) throws IOException, InterruptedException {
		at.inpAdAddTaskFromSheet(driver, task, taskType, i);
		i++;
		
	}

	@Test(priority = 2, enabled = false)
	public void verifyTaskFromSheet() throws IOException, InterruptedException {
		LinkedHashMap<String, String> taskLmpInAAT = at.getAdAddTaskAllTaskAndTaskType(driver);
        
		Thread.sleep(1000);
		at.clickAdAddTaskSubmitBtn();

		soft.assertEquals(at.getAdAddTaskToastMsg(driver), "Task added successfully");
		Thread.sleep(3000);
		td.selAdTaskDashboardDepartmentName(dept);
		Thread.sleep(1000);
		LinkedHashMap<String, String> taskLmpInATD = td.getAdTaskDashboardAllTaskAndTaskType(driver);
		
		Thread.sleep(1000);
		driver.get(UtilityClass.getPFData("URL"));
		lp.inpEmLoginPageEmail(UtilityClass.getPFData("Email"));
		lp.inpEmLoginPagePwd(UtilityClass.getPFData("Password"));
		lp.clickStaffLoginPageLoginBtn();
		log.info("Report Form Opening by Selecting Department and Date...");
		esm.clickEmSideMenuDailyReportBtn();
		erp.selEmReportPageDepartmentName(driver,dept);
		Thread.sleep(2000);
		LinkedHashMap<String, String> taskLmpInEmRF = erp.getEmReportFormTaskAndTAskType(driver);
		
		System.out.println(taskLmpInAAT.toString());
		System.out.println(taskLmpInATD.toString());
		System.out.println(taskLmpInEmRF.toString());
		
		// Check if the sizes are the same
		soft.assertEquals(taskLmpInAAT.size(), taskLmpInATD.size(),
				taskLmpInAAT + "<=>" + taskLmpInATD.size() + "The task maps sizes are not equal!");
		soft.assertEquals(taskLmpInATD.size(), taskLmpInEmRF.size(),
				taskLmpInATD + "<=>" + taskLmpInEmRF.size() + "The task maps sizes are not equal!");
		// Compare entry by entry
		Iterator<Map.Entry<String, String>> it1 = taskLmpInAAT.entrySet().iterator();
		Iterator<Map.Entry<String, String>> it2 = taskLmpInATD.entrySet().iterator();
		Iterator<Map.Entry<String, String>> it3 = taskLmpInEmRF.entrySet().iterator();

		int SrNo = 1;
		while (it1.hasNext() && it2.hasNext()) {
			Map.Entry<String, String> entry1 = it1.next();
			Map.Entry<String, String> entry2 = it2.next();
			Map.Entry<String, String> entry3 = it3.next();
			String m1 = SrNo + "] TF" + entry1.getKey() + "<=>" + entry1.getValue();
			String m2 = SrNo + "] TD" + entry2.getKey() + "<=>" + entry2.getValue();
			String m3 = SrNo + "] RF" + entry3.getKey() + "<=>" + entry3.getValue();
			Reporter.log(m1, true);
			Reporter.log(m2, true);
			Reporter.log(m3, true);
			soft.assertEquals(entry1.getKey(), entry2.getKey(), "Keys at SrNo. " + SrNo + " are not equal!");
			soft.assertEquals(entry1.getValue(), entry2.getValue(), "Values at SrNo. " + SrNo + " are not equal!");
			
			soft.assertEquals(entry2.getKey(), entry3.getKey(), "Keys at SrNo. " + SrNo + " are not equal!");
			soft.assertEquals(entry2.getValue().toLowerCase(), entry3.getValue(), "Values at SrNo. " + SrNo + " are not equal!");
			
			SrNo++;
		}
		soft.assertAll();
	}


	@AfterMethod
	public void FailedTCSS(ITestResult s1) throws IOException, InterruptedException {
		
		String rs = RandomString.make(2);
		if (s1.getStatus() == ITestResult.FAILURE) {
			UtilityClass.captureSS(driver, rs);
		}
	}

	@AfterClass
	public void closeBrowser() throws InterruptedException {
		at.clickAdAddTaskSubmitBtn();
		// driver.close();
	}
}
