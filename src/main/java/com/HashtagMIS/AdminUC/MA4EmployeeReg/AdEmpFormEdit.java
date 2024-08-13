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

public class AdEmpFormEdit {
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
	@FindBy(xpath = "//div[@class='select__multi-value__label css-1y7rh0y-MultiValueGeneric2']")
	private List<WebElement>  departmentLst;
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

	public AdEmpFormEdit(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.act = new Actions(driver);
	}

	public void getAdEmpFormEditTitle(WebDriver driver) throws InterruptedException {
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

	public List<String> getAdEmpFormEditData() throws InterruptedException {
		ArrayList<String> ar = new ArrayList<String>();
		
		ar.add(getAdEmpFormEditName());
		ar.add(getAdEmpFormEditEmail());
		ar.add(getAdEmpFormEditPwD());
		for(WebElement d:departmentLst) {
			ar.add(d.getText());
		}
		ar.add(getAdEmpFormEditDesgni());
		ar.add(getAdEmpFormEditShiftStart());
		ar.add(getAdEmpFormEditShiftEnd());
		ar.add(getAdEmpFormEditDoJ());
		ar.add(getAdEmpFormEditAccess());
		Collections.sort(ar);
		return ar;
	}

	public void inpAdEmpFormEditName(String nam) throws InterruptedException {
		nameInp.clear();
		nameInp.sendKeys(nam);
	}

	public void inpAdEmpFormEditEmail(String emaill) throws InterruptedException {

		emailInp.clear();
		emailInp.sendKeys(emaill);
	}

	public void inpAdEmpFormEditPwd(String pwd) {
		pwdInp.clear();
		pwdInp.sendKeys(pwd);
	}

	public void inpAdEmpFormEditDesgni(String dsign) {
		designationInp.clear();
		designationInp.sendKeys(dsign);
	}

	//public void seleEmpFormEditDept(String dept) {
	//	UtilityClass.selectByName(department, dept);
	//}

	public void inpAdEmpFormEditShiftStart(String start) {
		shiftStart.sendKeys(start);
	}

	public void inpAdEmpFormEditShiftEnd(String end) {
		shiftEnd.sendKeys(end);
	}

	public void inpAdEmpFormEditDoJ(String ddmm) {		
		dojInp.sendKeys(ddmm);
		act.sendKeys(Keys.ARROW_RIGHT).perform();
		act.sendKeys("2024").perform();
	}

	public void clickAdEmpFormEditUpdateBtn() {

		updateBtn.click();
	}

	public void clickAdEmpFormEditCancelBtn() {
		cancelBtn.click();
	}

	public String getAdEmpFormEditToastMsg(WebDriver driver) {
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

	public String getAdEmpFormEditName() throws InterruptedException {
		Thread.sleep(1000);
		return nameInp.getAttribute("value");
	}

	public String getAdEmpFormEditEmail() {
		return emailInp.getAttribute("value");
	}

	public String getAdEmpFormEditPwD() {
		return pwdInp.getAttribute("value");
	}

	public String getAdEmpFormEditDesgni() {
		return designationInp.getAttribute("value");
	}

	public  String getAdEmpFormEditAccess() {
		
		return UtilityClass.getSelectedOption(accessSel);
		
	
	}

	public String getAdEmpFormEditShiftStart() {
		return shiftStart.getAttribute("value");
		
	}

	public String getAdEmpFormEditShiftEnd() {
		return shiftEnd.getAttribute("value");
	}

	public String getAdEmpFormEditDoJ() {
		return dojInp.getAttribute("value");
	}
}
