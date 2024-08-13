package com.HashtagMIS.AdminUC.MA4EmployeeReg;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import LibraryFiles.UtilityClass;

public class AdEmpForm {

	@FindBy(xpath = "//div[@role='option']")
	private List<WebElement> deptLst;
	@FindBy(xpath = "//h2[text()='Add Staff Form']")
	private WebElement efTitle;
	@FindBy(xpath = "//input[@name='name']")
	private WebElement nameInp;
	@FindBy(xpath = "//input[@name='email']")
	private WebElement emailInp;
	@FindBy(xpath = "(//input[@type='text'])[2]")
	private WebElement pwdInp;
	@FindBy(xpath = "//input[@name='role']")
	private WebElement designationInp;
	@FindBy(xpath = "//input[@class='Select__input']")
	private WebElement departmentInp;
	@FindBy(xpath = "//input[@name='shiftStartTime']")
	private WebElement shiftStart;
	@FindBy(xpath = "//input[@name='shiftEndTime']")
	private WebElement shiftEnd;
	@FindBy(xpath = "//input[@type='date']")
	private WebElement dojInp;
	@FindBy(xpath = "//select[@name='access']")
	private WebElement accessSel;
	@FindBy(xpath = "//button[text()='Create']")
	private WebElement createBtn;
	@FindBy(xpath = "//button[text()='Cancel']")
	private WebElement cancelBtn;
	@FindBy(xpath = "//div[@class='errorMark']")
	private WebElement errorMsg;
	@FindBy(xpath = "//div[@class='errorMark']")
	private List<WebElement> errorMsgList;
	@FindBy(xpath = "//div[@role='alert']/child::div[2]")
	private WebElement toastMsg;
	Actions act;

	public AdEmpForm(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.act = new Actions(driver);
	}

	public void getAdEmpFormTitle(WebDriver driver) throws InterruptedException {
		UtilityClass.DrawBorder(driver, efTitle);
		Thread.sleep(500);
		efTitle.isDisplayed();
	}

	public void getAdEmpFormErrorMsg(WebDriver driver) {
		UtilityClass.DrawBorderList(driver, errorMsgList);
		errorMsg.isDisplayed();
	}

	public String getAdEmpFormErrorMsgList(WebDriver driver) {
		try {
			UtilityClass.DrawBorderList(driver, errorMsgList);
			return String.valueOf(errorMsgList.size());
		} catch (org.openqa.selenium.NoSuchElementException e1) {
			e1.printStackTrace();
			return "0";
		}
	}

	public List<String> getAdEmpFormDeptLstInDrpDwn() {
		departmentInp.click();
		ArrayList<String> al = new ArrayList<String>();
		for (WebElement s : deptLst) {
			// System.out.println(s.getText());
			al.add(s.getText());
		}

		return al;

	}

	public void inpAdEmpFormName(String nam) {
		nameInp.sendKeys(nam);
	}

	public void inpAdEmpFormEmail(String emaill) {
		emailInp.sendKeys(emaill);
	}

	public void inpAdEmpFormPwd(String pwd) {
		pwdInp.sendKeys(pwd);
	}

	public void seleAdEmpFormDept(String dept1, String dept2, String dept3, String dept4) {
		departmentInp.sendKeys(dept1);
		act.sendKeys(Keys.ENTER).perform();
		departmentInp.sendKeys(dept2);
		act.sendKeys(Keys.ENTER).perform();
		departmentInp.sendKeys(dept3);
		act.sendKeys(Keys.ENTER).perform();
		departmentInp.sendKeys(dept4);
		act.sendKeys(Keys.ENTER).perform();
	}

	public void inpAddEmpFormDesgni(String dsign) {
		designationInp.sendKeys(dsign);
	}

	public void inpAdEmpFormShiftStart(String start) {
		shiftStart.sendKeys(start);
	}

	public void inpAdEmpFormShiftEnd(String end) {
		shiftEnd.sendKeys(end);
	}

	public String getAdEmpFormShiftStart() {
		return shiftStart.getAttribute("value");
	}

	public String getAdEmpFormShiftEnd() {
		return shiftEnd.getAttribute("value");
	}
	public String getAdEmpFormDate() throws InterruptedException {
		Thread.sleep(200);
		return dojInp.getAttribute("value");
	}
	

	public void inpAdEmpFormDtOJ(String ddmm) throws InterruptedException {
		dojInp.sendKeys(ddmm);
		act.sendKeys(Keys.ARROW_RIGHT).perform();
		act.sendKeys("2024").perform();
	}

	public void selAdEmpFormAccess(String acc) {
		UtilityClass.selectByVisibleTxt(accessSel, acc);
	}

	public void clickAdEmpFormCreateBtn() {
		createBtn.click();
	}

	public void clickAdEmpFormCancelBtn() {
		cancelBtn.click();
	}

	public String getAdEmpFormToastMsg(WebDriver driver) {

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
