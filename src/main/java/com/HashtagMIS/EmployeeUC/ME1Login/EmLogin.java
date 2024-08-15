package com.HashtagMIS.EmployeeUC.ME1Login;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import LibraryFiles.UtilityClass;

public class EmLogin {
	@FindBy(xpath = "//input[@type='email']")
	private WebElement Email;
	@FindBy(xpath = "//input[@type='email']/following-sibling::span")
	private WebElement EmailEr;
	@FindBy(xpath = "//input[@type='password']")
	private WebElement pwd;
	@FindBy(xpath = "//input[@type='password']/following-sibling::span")
	private WebElement pwdEr;
	@FindBy(xpath = "//button[text()='Login']")
	private WebElement SignInBtn;
	@FindBy(xpath = "//a[text()='Forgot Password?']")
	private WebElement forgotPwdBtn;
	@FindBy(xpath = "//div[@role='alert']/child::div[2]")
	private WebElement toastMsg;
	@FindBy(xpath = "//span[@class='text-red-500']")
	private List<WebElement> errMsgList;
	@FindBy(xpath = "//span[@class='text-red-500']")
	private WebElement errMsg;
	
	public EmLogin(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	public void EmLoginPageSignIn(WebDriver driver, String email,String pWD) throws IOException {
		driver.get(UtilityClass.getPFData("URL"));
		Email.sendKeys(email);
		pwd.sendKeys(pWD);
		SignInBtn.click();
	}
	public void inpEmLoginPageEmail(String email) {
		Email.sendKeys(email);
	}
	public String getEmLoginPageEmailEr() {
		return EmailEr.getText();
	}
	public void inpEmLoginPagePwd(String pWD) {
		pwd.sendKeys(pWD);
	}
	public void clickEmLoginPageLoginBtn() {
		SignInBtn.click();
	}
	
	public void clickEmLoginPageForgotPwdLink() {
		forgotPwdBtn.click();
	}
	public String getEmLoginPageErrorMsg(WebDriver driver) {
		UtilityClass.DrawBorderList(driver, errMsgList);
		return toastMsg.getText();
	}
	public String getEmLoginPageToastMsg(WebDriver driver) {
		
		try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(expectedConditions -> toastMsg.isDisplayed()); // Lambda for expected condition
	        UtilityClass.DrawBorder(driver, toastMsg);
	        return toastMsg.getText();
	    } catch (TimeoutException e) {
	        // Handle timeout exception gracefully (e.g., log, retry, throw custom exception)
	     //   System.out.println("Warning: Toast message element not found within "+"____"  + " seconds.");
	        UtilityClass.DrawBorderList(driver, errMsgList);
	       // Or throw a custom exception if desired
	        if(!errMsg.getText().isEmpty()) {
	        	 return errMsg.getText(); 
	        }
	        else{
	        	 return "Null"; 
	        }
	    }
	}
	public void getEmLoginPageForgotPwdBtn(WebDriver driver) {
		UtilityClass.DrawBorderList(driver, errMsgList);
		
	}
	
}
