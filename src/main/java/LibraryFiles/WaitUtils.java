package LibraryFiles;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils {
	 private WebDriver driver;
	    private static WebDriverWait wait;

	    public WaitUtils(WebDriver driver) {
	        this.driver = driver;
	        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    }

	    public static WebElement waitForElementToBeVisible(WebElement element) {
	        return wait.until(ExpectedConditions.visibilityOf(element));
	    }

	    public WebElement waitForElementToBeClickable(WebElement element) {
	        return wait.until(ExpectedConditions.elementToBeClickable(element));
	    }
	    public void waitForElementToBeCleared(By by) {
	        wait.until(new ExpectedCondition<Boolean>() {
	            public Boolean apply(WebDriver driver) {
	                WebElement element = driver.findElement(by);
	                return element.findElements(By.className("select__multi-value")).isEmpty();
	            }
	        });
	    }
}
