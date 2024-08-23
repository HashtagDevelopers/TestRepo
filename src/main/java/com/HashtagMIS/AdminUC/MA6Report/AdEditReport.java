package com.HashtagMIS.AdminUC.MA6Report;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v123.indexeddb.model.Key;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import LibraryFiles.UtilityClass;

public class AdEditReport {

	
	@FindBy(xpath = "//div[@id='cell-2-undefined']")
	private List<WebElement> taskList;
	@FindBy(xpath = "//textarea/ancestor::div[2]/child::div[2]")
	private List<WebElement> StringTask;
	@FindBy(xpath = "//textarea")
	private List<WebElement> tableTextInp;
	@FindBy(xpath = "(//input[@type='text'])[1]")
	private WebElement name;
	@FindBy(xpath = "(//input[@type='text'])[2]")
	private WebElement selDepartment;
	@FindBy(xpath = "//input[@type='date']")
	private WebElement reportDate;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement updateBtn;
	@FindBy(xpath = "//a[text()='Go Back']")
	private WebElement backBtn;
	@FindBy(xpath = "//div[@role='alert']/child::div[2]")
	private WebElement toastMsg;
	@FindBy(xpath = "//div[@class='errorMark']")
	private WebElement errorMsg;
	@FindBy(xpath = "//div[@class='errorMark']")
	private List<WebElement> errorMsgList;

	Actions act;
	ArrayList<String> al;
	LinkedHashMap<String, String> lmp;

	public AdEditReport(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.act = new Actions(driver);
	}

	

	public String getAdEditReportDate() throws ParseException {
		
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
	public void inpAdEditReportDate(String dd,String mm,String yyyy) {
		reportDate.sendKeys(dd+mm);
		act.sendKeys(Keys.ARROW_RIGHT).perform();
		act.sendKeys(yyyy).perform();
	}

	
	
	public String getAdEditReportDept() {
		return selDepartment.getAttribute("value");

	}

	public List<String> getAdEditReportUpperInfo() throws InterruptedException, ParseException {
		Thread.sleep(2000);
		al = new ArrayList<String>();
		al.add(name.getAttribute("value"));
		al.add(getAdEditReportDept());
		al.add(getAdEditReportDate());	
		al.add("Unchecked");
		return al;
	}
	
	public LinkedHashMap<String, String> getAdEditReportTaskAndValue(WebDriver driver) {
		lmp = new LinkedHashMap<String, String>();
		for (int i = 1; i <= taskList.size(); i++) {
			String task = driver
					.findElement(
							By.xpath("(//div[@id='cell-2-undefined'])[" + i + "]/child::div"))
					.getText().trim();
			String value = driver.findElement(By.xpath("(//div[@id='cell-3-undefined'])["+i+"]/*"))
					.getText().trim();
			lmp.put(task, value);

		}
		return lmp;
	}

	public void clickAdEditReportBackBtn() throws InterruptedException {
		
		backBtn.click();

	}

	public void clickAdEditReportUpdateBtn() throws InterruptedException {

		updateBtn.click();

	}

	public void inpAdEditReportAllTask(WebDriver driver,String dept) throws InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Thread.sleep(3000);
		for(int i=1;i<tableTextInp.size()+1; i++) {
			// Wait for the textarea to be visible and clickable
			WebElement textarea = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//textarea)["+i+"]")));
			wait.until(ExpectedConditions.elementToBeClickable(textarea));	
			textarea.clear();
			// Optional: Wait to ensure the textarea is cleared
			wait.until(ExpectedConditions.textToBePresentInElementValue(textarea, ""));
			
			String newValue = dept + " " + StringTask.get(i-1).getText();
			js.executeScript("arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('input', { bubbles: true }));", 
	                  textarea, newValue);
			textarea.sendKeys(" ");
		}
	}
	public String getAdEditReportToastMsg(WebDriver driver) {

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
