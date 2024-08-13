package com.HashtagMIS.EmployeeUC.ME3Report;

import java.time.Duration;
import java.util.ArrayList;
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


public class EmHistory {

	@FindBy(xpath = "//h1[text()='My Reports']")
	private WebElement HPtitle;
	
	@FindBy(xpath = "//div[@id='cell-2-undefined']")
	private List<WebElement> dateList;
	@FindBy(xpath = "//input[@type='number']")
	private List<WebElement> tableNumberInp;
	@FindBy(xpath = "//input[@type='text']")
	private List<WebElement> tableTextInp;
	@FindBy(xpath = "//select")
	private WebElement selDepartment;
	@FindBy(xpath = "//input[@type='date']")
	private WebElement Date;
	@FindBy(xpath = "//div[@id='cell-2-undefined']/child::div")
	private WebElement cnDateTime;
	@FindBy(xpath = "//a[text()='Go Back']")
	private WebElement backBtn;
	
	
	Actions act;

	public EmHistory(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.act = new Actions(driver);
	}

	

	public void selEmHistoryPageDepartmentName(String depart) {
		UtilityClass.selectByVisibleTxt(selDepartment, depart);

	}

	public void inpEmHistoryPageDate(String dt) {
		Date.sendKeys(dt);
		act.sendKeys(Keys.ARROW_RIGHT).perform();
		act.sendKeys("2024").perform();

	}

	public boolean getEmHistoryPageTitle(WebDriver driver) throws InterruptedException {
		UtilityClass.DrawBorder(driver, HPtitle);
		Thread.sleep(500);
		return HPtitle.isDisplayed();
	}

	public String getEmHistoryPageReportTime() {
		return cnDateTime.getText();
	}
	public String getEmHistoryPageCurrentReportStatus(WebDriver driver, String cnDate) {
		WebElement wb = driver.findElement(By.xpath("//div[text()='" + cnDate + "']/ancestor::div[2]/div/span"));
		 UtilityClass.DrawBorder(driver, wb);
		 
		return wb.getText();
	}
	public List<String> getEmHistoryPageCurrentReportData(WebDriver driver, String cnDate) {
		List<WebElement> data = driver.findElements(By.xpath("//div[text()='" + cnDate + "']/ancestor::div[2]/div/div"));
		ArrayList<String> al = new ArrayList<String>();
		for (WebElement f : data) {
			al.add(f.getText());
		}
		al.remove(0);
		al.remove(al.size()-1);
		al.add(driver.findElement(By.xpath("//div[text()='" + cnDate + "']/ancestor::div[2]/div/span")).getText());
		//System.out.println(al);
		return al;
	}

	public void clickEmHistoryPageCurrentReportViewBtn(WebDriver driver, String cnDatetime) throws InterruptedException {
		Thread.sleep(2500);
		driver.findElement(By.xpath("//div[text()='"+cnDatetime+"']/ancestor::div[2]/div[6]/div")).click();

	}
	public void inpEmHistoryPageTaskList(WebDriver driver, String task, String num) {
		for (WebElement s1 : tableTextInp) {
			s1.sendKeys(num);
		}
		for (WebElement s1 : tableNumberInp) {
			s1.sendKeys(task);
		}
	}
	
	

	

	

	

	public void clickEmHistoryPageBackBtn() {
		backBtn.click();
	}

	
}
