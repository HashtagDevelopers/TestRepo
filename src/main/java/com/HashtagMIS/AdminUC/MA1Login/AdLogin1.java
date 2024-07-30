package com.HashtagMIS.AdminUC.MA1Login;

import java.time.Duration;


import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import LibraryFiles.UtilityClass;

public class AdLogin1 {
	@FindBy(xpath = "//input[@type='email']")
	private WebElement emailInp;
	
	@FindBy(xpath = "//button[text()='Login']")
	private WebElement LoginBtn;

	@FindBy(xpath = "//div[@role='alert']/child::div[2]")
	private WebElement toastMsg;

	@FindBy(xpath = "//span[@class='text-red-500']")
	private WebElement errMsg;
	
	public AdLogin1(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	public void inpAdLoginPage1Email(String email) {
		emailInp.sendKeys(email);
	}
	public void clickAdLoginPage1LoginBtn() {
		LoginBtn.click();
	}
	
	public String getAdLoginPage1ToastMsg(WebDriver driver) {
		
		try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(expectedConditions -> toastMsg.isDisplayed()); // Lambda for expected condition
	        UtilityClass.DrawBorder(driver, toastMsg);
	        return toastMsg.getText();
	    } catch (TimeoutException e) {
	        // Handle timeout exception gracefully (e.g., log, retry, throw custom exception)
	     //   System.out.println("Warning: Toast message element not found within "+"____"  + " seconds.");
	        UtilityClass.DrawBorder(driver, errMsg);
	        return errMsg.getText(); // Or throw a custom exception if desired
	    }
	}
	
	
}
