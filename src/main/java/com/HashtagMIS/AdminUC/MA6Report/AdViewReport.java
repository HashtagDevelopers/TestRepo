package com.HashtagMIS.AdminUC.MA6Report;

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

public class AdViewReport {

	@FindBy(xpath = "//h2[contains(text(),'Employee Name')]")
	private WebElement SVtitle;
	@FindBy(xpath = "//input[@type='text']")
	private WebElement dept;
	@FindBy(xpath = "//input[@type='date']")
	private WebElement reportDate;
	@FindBy(xpath = "(//h2)[3]")
	private WebElement ChkUnChk;
	@FindBy(xpath = "//input[@type='checkbox']")
	private WebElement chkBox;
	@FindBy(xpath = "//div[@id='cell-2-undefined']")
	private List<WebElement> taskList;	
	@FindBy(xpath = "//button[text()='Submit']")
	private WebElement submitBtn;
	@FindBy(xpath = "//a[text()='Go Back']")
	private WebElement backBtn;
	@FindBy(xpath = "//div[@role='alert']/child::div[2]")
	private WebElement toastMsg;
	

	Actions act;
	ArrayList<String> al;
	LinkedHashMap<String, String> lmp;
	public AdViewReport(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.act = new Actions(driver);
	}
	public ArrayList<String>  getAdViewReportChkUnChkSce(WebDriver driver) throws InterruptedException {
		al=new ArrayList<String>();
		
		al.addAll(Arrays.asList(ChkUnChk.getText(), String.valueOf(chkBox.isSelected())));	
	//	UtilityClass.DrawBorderList(driver, al);
		Thread.sleep(1500);
		return al;
	}
	public String getAdViewReportChkUnChk(WebDriver driver) throws InterruptedException {
		
		UtilityClass.DrawBorder(driver, ChkUnChk);
		Thread.sleep(1500);
		return ChkUnChk.getText();

	}
	public boolean getAdViewReportChkBoxIsSelected(WebDriver driver) throws InterruptedException {
		UtilityClass.DrawBorder(driver, chkBox);
		Thread.sleep(500);
		return chkBox.isSelected();

	}
	public void checkAdViewReportChkBox() throws InterruptedException {
		 chkBox.click();
		 submitBtn.click();
	}
	public void clickAdViewReportChkBox() throws InterruptedException {
		 chkBox.click();
	}
	public void clickAdViewReportSubmitBtn() throws InterruptedException {
		 submitBtn.click();
	}

	public boolean getAdViewReportTitle(WebDriver driver) throws InterruptedException {
		UtilityClass.DrawBorder(driver, SVtitle);
		Thread.sleep(2000);
		return SVtitle.isDisplayed();
	}

	public String getAdViewReportDate() throws ParseException {
		String dateValue = reportDate.getAttribute("value");
		SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");

		// Parse the date string into a Date object
		Date date = inputFormat.parse(dateValue);

		// Define the output format (desired format: DD-MMM-YYYY)
		SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MMM-yyyy");

		// Format the Date object into the desired string format
		String formattedDate = outputFormat.format(date);
		return formattedDate;
	}

	

	public List<String> getAdViewReportUpperInfo() throws ParseException {
		al = new ArrayList<String>();
		al.add(SVtitle.getText());		
		al.add(dept.getAttribute("value"));
		al.add(getAdViewReportDate());
		al.add(ChkUnChk.getText());
		return al;
	}

	public LinkedHashMap<String, String> getAdViewReportTaskAndValue(WebDriver driver) {
		 lmp=new LinkedHashMap<String, String>();
		 for (int i = 1; i <= taskList.size(); i++) {
				String task = driver.findElement(By.xpath("(//div[@class='sc-dhKdPU jFfAhm rdt_TableRow'] )["+i+"]/child::div[2]/div")).getText().trim();
				String value = driver.findElement(By.xpath("(//div[@id='cell-3-undefined'])["+i+"]/child::div")).getText().trim();		
				lmp.put(task, value);
				
			}
			return lmp;
	}	
	
	public void getAdViewReportBackBtn() throws InterruptedException {
		Thread.sleep(500);
		backBtn.click();

	}
	public String getAdViewReportToastMsg(WebDriver driver) {

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
