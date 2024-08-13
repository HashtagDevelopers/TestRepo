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
import com.HashtagMIS.EmployeeUC.ME3Report.EmViewReport;
import com.HashtagMIS.EmployeeUC.SubAdminUC.SAEmTeamReport;
import com.HashtagMIS.EmployeeUC.SubAdminUC.SAEmViewTeamReport;
import LibraryFiles.BaseClass;
import LibraryFiles.UtilityClass;

public class F1AdCheckTC extends BaseClass {
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
	Logger log = LogManager.getLogger(F1AdCheckTC.class);
	PrintWriter pw, pw1;

	String StaffName = "Krunal", subadmin = "Jay",admin="DEVELOPERS", dd = "25", mm = "07", Department = "Incident",
			CnDtTime, cntDate;

	StringBuilder sb;

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
		employeeSignIn();
		// form fill
		submitReport();
		Thread.sleep(200);
		esm.clickEmSideMenuHistoryBtn();
		soft.assertEquals(ehp.getEmHistoryPageCurrentReportStatus(driver,CnDtTime), "Unchecked");
		Thread.sleep(1500);
		ehp.clickEmHistoryPageCurrentReportViewBtn(driver, CnDtTime);
		Thread.sleep(200);
		soft.assertEquals(evr.getEmViewReportChkUnChk(driver), "Unchecked");
	}

	@Test(enabled = false)
	public void Flow1SubadminCheckAfterFormSubmitAndAdminUncheck() throws IOException, InterruptedException {
		// 1 SA check after form submit
		log.info("SubAdmin Signing select current report...");
		subadminSignIn();
		esm.clickEmSideMenuTeamReportBtn();
		log.info("Verify SA team report dashboard");
		Reporter.log(11 + satr.getSAEmTeamReportCurrentReportStatus(driver, CnDtTime) + " = Unchecked", true);
		soft.assertEquals(satr.getSAEmTeamReportCurrentReportStatus(driver, CnDtTime), "Unchecked", "11");

		satr.clickSAEmTeamReportCurrentReportViewBtn(driver, CnDtTime);

		log.info("Verify SAdmin View Report");
		Reporter.log(12 + savr.getSAEmViewTeamReportChkUnChk(driver) + " = Unchecked", true);
		Reporter.log(13 + String.valueOf(savr.getSAEmViewTeamReportCheckBoxIsSelected(driver)) + " = False", true);
		soft.assertEquals(savr.getSAEmViewTeamReportChkUnChk(driver), "Unchecked", "12");
		soft.assertFalse(savr.getSAEmViewTeamReportCheckBoxIsSelected(driver), "13");
		// SA check
		log.info("SA check");
		savr.clickSAEmViewTeamReportSubmitBtn();
		soft.assertEquals(savr.getSAEmViewTeamReportToastMsg(driver), "Please click on checkbox.", "13");
		savr.clickSAEmViewTeamReportCheckBox();
		savr.clickSAEmViewTeamReportSubmitBtn();
		soft.assertEquals(savr.getSAEmViewTeamReportToastMsg(driver), "Report checked successfully.", "13");
        //1
		log.info("Verify SA team report dashboard");
		Reporter.log(14 + satr.getSAEmTeamReportCurrentReportStatus(driver, CnDtTime) + " = Checked", true);
		soft.assertEquals(satr.getSAEmTeamReportCurrentReportStatus(driver, CnDtTime), "Checked", "14");

		satr.clickSAEmTeamReportCurrentReportViewBtn(driver, CnDtTime);
		log.info("Verify SAdmin View Report");
		Reporter.log(15 + savr.getSAEmViewTeamReportChkUnChk(driver) + " = Checked by: " + subadmin, true);
		Reporter.log(16 + String.valueOf(savr.getSAEmViewTeamReportCheckBoxIsPresent()) + " = false", true);
		soft.assertEquals(savr.getSAEmViewTeamReportChkUnChk(driver), "Checked by: " + subadmin, "15");
		soft.assertFalse(savr.getSAEmViewTeamReportCheckBoxIsPresent(), "16");
		// 2
		employeeSignIn();
		log.info("Verify in employee...");
		esm.clickEmSideMenuHistoryBtn();
		Reporter.log(ehp.getEmHistoryPageCurrentReportStatus(driver,CnDtTime) + " = Checked", true);
		soft.assertEquals(ehp.getEmHistoryPageCurrentReportStatus(driver,CnDtTime), "Checked");
		ehp.clickEmHistoryPageCurrentReportViewBtn(driver, CnDtTime);
		Thread.sleep(1500);
		Reporter.log(2 + evr.getEmViewReportChkUnChk(driver) + " = Checked by: " + subadmin, true);
		soft.assertEquals(evr.getEmViewReportChkUnChk(driver), "Checked by: " + subadmin, "2");
		// 3
		adminSignIn();
		log.info("Verify in admin...");
		asm.clickAdSideMenuReportsBtn();
		soft.assertEquals(ard.getAdReportDashboardCurrentReportStatus(Department, StaffName, driver, CnDtTime),
				"Checked", "31");
		ard.clickAdReportDashboardViewBtnForDateTime(driver, CnDtTime);
		soft.assertEquals(avr.getAdViewReportChkUnChk(driver), "Checked by: " + subadmin, "32");
		soft.assertTrue(avr.getAdViewReportChkBoxIsSelected(driver), "33");

		// 2 Admin Uncheck after SA submit
		log.info("admin uncheck flow...");
		avr.checkAdViewReportChkBox();
		soft.assertEquals(ard.getAdReportDashboardCurrentReportStatus(Department, StaffName, driver, CnDtTime),
				"Unchecked", "34");
		ard.clickAdReportDashboardViewBtnForDateTime(driver, CnDtTime);
		Reporter.log(35 + avr.getAdViewReportChkUnChk(driver) + " = Unchecked by : "+admin, true);
		soft.assertEquals(avr.getAdViewReportChkUnChk(driver), "Unchecked by : Admin", "35");
		soft.assertFalse(avr.getAdViewReportChkBoxIsSelected(driver), "36");
		// 4
		employeeSignIn();
		log.info("Admn Uncheck= verify in employee...");
		esm.clickEmSideMenuHistoryBtn();
		soft.assertEquals(ehp.getEmHistoryPageCurrentReportStatus(driver,CnDtTime), "Unchecked");
		ehp.clickEmHistoryPageCurrentReportViewBtn(driver, CnDtTime);
		
		Thread.sleep(3000);
		Reporter.log(4 + evr.getEmViewReportChkUnChk(driver) + " = Unchecked by : "+admin, true);
		soft.assertEquals(evr.getEmViewReportChkUnChk(driver), "Unchecked by : Admin", "4");

		// 5
		log.info("admin uncheck= SubAdmin Signing select current report...");
		subadminSignIn();
		esm.clickEmSideMenuTeamReportBtn();
		log.info("Admn Uncheck= verify in SubAdmin...");
		soft.assertEquals(satr.getSAEmTeamReportCurrentReportStatus(driver, CnDtTime), "Unchecked", "51");
		satr.clickSAEmTeamReportCurrentReportViewBtn(driver, CnDtTime);
		log.info("admin uncheck=Verify SAdmin View Report");
		soft.assertEquals(savr.getSAEmViewTeamReportChkUnChk(driver), "Unchecked by : "+admin, "52");
		soft.assertFalse(savr.getSAEmViewTeamReportCheckBoxIsSelected(driver), "53");

		// 3 SA check after admin uncheck
		log.info("SA check= after admin check");
		savr.clickSAEmViewTeamReportSubmitBtn();
		
		savr.clickSAEmViewTeamReportCheckBox();
		savr.clickSAEmViewTeamReportSubmitBtn();
		savr.clickSAEmViewTeamReportBackBtn();

		log.info("SA check= Verify SA team report dashboard");
		Reporter.log(14 + satr.getSAEmTeamReportCurrentReportStatus(driver, CnDtTime) + " = Checked", true);
		soft.assertEquals(satr.getSAEmTeamReportCurrentReportStatus(driver, CnDtTime), "Checked", "14");

		satr.clickSAEmTeamReportCurrentReportViewBtn(driver, CnDtTime);
		log.info("SA check= Verify SAdmin View Report");
		Reporter.log(15 + savr.getSAEmViewTeamReportChkUnChk(driver) + " = Checked by: " + subadmin, true);
		soft.assertEquals(savr.getSAEmViewTeamReportChkUnChk(driver), "Checked by: " + subadmin, "15");
		Reporter.log(16 + String.valueOf(savr.getSAEmViewTeamReportCheckBoxIsPresent()) + " = false", true);
		soft.assertFalse(savr.getSAEmViewTeamReportCheckBoxIsPresent(), "16");
		
		
		// 2
		employeeSignIn();
		log.info("SA check= Verify in employee...");
		esm.clickEmSideMenuHistoryBtn();
		soft.assertEquals(ehp.getEmHistoryPageCurrentReportStatus(driver,CnDtTime), "Unchecked");
		ehp.clickEmHistoryPageCurrentReportViewBtn(driver, CnDtTime);
		Thread.sleep(1500);
		Reporter.log(2 + evr.getEmViewReportChkUnChk(driver) + " = Checked by: " + subadmin, true);
		soft.assertEquals(evr.getEmViewReportChkUnChk(driver), "Checked by: " + subadmin, "2");
		// 3
		adminSignIn();
		log.info("SA check= Verify in admin...");
		asm.clickAdSideMenuReportsBtn();
		soft.assertEquals(ard.getAdReportDashboardCurrentReportStatus(Department, StaffName, driver, CnDtTime),
				"Checked", "31");
		ard.clickAdReportDashboardViewBtnForDateTime(driver, CnDtTime);
		soft.assertEquals(avr.getAdViewReportChkUnChk(driver), "Checked by: " + subadmin, "32");
		soft.assertTrue(avr.getAdViewReportChkBoxIsSelected(driver), "33");

		
		
//4 Admin check after SA check
		log.info("admin check= flow...");
		avr.clickAdViewReportSubmitBtn();
		soft.assertEquals(ard.getAdReportDashboardCurrentReportStatus(Department, StaffName, driver, CnDtTime),
				"Checked", "34");
		ard.clickAdReportDashboardViewBtnForDateTime(driver, CnDtTime);
		Reporter.log(35 + avr.getAdViewReportChkUnChk(driver) + " = Checked by : "+admin, true);
		soft.assertEquals(avr.getAdViewReportChkUnChk(driver), "Checked by : "+admin, "35");
		soft.assertFalse(avr.getAdViewReportChkBoxIsSelected(driver), "36");
		// 4
		employeeSignIn();
		log.info("Admn check= verify in employee...");
		esm.clickEmSideMenuHistoryBtn();
		soft.assertEquals(ehp.getEmHistoryPageCurrentReportStatus(driver,CnDtTime), "Unchecked");
		ehp.clickEmHistoryPageCurrentReportViewBtn(driver, CnDtTime);
		Thread.sleep(1500);
		Reporter.log(4 + evr.getEmViewReportChkUnChk(driver) + " = Checked by : "+admin, true);
		soft.assertEquals(evr.getEmViewReportChkUnChk(driver), "Checked by : "+admin, "4");

		// 5
		log.info("Admn check= SubAdmin Signing select current report...");
		subadminSignIn();
		esm.clickEmSideMenuTeamReportBtn();
		log.info("Admn check= verify in SubAdmin...");
		soft.assertEquals(satr.getSAEmTeamReportCurrentReportStatus(driver, CnDtTime), "Unchecked", "51");
		satr.clickSAEmTeamReportCurrentReportViewBtn(driver, CnDtTime);
		log.info("Admn check= Verify SAdmin View Report");
		soft.assertEquals(savr.getSAEmViewTeamReportChkUnChk(driver), "Checked by : "+admin, "52");
		soft.assertFalse(savr.getSAEmViewTeamReportCheckBoxIsPresent(), "53");

		//// Delete report
//		adminSignIn();
//		asm.clickAdSideMenuReportsBtn();
//		ard.clickAdReportDashboardDeleteBtnForDateTime(driver, CnDtTime);

	}

	@Test(enabled = true)
	public void Flow2AdminCheckAfterFormSubmitAndSubAdminCheck() throws IOException, InterruptedException {
		// 1 AdminCheckAfterFormSubmit
		adminSignIn();
		log.info("Direct Admin check= Verify in admin...");
		asm.clickAdSideMenuReportsBtn();
		soft.assertEquals(ard.getAdReportDashboardCurrentReportStatus(Department, StaffName, driver, CnDtTime),
				"Unchecked", "31");
		ard.clickAdReportDashboardViewBtnForDateTime(driver, CnDtTime);
		soft.assertEquals(avr.getAdViewReportChkUnChk(driver), "Unchecked", "32");
	
		soft.assertFalse(avr.getAdViewReportChkBoxIsSelected(driver), "33");

		log.info("Direct admin check= flow...");
		
		//avr.clickAdViewReportSubmitBtn();
		
		
		
		
		Thread.sleep(5000);
		avr.clickAdViewReportChkBox();
		
		avr.clickAdViewReportSubmitBtn();
		
		soft.assertEquals(ard.getAdReportDashboardCurrentReportStatus(Department, StaffName, driver, CnDtTime),
				"Checked", "34");
		
		ard.clickAdReportDashboardViewBtnForDateTime(driver, CnDtTime);
		Reporter.log(35 + avr.getAdViewReportChkUnChk(driver) + " = Checked by: "+admin, true);
		soft.assertEquals(avr.getAdViewReportChkUnChk(driver), "Checked by: "+admin, "35");
		soft.assertTrue(avr.getAdViewReportChkBoxIsSelected(driver), "36");
		// 4
		employeeSignIn();
		log.info("Direct Admn check= verify in employee...");
		esm.clickEmSideMenuHistoryBtn();
		soft.assertEquals(ehp.getEmHistoryPageCurrentReportStatus(driver,CnDtTime), "Checked");
		ehp.clickEmHistoryPageCurrentReportViewBtn(driver, CnDtTime);
		Thread.sleep(1500);
		Reporter.log(4 + evr.getEmViewReportChkUnChk(driver) + " = Checked by: "+admin, true);
		soft.assertEquals(evr.getEmViewReportChkUnChk(driver), "Checked by: "+admin, "4");

		// 5
		log.info("Direct Admn check= SubAdmin Signing select current report...");
		subadminSignIn();
		esm.clickEmSideMenuTeamReportBtn();
		log.info("Direct Admn check= verify in SubAdmin...");
		soft.assertEquals(satr.getSAEmTeamReportCurrentReportStatus(driver, CnDtTime), "Checked", "51");
		satr.clickSAEmTeamReportCurrentReportViewBtn(driver, CnDtTime);
		log.info("DirectAdmn check= Verify SAdmin View Report");
		soft.assertEquals(savr.getSAEmViewTeamReportChkUnChk(driver), "Checked by: "+admin, "52");
		soft.assertFalse(savr.getSAEmViewTeamReportCheckBoxIsPresent(), "53");

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

	public void subadminSignIn() throws IOException {
		driver.get(UtilityClass.getPFData("URL"));
		elp.inpEmLoginPageSignIn(UtilityClass.getPFData("SAEmail"), UtilityClass.getPFData("SAPassword"));
	}

	public void submitReport() throws IOException, InterruptedException {
		log.info("Report Form Opening by Selecting Department and Date...");
		esm.clickEmSideMenuDailyReportBtn();
		Thread.sleep(300);
		erp.selEmReportPageDepartmentName(driver, Department);
		erp.inpEmReportPageDate(dd , mm);

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
		
	}
}