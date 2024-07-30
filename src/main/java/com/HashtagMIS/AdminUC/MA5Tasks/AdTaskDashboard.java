package com.HashtagMIS.AdminUC.MA5Tasks;

import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

public class AdTaskDashboard {

	@FindBy(xpath = "//select[@name='department']")
	private WebElement selDepartment;
	@FindBy(xpath = "//a[text()='Task Form']")
	private WebElement taskFormBtn;
	@FindBy(xpath = "//td[1]")
	private List<WebElement> taskLst;
	@FindBy(xpath = "//tbody//tr")
	private List<WebElement> rowLst;
	@FindBy(xpath = "//button[@type='button']")
	private WebElement editBtn;
	@FindBy(xpath = "//button[@type='submit']")
	private WebElement submitBtn;
	@FindBy(xpath = "//button[@type='button']")
	private WebElement deleteBtn;
	@FindBy(xpath = "//div[@role='alert']/child::div[2]")
	private WebElement toastMsg;

	Actions act;
	ArrayList<String> al;

	public AdTaskDashboard(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.act = new Actions(driver);
	}

	public void selAdTaskDashboardDepartmentName(String dept) {
		UtilityClass.selectByVisibleTxt(selDepartment, dept);
	}

	public void clickAdTaskDashboardTaskFormBtn() {
		taskFormBtn.click();
	}

	public List<String> getAdTaskDashboardDeptLstInDrpDwn() throws InterruptedException {
		Thread.sleep(1000);
		return UtilityClass.getAllOptionList(selDepartment);

	}
	public void clickAdTaskDashboardSubmitBtn() {
		submitBtn.click();
	}
	public void dragNDropAdTaskDashboardTask(WebDriver driver) throws InterruptedException {
		
		//for(int i=1;i<rowLst.size()-10;i=i+3) {
		WebElement src = driver.findElement(By.xpath("//tbody//tr[1]"));
		WebElement dest = driver.findElement(By.xpath("//tbody//tr[5]"));
		Thread.sleep(1000);
	///	act.dragAndDrop(src,dest).perform();
		act.clickAndHold(src).perform();
		Thread.sleep(2000);
		act.moveToLocation(0,100).perform();
		Thread.sleep(2000);
		act.release().perform();
		Thread.sleep(2000);
	//	}
	}
	public LinkedHashMap<String, String> getAdTaskDashboardAllTaskAndTaskType(WebDriver driver) throws InterruptedException {
		LinkedHashMap<String, String> lmp = new LinkedHashMap<>();
		Thread.sleep(500);
		for (int i = 1; i <= taskLst.size(); i++) {
			String task = driver.findElement(By.xpath("(//td[1])[" + i + "]")).getText().trim();
			String taskType = driver.findElement(By.xpath("(//td[2])[" + i + "]")).getText().toLowerCase();
			lmp.put(task, taskType);
		}
		/*for (Map.Entry<String, String> entry : lmp.entrySet()) {
            System.out.println("Task Dashboard === Key: " + entry.getKey() + " | Value: " + entry.getValue());
        }*/
		//lmp.put("task", "taskType");
		return lmp;

	}
/*
	public List<String> getAdTaskDashboardTaskInfo(WebDriver driver, String task) {
		al = new ArrayList<String>();
		List<WebElement> infoList = driver
				.findElements(By.xpath("//td[text()='" + task + "']/parent::tr"));
		for (WebElement s1 : infoList) {
			al.add(s1.getText());
		}
		al.remove(al.size() - 1);
		return al;
	}
	public List<String> getAdTaskDashboardTaskList() {
		al = new ArrayList<String>();
		for (WebElement s1 : taskLst) {
			al.add(s1.getText());
		}
		return al;
	}
	public List<String> getAdTaskDashboardTaskTypeList() {
		al = new ArrayList<String>();
		for (WebElement s1 : taskTypeLst) {
			al.add(s1.getText());
		}
		return al;
	}*/
	public void clickAdTaskDashboardEditBtn() {
		editBtn.click();
	}

	public void clickAdTaskDashboardDeleteBtn() {
		deleteBtn.click();
	}

	public String getAdTaskDashboardToastMsg(WebDriver driver) {
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
