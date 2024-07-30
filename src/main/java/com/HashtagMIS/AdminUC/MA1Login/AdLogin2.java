package com.HashtagMIS.AdminUC.MA1Login;

import java.time.Duration;


import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import LibraryFiles.UtilityClass;

public class AdLogin2 {
	
	@FindBy(xpath = "//input[@type='number']")
	private WebElement otpInp;
	@FindBy(xpath = "//button[text()='Submit']")
	private WebElement SubmitBtn;
	@FindBy(xpath = "//a[text()='Forgot Password?']")
	private WebElement resendOtpBtn;
	@FindBy(xpath = "//div[@role='alert']/child::div[2]")
	private WebElement toastMsg;
	@FindBy(xpath = "//span[@class='text-red-500']")
	private WebElement errMsg;
	
	public AdLogin2(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void inpAdLoginPage2Otp(String otp) {
		otpInp.sendKeys(otp);
	} 
	
	public void clickAdLoginPage2SubmitBtn() {
		SubmitBtn.click();
	}
	
	public void clickAdLoginPage2ResendOtpLink() {
		resendOtpBtn.click();
	}
	public String getAdLoginPage2ErrorMsg(WebDriver driver) {
		UtilityClass.DrawBorder(driver, errMsg);
		return errMsg.getText();
	}
	public String getAdLoginPage2ToastMsg(WebDriver driver) {
		
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
