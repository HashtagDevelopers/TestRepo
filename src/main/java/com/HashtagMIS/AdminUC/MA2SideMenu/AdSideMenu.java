package com.HashtagMIS.AdminUC.MA2SideMenu;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdSideMenu {
	@FindBy(xpath = "//span[contains(text(),'Employee')]")//div[text()='Add Staff']/parent::a
	private WebElement empBtn;
	@FindBy(xpath = "//span[contains(text(),'Department')]")
	private WebElement departmentBtn;
	@FindBy(xpath = "//span[contains(text(),'Task')]")
	private WebElement taskBtn;
	@FindBy(xpath = "//span[contains(text(),'Reports')]")
	private WebElement reportsBtn;
	@FindBy(xpath = "//span[contains(text(),'Logout')]")
	private WebElement logoutBtn;
	public AdSideMenu(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	public void clickAdSideMenuEmpBtn() {
		empBtn.click();
	}
	public void clickAdSideMenuDepartmentBtn() {
		departmentBtn.click();
	}
	public void clickAdSideMenuReportsBtn() {
		reportsBtn.click();
	}
	public void clickAdSideMenuTasksBtn() {
		taskBtn.click();
	}
	
	public void clickAdSideMenuLogoutBtn() {
		logoutBtn.click();
	}
}
