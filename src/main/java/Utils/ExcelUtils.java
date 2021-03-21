package Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.testng.annotations.DataProvider;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class ExcelUtils {
	

	
	static String[][] tabArray = null;
    Workbook workbk;
    static int rowCount;
	static int colCount;
	String ExcelPath = System.getProperty("user.dir") + File.separator + "resources" + File.separator
			+ "TestDataSource.xls";
  
    
 // Excel API to read test data from excel workbook
    public static String[][] getExcelData(String xlPath, String shtName)
            throws Exception {
        Workbook workbk = Workbook.getWorkbook(new File(xlPath));
        Sheet sht = workbk.getSheet(shtName);
        rowCount = sht.getRows();
        colCount = sht.getColumns();
        tabArray = new String[rowCount][colCount - 2];
        System.out.println("erow: " + rowCount);
        System.out.println("ecol: " + colCount);
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < 3; j++) {
                tabArray[i][j] = sht.getCell(j, i).getContents();
            }
        }
        return (tabArray);
    }

    @DataProvider (name = "updateDataSource")
    public Object[][] getLoginData() throws Exception {
        Object[][] retObjArr = getExcelData(ExcelPath, "endToEnd");
        System.out.println("getData function executed!!");
        return retObjArr;
    }
	

	// Read Excel Sheet Values for Data

	//@DataProvider(name = "dataSource")
	public static Object[][] get_Data(String ExcelPath, String SheetName, int coloums) throws Exception, IOException {

		/*String ExcelPath = System.getProperty("user.dir") + File.separator + "resources" + File.separator
				+ "TestDataSource.xls";
	    String SheetName = "endToEnd";*/
		//coloums = 4;

		Workbook workbook = Workbook.getWorkbook(new File(ExcelPath));
		Sheet sheet = workbook.getSheet(SheetName);

		int totalRecords = sheet.getRows() - 1;
		int initalPosition = 1;

		Object[][] values = new Object[totalRecords][coloums];

		for (int i = 0; i < totalRecords; i++, initalPosition++) {
			for (int j = 0; j < coloums; j++) {
				values[i][j] = sheet.getCell(j, initalPosition).getContents();
			}
		}

		// String[][] TestData = Arrays.deepToString(values);
		System.out.println("Values are " + values[0][0]);

		workbook.close();
		return values;

	}

	public static void write_Data(int coloumnLabel, int rowLabel, int coloumnData, int rowData, String columnValue,
			String columnName) throws IOException, RowsExceededException, WriteException {

		String ExcelPath = System.getProperty("user.dir") + File.separator + "resources" + File.separator
				+ "TestDataSource1.xls";
		String SheetName = "methodology";

		File f = new File(ExcelPath);
		WritableWorkbook myWorrkbook = Workbook.createWorkbook(f);
		WritableSheet excelSheet = myWorrkbook.createSheet(SheetName, 0);

		// add something into the Excel sheet in the
		Label label = new Label(coloumnLabel, rowLabel, columnName);
		excelSheet.addCell(label);

		// enter value in the respected cell
		label = new Label(coloumnData, rowData, columnValue);
		excelSheet.addCell(label);

		myWorrkbook.write();
		myWorrkbook.close();

	}

	public static void setValueIntoCell(String strSheetName, int iColumnNumber, int iRowNumber, String strData)
			throws WriteException, IOException, BiffException {

		String ExcelPath = System.getProperty("user.dir") + File.separator + "resources" + File.separator
				+ "TestDataSource.xls";
		String CopyExcelPath = System.getProperty("user.dir") + File.separator + "resources" + File.separator
				+ "TestDataCopy.xls";

		String SheetName = "methodology";

		File f = new File(ExcelPath);
		Workbook wbook = Workbook.getWorkbook(f);
		WritableWorkbook workbookCopy = Workbook.createWorkbook(new File(CopyExcelPath), wbook);

		WritableSheet wshTemp = workbookCopy.getSheet(SheetName);
		Label labTemp = new Label(iColumnNumber, iRowNumber, strData);
		System.out.println(
				"Updating on the column " + iColumnNumber + "on the Row " + iRowNumber + "With data" + strData);

		try {
			wshTemp.addCell(labTemp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void updateXLSFile(int rowNo, int cellNo) throws IOException {

		FileInputStream inputFile = null;
		HSSFWorkbook workbook = null;
		String ExcelPath = System.getProperty("user.dir") + File.separator + "resources" + File.separator
				+ "TestDataSource.xls";
		String SheetName = "methodology";

		try {
			inputFile = new FileInputStream(new File(ExcelPath));
			workbook = new HSSFWorkbook(inputFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		HSSFSheet sheet = workbook.getSheetAt(0);
		HSSFCell cell = null;

		// Updating value of cell with integer
		cell = sheet.getRow(rowNo).getCell(cellNo);
		cell.setCellValue("abc");
		inputFile.close();

		try {

			FileOutputStream outFile = new FileOutputStream(new File(ExcelPath));
			// outFile = new FileOutputStream(new File(ExcelPath));
			workbook.write(outFile);
			outFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

	/*public static void updateExcelFile(int rowNo, int cellNo, String value, int sheetNum) throws IOException {

		FileInputStream inputFile = null;
		HSSFWorkbook workbook = null;
		String ExcelPath = System.getProperty("user.dir") + File.separator + "resources" + File.separator
				+ "TestDataSource.xls";
		String SheetName = "methodology";

		FileInputStream fsIP = new FileInputStream(new File(ExcelPath)); // Read the spreadsheet that needs to be
																			// updated

		HSSFWorkbook wb = new HSSFWorkbook(fsIP); // Access the workbook

		HSSFSheet worksheet = wb.getSheetAt(sheetNum); // Access the worksheet, so that we can update / modify it.

		Cell cell = null; // declare a Cell object

		cell = worksheet.getRow(rowNo).getCell(cellNo); // Access the second cell in second row to update the value

		cell.setCellValue(value); // Get current cell value value and overwrite the value

		fsIP.close(); // Close the InputStream

		FileOutputStream output_file = new FileOutputStream(new File(ExcelPath)); // Open FileOutputStream to write
																					// updates

		wb.write(output_file); // write changes

		output_file.close(); // close the stream
	}
*/
	public static void doUpdate(String filePath) {

		try {
			// Get the original Excel File
			Workbook wb = Workbook.getWorkbook(new File(filePath));

			// Open A copy of the file and specify the data to write back to the original
			// file
			WritableWorkbook wwb = Workbook.createWorkbook(new File(filePath));

			// A1 Update the 1st WorkBook
			WritableSheet wshet0 = wwb.getSheet(0);
			WritableCell wc00 = wshet0.getWritableCell(0, 0);

			if (wc00.getType() == CellType.LABEL) {
				Label label00 = (Label) wc00;
				label00.setString("updata data");
			}
			// Add a worksheet
			WritableSheet sheet = wwb.createSheet("Add a workbook", 1);

			// Write some test data
			sheet.addCell(new Label(0, 0, "test data"));

			// Close the work thin object
			wwb.write();
			wwb.close();
			wb.close();

		} catch (Exception e) {
			System.out.println(e);
		}

	}
	
	
	//read an existing excel
	 public static void readExcelData(String excelPath) throws BiffException, IOException
	 {
	  Workbook workbook = Workbook.getWorkbook(new File(excelPath));
	  Sheet sheet = workbook.getSheet(0); 
	  for(int i=0; i<sheet.getRows();i++)
	  {
	   for(int j=0; j<sheet.getColumns();j++)
	   {
	    Cell cell = sheet.getCell(j,i);
	    System.out.print(cell.getContents());
	    //get their types
	    CellType type = sheet.getCell(j,i).getType();
	    System.out.print("("+type+")" + " ");
	   }
	   System.out.println(" ");
	  } 
	  
	  CellType type = sheet.getCell(1,1).getType();
	  if (type == CellType.LABEL)
	  {
	   System.out.println("cell(1,1) data is a label");
	  }
	 }
	 
	 //Write to an existing excel or add a new row
	 public static void writeToExcel(String excelPath, String outputtestdata,String value, int sheetNo ,int r ,int c) throws BiffException, IOException, RowsExceededException, WriteException
	 {
	  Workbook workbook = Workbook.getWorkbook(new File(excelPath));
	  //create a new excel and copy from existing
	  WritableWorkbook copy = Workbook.createWorkbook(new File(outputtestdata), workbook);
	  WritableSheet sheet = copy.getSheet(sheetNo);
	  //Label(colno, rowno, string)
	  Label label = new Label(c,r, value);
	  sheet.addCell(label);
	  copy.write();
	  copy.close();
	 }
	 
	 //Write to an existing excel or add a new row
	 public static void writeToExcelWithMinMax(String excelPath, String outputtestdata,String value, int meanSheetNo 
			 , int maxSheetNo , int minRow, int maxRow,int c) throws BiffException, IOException, RowsExceededException, 
	         WriteException	 {
		 
	  Workbook workbook = Workbook.getWorkbook(new File(excelPath));
	  //create a new excel and copy from existing
	  WritableWorkbook copy = Workbook.createWorkbook(new File(outputtestdata), workbook);
	  
	  for(; meanSheetNo<maxSheetNo; meanSheetNo++) {
		  for(; minRow<=maxRow; minRow++) {
			  System.out.println("The curent sheet nu: "+meanSheetNo);
			  WritableSheet sheet = copy.getSheet(meanSheetNo);
			  //Label(colno, rowno, string)
			  Label label = new Label(c,minRow, value);
			  sheet.addCell(label);
			  
		  }
	  }
	  
	  copy.write();
	  copy.close();
	 }


}
