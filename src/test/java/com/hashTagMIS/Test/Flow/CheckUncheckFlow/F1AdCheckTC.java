package com.hashTagMIS.Test.Flow.CheckUncheckFlow;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
import LibraryFiles.BaseClass;
import LibraryFiles.UtilityClass;

/*emp submit form his status =>Subadmin Signin  his status => subadmin check his status 
 * emp Signin his status=>admin signin his status=>admin uncheck his status=>emp Signin his status
 *  =>subadmin signin his status=> subadmin check his status =>emp Signin his status=>
 *  admin signIn his status=>admin check his status=>employee sign his status=>
 *  subadmin signin his status */
/*emp submit form his status =>admin signin his status=>admin check his status=>emp Signin his status
 *  =>subadmin signin his status*/





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
	EmReportPreviewPage epp;
	
	SAEmTeamReport satr;
	SAEmViewTeamReport savr;

	SoftAssert soft;
	Logger log = LogManager.getLogger(F1AdCheckTC.class);
	PrintWriter pw, pw1;

	String StaffName = "Krunal", subadmin = "Jay", admin = "DEVELOPERS", dd = "25", mm = "07", Department = "Care Support",
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
		epp = new EmReportPreviewPage(driver);
		satr = new SAEmTeamReport(driver);
		savr = new SAEmViewTeamReport(driver);
	}

	@BeforeMethod
	public void LogIn() throws IOException, InterruptedException {
		soft = new SoftAssert();
		employeeSignIn();
		// form fill
		submitReport();
		Thread.sleep(200);
		esm.clickEmSideMenuHistoryBtn();
		soft.assertEquals(ehp.getEmHistoryPageCurrentReportStatus(driver, CnDtTime), "Unchecked" ,"1");
		Thread.sleep(1500);
		ehp.clickEmHistoryPageCurrentReportViewBtn(driver, CnDtTime);
		Thread.sleep(200);
		soft.assertEquals(evr.getEmViewReportChkUnChk(driver), "Unchecked","2");
	}

	@Test(enabled = false,groups = "Regression")
	public void Flow1SubadminCheckAfterFormSubmitAndAdminUncheckTest() throws IOException, InterruptedException {
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
		Reporter.log(13 + String.valueOf(savr.getSAEmViewTeamReportCheckBoxIsSelected(driver)) + " = false", true);
		soft.assertEquals(savr.getSAEmViewTeamReportChkUnChk(driver), "Unchecked", "12");
		soft.assertFalse(savr.getSAEmViewTeamReportCheckBoxIsSelected(driver), "13");
		// SA check
		log.info("SA check");
		savr.clickSAEmViewTeamReportSubmitBtn();
		soft.assertEquals(savr.getSAEmViewTeamReportToastMsg(driver), "Please select checkbox.", "14");	
		//Report Checked by Subadmin
		savr.clickSAEmViewTeamReportCheckBox();
		savr.clickSAEmViewTeamReportSubmitBtn();
		////soft.assertEquals(savr.getSAEmViewTeamReportToastMsg(driver), "Checked successfully.", "15");
		// 1
		log.info("Verify SA team report dashboard");
		Reporter.log(16 + satr.getSAEmTeamReportCurrentReportStatus(driver, CnDtTime) + " = Checked", true);
		soft.assertEquals(satr.getSAEmTeamReportCurrentReportStatus(driver, CnDtTime), "Checked", "16");

		satr.clickSAEmTeamReportCurrentReportViewBtn(driver, CnDtTime);
		log.info("Verify SAdmin View Report");
		Reporter.log(17 + savr.getSAEmViewTeamReportChkUnChk(driver) + " = Checked by: " + subadmin, true);
		Reporter.log(18 + String.valueOf(savr.getSAEmViewTeamReportCheckBoxIsPresent()) + " = false", true);
		soft.assertEquals(savr.getSAEmViewTeamReportChkUnChk(driver), "Checked by: " + subadmin, "17");
		soft.assertFalse(savr.getSAEmViewTeamReportCheckBoxIsPresent(), "18");
		// 2
		employeeSignIn();
		log.info("Verify in employee...");
		esm.clickEmSideMenuHistoryBtn();
		Reporter.log("19"+ehp.getEmHistoryPageCurrentReportStatus(driver, CnDtTime) + " = Checked", true);
		soft.assertEquals(ehp.getEmHistoryPageCurrentReportStatus(driver, CnDtTime), "Checked","19");
		ehp.clickEmHistoryPageCurrentReportViewBtn(driver, CnDtTime);
		Thread.sleep(1500);
		Reporter.log(20 + evr.getEmViewReportChkUnChk(driver) + " = Checked by: " + subadmin, true);
		soft.assertEquals(evr.getEmViewReportChkUnChk(driver), "Checked by: " + subadmin, "20");
		// 3
		adminSignIn();
		log.info("Verify in admin...");
		asm.clickAdSideMenuReportsBtn();
		soft.assertEquals(ard.getAdReportDashboardCurrentReportStatus(Department, StaffName, driver, CnDtTime),
				"Checked", "21");
		ard.clickAdReportDashboardViewBtnForDateTime(driver, CnDtTime);
		soft.assertEquals(avr.getAdViewReportChkUnChk(driver), "Checked by: " + subadmin, "22");
		soft.assertTrue(avr.getAdViewReportChkBoxIsSelected(driver), "23");

		// 2 Admin Uncheck after SA submit
		log.info("admin uncheck flow...");
		//Report Unchecked by admin
		avr.checkAdViewReportChkBox();
		soft.assertEquals(ard.getAdReportDashboardCurrentReportStatus(Department, StaffName, driver, CnDtTime),
				"Unchecked", "24");
		ard.clickAdReportDashboardViewBtnForDateTime(driver, CnDtTime);
		Reporter.log(25 + avr.getAdViewReportChkUnChk(driver) + " = Unchecked by : " + admin, true);
		soft.assertEquals(avr.getAdViewReportChkUnChk(driver), "Unchecked by : "+ admin, "25");
		soft.assertFalse(avr.getAdViewReportChkBoxIsSelected(driver), "26");
		// 4
		employeeSignIn();
		log.info("Admn Uncheck= verify in employee...");
		esm.clickEmSideMenuHistoryBtn();
		soft.assertEquals(ehp.getEmHistoryPageCurrentReportStatus(driver, CnDtTime), "Unchecked","27");
		ehp.clickEmHistoryPageCurrentReportViewBtn(driver, CnDtTime);

		Thread.sleep(3000);
		Reporter.log(28 + evr.getEmViewReportChkUnChk(driver) + " = Unchecked by : " + admin, true);
		soft.assertEquals(evr.getEmViewReportChkUnChk(driver), "Unchecked by : " + admin, "28");

		// 5
		log.info("admin uncheck= SubAdmin Signing select current report...");
		subadminSignIn();
		esm.clickEmSideMenuTeamReportBtn();
		log.info("Admn Uncheck= verify in SubAdmin...");
		soft.assertEquals(satr.getSAEmTeamReportCurrentReportStatus(driver, CnDtTime), "Unchecked", "29");
		satr.clickSAEmTeamReportCurrentReportViewBtn(driver, CnDtTime);
		log.info("admin uncheck=Verify SAdmin View Report");
		soft.assertEquals(savr.getSAEmViewTeamReportChkUnChk(driver), "Unchecked by : " + admin, "30");
		soft.assertFalse(savr.getSAEmViewTeamReportCheckBoxIsSelected(driver), "31");

		// 3 SA check after admin uncheck
		//Report Checked by Subadmin
		log.info("SA check= after admin check");
		savr.clickSAEmViewTeamReportSubmitBtn();
		savr.clickSAEmViewTeamReportCheckBox();
		savr.clickSAEmViewTeamReportSubmitBtn();
		
		//savr.clickSAEmViewTeamReportBackBtn();

		log.info("SA check= Verify SA team report dashboard");
		Reporter.log(32 + satr.getSAEmTeamReportCurrentReportStatus(driver, CnDtTime) + " = Checked", true);
		soft.assertEquals(satr.getSAEmTeamReportCurrentReportStatus(driver, CnDtTime), "Checked", "32");

		satr.clickSAEmTeamReportCurrentReportViewBtn(driver, CnDtTime);
		log.info("SA check= Verify SAdmin View Report");
		Reporter.log(33 + savr.getSAEmViewTeamReportChkUnChk(driver) + " = Checked by: " + subadmin, true);
		soft.assertEquals(savr.getSAEmViewTeamReportChkUnChk(driver), "Checked by: " + subadmin, "33");
		Reporter.log(34 + String.valueOf(savr.getSAEmViewTeamReportCheckBoxIsPresent()) + " = false", true);
		soft.assertFalse(savr.getSAEmViewTeamReportCheckBoxIsPresent(), "34");

		// 2
		employeeSignIn();
		log.info("SA check= Verify in employee...");
		esm.clickEmSideMenuHistoryBtn();
		soft.assertEquals(ehp.getEmHistoryPageCurrentReportStatus(driver, CnDtTime), "Checked","35");
		ehp.clickEmHistoryPageCurrentReportViewBtn(driver, CnDtTime);
		Thread.sleep(1500);
		Reporter.log(36 + evr.getEmViewReportChkUnChk(driver) + " = Checked by: " + subadmin, true);
		soft.assertEquals(evr.getEmViewReportChkUnChk(driver), "Checked by: " + subadmin, "36");
		// 3
		adminSignIn();
		log.info("SA check= Verify in admin...");
		asm.clickAdSideMenuReportsBtn();
		soft.assertEquals(ard.getAdReportDashboardCurrentReportStatus(Department, StaffName, driver, CnDtTime),
				"Checked", "37");
		ard.clickAdReportDashboardViewBtnForDateTime(driver, CnDtTime);
		soft.assertEquals(avr.getAdViewReportChkUnChk(driver), "Checked by: " + subadmin, "38");
		soft.assertTrue(avr.getAdViewReportChkBoxIsSelected(driver), "39");

		// 4 Admin check after SA check
		log.info("admin check= flow...");
		//Report Checked by Admin
		avr.clickAdViewReportSubmitBtn();
		soft.assertEquals(ard.getAdReportDashboardCurrentReportStatus(Department, StaffName, driver, CnDtTime),
				"Checked", "40");
		ard.clickAdReportDashboardViewBtnForDateTime(driver, CnDtTime);
		Reporter.log(41 + avr.getAdViewReportChkUnChk(driver) + " = Checked by: " + admin, true);
		soft.assertEquals(avr.getAdViewReportChkUnChk(driver), "Checked by: " + admin, "41");
		soft.assertTrue(avr.getAdViewReportChkBoxIsSelected(driver), "42");
		// 4
		employeeSignIn();
		log.info("Admn check= verify in employee...");
		esm.clickEmSideMenuHistoryBtn();
		soft.assertEquals(ehp.getEmHistoryPageCurrentReportStatus(driver, CnDtTime), "Checked","43");
		ehp.clickEmHistoryPageCurrentReportViewBtn(driver, CnDtTime);
		Thread.sleep(1500);
		Reporter.log(44 + evr.getEmViewReportChkUnChk(driver) + " = Checked by: " + admin, true);
		soft.assertEquals(evr.getEmViewReportChkUnChk(driver), "Checked by: " + admin, "44");

		// 5
		log.info("Admn check= SubAdmin Signing select current report...");
		subadminSignIn();
		esm.clickEmSideMenuTeamReportBtn();
		log.info("Admn check= verify in SubAdmin...");
		soft.assertEquals(satr.getSAEmTeamReportCurrentReportStatus(driver, CnDtTime), "Checked", "45");
		satr.clickSAEmTeamReportCurrentReportViewBtn(driver, CnDtTime);
		log.info("Admn check= Verify SAdmin View Report");
		soft.assertEquals(savr.getSAEmViewTeamReportChkUnChk(driver), "Checked by: " + admin, "46");
		soft.assertFalse(savr.getSAEmViewTeamReportCheckBoxIsPresent(), "47");

		//// Delete report
//		adminSignIn();
//		asm.clickAdSideMenuReportsBtn();
//		ard.clickAdReportDashboardDeleteBtnForDateTime(driver, CnDtTime);
		soft.assertAll();
	}

	@Test(enabled = true,groups = "Regression")
	public void Flow2AdminCheckAfterFormSubmitAndSubAdminCheckTest() throws IOException, InterruptedException {
		// 1 AdminCheckAfterFormSubmit
		adminSignIn();
		log.info("Direct Admin check= Verify in admin...");
		asm.clickAdSideMenuReportsBtn();
		soft.assertEquals(ard.getAdReportDashboardCurrentReportStatus(Department, StaffName, driver, CnDtTime),
				"Unchecked", "51");
		ard.clickAdReportDashboardViewBtnForDateTime(driver, CnDtTime);
		soft.assertEquals(avr.getAdViewReportChkUnChk(driver), "Unchecked", "52");

		soft.assertFalse(avr.getAdViewReportChkBoxIsSelected(driver), "53");

		log.info("Direct admin check= flow...");

		// avr.clickAdViewReportSubmitBtn();

		Thread.sleep(5000);
		//Report Checked By Admin
		avr.clickAdViewReportChkBox();

		avr.clickAdViewReportSubmitBtn();

		soft.assertEquals(ard.getAdReportDashboardCurrentReportStatus(Department, StaffName, driver, CnDtTime),
				"Checked", "54");

		ard.clickAdReportDashboardViewBtnForDateTime(driver, CnDtTime);
		Reporter.log(55 + avr.getAdViewReportChkUnChk(driver) + " = Checked by: " + admin, true);
		soft.assertEquals(avr.getAdViewReportChkUnChk(driver), "Checked by: " + admin, "55");
		soft.assertTrue(avr.getAdViewReportChkBoxIsSelected(driver), "56");
		// 2
		employeeSignIn();
		log.info("Direct Admn check= verify in employee...");
		esm.clickEmSideMenuHistoryBtn();
		soft.assertEquals(ehp.getEmHistoryPageCurrentReportStatus(driver, CnDtTime), "Checked","57");
		ehp.clickEmHistoryPageCurrentReportViewBtn(driver, CnDtTime);
		Thread.sleep(1500);
		Reporter.log(58 + evr.getEmViewReportChkUnChk(driver) + " = Checked by: " + admin, true);
		soft.assertEquals(evr.getEmViewReportChkUnChk(driver), "Checked by: " + admin, "58");

		// 3
		log.info("Direct Admn check= SubAdmin Signing select current report...");
		subadminSignIn();
		esm.clickEmSideMenuTeamReportBtn();
		log.info("Direct Admn check= verify in SubAdmin...");
		soft.assertEquals(satr.getSAEmTeamReportCurrentReportStatus(driver, CnDtTime), "Checked", "59");
		satr.clickSAEmTeamReportCurrentReportViewBtn(driver, CnDtTime);
		log.info("DirectAdmn check= Verify SAdmin View Report");
		soft.assertEquals(savr.getSAEmViewTeamReportChkUnChk(driver), "Checked by: " + admin, "60");
		soft.assertFalse(savr.getSAEmViewTeamReportCheckBoxIsPresent(), "61");

		// 4
		adminSignIn();
		log.info("Direct Admin check= Verify in admin...");
		asm.clickAdSideMenuReportsBtn();
		ard.clickAdReportDashboardViewBtnForDateTime(driver, CnDtTime);

		Thread.sleep(5000);
		// 2 admin uncheck after he check
		avr.clickAdViewReportChkBox();

		avr.clickAdViewReportSubmitBtn();

		soft.assertEquals(ard.getAdReportDashboardCurrentReportStatus(Department, StaffName, driver, CnDtTime),
				"Unchecked", "62");

		ard.clickAdReportDashboardViewBtnForDateTime(driver, CnDtTime);
		Reporter.log(63 + avr.getAdViewReportChkUnChk(driver) + " = Unchecked by : " + admin, true);
		soft.assertEquals(avr.getAdViewReportChkUnChk(driver), "Unchecked by : " + admin,"63");
	
		soft.assertFalse(avr.getAdViewReportChkBoxIsSelected(driver),"64");

		// 2
		employeeSignIn();
		log.info("Direct Admn check= verify in employee...");
		esm.clickEmSideMenuHistoryBtn();
		soft.assertEquals(ehp.getEmHistoryPageCurrentReportStatus(driver, CnDtTime), "Unchecked","65");
		ehp.clickEmHistoryPageCurrentReportViewBtn(driver, CnDtTime);
		Thread.sleep(1500);
		Reporter.log(66 + evr.getEmViewReportChkUnChk(driver) + " = Unchecked by : " + admin, true);
		soft.assertEquals(evr.getEmViewReportChkUnChk(driver), "Unchecked by : " + admin,"66");

		// 3
		log.info("Direct Admn check= SubAdmin Signing select current report...");
		subadminSignIn();
		esm.clickEmSideMenuTeamReportBtn();
		log.info("Direct Admn check= verify in SubAdmin...");
		soft.assertEquals(satr.getSAEmTeamReportCurrentReportStatus(driver, CnDtTime), "Unchecked","67");
		satr.clickSAEmTeamReportCurrentReportViewBtn(driver, CnDtTime);
		log.info("DirectAdmn check= Verify SAdmin View Report");
		soft.assertEquals(savr.getSAEmViewTeamReportChkUnChk(driver), "Unchecked by : " + admin,"68");
		soft.assertTrue(savr.getSAEmViewTeamReportCheckBoxIsPresent(),"69");

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
		elp.EmLoginPageSignIn(driver,UtilityClass.getPFData("Email"), UtilityClass.getPFData("Password"));
	}

	public void subadminSignIn() throws IOException {	
		elp.EmLoginPageSignIn(driver, UtilityClass.getPFData("SAEmail"), UtilityClass.getPFData("SAPassword"));
	}

	public void submitReport() throws IOException, InterruptedException {
		log.info("Report Form Opening by Selecting Department and Date...");
		esm.clickEmSideMenuDailyReportBtn();
		Thread.sleep(300);
		erp.selEmReportPageDepartmentName(driver, Department);
		erp.inpEmReportPageDate(dd, mm);

		erp.inpEmReportForm5Task();

		log.info("Submitting Report....");
		System.out.println("rp start time= " + getTimeDateSs());
		erp.clickEmReportPageSendBtn();
		System.out.println("rp end time= " + getTimeDateSs());

		log.info("Submitting Report....");
		while (true) {
			// Get the current time in seconds
			int seconds = LocalTime.now().getSecond();

			// Check if the seconds are less than 45
			if (seconds < 40) {
				// button.click(); // Perform the click
				CnDtTime = getTimeDate();
				System.out.println("cndt=" + CnDtTime);
				System.out.println("cndt time= " + getTimeDateSs());
				Thread.sleep(5000);
				System.out.println("pp start time= " + getTimeDateSs());
				epp.clickEmReportPreviewPageConfirmBtn();
				System.out.println("pp end time= " + getTimeDateSs());
				System.out.println("Button clicked at seconds: " + seconds);
				break; // Exit the loop after clicking
			}

			// Sleep for a short time to prevent excessive CPU usage
			Thread.sleep(500); // Adjust the sleep time if necessary
		}
		System.out.println("ok start time= " + getTimeDateSs());
		erp.clickEmReportPageAreYouSureOKBtn();
		System.out.println("ok end time= " + getTimeDateSs());

	}
	public String getTimeDateSs() {
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss.SSS a")).toUpperCase();
	}
}