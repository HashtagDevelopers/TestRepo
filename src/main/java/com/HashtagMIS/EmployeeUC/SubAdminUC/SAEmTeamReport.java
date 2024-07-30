package com.HashtagMIS.EmployeeUC.SubAdminUC;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import LibraryFiles.UtilityClass;

public class SAEmTeamReport {

	@FindBy(xpath = "//h1[text()='Team Reports']")
	private WebElement SAPtitle;
	
	ArrayList<String> al;
	Actions act;

	public SAEmTeamReport(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.act = new Actions(driver);
	}
	public boolean getSAEmTeamReportTitle(WebDriver driver) throws InterruptedException {
		UtilityClass.DrawBorder(driver, SAPtitle);
		Thread.sleep(500);
		return SAPtitle.isDisplayed();
	}
	
	public List<String> getSAEmTeamReportInfoList(WebDriver driver, String datetime) {
		al = new ArrayList<String>();
		List<WebElement> infoList = driver.findElements(By.xpath("//div[text()='"+datetime+"']/ancestor::div[2]/div"));
		
		for (WebElement s1 : infoList) {
			al.add(s1.getText());
		}
		al.remove(0);
		al.remove(al.size()-1);
		return al;
	}

	public void clickSAEmTeamReportCurrentReportViewBtn(WebDriver driver, String cnDatetime) {
		driver.findElement(By.xpath("//div[text()='"+cnDatetime+"']/ancestor::div[2]/div[6]/div")).click();

	}
	public String getSAEmTeamReportCurrentReportStatus(WebDriver driver, String cnDatetime) throws InterruptedException {
		
		 WebElement wb = driver.findElement(By.xpath("//div[text()='"+cnDatetime+"']/ancestor::div[2]/div[5]/span"));
		  UtilityClass.DrawBorder(driver, wb);
		  Thread.sleep(1500);
		  return wb.getText();
		 
	}
		
}
