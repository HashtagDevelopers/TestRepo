package DataProviders;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;


public class C1DSLoginPage {   
	 String filePath =".\\Test Data\\excelDataProviderLoginPage.xlsx";
	@DataProvider(name = "LoginPageDS")
	public String[][] dataContainerLoginPage() throws IOException {
		//return getAllExcelData("LoginCombination");
		return getSelectiveExcelData("LoginCombination");
				
	}
	@DataProvider(name = "ForgotPwdPageDS")
	public String[][] dataContainerForgotPwdPage() throws IOException {
		return getAllExcelData("ForgotPassword");
	//	return getSelectiveExcelData("ForgotPassword");
				
	}
	@DataProvider(name = "AdminLoginDS")
	public String[][] dataContainerAdminLoginPage() throws IOException {
		return getAllExcelData("AdminLogin");
				
	}
		
	
	
	
	public String[][] getAllExcelData(String sheetName) throws IOException { // int row, int col
		FileInputStream file = new FileInputStream(filePath);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet(sheetName);
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
	int startRow = 1;  //1 to 5 verify Toast
	int endRow = 2;    //6 to 8 verify Error
	public String[][] getSelectiveExcelData(String sheetName) throws IOException { // int row, int col
		FileInputStream file = new FileInputStream(filePath);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet(sheetName);
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

}
