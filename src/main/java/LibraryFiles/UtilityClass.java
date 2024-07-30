package LibraryFiles;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;

public class UtilityClass {

	public static void CreateCSVFile() throws IOException {
		FileWriter fr = new FileWriter(".\\excel Data.xlsx");
		BufferedWriter br = new BufferedWriter(fr);
		br.write("Kiran");
		br.close();
	}

	public static void CreateCSVFileP() throws IOException {
		PrintWriter pw = new PrintWriter(new File(".\\excel Data1.csv"));
		StringBuilder sb = new StringBuilder();
		sb.append("task in ");
		sb.append(",");
		sb.append("\r\n");
		pw.write(sb.toString());
		pw.close();
	}

	public static String getPFData(String key) throws IOException {
		FileInputStream file = new FileInputStream(".\\PropertyFile.properties");
		Properties p = new Properties();
		p.load(file);
		return p.getProperty(key);
	}

	public static void captureSS(WebDriver driver, String RS) throws IOException {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File dest = new File(".\\FailedTCScreenShot\\" + RS + ".png");
		FileHandler.copy(src, dest);
	}

	public static String getExcelData(int rowIndex, int colIndex) throws IOException {
		FileInputStream file = new FileInputStream(".\\TestData\\excelData.xlsx");
		return WorkbookFactory.create(file).getSheet("Sheet").getRow(rowIndex).getCell(colIndex).getStringCellValue();
	}

	public static void DrawBorder(WebDriver driver, WebElement error) {
		((JavascriptExecutor) driver)
				.executeScript("arguments[0].setAttribute('style', 'border:2px solid red; background:yellow')", error);
	}

	public static void DrawBorderList(WebDriver driver, List<WebElement> errors) {
		for (WebElement ele : errors) {
			((JavascriptExecutor) driver).executeScript(
					"arguments[0].setAttribute('style', 'border:2px solid red; background:yellow')", ele);
		}
	}

	public static void ScrollToElement(WebDriver driver, WebElement ele) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView;", ele);
	}

	public static void selectByVisibleTxt(WebElement ele, String value) {
		Select s = new Select(ele);
		s.selectByVisibleText(value);
	}

	public static String getSelectedOption(WebElement ele) {
		Select s = new Select(ele);
		return s.getFirstSelectedOption().getText();
	}

	public static ArrayList<String> getAllOptionList(WebElement ele) {
		Select s = new Select(ele);
		ArrayList<String> al = new ArrayList<String>();
		for (WebElement wb : s.getOptions()) {
			al.add(wb.getText());
		}
		al.remove(0);
		return al;
	}
}
