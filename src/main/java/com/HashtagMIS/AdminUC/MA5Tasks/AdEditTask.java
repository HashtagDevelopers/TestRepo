package com.HashtagMIS.AdminUC.MA5Tasks;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import LibraryFiles.UtilityClass;

public class AdEditTask {

	@FindBy(xpath = "//h2[contains(text(),'Task')]")
	private WebElement SVtitle;
	@FindBy(xpath = "//tr")
	private List<WebElement> tableRow;
	@FindBy(xpath = "//div[@id='cell-2-undefined']")
	private List<WebElement> taskList;

	@FindBy(xpath = "//div[@id='cell-3-undefined']")
	private List<WebElement> enteredTaskList;
	@FindBy(xpath = "//input[@type='text']")
	private List<WebElement> tableTextInp;
	@FindBy(xpath = "//input[@type='text']")
	private WebElement selDepartment;
	@FindBy(xpath = "//input[@type='date']")
	private WebElement TaskDate;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement submitBtn;
	@FindBy(xpath = "//a[text()='Go Back']")
	private WebElement backBtn;
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

	public AdEditTask(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.act = new Actions(driver);
	}
	public String getAdEditTaskTitle(WebDriver driver) throws InterruptedException {
		UtilityClass.DrawBorder(driver, SVtitle);
		Thread.sleep(2000);
		return SVtitle.getText();
	}
	public String getAdEditTaskDate() {
		return TaskDate.getAttribute("value");

	}
	public String getEditTaskDept() {
		return selDepartment.getAttribute("value");

	}
	public List<String> getEditTaskUpperInfo() throws InterruptedException {
		Thread.sleep(2000);
		al = new ArrayList<String>();
		al.add(TaskDate.getAttribute("value"));
		al.add(selDepartment.getAttribute("value"));
		return al;
	}
	public List<String> getEditTaskTaskList() {
		al = new ArrayList<String>();
		for (WebElement s1 : taskList) {
			al.add(s1.getText().toLowerCase().trim());
		}
		
		return al;
	}
	public List<String> getAdEditTaskEnteredTaskList() {
		al = new ArrayList<String>();
		for (WebElement s1 : enteredTaskList) {
			al.add(s1.getText().trim());
		}
		
		return al;
	}
	public LinkedHashMap<String,String> getAdEditTaskLinkedHashmap() {
		LinkedHashMap<String,String> mp = new LinkedHashMap<>();
		for (int i = 0; i < taskList.size(); i++) {	
			mp.put(taskList.get(i).getText().toLowerCase().trim(),enteredTaskList.get(i).getText().toLowerCase().trim());
		}
		return mp;
	}
	public void getAdEditTaskBackBtn() throws InterruptedException {
		Thread.sleep(2000);
		backBtn.click();

	}
	
}
