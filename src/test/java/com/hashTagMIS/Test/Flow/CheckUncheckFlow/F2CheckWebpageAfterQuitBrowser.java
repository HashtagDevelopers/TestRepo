package com.hashTagMIS.Test.Flow.CheckUncheckFlow;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import com.HashtagMIS.EmployeeUC.ME3Report.EmReportPreviewPage;
import com.HashtagMIS.EmployeeUC.ME3Report.EmViewReport;
import com.HashtagMIS.EmployeeUC.SubAdminUC.SAEmTeamReport;
import com.HashtagMIS.EmployeeUC.SubAdminUC.SAEmViewTeamReport;

import DataProviders.C2DSEmReport;
import LibraryFiles.BaseClass;
import LibraryFiles.UtilityClass;


public class F2CheckWebpageAfterQuitBrowser extends BaseClass {
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
	EmReportPreviewPage epp;

	SAEmTeamReport satr;
	SAEmViewTeamReport savr;

	SoftAssert soft;
	Logger log = LogManager.getLogger(F2CheckWebpageAfterQuitBrowser.class);
	PrintWriter pw, pw1;

	String StaffName = "Krunal", subadmin = "Jay", admin = "DEVELOPERS", dd = "25", mm = "07",
			Department = "Care Support", CnDtTime, cntDate;

	StringBuilder sb;

	public String sheetName;

	@BeforeClass
	public void initialiseDataProvider() throws IOException, InterruptedException {
		sheetName = "WebpagesSecurity";
		C2DSEmReport.setSheetName(sheetName, 9, 9);
		
	}

	@BeforeMethod
	public void openBrowser() throws IOException, InterruptedException {
		soft = new SoftAssert();
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
		epp = new EmReportPreviewPage(driver);
		satr = new SAEmTeamReport(driver);
		savr = new SAEmViewTeamReport(driver);
		
	}

	@Test(enabled = true, groups = "Regression", dataProvider = "ReportFlowDS", dataProviderClass = C2DSEmReport.class)
	public void CheckURLAfterAdminCloseBrowserTest(String sce, String usecase, String url)
			throws IOException, InterruptedException {

		if (sce.equals("SubAdmin")) {
			subadminSignIn();
			
		} else {
			adminSignIn();
		}
		Thread.sleep(1500);
		driver.close();
		initialiseBrowser();
		 int retries = 3;
		    for (int i = 0; i < retries; i++) {
		        driver.get(url);
				Thread.sleep(2000); // Wait for the page to load
				
				if (sce.equals("SubAdmin")) {
				    Reporter.log(usecase + " " + driver.getCurrentUrl() + " <==> http://localhost:5173/", true);
				    soft.assertEquals(driver.getCurrentUrl(), "http://localhost:5173/");
				} else if (sce.equals("Admin")) {
				    Reporter.log(usecase + " " + driver.getCurrentUrl() + " <==> http://localhost:5173/admin", true);
				    soft.assertEquals(driver.getCurrentUrl(), "http://localhost:5173/admin");
				}

				// If successful, exit the retry loop
				break;
		    }
		Thread.sleep(500);
		soft.assertAll();
		driver.close();
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

	public void subadminSignIn() throws IOException {
		elp.EmLoginPageSignIn(driver, UtilityClass.getPFData("SAEmail"), UtilityClass.getPFData("SAPassword"));
	}

}