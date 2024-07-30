package com.HashtagMIS.EmployeeUC.ME3Report;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.poi.hpsf.Array;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import LibraryFiles.UtilityClass;

public class EmViewReport {

	@FindBy(xpath = "//h1[contains(text(),'Daily Report:')]")
	private WebElement vrtitle;
	@FindBy(xpath = "//div[@id='cell-2-undefined']")
	private List<WebElement> taskList;	
	@FindBy(xpath = "//input[@type='date']")
	private WebElement Date;
	@FindBy(xpath = "//a[text()='Go Back']")
	private WebElement backBtn;
	@FindBy(xpath = "//h2")
	private WebElement ChkUnChk;
	@FindBy(xpath = "//input[@type='checkbox']")
	private List<WebElement> cb;
	
	Actions act;
	LinkedHashMap<String, String> lmp;
	

	public EmViewReport(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.act = new Actions(driver);
	}
	public String  getEmViewReportChkUnChk(WebDriver driver) throws InterruptedException {
		Thread.sleep(1500);
		UtilityClass.DrawBorder(driver, ChkUnChk);
		Thread.sleep(1500);
		return ChkUnChk.getText();
	}
	
	
	public String getEmViewReportPageTitle(WebDriver driver) throws InterruptedException {
		UtilityClass.DrawBorder(driver, vrtitle);
		Thread.sleep(3000);
		return vrtitle.getText();
	}

	public LinkedHashMap<String, String> getEmViewReportTaskAndValue(WebDriver driver) {
		 lmp=new LinkedHashMap<String, String>();
		 for (int i = 1; i <= taskList.size(); i++) {
				String task = driver.findElement(By.xpath("(//div[@class='sc-dhKdPU jFfAhm rdt_TableRow'] )["+i+"]/child::div[2]/div")).getText().trim();
				String value = driver.findElement(By.xpath("(//div[@class='sc-dhKdPU jFfAhm rdt_TableRow'] )["+i+"]/child::div[3]/textarea")).getText().trim();		
				lmp.put(task, value);
				
			}
			return lmp;
	}	
	public void clickEmViewReportBackBtn() {
		backBtn.click();
	}
	

}
