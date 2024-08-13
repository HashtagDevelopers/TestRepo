package com.hashTagMIS.Test.EmployeeUC.c2Report;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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

/*Submit Report=> Check Data in History Dashboard=>verify Report in Employee View=>
 * verify Data in SA Team Report Dashboard=>verify Report in SA View Report=>
 * verify Data in Admin Report Dashboard=>verify Report in Admin View and Edit Report=>
 * Admin Edit Report=>verify Report in Admin  Edit and View Report=>
 * verify Report in SubAdmin Dashboard =>verify Report in SubAdmin View Report=>
 * verify Report in history Dashboard =>verify Report in employee View Report=>*/
public class F1EmSubmitReportTC extends BaseClass {
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
	String CnDtTime, cntDate, yestDate, tomDate, StaffName = "Krunal";
	int cd, cm, cy;
	Logger log = LogManager.getLogger(F1EmSubmitReportTC.class);
	PrintWriter pw, pw1;
	StringBuilder sb;
	ArrayList<String> ExpEmHPDLst;
	ArrayList<String> ExpAdRpDLst;
	ArrayList<String> ExpSATRpDLst;
	ArrayList<String> ExpAdVRUI;

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

	@Test(enabled = true, dataProvider = "ReportFlowDS1", dataProviderClass = DataProviders.C2DSEmReport.class)
	public void FillReport(String Scenario, String textarea, String Department, String dd, String mm, String toastmsg)
			throws IOException, InterruptedException, ParseException {
		String rpdt = dd + "-" + mm + "-" + 2024;

		SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy");
		// Parse the date string into a Date object
		Date date = inputFormat.parse(rpdt);
		// Define the output format (desired format: DD-MMM-YYYY)
		SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MMM-yyyy");
		// Format the Date object into the desired string format
		String formattedDate = outputFormat.format(date);
		ExpAdVRUI.addAll(Arrays.asList("Employee Name: " + StaffName, Department, formattedDate, "Unchecked"));

		log.info("Report Form Opening by Selecting Department and Date...");
		esm.clickEmSideMenuDailyReportBtn();
		erp.selEmReportPageDepartmentName(driver, Department);
		erp.inpEmReportPageDate(dd, mm);

		log.info("Filling Report and collecting entered task....");
		erp.inpEmReportFormAllTask(Department);
		log.info("Collecting task-value from Staff Report...");
		LinkedHashMap<String, String> TVinEmReport = erp.getEmReportFormTaskAndValue(driver);

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
		String tm = erp.getEmReportFormToastMsg(driver);
		Reporter.log(tm + "<==>" + toastmsg, true);
		soft.assertEquals(tm, toastmsg, "Toast dont match");

		log.info("Collecting report data in EmHistory Page...");
		soft.assertTrue(ehp.getEmHistoryPageTitle(driver), "HP Title");
		Thread.sleep(1000);
		ExpEmHPDLst.addAll(Arrays.asList(rpdt, Department, getTimeDate(), "Unchecked"));
		List<String> ActEmHistoryEleLst = ehp.getEmHistoryPageCurrentReportData(driver, CnDtTime);

		log.info("comparing EmHistory dashboard...");
		UtilsClass.compareTwoList(ActEmHistoryEleLst, ExpEmHPDLst, soft);

		log.info("Moving to Employee view Report...");
		ehp.clickEmHistoryPageCurrentReportViewBtn(driver, CnDtTime);
		soft.assertEquals(evr.getEmViewReportPageTitle(driver), "Daily Report: " + rpdt, "EmVR title");
		soft.assertEquals(evr.getEmViewReportChkUnChk(driver), "Unchecked", "EmVR status is not unchecked in EmVr");

		log.info("Collecting task From Employee View...");
		Thread.sleep(2000);
		LinkedHashMap<String, String> TVinEmViewReport = evr.getEmViewReportTaskAndValue(driver);

		Thread.sleep(2000);
		log.info("SubAdmin Signing in...");
		SubadminSignIn();
		ExpSATRpDLst.addAll(Arrays.asList(StaffName, CnDtTime, rpdt, Department, "Unchecked"));
		esm.clickEmSideMenuTeamReportBtn();
		soft.assertTrue(satr.getSAEmTeamReportTitle(driver), "SubAdmin TR title");

		List<String> ActSATRpDLst = satr.getSAEmTeamReportInfoList(driver, CnDtTime);

		log.info("comparing SubAdmin TR Dashboard...");
		UtilsClass.compareTwoList(ActSATRpDLst, ExpSATRpDLst, soft);

		log.info("Moving to SubAdmin View Team Report...");
		satr.clickSAEmTeamReportCurrentReportViewBtn(driver, CnDtTime);
		Thread.sleep(2000);
		soft.assertFalse(savr.getSAEmViewTeamReportCheckBoxIsSelected(driver), "SAVr checkbox");
		List<String> SAEmVTRUpperInfo = savr.getSAEmViewTeamReportUpperInfo();
		LinkedHashMap<String, String> TVinSAViewReport = savr.getSAEmViewTeamReportTaskAndValue(driver);

		log.info("Admin Signing in...");
		adminSignIn();
		log.info("Selecting current Report...");
		asm.clickAdSideMenuReportsBtn();
		Thread.sleep(500);
		ard.selAdReportDashboardDepartmentName(Department);
		Thread.sleep(500);
		ard.selAdReportDashboardDepartmentStaff(StaffName);
		Thread.sleep(500);

		log.info("collecting current Report Data in Admin dashboard....");
		ExpAdRpDLst.addAll(Arrays.asList(StaffName, CnDtTime, rpdt, Department, "Unchecked"));
		Thread.sleep(500);
		List<String> ActAdRpDInfoLst = ard.getAdReportDashboardReportInfoList(driver, CnDtTime);
		Thread.sleep(500);
		UtilsClass.compareTwoList(ActAdRpDInfoLst, ExpAdRpDLst, soft);

		log.info("Moving to admin view Report....");
		ard.clickAdReportDashboardViewBtnForDateTime(driver, CnDtTime);
		soft.assertTrue(avr.getAdViewReportTitle(driver), "AVR title");

		log.info("collecting data in admin view report....");
		Thread.sleep(1000);
		soft.assertFalse(avr.getAdViewReportChkBoxIsSelected(driver), "AdViewReport checkbox");
		List<String> AdVRUpperInfo = avr.getAdViewReportUpperInfo();
		LinkedHashMap<String, String> TVinAdViewReport = avr.getAdViewReportTaskAndValue(driver);
		avr.getAdViewReportBackBtn();
		Thread.sleep(500);

		log.info("Moving to admin Edit report....");
		ard.clickAdReportDashboardEditBtnForDateTime(driver, CnDtTime);
		soft.assertTrue(aer.getAdEditReportTitle(driver), "AdEpTitle");

		log.info("collecting data in admin Edit report....");
		List<String> AdERUpperInfo = aer.getAdEditReportUpperInfo();
		LinkedHashMap<String, String> TVinAdEditReport = aer.getAdEditReportTaskAndValue(driver);

		log.info("comparing upper form info");
		Reporter.log(ExpAdVRUI.toString(), true);
		Reporter.log(SAEmVTRUpperInfo.toString(), true);
		Reporter.log(AdVRUpperInfo.toString().toString(), true);
		Reporter.log(AdERUpperInfo.toString(), true);

		log.warn("Comparing header data in admin view and admin Edit report....");
		for (int i = 0; i < ExpAdVRUI.size(); i++) {
			String saEmViewReportHeader = SAEmVTRUpperInfo.get(i);
			String adViewReportHeader = AdVRUpperInfo.get(i);
			String adEditReportHeader = AdERUpperInfo.get(i);
			Reporter.log(ExpAdVRUI.get(i) + "<==>" + adViewReportHeader + "<==>" + adEditReportHeader, true);
			soft.assertEquals(ExpAdVRUI.get(i), saEmViewReportHeader,
					"HeaderData element at index" + i + "Dont Matched");
			soft.assertEquals(adViewReportHeader, saEmViewReportHeader,
					"HeaderData element at index" + i + "Dont Matched");
			soft.assertEquals(adEditReportHeader, saEmViewReportHeader,
					"HeaderData element at index" + i + "Dont Matched");
		}
		soft.assertEquals(SAEmVTRUpperInfo, ExpAdVRUI, "Header element at admin view are not as expected");
		soft.assertEquals(AdVRUpperInfo, ExpAdVRUI, "Header element at admin view are not as expected");
		soft.assertEquals(AdERUpperInfo, ExpAdVRUI, "Header element at admin view and edit Dont Matched");
		log.warn("Compared header data in admin view and admin Edit report");

		log.info("Verify all Report...");
		// Compare entry by entry
		System.out.println(TVinEmReport.toString());
		System.out.println(TVinEmViewReport.toString());
		System.out.println(TVinSAViewReport.toString());
		System.out.println(TVinAdViewReport.toString());
		System.out.println(TVinAdEditReport.toString());

		Iterator<Map.Entry<String, String>> it1 = TVinEmReport.entrySet().iterator();
		Iterator<Map.Entry<String, String>> it2 = TVinEmViewReport.entrySet().iterator();
		Iterator<Map.Entry<String, String>> it3 = TVinSAViewReport.entrySet().iterator();
		Iterator<Map.Entry<String, String>> it4 = TVinAdViewReport.entrySet().iterator();
		Iterator<Map.Entry<String, String>> it5 = TVinAdEditReport.entrySet().iterator();

		int SrNo = 1;
		while (it1.hasNext() && it2.hasNext()) {
			Map.Entry<String, String> entry1 = it1.next();
			Map.Entry<String, String> entry2 = it2.next();
			Map.Entry<String, String> entry3 = it3.next();
			Map.Entry<String, String> entry4 = it4.next();
			Map.Entry<String, String> entry5 = it5.next();
			String m1 = SrNo + "] EmRF " + entry1.getKey() + "<=>" + entry1.getValue();
			String m2 = SrNo + "] EmVR " + entry2.getKey() + "<=>" + entry2.getValue();
			String m3 = SrNo + "] SAVR " + entry3.getKey() + "<=>" + entry3.getValue();
			String m4 = SrNo + "] AdVR " + entry4.getKey() + "<=>" + entry4.getValue();
			String m5 = SrNo + "] AdER " + entry5.getKey() + "<=>" + entry5.getValue();

			Reporter.log(m1, true);
			Reporter.log(m2, true);
			Reporter.log(m3, true);
			Reporter.log(m4, true);
			Reporter.log(m5, true);
			soft.assertEquals(entry2.getKey(), entry1.getKey(),
					"Em Report and Em View report Keys at SrNo. " + SrNo + " are not equal!");
			soft.assertEquals(entry2.getValue(), entry1.getValue(),
					"Em Report and Em View report Values at SrNo. " + SrNo + " are not equal!");

			soft.assertEquals(entry3.getKey(), entry1.getKey(),
					"Em Report and SA View report Keys at SrNo. " + SrNo + " are not equal!");
			soft.assertEquals(entry3.getValue(), entry1.getValue(),
					"Em Report and SA View Values at SrNo. " + SrNo + " are not equal!");

			soft.assertEquals(entry4.getKey(), entry1.getKey(),
					"Em Report and Ad View report Keys at SrNo. " + SrNo + " are not equal!");
			soft.assertEquals(entry4.getValue(), entry1.getValue(),
					"Em Report and Ad View report Values at SrNo. " + SrNo + " are not equal!");

			soft.assertEquals(entry5.getKey(), entry1.getKey(),
					"Em Report and Ad Edit report Keys at SrNo. " + SrNo + " are not equal!");
			soft.assertEquals(entry5.getValue(), entry1.getValue(),
					"Em Report and Ad Edit Values at SrNo. " + SrNo + " are not equal!");

			SrNo++;
		}
		// soft.assertAll();
		Reporter.log("<=======================================>", true);

//2 Edit Scenario		
		aer.inpAdEditReportAllTask(driver, Department);
		int d = Integer.valueOf(dd) + 2;
		aer.inpAdEditReportDate(String.valueOf(d), "05", "2024");
		Thread.sleep(500);
		LinkedHashMap<String, String> AEDataAfterEdit = aer.getAdEditReportTaskAndValue(driver);
		aer.clickAdEditReportUpdateBtn();
		soft.assertEquals(aer.getAdEditReportToastMsg(driver), "Report updated successfully");
		Thread.sleep(500);
		LinkedHashMap<String, String> TVinAdEditReport2 = aer.getAdEditReportTaskAndValue(driver);
		aer.clickAdEditReportBackBtn();
		ard.clickAdReportDashboardViewBtnForDateTime(driver, CnDtTime);
		Thread.sleep(500);
		LinkedHashMap<String, String> TVinAdViewReport2 = avr.getAdViewReportTaskAndValue(driver);
		Thread.sleep(500);

		log.info("SubAdmin Signing in...");
		SubadminSignIn();
		esm.clickEmSideMenuTeamReportBtn();
		log.info("SubAdmin TR Dashboard...");

		List<String> ActSATRpDLst2 = satr.getSAEmTeamReportInfoList(driver, CnDtTime);
		UtilsClass.compareTwoList(ActSATRpDLst2, ExpSATRpDLst, soft);

		log.info("SubAdmin View Team Report...");
		satr.clickSAEmTeamReportCurrentReportViewBtn(driver, CnDtTime);
		Thread.sleep(5000);
		soft.assertFalse(savr.getSAEmViewTeamReportCheckBoxIsSelected(driver),
				"SA Em View TeamReport Checkbox Displayed");
		LinkedHashMap<String, String> TVinSAViewReport2 = savr.getSAEmViewTeamReportTaskAndValue(driver);

		// Employee SignIn
		employeeSignIn();
		log.info("Collecting report data in EmHistory Page...");
		ehp.getEmHistoryPageTitle(driver);
		Thread.sleep(2000);

		List<String> ActEmHistoryEleLst2 = ehp.getEmHistoryPageCurrentReportData(driver, CnDtTime);

		log.warn("Comparing report data in History Page...");
		UtilsClass.compareTwoList(ActEmHistoryEleLst2, ExpEmHPDLst, soft);

		log.info("Moving to Employee view Report...");
		ehp.clickEmHistoryPageCurrentReportViewBtn(driver, CnDtTime);
		String svt1 = evr.getEmViewReportPageTitle(driver);
		soft.assertEquals(svt1, "Daily Report: " + rpdt);

		log.info("Collecting task From Staff View...");
		Thread.sleep(2000);
		LinkedHashMap<String, String> TVinEmViewReport2 = evr.getEmViewReportTaskAndValue(driver);

		log.info("Verify all Report...");
		// Compare entry by entry

		System.out.println(AEDataAfterEdit.toString());
		System.out.println(TVinAdEditReport2.toString());
		System.out.println(TVinAdViewReport2.toString());
		System.out.println(TVinSAViewReport2.toString());
		System.out.println(TVinEmViewReport2.toString());

		Iterator<Map.Entry<String, String>> ite1 = AEDataAfterEdit.entrySet().iterator();
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
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a");
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
