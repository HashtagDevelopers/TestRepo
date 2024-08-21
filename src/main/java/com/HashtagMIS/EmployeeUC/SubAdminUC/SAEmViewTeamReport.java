package com.HashtagMIS.EmployeeUC.SubAdminUC;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import LibraryFiles.UtilityClass;

public class SAEmViewTeamReport {


	@FindBy(xpath = "//div[@id='cell-2-undefined']")
	private List<WebElement> taskList;
	@FindBy(xpath = "(//input[@type='text'])[1]")
	private WebElement name;
	@FindBy(xpath = "//input[@type='date']")
	private WebElement date;
	@FindBy(xpath = "(//input[@type='text'])[2]")
	private WebElement dept;
	@FindBy(xpath = "//a[text()='Go Back']")
	private WebElement backBtn;
	@FindBy(xpath = "//button[text()='Submit']")
	private WebElement submitBtn;
	@FindBy(xpath = "//input[@type='checkbox']")
	private WebElement chk;
	@FindBy(xpath = "//input[@type='checkbox']")
	private List<WebElement> chkLst;
	@FindBy(xpath = "//h2")
	private WebElement ChkUnChk;
	@FindBy(xpath = "//div[@role='alert']/child::div[2]")
	private WebElement toastMsg;
	Actions act;
	LinkedHashMap<String, String> lmp;
	ArrayList<String> al;

	public SAEmViewTeamReport(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.act = new Actions(driver);
	}

	public ArrayList<String> getSAEmViewTeamReportChkUnChkSce(WebDriver driver) throws InterruptedException {
		al = new ArrayList<String>();
		al.addAll(Arrays.asList(ChkUnChk.getText(), String.valueOf(!chkLst.isEmpty())));

		UtilityClass.DrawBorder(driver, ChkUnChk);
		UtilityClass.DrawBorderList(driver, chkLst);
		Thread.sleep(1500);
		return al;
	}

	public List<String> getSAEmViewTeamReportUpperInfo() throws InterruptedException, ParseException {
		al = new ArrayList<String>();
		al.addAll(Arrays.asList(name.getAttribute("value"), dept.getAttribute("value"), getSAEmViewTeamReportDate(),
				ChkUnChk.getText()));
		return al;
	}

	public String getSAEmViewTeamReportDate() throws InterruptedException, ParseException {
		String dateValue = date.getAttribute("value");
		SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");

		// Parse the date string into a Date object
		Date date = inputFormat.parse(dateValue);

		// Define the output format (desired format: DD-MMM-YYYY)
		SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MMM-yyyy");

		// Format the Date object into the desired string format
		String formattedDate = outputFormat.format(date);
		return formattedDate;
	}

	public String getSAEmViewTeamReportChkUnChk(WebDriver driver) throws InterruptedException {
		Thread.sleep(300);
		UtilityClass.DrawBorder(driver, ChkUnChk);
		Thread.sleep(1500);
		return ChkUnChk.getText();
	}

	public LinkedHashMap<String, String> getSAEmViewTeamReportTaskAndValue(WebDriver driver) {
		lmp = new LinkedHashMap<String, String>();
		for (int i = 1; i <= taskList.size(); i++) {
			String task = driver
					.findElement(
							By.xpath("(//div[@class='sc-dhKdPU jFfAhm rdt_TableRow'] )[" + i + "]/child::div[2]/div"))
					.getText().trim();
			String value = driver
					.findElement(By.xpath(
							"(//div[@class='sc-dhKdPU jFfAhm rdt_TableRow'] )[" + i + "]/child::div[3]/textarea"))
					.getText().trim();
			lmp.put(task, value);

		}
		return lmp;
	}

	public boolean getSAEmViewTeamReportCheckBoxIsSelected(WebDriver driver) throws InterruptedException {
		UtilityClass.DrawBorder(driver, ChkUnChk);
		Thread.sleep(1500);
		return chk.isSelected();
	}

	public boolean getSAEmViewTeamReportCheckBoxIsPresent() {

		return !chkLst.isEmpty();
	}

	public void clickSAEmViewTeamReportCheckBox() {
		chk.click();
	}

	public void clickSAEmViewTeamReportSubmitBtn() {
		submitBtn.click();
	}

	public void clickSAEmViewTeamReportBackBtn() {
		backBtn.click();
	}

	public String getSAEmViewTeamReportToastMsg(WebDriver driver) {

		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			wait.until(expectedConditions -> toastMsg.isDisplayed()); // Lambda for expected condition
			UtilityClass.DrawBorder(driver, toastMsg);
			return toastMsg.getText();
		} catch (TimeoutException e) {

			// System.out.println("Warning: Toast message element not found within "+"5" + "
			// seconds.");
			return "null";
		}
	}
}
