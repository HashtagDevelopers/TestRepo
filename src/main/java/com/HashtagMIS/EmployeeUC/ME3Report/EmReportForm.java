package com.HashtagMIS.EmployeeUC.ME3Report;

import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

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

public class EmReportForm {

	@FindBy(xpath = "//p[text()='Add Report Info']")
	private WebElement RFtitle;
	@FindBy(xpath = "//tr")
	private List<WebElement> tableRow;
	@FindBy(xpath = "//td[1]")
	private List<WebElement> taskLst;

	@FindBy(xpath = "//input[@type='number']")
	private List<WebElement> tableNumberInp;
	@FindBy(xpath = "//input")
	private List<WebElement> placeholder;
	@FindBy(xpath = "//textarea")
	private List<WebElement> placeholderText;
	@FindBy(xpath = "//textarea/ancestor::tr/td[2]")
	private List<WebElement> StringTask;
	@FindBy(xpath = "//textarea")
	private List<WebElement> tableTextInp;
	@FindBy(xpath = "//select[@name='department']")
	private WebElement selDepartment;
	@FindBy(xpath = "//input[@type='date']")
	private WebElement Date;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement submitBtn;
	@FindBy(xpath = "//button[@type='button']")
	private WebElement cancel;
	@FindBy(xpath = "//div[@role='alert']/child::div[2]")
	private WebElement toastMsg;
	@FindBy(xpath = "//div[@class='errorMark']")
	private WebElement errorMsg;
	@FindBy(xpath = "//p[@class='errorMark']")
	private List<WebElement> errorMsgList1;

	@FindBy(xpath = "//div[@class='errorMark']")
	private List<WebElement> errorMsgList;

	@FindBy(xpath = "//td/child::input")
	private List<WebElement> allNumericTask;
	@FindBy(xpath = "//td/child::textarea")
	private List<WebElement> allTextTask;
	@FindBy(xpath = "//button[text()='Yes, submit it!']")
	private WebElement yesBtnInASPU;
	LinkedHashMap<String, String> lmp;
	Actions act;
	ArrayList<String> al;

	public EmReportForm(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.act = new Actions(driver);
	}

	public void selEmReportPageDepartmentName(WebDriver driver, String depart) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait for the select element to be visible and enabled
        WebElement selectElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@name='department']")));
        wait.until(ExpectedConditions.elementToBeClickable(selectElement));

        // Ensure the select element is enabled before interacting with it
        if (selectElement.isEnabled()) {
        	UtilityClass.selectByVisibleTxt(selDepartment, depart);
        } else {
        //    throw new UnsupportedOperationException("The select element is disabled");
        }

	}

	public LinkedHashMap<String, String> getEmReportFormTaskAndPlaceholder(WebDriver driver) {
		lmp = new LinkedHashMap<String, String>();
		for (int i = 1; i <= taskLst.size(); i++) {
			String task = driver.findElement(By.xpath("(//td[2])[" + i + "]")).getText().trim();
			WebElement parentEle = driver.findElement(By.xpath("(//td[2])[" + i + "]/ancestor::tr/td[3]"));
			WebElement childEle = parentEle.findElement(By.xpath(".//*"));	
			lmp.put(task, childEle.getAttribute("placeholder"));
			
		}

		return lmp;

	}

	public LinkedHashMap<String, String> getEmReportFormTaskAndValue(WebDriver driver) {
		lmp = new LinkedHashMap<String, String>();
		for (int i = 1; i <= taskLst.size(); i++) {
			String task = driver.findElement(By.xpath("(//td[2])[" + i + "]")).getText().trim();
			WebElement parentEle = driver.findElement(By.xpath("(//td[2])[" + i + "]/ancestor::tr/td[3]"));
			WebElement childEle = parentEle.findElement(By.xpath(".//*"));
			lmp.put(task, childEle.getAttribute("value"));

		}
//			for (Entry<String, String> entry : lmp.entrySet()) {
//	            System.out.println("Task Dashboard === Key: " + entry.getKey() + " | Value: " + entry.getValue());
//	        }
		// lmp.put("task", "taskType");
		return lmp;

	}

	public void inpEmReportPageDate(String dd,String mm) throws InterruptedException {
		act.sendKeys(Keys.TAB).perform();
		Thread.sleep(300);
		act.sendKeys(Keys.TAB).perform();
		Thread.sleep(300);
		act.sendKeys(mm).perform();
		Thread.sleep(300);
		act.sendKeys(Keys.ARROW_LEFT).perform();
		Thread.sleep(300);
		act.sendKeys(dd).perform();
		Thread.sleep(300);
	}
	public void inpEmReportPageDateForMultipleForm(String dt) throws InterruptedException {
		Date.sendKeys(dt);
		Thread.sleep(300);
		act.sendKeys(Keys.ARROW_RIGHT).perform();
		Thread.sleep(300);
		act.sendKeys(Keys.ARROW_DOWN).perform();
		Thread.sleep(300);
		act.sendKeys(Keys.ARROW_LEFT).perform();
		Thread.sleep(300);
		Date.sendKeys(dt);
		Thread.sleep(300);
	}

	public void inpEmReportFormAllTask(String Dept) {
		int row = tableNumberInp.size();
		int num = 501;
		for (WebElement s1 : tableNumberInp) {
			s1.sendKeys(String.valueOf(num));
			num = num + 1;
		}
		if (StringTask.size() == tableTextInp.size()) {
			for (int i = 0; i < StringTask.size(); i++) {
				WebElement t1 = StringTask.get(i);
				WebElement s1 = tableTextInp.get(i);
				s1.sendKeys(Dept + " " + t1.getText() + " Long calls duration-"
						+ "1. 32 m 56 s (Call to HI Wembley. Their main number was not working, Followed vonage troubleshooting"
						+ "2. 32 m 56 s (Call to HI Wembley. Their main number was not working, Followed vonage troubleshooting");
			}
		}
		
	}

	public void inpEmReportForm5Task() {
		int num = 401;
		for (int i = 0; i < 5; i++) {
			tableNumberInp.get(i).sendKeys(String.valueOf(num));
			num = num + 1;
		}
		if (StringTask.size() == tableTextInp.size()) {
			for (int i = 0; i < StringTask.size(); i++) {
				WebElement t1 = StringTask.get(i);
				WebElement s1 = tableTextInp.get(i);
				s1.sendKeys(t1.getText());
			}
		}
	
		
	}

	public void clickEmReportPageAreYouSureOKBtn() {
		yesBtnInASPU.click();
	}

	public void clickEmReportPageSubmitBtn() {

		submitBtn.click();
		
	}

	public void clickEmReportFormPageCancelBtn() {
		cancel.click();
	}

	public String getEmReportFormToastMsg(WebDriver driver) {

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
