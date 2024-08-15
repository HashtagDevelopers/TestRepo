package com.HashtagMIS.EmployeeUC.ME3Report;

import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import LibraryFiles.UtilityClass;

public class EmReportPreviewPage {

	@FindBy(xpath = "//p[text()='Add Report Info']")
	private WebElement RFtitle;
	@FindBy(xpath = "//tr")
	private List<WebElement> tableRow;
	@FindBy(xpath = "//table[@aria-label='preview tasks']//td[1]")
	private List<WebElement> taskLst;

	@FindBy(xpath = "//button[text()='Confirm']")
	private WebElement submitBtn;
	@FindBy(xpath = "//button[text()='Cancel']")
	private WebElement cancel;


	LinkedHashMap<String, String> lmp;
	Actions act;


	public EmReportPreviewPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.act = new Actions(driver);
	}
	public LinkedHashMap<String, String> getEmReportPreviewPageTaskAndValue(WebDriver driver) {
		lmp = new LinkedHashMap<String, String>();
		for (int i = 1; i <= taskLst.size(); i++) {
			String task = driver.findElement(By.xpath("(//table[@aria-label='preview tasks']//td[2])[" + i + "]")).getText().trim();
			String value = driver.findElement(By.xpath("(//table[@aria-label='preview tasks']//td[2])[" + i + "]/following-sibling::td")).getText().trim();
			lmp.put(task, value);

		}
		return lmp;

	}

	public void clickEmReportPreviewPageConfirmBtn() {

		submitBtn.click();
		
	}

	public void clickEmReportPreviewPageCancelBtn() {
		cancel.click();
	}

}
