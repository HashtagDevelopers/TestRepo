package com.HashtagMIS.AdminUC.MA5Tasks;

import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import LibraryFiles.UtilityClass;
import net.bytebuddy.utility.RandomString;

public class AdAddTask {

	@FindBy(xpath = "//h2[contains(text(),'Task')]")
	private WebElement adTasktitle;
	@FindBy(xpath = "//select[@name='department']")
	private WebElement selDepartment;
	@FindBy(xpath = "//button[text()='Add Task']")
	private WebElement addMoreBtn;
	@FindBy(xpath = "//input[@type='text']")
	private WebElement taskInp;
	@FindBy(xpath = "//select[@aria-label='Select Task type']")
	private WebElement selTaskType;
	@FindBy(xpath = "//button[text()='Add']")
	private WebElement AddBtn;
	@FindBy(xpath = "//button[text()='X']")
	private WebElement deleteTaskBtn;
	@FindBy(xpath = "//a[text()='Go Back']")
	private WebElement backBtn;

	@FindBy(xpath = "//input[@type='text']")
	private List<WebElement> taskList;


	@FindBy(xpath = "//div[@role='alert']/child::div[2]")
	private WebElement toastMsg;
	@FindBy(xpath = "//div[@class='errorMark']")
	private WebElement errorMsg;
	@FindBy(xpath = "//p[@class='errorMark']")
	private List<WebElement> errorMsgList1;

	@FindBy(xpath = "//div[@class='errorMark']")
	private List<WebElement> errorMsgList;

	Actions act;
	ArrayList<String> al;
	LinkedHashMap<String, String> lmp;

	public AdAddTask(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.act = new Actions(driver);
	}

	public String getAdAddTaskTitle(WebDriver driver) throws InterruptedException {
		UtilityClass.DrawBorder(driver, adTasktitle);
		Thread.sleep(2000);
		return adTasktitle.getText();
	}

	public void selAdAddTaskDept(String dept) {
		UtilityClass.selectByVisibleTxt(selDepartment, dept);
	}

	public void clickAdAddTaskAddMoreBtn() throws InterruptedException {
		addMoreBtn.click();
	}

	public void clickAdAddTaskSubmitBtn() throws InterruptedException {
		AddBtn.click();
	}

	public void inpAdAddTaskTaskRandomly(WebDriver driver, String Dept) throws InterruptedException {

		for (int i = 1; i < 21; i++) {
			
			act.sendKeys(Keys.HOME).perform();
			
			addMoreBtn.click();
			
			act.sendKeys(Keys.END).perform();
			
			driver.findElement(By.xpath("(//input[@type='text'])[" + i + "]")).sendKeys(Dept + " Task " + i);
			
			WebElement sel = driver.findElement(By.xpath("(//select[@aria-label='Select Task type'])[" + i + "]"));
			if (i % 2 == 0) {
				UtilityClass.selectByVisibleTxt(sel, "Number");
			} else {
				UtilityClass.selectByVisibleTxt(sel, "Textarea");
			}
			
			act.click().perform();
			
		}
	}

	public void inpAdAddTaskFromSheet(WebDriver driver, String task, String taskType, int i)
			throws InterruptedException {
		act.click().perform();
		Thread.sleep(200);
		act.sendKeys(Keys.HOME).perform();
		Thread.sleep(200);
		addMoreBtn.click();
		Thread.sleep(200);
		act.sendKeys(Keys.END).perform();
		Thread.sleep(200);
		driver.findElement(By.xpath("(//input[@type='text'])[" + i + "]")).sendKeys(task);
		Thread.sleep(200);
		WebElement sel = driver.findElement(By.xpath("(//select[@aria-label='Select Task type'])[" + i + "]"));
		UtilityClass.selectByVisibleTxt(sel, taskType);
		
		
		
	}

	public LinkedHashMap<String, String> getAdAddTaskAllTaskAndTaskType(WebDriver driver) throws InterruptedException {
		act.click().perform();
		act.sendKeys(Keys.END).perform();
		System.out.println("size= "+taskList.size());
		lmp = new LinkedHashMap<>();
		for (int i = 1; i <= taskList.size(); i++) {
			String task = driver.findElement(By.xpath("(//input[@type='text'])[" + i + "]")).getAttribute("value")
					.trim();
			WebElement sel = driver.findElement(By.xpath("(//select[@aria-label='Select Task type'])[" + i + "]"));
			lmp.put(task, UtilityClass.getSelectedOption(sel).toLowerCase());
		}
		/*for (Map.Entry<String, String> entry : lmp.entrySet()) {
            System.out.println("Task Form === Key: " + entry.getKey() + " | Value: " + entry.getValue());
        }*/
		// lmp.put("task234", "taskType123");
		return lmp;

	}

	public void selAdAddTaskTaskType(String taskType) {
		UtilityClass.selectByVisibleTxt(selTaskType, taskType);

	}

	public void clickAdAddTaskBackBtn() throws InterruptedException {
		Thread.sleep(2000);
		backBtn.click();
	}

	public void clickAdAddTaskDeleteeBtn() throws InterruptedException {
		deleteTaskBtn.click();
	}

	public String getAdAddTaskToastMsg(WebDriver driver) {
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
