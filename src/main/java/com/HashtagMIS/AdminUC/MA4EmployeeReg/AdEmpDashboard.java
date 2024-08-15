package com.HashtagMIS.AdminUC.MA4EmployeeReg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import LibraryFiles.UtilityClass;

public class AdEmpDashboard {

	@FindBy(xpath = "//select[@name='department']")
	private WebElement deptLst;
	@FindBy(xpath = "//select[@name='staffNames']")
	private WebElement EmpLst;
	@FindBy(xpath = "//a[text()='Add Employee']")
	private WebElement addEmpBtn;
	@FindBy(xpath = "//div[@id='cell-1-undefined']")
	private List<WebElement> totalEmp;

	@FindBy(xpath = "(//button[@type='button'])[2]")
	private WebElement edit;
	@FindBy(xpath = "(//button[@type='button'])[3]")
	private WebElement delete;
	@FindBy(xpath = "//button[text()='Yes, delete it!']")
	private WebElement AYSOK;
	@FindBy(xpath = "//button[text()='OK']")
	private WebElement OKPOP;

	public AdEmpDashboard(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void clickAdEmpDashboardAddEmpBtn() {
		addEmpBtn.click();
	}

	public List<String> getAdEmpDashboardDeptLstInDrpDwn() {
		return UtilityClass.getAllOptionList(deptLst);

	}

	public List<String> getAdEmpDashboardEmpLstInDrpDwn() {
		return UtilityClass.getAllOptionList(EmpLst);
	}

	public int getAdEmpDashboardTotalEmployee() {
		return Integer.valueOf(totalEmp.size());
	}

	public ArrayList<String> getAdEmpDashboardEmpDataForName(WebDriver driver, String Name) {
		ArrayList<String> ar = new ArrayList<String>();
		List<WebElement> wb = driver.findElements(By.xpath("//div[text()='" + Name + "']/ancestor::div[2]/div"));
		for (WebElement s : wb) {
			if (!s.getText().trim().equals("")) {
				ar.add(s.getText());
			}
		}
		ArrayList<String> al = new ArrayList<String>();
		String st = ar.toString();
		String str = st.substring(1, st.length() - 1);
		String[] t = str.split(",");
		for (int i = 0; i < t.length; i++) {
			al.add(t[i].trim());
		}
		Collections.sort(al);
		return al;

	}

	public void clickAdEmpDashboardEditBtnRandomly() {
		edit.click();
	}

	public void clickAdEmpDashboardEditBtnForName(WebDriver driver, String name) {
		driver.findElement(By.xpath("(//div[text()='" + name + "']/ancestor::div[2]/div)[5]/div/button[1]")).click();
	}

	public void clickAdEmpDashboardDeleteBtn() {
		delete.click();
		AYSOK.click();
		OKPOP.click();
	}
}
