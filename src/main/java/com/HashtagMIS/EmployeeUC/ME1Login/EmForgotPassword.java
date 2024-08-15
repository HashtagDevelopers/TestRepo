package com.HashtagMIS.EmployeeUC.ME1Login;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import LibraryFiles.UtilityClass;

public class EmForgotPassword {
	@FindBy(xpath = "//input[@type='email']")
	private WebElement Email;
	@FindBy(xpath = "//button[text()='Send Password']")
	private WebElement sendPwdBtn;
	@FindBy(xpath = "//h3")
	private WebElement pageTitle;
	@FindBy(xpath = "//div[@role='alert']/child::div[2]")
	private WebElement toastMsg;
	@FindBy(xpath = "//span[@class='text-red-500']")
	private List<WebElement> errMsgList;
	@FindBy(xpath = "//span[@class='text-red-500']")
	private WebElement errMsg;
	
	public EmForgotPassword(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void inpEmForgotPasswordPageEmail(String email) {
		Email.sendKeys(email);
	}

	public String getEmForgotPasswordPageTitle() {
	return	pageTitle.getText();
	}
	public void clickEmForgotPasswordPageTitleSendPwdBtn() {
		sendPwdBtn.click();
		}
	public String getEmForgotPasswordPageErrorMsg(WebDriver driver) {
		UtilityClass.DrawBorderList(driver, errMsgList);
		return toastMsg.getText();
	}
	public String getEmForgotPasswordPageToastMsg(WebDriver driver) {
		
		try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(expectedConditions -> toastMsg.isDisplayed()); // Lambda for expected condition
	        UtilityClass.DrawBorder(driver, toastMsg);
	        return toastMsg.getText();
	    } catch (TimeoutException e) {
	        // Handle timeout exception gracefully (e.g., log, retry, throw custom exception)
	     //   System.out.println("Warning: Toast message element not found within "+"____"  + " seconds.");
	        UtilityClass.DrawBorderList(driver, errMsgList);
	        return errMsg.getText(); // Or throw a custom exception if desired
	    }
	}
	
	
}
