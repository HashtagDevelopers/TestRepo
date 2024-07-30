package com.HashtagMIS.EmployeeUC.ME2sideMenubar;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EmSideMenu {
	@FindBy(xpath = "//span[contains(text(),'Daily Report')]")//div[text()='Add Staff']/parent::a
	private WebElement dailyReportBtn;
	@FindBy(xpath = "//span[contains(text(),'History')]")
	private WebElement historyBtn;
	@FindBy(xpath = "//span[contains(text(),'Team Reports')]")
	private WebElement teamReportBtn;
	@FindBy(xpath = "//span[contains(text(),'Profile')]")
	private WebElement profileBtn;
	public EmSideMenu(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	public void clickEmSideMenuDailyReportBtn() {
		dailyReportBtn.click();
	}
	public void clickEmSideMenuHistoryBtn() {
		historyBtn.click();
	}
	public void clickEmSideMenuTeamReportBtn() {
		teamReportBtn.click();
	}
	public void clickEmSideMenuProfileBtn() {
		profileBtn.click();
	}
}
