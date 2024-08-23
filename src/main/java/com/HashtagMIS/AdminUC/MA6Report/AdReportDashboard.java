package com.HashtagMIS.AdminUC.MA6Report;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import LibraryFiles.UtilityClass;
import LibraryFiles.WaitUtils;

public class AdReportDashboard {

	@FindBy(xpath = "//tr")
	private List<WebElement> tableRow;
	@FindBy(xpath = "//div[@id='cell-2-undefined']")
	private List<WebElement> taskList;
	@FindBy(xpath = "//input[@type='text']/ancestor::tr/td[1]")
	private List<WebElement> Stringtask;
	@FindBy(xpath = "//input[@type='text']")
	private List<WebElement> tableTextInp;
	@FindBy(xpath = "//div[text()='Select Department...']")
	private WebElement DepartmentSel;
	@FindBy(xpath = "//input[@type='text']")
	private WebElement DepartmentInp;
	@FindBy(xpath = "//select[@name='staffNames']")
	private WebElement selStaff;
	@FindBy(xpath = "//input[@type='date']")
	private WebElement startDate;
	@FindBy(xpath = "//input[@type='date']")
	private WebElement endDate;
	@FindBy(xpath = "//button[@type='submit']")
	private WebElement viewBtn;
	@FindBy(xpath = "(//button[@type='button'])[3]")
	private WebElement editBtn;
	@FindBy(xpath = "//button[@type='button']")
	private WebElement deleteBtn;
	@FindBy(xpath = "//div[@role='alert']/child::div[2]")
	private WebElement toastMsg;

	Actions act;
	ArrayList<String> al;
	WebDriverWait wait;
	public AdReportDashboard(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.act = new Actions(driver);
		this.wait = new WebDriverWait(driver,Duration.ofSeconds(40));
	}

	public List<String> getAdReportDashboardDeptLstInDrpDwn() {
		return UtilityClass.getAllOptionList(DepartmentSel);

	}

	public void inpAdReportDashboardDepartmentName(String depart) throws InterruptedException {
		DepartmentInp.click();
		Thread.sleep(100);
		act.sendKeys(depart).sendKeys(Keys.ENTER).perform();

	}

	public void selAdReportDashboardDepartmentStaff(String sName) {
		UtilityClass.selectByVisibleTxt(selStaff, sName);
	}

	public void inpAdReportDashboardStartDate(String dt) {
		startDate.sendKeys(dt);
		act.sendKeys(Keys.ARROW_RIGHT).perform();
		act.sendKeys("2024").perform();
	}

	public List<String> getAdReportDashboardReportInfoList(WebDriver driver, String datetime) {
		al = new ArrayList<String>();
		List<WebElement> infoList = driver
				.findElements(By.xpath("//div[text()='" + datetime + "']/ancestor::div[2]/div"));

		for (WebElement s1 : infoList) {
			al.add(s1.getText());
		}
		al.removeIf(String::isBlank);
		return al;
	}

	public void clickAdReportDashboardViewBtnForDateTime(WebDriver driver, String datetime) throws InterruptedException {	
		WebElement wb =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='" + datetime + "']/ancestor::div[2]/descendant::button[1]")));
		wait.until(ExpectedConditions.elementToBeClickable(wb));
		wb.click();
	}

	public void clickAdReportDashboardEditBtnForDateTime(WebDriver driver, String datetime) {
		WebElement wb =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='" + datetime + "']/ancestor::div[2]/descendant::button[2]")));
		wait.until(ExpectedConditions.elementToBeClickable(wb));
		wb.click();
	}

	public void clickAdReportDashboardDeleteBtnForDateTime(WebDriver driver, String datetime) {
		WebElement wb =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='" + datetime + "']/ancestor::div[2]/descendant::button[3]")));
		wait.until(ExpectedConditions.elementToBeClickable(wb));
		wb.click();
	
	}

	public String getAdReportDashboardCurrentReportStatus(String depart, String sName, WebDriver driver,
			String datetime) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		// Wait for the select element to be visible and enabled
		WebElement inpElementDept = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='text']")));
		wait.until(ExpectedConditions.elementToBeClickable(inpElementDept));
		WebElement selectElementStaff = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@name='staffNames']")));
		wait.until(ExpectedConditions.elementToBeClickable(selectElementStaff));
		// Ensure the select element is enabled before interacting with it
		if ( selectElementStaff.isEnabled()) {
			inpAdReportDashboardDepartmentName(depart);
			UtilityClass.selectByVisibleTxt(selectElementStaff, sName);
		} else {
			throw new UnsupportedOperationException("The select element is disabled");
		}
		WebElement wb = driver
				.findElement(By.xpath("//div[text()='" + datetime + "']/ancestor::div[2]/descendant::div[9]/span"));
		UtilityClass.DrawBorder(driver, wb);
		Thread.sleep(1500);
		return wb.getText();
	}

	public void clickAdReportDashboardViewBtn() {
		viewBtn.click();
	}

	public void clickAdAdReportDashboardEditBtn() {
		editBtn.click();
	}

	public void clickAdAdReportDashboardDeleteBtn() {
		deleteBtn.click();
	}

	public String getAdAdReportDashboardToastMsg(WebDriver driver) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			wait.until(expectedConditions -> toastMsg.isDisplayed()); // Lambda for expected condition
			UtilityClass.DrawBorder(driver, toastMsg);
			return toastMsg.getText();
		} catch (TimeoutException e) {
			// Handle timeout exception gracefully (e.g., log, retry, throw custom
			// exception)
			System.out.println("Warning: Toast message element not found within " + "5" + " seconds.");
			return "null"; // Or throw a custom exception if desired
		}
	}
}
