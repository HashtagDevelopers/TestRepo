package DataProviders;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class A1DSAddDeptAndEmp {
	String filePath= ".\\Test Data\\excelAdmnDeptAndStaff.xlsx";
	 private static String sheetName;
		static int startRow ;
		static int endRow ; 
		public static void setSheetName(String sh, int sr, int er) {
			sheetName = sh;
			startRow = sr;
			endRow = er;
		}
	@DataProvider(name = "EmpFuctionalDS")
	public String[][] DataContainerEmployeeFuctional() throws IOException {	
	//  return getAllDataFromSheet("StaffFunctional");
		return getSelectiveDataFromSheet();
	}
	public String[][] getSelectiveDataFromSheet() throws IOException {
		FileInputStream file = new FileInputStream(filePath);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet(sheetName);
		int row = endRow - startRow + 1;
		int col = sheet.getRow(0).getLastCellNum();
		System.out.println(row + "  " + col);
		String[][] data = new String[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				DataFormatter df = new DataFormatter();
				data[i][j] = df.formatCellValue(sheet.getRow(i + startRow + 1).getCell(j));
			}
			System.out.println();
		}

		workbook.close();
		file.close();
		return data;
	}
	private String[][] getAllDataFromSheet() throws IOException {
		FileInputStream file = new FileInputStream(filePath);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet(sheetName);
		int row = sheet.getPhysicalNumberOfRows() - 2;
		int col = sheet.getRow(0).getLastCellNum();
	//	System.out.println(row + "  " + col);

		String[][] data = new String[row][col];
		DataFormatter df = new DataFormatter();

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				data[i][j] = df.formatCellValue(sheet.getRow(i + 2).getCell(j));
			}
			System.out.println();
		}

		workbook.close();
		file.close();
		return data;
	}

	@DataProvider(name = "DepartmentFunctionalDS")
	public String[][] DataContainerDepartment() throws IOException {
		return getAllDataFromSheet();
	}
	@DataProvider(name = "DepartmentMultiDS")
	public String[][] DataContainerDepartmentMulti() throws IOException {
		return getAllDataFromSheet();
	}
	@DataProvider(name = "DepartmentEditDS")
	public String[][] DataContainerDepartmentEdit() throws IOException {
		return getAllDataFromSheet();
	}
	

	@DataProvider(name = "EmpMultipleDS")
	public String[][] DataContainerEmployeeMultiple() throws IOException {
		return getSelectiveDataFromSheet();
		//return getAllDataFromSheet("ActStaff");
	}
	@DataProvider(name = "EmpEditDS")
	public String[][] DataContainerEmployeeEdit() throws IOException {
		return getSelectiveDataFromSheet();
		//return getAllDataFromSheet("StaffEdit");
	}
	
}
