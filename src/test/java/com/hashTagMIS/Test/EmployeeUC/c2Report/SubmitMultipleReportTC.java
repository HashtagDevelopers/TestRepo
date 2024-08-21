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
import com.HashtagMIS.EmployeeUC.ME3Report.EmReportPreviewPage;
import com.HashtagMIS.EmployeeUC.ME3Report.EmViewReport;
import com.HashtagMIS.EmployeeUC.SubAdminUC.SAEmTeamReport;
import com.HashtagMIS.EmployeeUC.SubAdminUC.SAEmViewTeamReport;

import LibraryFiles.BaseClass;
import LibraryFiles.UtilityClass;
import LibraryFiles.UtilsClass;

/*Add Multiple Report*/
public class SubmitMultipleReportTC extends BaseClass {
	EmLogin elp;
	EmSideMenu esm;
	EmReportForm erp;
	EmReportPreviewPage erpp;
	SoftAssert soft;
	String CnDtTime, cntDate;
	Logger log = LogManager.getLogger(SubmitMultipleReportTC.class);
	String Department = "Incident";

	@BeforeClass
	public void openBrowser() throws IOException, InterruptedException {
		initialiseBrowser();
		elp = new EmLogin(driver);
		esm = new EmSideMenu(driver);
		erp = new EmReportForm(driver);
		erpp = new EmReportPreviewPage(driver);
	}

	@BeforeMethod
	public void openLogIn() throws IOException, InterruptedException {
		soft = new SoftAssert();
		elp.EmLoginPageSignIn(driver, UtilityClass.getPFData("Email"), UtilityClass.getPFData("Password"));
		log.info("Login success");
	}

	@Test()
	public void FillReportTest() throws IOException, InterruptedException {
		int y = 1154;
		for (int i = 1; i < 1154; i++) {
			// log.info("Report Form Opening by Selecting Department and Date...");
			esm.clickEmSideMenuDailyReportBtn();
			erp.selEmReportPageDepartmentName(driver, Department);

			erp.inpEmReportPageDateForMultipleForm(String.valueOf(y));
			y = y - 1;
			System.out.println(erp.getEmReportPageDate());
			// log.info("Filling Report and collecting entered task....");
			erp.inpEmReportForm5Task();
			// log.info("Submitting Report....");
			erp.clickEmReportPageSendBtn();
			
		 
			erpp.clickEmReportPreviewPageConfirmBtn();
			
			erp.clickEmReportPageAreYouSureOKBtn();
			
			Thread.sleep(500);
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
}
