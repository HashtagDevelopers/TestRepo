package DataProviders;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class A2DSAddTask {
	//String filePath = ".\\Test Data\\Care Support.xlsx";
	 private static String sheetName;
	 private static String filePath;
		static int startRow ;
		static int endRow ; 
		public static void setSheetName(String fp,String sh, int sr, int er) {
			filePath = fp;
			sheetName = sh;
			startRow = sr;
			endRow = er;
		}
	@DataProvider(name = "AddTaskDS")
	public String[][] DataContainerTask() throws IOException {
		return getAllDataFromSheet();
	}

	private String[][] getAllDataFromSheet() throws IOException {
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

	public String[][] getSelectiveDataFromSheet() throws IOException {
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
