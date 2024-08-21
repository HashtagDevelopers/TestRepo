package DataProviders;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class C1DSLoginPage {
	String filePath = ".\\Test Data\\excelDataProviderLoginPage.xlsx";
	private static String sheetName;
	static int startRow ;
	static int endRow ; 
	public static void setSheetName(String sh, int sr, int er) {
		sheetName = sh;
		startRow = sr;
		endRow = er;
	}

	@DataProvider(name = "LoginPageDS")
	public String[][] dataContainerLoginPage() throws IOException {
		 return getAllExcelData();
		//return getSelectiveExcelData();

	}

	@DataProvider(name = "ForgotPwdPageDS")
	public String[][] dataContainerForgotPwdPage() throws IOException {
		 return getAllExcelData();
		//return getSelectiveExcelData();

	}

	@DataProvider(name = "AdminLoginDS")
	public String[][] dataContainerAdminLoginPage() throws IOException {
		// return getAllExcelData("AdminLogin");
		return getAllExcelData();

	}

	public String[][] getAllExcelData() throws IOException { // int row, int col
		FileInputStream file = new FileInputStream(filePath);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet(sheetName);
		int row = sheet.getPhysicalNumberOfRows() - 2;
		int col = sheet.getRow(0).getLastCellNum();
		System.out.println(row + "  " + col);
		String[][] data = new String[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				DataFormatter df = new DataFormatter();
				data[i][j] = df.formatCellValue(sheet.getRow(i + 2).getCell(j));
				// System.out.print(data[i][j]+" ");
			}
			System.out.println();
		}

		workbook.close();
		file.close();
		return data;
	}

	

	public String[][] getSelectiveExcelData() throws IOException { // int row, int col
		FileInputStream file = new FileInputStream(filePath);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet(sheetName);
		int row = endRow - startRow + 1;// sheet.getPhysicalNumberOfRows();
		int col = sheet.getRow(0).getLastCellNum();
		System.out.println(row + "  " + col);
		String[][] data = new String[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				DataFormatter df = new DataFormatter();
				data[i][j] = df.formatCellValue(sheet.getRow(i + startRow + 1).getCell(j));
				// System.out.print(data[i][j]+" ");
			}
			System.out.println();
		}
		workbook.close();
		file.close();
		return data;
	}

}
