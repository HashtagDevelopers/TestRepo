package com.HashtagMIS.AdminUC.MA3Department;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import LibraryFiles.UtilityClass;

public class AdEditDepartmentForm {
	@FindBy(xpath = "//label[text()='Enter Department']")
	private WebElement deptTitle;
	@FindBy(xpath = "//h2[text()='Update Department']/following-sibling::input")
	private WebElement departmentInp;
	@FindBy(xpath = "//button[text()='Yes, delete it!']")
	private WebElement YesBtn;
	@FindBy(xpath = "//button[text()='OK']")
	private WebElement okBtn;
	@FindBy(xpath = "//button[text()='Cancel']")
	private WebElement cancelBtn;
	@FindBy(xpath = "//div[@class='errorMark']")
	private WebElement errorMsg;
	@FindBy(xpath = "//div[@class='errorMark']")
	private List<WebElement> errorMsgList;
	@FindBy(xpath = "//div[@role='alert']/child::div[2]")
	private WebElement toastMsg;
	@FindBy(xpath = "//button[@id='pagination-next-page']")
	private WebElement nextBtn;
	Actions act;
ArrayList<String> al= new ArrayList<String>();
	public AdEditDepartmentForm(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.act = new Actions(driver);
	}

	public void getAdEditDepartmentFormTitle(WebDriver driver) throws InterruptedException {
		UtilityClass.DrawBorder(driver, deptTitle);
		Thread.sleep(500);
		deptTitle.isDisplayed();
	}

	public void getAdEditDepartmentFormErrorMsg(WebDriver driver) {
		UtilityClass.DrawBorderList(driver, errorMsgList);
		errorMsg.isDisplayed();
	}

	public String getAdEditDepartmentFormErrorMsgList(WebDriver driver) {
		try {
			UtilityClass.DrawBorderList(driver, errorMsgList);
			return String.valueOf(errorMsgList.size());
		} catch (org.openqa.selenium.NoSuchElementException e1) {
			e1.printStackTrace();
			return "0";
		}
	}

	public void deleteAdEditDepartmentFormDept(WebDriver driver) {
		al.addAll(Arrays.asList("3D Design", "Accounts", "Care Support", "Data Analyst", "E-Commerce", "Food",
				"Game Development", "Graphic design", "Incident", "Info", "IT", "Laundry", "Maintenance", "MPR",
				"Procurement", "Project Management", "Security", "Software Development", "System Admin", "Training",
				"Video Editing"));
		do {
			for (int i = 1; i < 11; i++) {

				String task = driver.findElement(By.xpath("(//div[@id='cell-1-undefined']/div)[" + i + "]")).getText();
				if (!al.contains(task)) {
					driver.findElement(By.xpath(
							"//div[text()=\'" + task + "\']/parent::div/following-sibling::div/descendant::button[2]"))
							.click();
					i--;
					YesBtn.click();
					okBtn.click();
				}

			}
			nextBtn.click();
		} while (nextBtn.getAttribute("aria-disabled").equals("false"));
	}

	public void inpAdEditDepartmentFormDept(String dept) {
		departmentInp.clear();
		departmentInp.sendKeys(dept);
	}

	public void clickAdEditDepartmentFormOkBtn(WebDriver driver) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", okBtn); // addDeptBtn.click();
	}

	public WebElement rtnEditDepartmentFormOkBtn() {
		return okBtn;
	}

	public void clickAdEditDepartmentFormCancelBtn() {
		cancelBtn.click();
	}

	public String getAdEditDepartmentFormToastMsg(WebDriver driver) {

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
