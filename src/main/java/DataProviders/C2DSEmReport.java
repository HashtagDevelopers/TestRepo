package DataProviders;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class C2DSEmReport {
	 String filePath =".\\TestData\\excelDataProviderReport.xlsx";
	 int startRowMulti = 1;  //9
	 int endRowMulti = 1;
	 @DataProvider(name = "ReportFlowDS1")
		public String[][] DataContainerReportFlow1() throws IOException { 
		return DataContainerControl("ReportMulti");
	 }
	 @DataProvider(name = "ReportFlowDS")
		public String[][] DataContainerReportFlow() throws IOException { 
		return DataContainerMulti("ReportMulti");
	 }
	
	public String[][] DataContainerControl(String sheet1) throws IOException { 
		FileInputStream file = new FileInputStream(filePath);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet(sheet1);
		 int row = endRowMulti-startRowMulti+1;
		 int col = sheet.getRow(0).getLastCellNum();
		 System.out.println(row+"  "+col);
		String[][] data = new String[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				DataFormatter df = new DataFormatter();
				data[i][j] = df.formatCellValue(sheet.getRow(i+startRowMulti+1).getCell(j));
			}
			System.out.println();
		}
	
		workbook.close();
		file.close();
        return data;
	}
	public String[][] DataContainerMulti(String sheet1) throws IOException { 
		FileInputStream file = new FileInputStream(filePath);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet(sheet1);
		 int row = sheet.getPhysicalNumberOfRows()-2;
		 int col = sheet.getRow(0).getLastCellNum();
		 System.out.println(row+"  "+col);
		String[][] data = new String[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				DataFormatter df = new DataFormatter();
				data[i][j] = df.formatCellValue(sheet.getRow(i+2).getCell(j));
			}
			System.out.println();
		}
	
		workbook.close();
		file.close();
        return data;
	}
	
	@DataProvider(name = "ReportMultipleDS")
	public String[][] DataContainerReportMulti() throws IOException { 
		FileInputStream file = new FileInputStream(filePath);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet("ReportMulti");
		 int row = sheet.getPhysicalNumberOfRows()-2;
		 int col = sheet.getRow(0).getLastCellNum();
		 System.out.println(row+"  "+col);
		String[][] data = new String[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				DataFormatter df = new DataFormatter();
				data[i][j] = df.formatCellValue(sheet.getRow(i+2).getCell(j));
			}
			System.out.println();
		}
	
		workbook.close();
		file.close();
        return data;
	}
	

	@DataProvider(name = "ReportFunctionalDS")
	public String[][] DataContainerReportFunctional() throws IOException { // int row, int col
		FileInputStream file = new FileInputStream(filePath);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet("ReportFunctional");
		 int row = sheet.getPhysicalNumberOfRows()-2;
		 int col = sheet.getRow(0).getLastCellNum();
		 System.out.println(row+"  "+col);
		String[][] data = new String[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				DataFormatter df = new DataFormatter();
				data[i][j] = df.formatCellValue(sheet.getRow(i+2).getCell(j));
			}
			System.out.println();
		}
	
		workbook.close();
		file.close();
        return data;
	}
	
	@DataProvider(name = "ReportUnitDS")
	public String[][] DataContainerReportUnit() throws IOException { 
		FileInputStream file = new FileInputStream(filePath);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet("ReportUnit");
		 int row = sheet.getPhysicalNumberOfRows()-2;
		 int col = sheet.getRow(0).getLastCellNum();
		 System.out.println(row+"  "+col);
		String[][] data = new String[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				DataFormatter df = new DataFormatter();
				data[i][j] = df.formatCellValue(sheet.getRow(i+2).getCell(j));
				
			}
			System.out.println();
		}
		workbook.close();
		file.close();
        return data;
	}
	
	int startRow = 1;  //1 to 5 verify Toast
	int endRow = 1;    //6 to 8 verify Error
	@DataProvider(name = "ReportUnitDS1")
	public String[][] DataContainerReportUnit1() throws IOException { // int row, int col
		FileInputStream file = new FileInputStream(filePath);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet("ReportUnit");
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

	int startRowEdit = 4; 
	int endRowEdit = 4;    
	@DataProvider(name = "ReportEditFlowDS")
	public String[][] DataContainerReportEditFlow() throws IOException { 
		FileInputStream file = new FileInputStream(filePath);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet("ReportEdit");
		 int row = endRowEdit-startRowEdit+1;
		 int col = sheet.getRow(0).getLastCellNum();
		 System.out.println(row+"  "+col);
		String[][] data = new String[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				DataFormatter df = new DataFormatter();
				data[i][j] = df.formatCellValue(sheet.getRow(i+startRowEdit+1).getCell(j));
			}
			System.out.println();
		}	
		workbook.close();
		file.close();
        return data;
	}
	
	@DataProvider(name = "ReportEditFunctionalDS")
	public String[][] DataContainerReportEdit() throws IOException { 
		FileInputStream file = new FileInputStream(filePath);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet("ReportEdit");
		 int row = sheet.getPhysicalNumberOfRows()-2;
		 int col = sheet.getRow(0).getLastCellNum();
		 System.out.println(row+"  "+col); 
		String[][] data = new String[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				DataFormatter df = new DataFormatter();
				data[i][j] = df.formatCellValue(sheet.getRow(i+2).getCell(j));
			}
			System.out.println();
		}	
		workbook.close();
		file.close();
        return data;
	}
	

}
