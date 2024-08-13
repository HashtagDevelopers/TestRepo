package com.hashTagMIS.Test.EmployeeUC.c2Report;

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

/*Add Multiple Report*/
public class SubmitMultipleReportTC extends BaseClass {
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
	String CnDtTime, cntDate, yestDate, tomDate, StaffName = "Employee";
	int cd, cm, cy;
	Logger log = LogManager.getLogger(SubmitMultipleReportTC.class);
	PrintWriter pw, pw1;
	StringBuilder sb;
	ArrayList<String> ExpEmHPDLst;
	ArrayList<String> ExpAdRpDLst;
	ArrayList<String> ExpSATRpDLst;
	ArrayList<String> ExpAdVRUI;
	String Department = "System Admin";
	int d = 31;

	@BeforeClass
	public void openBrowser() throws IOException, InterruptedException {
		initialiseBrowser();
		elp = new EmLogin(driver);
		alp1 = new AdLogin1(driver);
		alp2 = new AdLogin2(driver);
		esm = new EmSideMenu(driver);
		asm = new AdSideMenu(driver);

		ard = new AdReportDashboard(driver);
		avr = new AdViewReport(driver);
		aer = new AdEditReport(driver);
		erp = new EmReportForm(driver);
		ehp = new EmHistory(driver);
		evr = new EmViewReport(driver);

		satr = new SAEmTeamReport(driver);
		savr = new SAEmViewTeamReport(driver);

	}

	@BeforeMethod
	public void openLogIn() throws IOException, InterruptedException {
		soft = new SoftAssert();
		ExpEmHPDLst = new ArrayList<String>();
		ExpAdRpDLst = new ArrayList<String>();
		ExpSATRpDLst = new ArrayList<String>();
		ExpAdVRUI = new ArrayList<String>();
		sb = new StringBuilder();

		elp.inpEmLoginPageSignIn(UtilityClass.getPFData("Email"), UtilityClass.getPFData("Password"));
		log.info("Login success");
	}

	@Test()
	public void FillReport() throws IOException, InterruptedException {
		int d = 30;
		for (int i = 1; i < 30; i++) {
			log.info("Report Form Opening by Selecting Department and Date...");
			esm.clickEmSideMenuDailyReportBtn();
			erp.selEmReportPageDepartmentName(driver, Department);

			erp.inpEmReportPageDateForMultipleForm("" + d + "");
			d = d - 1;

			Thread.sleep(100);
			log.info("Filling Report and collecting entered task....");
			erp.inpEmReportForm5Task();
			log.info("Submitting Report....");
			Thread.sleep(200);
			erp.clickEmReportPageAreYouSureOKBtn();
			Thread.sleep(200);
			driver.navigate().refresh();
		}
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
}
