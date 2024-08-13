package DataProviders;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class A1DSAddDeptAndEmp {
	String filePath = ".\\Test Data\\excelAdmnDeptandAndStaff.xlsx";
	int startRowS1 = 1;
	int endRowS1 = 1;

	public String[][] getSelectiveDataFromSheet(String sheetName) throws IOException {
		FileInputStream file = new FileInputStream(filePath);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet(sheetName);
		int row = endRowS1 - startRowS1 + 1;
		int col = sheet.getRow(0).getLastCellNum();
		System.out.println(row + "  " + col);
		String[][] data = new String[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				DataFormatter df = new DataFormatter();
				data[i][j] = df.formatCellValue(sheet.getRow(i + startRowS1 + 1).getCell(j));
			}
			System.out.println();
		}

		workbook.close();
		file.close();
		return data;
	}
	private String[][] getAllDataFromSheet(String sheetName) throws IOException {
		FileInputStream file = new FileInputStream(filePath);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet(sheetName);
		int row = sheet.getPhysicalNumberOfRows() - 2;
		int col = sheet.getRow(0).getLastCellNum();
		System.out.println(row + "  " + col);

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
		return getAllDataFromSheet("DeptFunctional");
	}
	@DataProvider(name = "DepartmentMultiDS")
	public String[][] DataContainerDepartmentMulti() throws IOException {
		return getAllDataFromSheet("DeptMulti");
	}
	@DataProvider(name = "DepartmentEditDS")
	public String[][] DataContainerDepartmentEdit() throws IOException {
		return getAllDataFromSheet("DeptEdit");
	}

	@DataProvider(name = "EmpMultipleDS")
	public String[][] DataContainerStaffMultiple() throws IOException {
		return getSelectiveDataFromSheet("ActStaff");
		//return getAllDataFromSheet("ActStaff");
	}

}
