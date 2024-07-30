package DataProviders;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class A1DSAddEmp {
	String filePath = ".\\TestData\\excelADPDeptStaff.xlsx";

	private String[][] getDataFromSheet(String sheetName) throws IOException {
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

	int startRowS1 = 6;
	int endRowS1 = 6;

	public String[][] getDataFromSheet1(String sheetName) throws IOException {
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

	@DataProvider(name = "DepartmentFunctionalDS")
	public String[][] DataContainerDepartment() throws IOException {
		return getDataFromSheet("DeptFunctional");
	}

	@DataProvider(name = "DepartmentMultiDS")
	public String[][] DataContainerDepartmentMulti() throws IOException {
		return getDataFromSheet("DeptMulti");
	}

	@DataProvider(name = "DepartmentEditDS")
	public String[][] DataContainerDepartmentEdit() throws IOException {
		return getDataFromSheet("DeptEdit");
	}

	@DataProvider(name = "StaffMultipleDS")
	public String[][] DataContainerStaffMultiple() throws IOException {
		return getDataFromSheet("StaffMulti");
	}

	int startRowMulti = 1;
	int endRowMulti = 1;

	@DataProvider(name = "StaffFlowDS")
	public String[][] DataContainerStaffFlow() throws IOException {
		FileInputStream file = new FileInputStream(filePath);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet("StaffMulti");
		int row = endRowMulti - startRowMulti + 1;
		int col = sheet.getRow(0).getLastCellNum();
		System.out.println(row + "  " + col);
		String[][] data = new String[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				DataFormatter df = new DataFormatter();
				data[i][j] = df.formatCellValue(sheet.getRow(i + startRowMulti + 1).getCell(j));
			}
			System.out.println();
		}

		workbook.close();
		file.close();
		return data;
	}

	@DataProvider(name = "StaffFunctionalDS")
	public String[][] DataContainerStaffFunctional() throws IOException { // int row, int col
		return getDataFromSheet("StaffFunctional");
	}

	@DataProvider(name = "StaffUnitDS")
	public String[][] DataContainerStaffUnit() throws IOException {
		return getDataFromSheet("StaffUnit");
	}

	int startRow = 48; // 1 to 5 verify Toast
	int endRow = 49; // 6 to 8 verify Error

	@DataProvider(name = "StaffUnitDS1")
	public String[][] DataContainerStaffUnit1() throws IOException { // int row, int col
		FileInputStream file = new FileInputStream(filePath);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet("Sheet1");
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

	int startRowEdit = 5;
	int endRowEdit = 5;

	@DataProvider(name = "StaffEditFlowDS")
	public String[][] DataContainerStaffEditFlow() throws IOException {
		FileInputStream file = new FileInputStream(filePath);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet("StaffEdit");
		int row = endRowEdit - startRowEdit + 1;
		int col = sheet.getRow(0).getLastCellNum();
		System.out.println(row + "  " + col);
		String[][] data = new String[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				DataFormatter df = new DataFormatter();
				data[i][j] = df.formatCellValue(sheet.getRow(i + startRowEdit + 1).getCell(j));
			}
			System.out.println();
		}
		workbook.close();
		file.close();
		return data;
	}

	@DataProvider(name = "StaffEditFunctionalDS")
	public String[][] DataContainerStaffEdit() throws IOException {
		return getDataFromSheet("StaffEdit");

	}
}
