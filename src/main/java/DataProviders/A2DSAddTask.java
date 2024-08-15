package DataProviders;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class A2DSAddTask {
	String filePath = ".\\Test Data\\Food.xlsx";
	@DataProvider(name = "AddTaskDS")
	public String[][] DataContainerTask() throws IOException {
		return getAllDataFromSheet("Sheet2");
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

}
