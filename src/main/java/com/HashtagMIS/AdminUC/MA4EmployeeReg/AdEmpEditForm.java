package com.HashtagMIS.AdminUC.MA4EmployeeReg;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
import LibraryFiles.WaitUtils;

public class AdEmpEditForm {
	@FindBy(xpath = "//p[text()='Update Staff Info']")
	private WebElement seTitle;
	@FindBy(xpath = "//h2[text()='Add Staff Form']")
	private WebElement efTitle;
	@FindBy(xpath = "//input[@name='name']")
	private WebElement nameInp;
	@FindBy(xpath = "//input[@name='email']")
	private WebElement emailInp;
	@FindBy(xpath = "//input[@name='password']")
	private WebElement pwdInp;
	@FindBy(xpath = "//input[@name='role']")
	private WebElement designationInp;
	@FindBy(xpath = "//input[@class='select__input']")
	private WebElement departmentInp;
	@FindBy(xpath = "//div[@class='select__multi-value__label css-1y7rh0y-MultiValueGeneric2']")
	private List<WebElement> departmentLst;
	@FindBy(xpath = "//div[contains(@class,'select__value-container')]")
	private WebElement departmentContainerInp;
	@FindBy(xpath = "//div[contains(@class,'select__multi-value')]")
	private List<WebElement> departmentMultiValue;

	@FindBy(xpath = "//input[@name='shiftStartTime']")
	private WebElement shiftStart;
	@FindBy(xpath = "//input[@name='shiftEndTime']")
	private WebElement shiftEnd;
	@FindBy(xpath = "//input[@type='date']")
	private WebElement dojInp;
	@FindBy(xpath = "//select[@name='access']")
	private WebElement accessSel;

	@FindBy(xpath = "//button[text()='Update']")
	private WebElement updateBtn;
	@FindBy(xpath = "//button[text()='Cancel']")
	private WebElement cancelBtn;
	@FindBy(xpath = "//div[@class='errorMark']")
	private WebElement errorMsg;
	@FindBy(xpath = "//div[@class='errorMark']")
	private List<WebElement> errorMsgList;
	@FindBy(xpath = "//div[@role='alert']/child::div[2]")
	private WebElement toastMsg;

	Actions act;
	private WaitUtils waitUtils;

	public AdEmpEditForm(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.act = new Actions(driver);
	}

	public void getAdEmpEditFormTitle(WebDriver driver) throws InterruptedException {
		UtilityClass.DrawBorder(driver, seTitle);
		Thread.sleep(500);
		seTitle.isDisplayed();
	}

	public void getAdEmpEditErrorMsg(WebDriver driver) {
		UtilityClass.DrawBorderList(driver, errorMsgList);
		errorMsg.isDisplayed();
	}

	public String getAdEmpEditErrorMsgList(WebDriver driver) {
		try {
			UtilityClass.DrawBorderList(driver, errorMsgList);
			return String.valueOf(errorMsgList.size());
		} catch (org.openqa.selenium.NoSuchElementException e1) {
			e1.printStackTrace();
			return "0";
		}

	}

	public List<String> getAdEmpEditFormData() throws InterruptedException {
		ArrayList<String> ar = new ArrayList<String>();
		ar.add(getAdEmpEditFormName());
		ar.add(getAdEmpEditFormEmail());
		ar.add(getAdEmpEditFormPwD());
		ar.add(getAdEmpEditFormAccess());
		for (WebElement d : departmentLst) {
			ar.add(d.getText());
		}
		ar.add(getAdEmpEditFormDesgni());
		ar.add(getAdEmpEditFormShiftStart());
		ar.add(getAdEmpEditFormShiftEnd());
		ar.add(getAdEmpEditFormDoJ());

		Collections.sort(ar);
		return ar;
	}

	public void inpAdEmpEditFormName(String nam) throws InterruptedException {
		nameInp.clear();
		nameInp.sendKeys(nam);
	}

	public void inpAdEmpEditFormEmail(String emaill) throws InterruptedException {

		emailInp.clear();
		emailInp.sendKeys(emaill);
	}

	public void inpAdEmpEditFormPwd(String pwd) {
		pwdInp.clear();
		pwdInp.sendKeys(pwd);
	}

	public void inpAdEmpEditFormDesgni(String dsign) {
		designationInp.clear();
		designationInp.sendKeys(dsign);
	}

	public void selAdEmpEditFormAccess(String access) {

		UtilityClass.selectByVisibleTxt(accessSel, access);
	}

	public void inpEmpEditFormDept(String dept1, String dept2, String dept3, String dept4) throws InterruptedException {
		System.out.println("sixsfsf " + departmentMultiValue.size());
		System.out.println("sixsfsf " + departmentMultiValue.isEmpty());
		while (!departmentMultiValue.isEmpty()) {
			departmentInp.click();
			act.sendKeys(Keys.BACK_SPACE).perform();
			Thread.sleep(500);
		}
		Thread.sleep(500);
		departmentInp.sendKeys(dept1);
		act.sendKeys(Keys.ENTER).perform();
		if (!dept2.isBlank()) {
			departmentInp.sendKeys(dept2);
			act.sendKeys(Keys.ENTER).perform();
		}
		if (!dept3.isBlank()) {
			departmentInp.sendKeys(dept3);
			act.sendKeys(Keys.ENTER).perform();
		}
		if (!dept4.isBlank()) {
			departmentInp.sendKeys(dept4);
			act.sendKeys(Keys.ENTER).perform();
		}
	}

	public void inpAdEmpEditFormShiftStart(String start) {
		shiftStart.sendKeys(start);
	}

	public void inpAdEmpEditFormShiftEnd(String end) {
		shiftEnd.sendKeys(end);
	}

	public void inpAdEmpEditFormDoJ(String ddmm) {
		dojInp.sendKeys(ddmm);
		act.sendKeys(Keys.ARROW_RIGHT).perform();
		act.sendKeys("2024").perform();
	}

	public void clickAdEmpEditFormUpdateBtn() {

		updateBtn.click();
	}

	public void clickAdEmpEditFormCancelBtn() {
		cancelBtn.click();
	}

	public String getAdEmpEditFormToastMsg(WebDriver driver) {
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

	public String getAdEmpEditFormName() throws InterruptedException {
		Thread.sleep(1000);
		return nameInp.getAttribute("value");
	}

	public String getAdEmpEditFormEmail() {
		return emailInp.getAttribute("value");
	}

	public String getAdEmpEditFormPwD() {
		return pwdInp.getAttribute("value");
	}

	public String getAdEmpEditFormDesgni() {
		return designationInp.getAttribute("value");
	}

	public String getAdEmpEditFormAccess() {

		return UtilityClass.getSelectedOption(accessSel);
	}

	public String getAdEmpEditFormShiftStart() {
		return shiftStart.getAttribute("value");
	}

	public String getAdEmpEditFormShiftEnd() {
		return shiftEnd.getAttribute("value");
	}

	public String getAdEmpEditFormDoJ() {
		return dojInp.getAttribute("value");
	}
}
