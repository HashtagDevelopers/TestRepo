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

public class F2AdEditReportTC extends BaseClass {
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
	String CnDtTime, cntDate, yestDate, tomDate, StaffName = "Krunal Employee";
	int cd, cm, cy;
	Logger log = LogManager.getLogger(F2AdEditReportTC.class);
	PrintWriter pw, pw1;
	StringBuilder sb;
	ArrayList<String> ExpEmHPDLst2;
	ArrayList<String> ExpAdRpDLst2;
	ArrayList<String> ExpSATRpDLst2;
	ArrayList<String> ExpAdVRUI2;
	
    
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
		ExpEmHPDLst2 = new ArrayList<String>();
		ExpAdRpDLst2 = new ArrayList<String>();
		ExpSATRpDLst2 = new ArrayList<String>();
		ExpAdVRUI2 = new ArrayList<String>();
		sb = new StringBuilder();

		elp.inpEmLoginPageSignIn(UtilityClass.getPFData("Email"), UtilityClass.getPFData("Password"));
		log.info("Login success");
	}

	@Test(enabled = true, dataProvider = "ReportFlowDS1", dataProviderClass = DataProviders.C2DSEmReport.class)
	public void AdEditReport(String Scenario, String textarea, String Department, String dd, String mm, String toastmsg)
			throws IOException, InterruptedException {

		log.info("Report Form Opening by Selecting Department and Date...");
		esm.clickEmSideMenuDailyReportBtn();
		erp.selEmReportPageDepartmentName(Department);
		erp.inpEmReportPageDate(dd + mm);

		log.info("Filling Report and collecting entered task....");
		erp.inpEmReportFormAllTask( Department);

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
		
//Edit Scenario		
		log.info("Selecting current Report...");
		asm.clickAdSideMenuReportsBtn();
		Thread.sleep(500);
		ard.selAdReportDashboardDepartmentName(Department);
		Thread.sleep(500);
		ard.selAdReportDashboardDepartmentStaff(StaffName);
		Thread.sleep(500);
	
		
		log.info("Moving to admin Edit report....");
		ard.clickAdReportDashboardEditBtnForDateTime(driver,CnDtTime);
		soft.assertTrue(aer.getAdEditReportTitle(driver),"adEdTitle");
		Thread.sleep(500);
		log.info("Editing Report....");
		aer.inpAdEditReportAllTask(driver, Department);
		
		int d=Integer.valueOf(dd)+2;
		aer.inpAdEditReportDate(String.valueOf(d),"05","2024");
		String rpedt = aer.getAdEditReportDate();
		LinkedHashMap<String, String> TVinAdErBeforeEditReport = aer.getAdEditReportTaskAndValue(driver);
		aer.clickAdEditReportUpdateBtn();
		soft.assertEquals(aer.getAdEditReportToastMsg(driver), "Report updated successfully");
		
		
		log.info("collecting Edited Report....");
		Thread.sleep(500);
		soft.assertEquals(aer.getAdEditReportDate(), rpedt,"Admin Edit Date");	
		LinkedHashMap<String, String> TVinAdEditReport2 = aer.getAdEditReportTaskAndValue(driver);
		Thread.sleep(1000);
		aer.clickAdEditReportBackBtn();
		
		
		
		log.info("moving Ad view Report....");	
		ard.clickAdReportDashboardViewBtnForDateTime(driver, CnDtTime);
		Thread.sleep(1000);
		soft.assertEquals(avr.getAdViewReportDate(), rpedt,"Admin VR Date");			
		LinkedHashMap<String, String> TVinAdViewReport2 = avr.getAdViewReportTaskAndValue(driver);
		avr.checkAdViewReportChkBox();
		Thread.sleep(500);
		log.info("comparing Admin Dashboard....");
		ExpAdRpDLst2.addAll(Arrays.asList(StaffName, CnDtTime, rpedt, Department, "Checked"));
		List<String> ActAdDashRD2 = ard.getAdReportDashboardReportInfoList(driver, CnDtTime);
		UtilsClass.compareTwoList(ActAdDashRD2, ExpAdRpDLst2, soft);
		ard.clickAdReportDashboardViewBtnForDateTime(driver, CnDtTime);
		soft.assertTrue(avr.getAdViewReportChkBoxIsSelected());
		
		
		log.info("SubAdmin Signing in...");
		driver.get(UtilityClass.getPFData("URL"));
		elp.inpEmLoginPageSignIn(UtilityClass.getPFData("SAEmail"), UtilityClass.getPFData("SAPassword"));
		esm.clickEmSideMenuTeamReportBtn();
		log.info("SubAdmin TR Dashboard...");
		
		ExpSATRpDLst2.addAll(Arrays.asList(StaffName, CnDtTime, rpedt, Department, "Checked"));
		List<String> ActSATRpDLst2 = satr.getSAEmTeamReportInfoList(driver, CnDtTime);		
		UtilsClass.compareTwoList(ActSATRpDLst2, ExpSATRpDLst2, soft);
		
		log.info("Moving SubAdmin View Team Report...");
		satr.clickSAEmTeamReportCurrentReportViewBtn(driver, CnDtTime);
		Thread.sleep(1000);
		soft.assertEquals(savr.getSAEmViewTeamReportDate(), rpedt,"SAEm VR Date");
		soft.assertEquals(savr.getSAEmViewTeamReportChkUnChk(), "Checked by: Admin","EmVR status is not unchecked in EmVr");
        soft.assertTrue(savr.getSAEmViewTeamReportCheckBoxIsPresent(), "SA Em View TeamReport Checkbox Displayed");
		LinkedHashMap<String, String> TVinSAViewReport2 = savr.getSAEmViewTeamReportTaskAndValue(driver);

		log.info("Moving Employee Sign In...");
		driver.get(UtilityClass.getPFData("URL"));
		elp.inpEmLoginPageSignIn(UtilityClass.getPFData("Email"), UtilityClass.getPFData("Password"));
		
		log.info("Collecting report data in EmHistory Page...");
		soft.assertTrue(ehp.getEmHistoryPageTitle(driver));
		Thread.sleep(1000);
		ExpEmHPDLst2.addAll(Arrays.asList(rpedt, Department, getTimeDate()));
		List<String> ActEmHistoryEleLst2 = ehp.getEmHistoryPageCurrentReportData(driver, CnDtTime);	
		UtilsClass.compareTwoList(ActEmHistoryEleLst2, ExpEmHPDLst2, soft);
		

		log.info("Moving to Employee view Report...");
		ehp.clickEmHistoryPageCurrentReportViewBtn(driver, CnDtTime);
		log.info("Collecting data From Staff View...");
		String svt1 = evr.getEmViewReportPageTitle(driver);
		soft.assertEquals(svt1, "Daily Report: " + rpedt);
		soft.assertEquals(evr.getEmViewReportChkUnChk(), "Checked by: Admin","EmVR status is not unchecked in EmVr");
		LinkedHashMap<String, String> TVinEmViewReport2 = evr.getEmViewReportTaskAndValue(driver);

		log.info("Verify all Report...");
		// Compare entry by entry

		System.out.println(TVinAdErBeforeEditReport.toString());
		System.out.println(TVinAdEditReport2.toString());
		System.out.println(TVinAdViewReport2.toString());
		System.out.println(TVinSAViewReport2.toString());
		System.out.println(TVinEmViewReport2.toString());

		Iterator<Map.Entry<String, String>> ite1 = TVinAdErBeforeEditReport.entrySet().iterator();
		Iterator<Map.Entry<String, String>> ite2 = TVinAdEditReport2.entrySet().iterator();
		Iterator<Map.Entry<String, String>> ite3 = TVinAdViewReport2.entrySet().iterator();
		Iterator<Map.Entry<String, String>> ite4 = TVinSAViewReport2.entrySet().iterator();
		Iterator<Map.Entry<String, String>> ite5 = TVinEmViewReport2.entrySet().iterator();

		int eSrNo1 = 1;
		while (ite1.hasNext() && ite2.hasNext()) {
			Map.Entry<String, String> entry1 = ite1.next();
			Map.Entry<String, String> entry2 = ite2.next();
			Map.Entry<String, String> entry3 = ite3.next();
			Map.Entry<String, String> entry4 = ite4.next();
			Map.Entry<String, String> entry5 = ite5.next();
			String m1 = eSrNo1 + "] AdER  " + entry1.getKey() + "<=>" + entry1.getValue();
			String m2 = eSrNo1 + "] AdER2 " + entry2.getKey() + "<=>" + entry2.getValue();
			String m3 = eSrNo1 + "] AdVR2 " + entry3.getKey() + "<=>" + entry3.getValue();
			String m4 = eSrNo1 + "] SAVR2 " + entry4.getKey() + "<=>" + entry4.getValue();
			String m5 = eSrNo1 + "] EmVR2 " + entry5.getKey() + "<=>" + entry5.getValue();

			Reporter.log(m1, true);
			Reporter.log(m2, true);
			Reporter.log(m3, true);
			Reporter.log(m4, true);
			Reporter.log(m5, true);
			soft.assertEquals(entry2.getKey(), entry1.getKey(),
					"AdEd Report and AdEdit Entered valued Report Keys at SrNo. " + eSrNo1 + " are not equal!");
			soft.assertEquals(entry2.getValue(), entry1.getValue(),
					"AdEd Report and AdEdit Entered valued Report Values at SrNo. " + eSrNo1 + " are not equal!");

			soft.assertEquals(entry3.getKey(), entry1.getKey(),
					"Ad view Report and AdEdit Entered Keys at SrNo. " + eSrNo1 + " are not equal!");
			soft.assertEquals(entry3.getValue(), entry1.getValue(),
					"Ad view Report and AdEdit Entered Values at SrNo. " + eSrNo1 + " are not equal!");

			soft.assertEquals(entry4.getKey(), entry1.getKey(),
					"SA View and AdEdit Entered Keys at SrNo. " + eSrNo1 + " are not equal!");
			soft.assertEquals(entry4.getValue(), entry1.getValue(),
					"SA View and AdEdit Entered Values at SrNo. " + eSrNo1 + " are not equal!");

			soft.assertEquals(entry5.getKey(), entry1.getKey(),
					"Em View Report and AdEdit Entered Keys at SrNo. " + eSrNo1 + " are not equal!");
			soft.assertEquals(entry5.getValue(), entry1.getValue(),
					"Em View Report and AdEdit Entered Values at SrNo. " + eSrNo1 + " are not equal!");
			eSrNo1++;
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
