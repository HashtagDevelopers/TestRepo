package com.hashTagMIS.Test.AdminUC.A4Checkflow;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.HashtagMIS.AdminUC.MA1Login.AdLogin1;
import com.HashtagMIS.AdminUC.MA1Login.AdLogin2;
import com.HashtagMIS.AdminUC.MA2SideMenu.AdSideMenu;
import com.HashtagMIS.AdminUC.MA6Report.AdEditReport;
import com.HashtagMIS.AdminUC.MA6Report.AdReportDashboard;
import com.HashtagMIS.AdminUC.MA6Report.AdViewReport;
import com.HashtagMIS.EmployeeUC.ME1Login.EmLogin;
import com.HashtagMIS.EmployeeUC.ME2sideMenubar.EmSideMenu;
import com.HashtagMIS.EmployeeUC.ME3Report.EmHistory;
import com.HashtagMIS.EmployeeUC.ME3Report.EmReportForm;
import com.HashtagMIS.EmployeeUC.ME3Report.EmViewReport;
import com.HashtagMIS.EmployeeUC.SubAdminUC.SAEmTeamReport;
import com.HashtagMIS.EmployeeUC.SubAdminUC.SAEmViewTeamReport;

import LibraryFiles.BaseClass;
import LibraryFiles.UtilityClass;
import LibraryFiles.UtilsClass;

public class F3AdCheckTC extends BaseClass {
	AdLogin1 alp1;
	AdLogin2 alp2;
	AdSideMenu asm;
	AdReportDashboard ard;
	AdViewReport avr;
	AdEditReport aer;
	
	EmLogin elp;
	EmSideMenu esm;
	EmReportForm erp;
	EmHistory ehp;
	EmViewReport evr;
	
	SAEmTeamReport satr;
	SAEmViewTeamReport savr;
	
	SoftAssert soft;
	Logger log = LogManager.getLogger(F3AdCheckTC.class);
	PrintWriter pw, pw1;
	
	String CnDtTime, cntDate, StaffName = "Employee";
	StringBuilder sb;
	ArrayList<String> ExpEmHPDLst2;
	ArrayList<String> ExpAdRpDLst2;
	ArrayList<String> ExpSATRpDLst2;
	ArrayList<String> ExpAdVRUI2;

	@BeforeClass
	public void openBrowser() throws IOException, InterruptedException {
		initialiseBrowser();
		
		alp1 = new AdLogin1(driver);
		alp2 = new AdLogin2(driver);	
		asm = new AdSideMenu(driver);
		aer = new AdEditReport(driver);
		ard = new AdReportDashboard(driver);
		avr = new AdViewReport(driver);
		
		elp = new EmLogin(driver);	
		esm = new EmSideMenu(driver);
		erp = new EmReportForm(driver);
		ehp = new EmHistory(driver);
		evr = new EmViewReport(driver);

		satr = new SAEmTeamReport(driver);
		savr = new SAEmViewTeamReport(driver);

	}

	@BeforeMethod
	public void openLogIn() throws IOException, InterruptedException {
		soft = new SoftAssert();
		elp.inpEmLoginPageSignIn(UtilityClass.getPFData("Email"), UtilityClass.getPFData("Password"));
		log.info("Login success");
	}

	@Test(enabled = true, dataProvider = "ReportFlowDS1", dataProviderClass = DataProviders.C2DSEmReport.class)
	public void AdEditReport(String Scenario, String textarea, String Department, String dd, String mm, String toastmsg)
			throws IOException, InterruptedException {

		log.info("Report Form Opening by Selecting Department and Date...");
		esm.clickEmSideMenuDailyReportBtn();
		erp.selEmReportPageDepartmentName(driver,Department);
		erp.inpEmReportPageDate(dd + mm);
		
		erp.inpEmReportForm5Task();

		log.info("Submitting Report....");
		// Function to repeatedly check millisecond value and click if it's 1
		while (true) {
			// Get current millisecond value using JavaScript (adjust if needed)
			if ((int) (System.currentTimeMillis() % 1000) == 1) {
				System.out.println("Button clicked at millisecond 1!");
				long currentTimeMillis1 = System.currentTimeMillis();
				int milliseconds1 = (int) (currentTimeMillis1 % 1000);
				// Extract milliseconds (0-999)
				System.out.println(milliseconds1);
				break;
				// Exit the loop after clicking
			}
			// Adjust sleep time based on your needs (consider shorter intervals)
			Thread.sleep(1); // Sleep for 10 milliseconds
		}
		CnDtTime = getTimeDate();
		erp.clickEmReportPageSubmitBtn();
		erp.clickEmReportPageAreYouSureOKBtn();

//Admin Scenario		
		log.info("Admin Signing in...");
		adminSignIn();

		
		soft.assertAll();
		Reporter.log("<=======================================>", true);
	}

	public String getTimeDate() {
		LocalDateTime currentDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");
		cntDate = currentDate.format(formatter);
		return cntDate.toUpperCase();
	}

	public void adminSignIn() throws IOException {
		driver.get(UtilityClass.getPFData("AdminURL"));
		alp1.inpAdLoginPage1Email(UtilityClass.getPFData("AdEmail"));
		alp1.clickAdLoginPage1LoginBtn();
		String otpSent = alp1.getAdLoginPage1ToastMsg(driver);
		Reporter.log(otpSent + "<===>OTP has been sent successfully", true);
		soft.assertEquals(otpSent, "OTP has been sent successfully");
		alp2.inpAdLoginPage2Otp(UtilityClass.getPFData("AdPassword"));
		alp2.clickAdLoginPage2SubmitBtn();
		log.info("Admin Sign in Success");
	}

	public void employeeSignIn() throws IOException {
		driver.get(UtilityClass.getPFData("URL"));
		elp.inpEmLoginPageSignIn(UtilityClass.getPFData("Email"), UtilityClass.getPFData("Password"));
	}

	public void SubadminSignIn() throws IOException {
		driver.get(UtilityClass.getPFData("URL"));
		elp.inpEmLoginPageSignIn(UtilityClass.getPFData("SAEmail"), UtilityClass.getPFData("SAPassword"));
	}
}