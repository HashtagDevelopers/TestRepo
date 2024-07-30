package DataProviders;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;


public class C1DSLoginPage {   
	 String filePath =".\\TestData\\excelDataProviderLoginPage.xlsx";
	@DataProvider(name = "LoginPageDS")
	public String[][] DataContainerLogin() throws IOException { // int row, int col
		FileInputStream file = new FileInputStream(filePath);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet("LoginCombination");
		 int row = sheet.getPhysicalNumberOfRows()-2;
		 int col = sheet.getRow(0).getLastCellNum();
		 System.out.println(row+"  "+col);
		String[][] data = new String[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				DataFormatter df = new DataFormatter();
				data[i][j] = df.formatCellValue(sheet.getRow(i+2).getCell(j));
				//System.out.print(data[i][j]+" ");
			}
			System.out.println();
		}
	
		workbook.close();
		file.close();
        return data;
	}
	int startRow = 4;  //1 to 5 verify Toast
	int endRow = 8;    //6 to 8 verify Error
	@DataProvider(name = "LoginPageDS1")
	public String[][] DataContainerLogin1() throws IOException { // int row, int col
		FileInputStream file = new FileInputStream(filePath);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet("LoginCombination");
		 int row = endRow-startRow+1;//sheet.getPhysicalNumberOfRows();
		 int col = sheet.getRow(0).getLastCellNum();
		 System.out.println(row+"  "+col);
		String[][] data = new String[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				DataFormatter df = new DataFormatter();
				data[i][j] = df.formatCellValue(sheet.getRow(i+startRow+1).getCell(j));
				//System.out.print(data[i][j]+" ");
			}
			System.out.println();
		}	
		workbook.close();
		file.close();
        return data;
	}
	@DataProvider(name = "ForgotPwdEEPageDS")
	public String[][] DataContainerEnterEmail() throws IOException { // int row, int col
		FileInputStream file = new FileInputStream(filePath);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet("ForgotPassword");
		 int row = sheet.getPhysicalNumberOfRows()-2;
		 int col = sheet.getRow(0).getLastCellNum();
		 System.out.println(row+"  "+col);
		String[][] data = new String[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				DataFormatter df = new DataFormatter();
				data[i][j] = df.formatCellValue(sheet.getRow(i+2).getCell(j));
				//System.out.print(data[i][j]+" ");
			}
			System.out.println();
		}
	
		workbook.close();
		file.close();
        return data;
	}
}
