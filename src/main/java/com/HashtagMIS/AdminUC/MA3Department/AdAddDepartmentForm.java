package com.HashtagMIS.AdminUC.MA3Department;


import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import LibraryFiles.UtilityClass;

public class AdAddDepartmentForm {
	@FindBy(xpath = "//label[text()='Enter Department']")
	private WebElement deptTitle;
	@FindBy(xpath = "//label[text()='Enter Department']/following-sibling::input")
	private WebElement departmentInp;
	@FindBy(xpath = "//div[@id='cell-2-undefined']")
	private List<WebElement> departmentList;
	@FindBy(xpath = "//button[text()='Add']")
	private WebElement addDeptBtn;
	@FindBy(xpath = "(//button[@type='button'])[2]")
	private WebElement editBtn;
	@FindBy(xpath = "//button[text()='Cancel']")
	private WebElement cancelBtn;
	@FindBy(xpath = "//button[text()='OK']")
	private WebElement okBtn;
	@FindBy(xpath = "//div[@class='errorMark']")
	private WebElement errorMsg;
	@FindBy(xpath = "//div[@class='errorMark']")
	private List<WebElement> errorMsgList;
	@FindBy(xpath = "//div[@role='alert']/child::div[2]")
	private WebElement toastMsg;
	
	Actions act;

	public AdAddDepartmentForm(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.act = new Actions(driver);
	}

	public void getAdAddDepartmentFormTitle(WebDriver driver) throws InterruptedException {
		UtilityClass.DrawBorder(driver, deptTitle);
		Thread.sleep(500);
		deptTitle.isDisplayed();
	}

	public List<String> getAdAddDepartmentFormDeptList() {
		ArrayList<String> al = new ArrayList<String>();
		for (WebElement d : departmentList) {
			al.add(d.getText());
		}
		return al;
	}

	public void getAdAddDepartmentFormErrorMsg(WebDriver driver) {
		UtilityClass.DrawBorderList(driver, errorMsgList);
		errorMsg.isDisplayed();
	}

	public String getAdAddDepartmentFormErrorMsgList(WebDriver driver) {
		try {
			UtilityClass.DrawBorderList(driver, errorMsgList);
			return String.valueOf(errorMsgList.size());
		} catch (org.openqa.selenium.NoSuchElementException e1) {
			e1.printStackTrace();
			return "0";
		}
	}

	public void inpAdAddDepartmentFormDept(String dept) {
		departmentInp.clear();
		departmentInp.sendKeys(dept);
	}

	public void clickAdAddDepartmentFormAddBtn(WebDriver driver) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", addDeptBtn);	//addDeptBtn.click();
	}
	public void clickAdAddDepartmentFormOkBtn(WebDriver driver) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", okBtn);	//addDeptBtn.click();
	}
	public WebElement rtnAdAddDepartmentFormAddBtn() {
		return addDeptBtn;
	}

	public void clickAdAddDepartmentFormEditBtn() {
		editBtn.click();
	}

	public void clickAdAddDepartmentFormCancelBtn() {
		cancelBtn.click();
	}

	public String getAdAddDepartmentFormToastMsg(WebDriver driver) {

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
	public WebElement rtnAdAddDepartmentFormToastMsg() {

		return toastMsg;
	}

}
