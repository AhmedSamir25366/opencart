package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

// DataProviders is a class which contains only data provider methods

public class DataProviders {

	// DataProvider 1
	@DataProvider
	public String[][] LoginData() throws IOException {
		String path = ".\\testdata\\testdata.xlsx"; // taking Excel file from testData folder 
		
		ExcelUtility xlutil = new ExcelUtility(path); // creating an object from ExcelUtility class
		
		int totalrows = xlutil.getRowCount("Sheet1"); // Sheet1 represents Sheet name 
		int totalcols = xlutil.getCellCount("Sheet1", 1); // 1 represents row number
		
		String logindata[][] = new String[totalrows][totalcols];
		
		for (int i = 1; i <= totalrows; i++) { // to read rows
			
			for (int j = 0; j < totalcols; j++) { // to read columns
				logindata[i - 1][j] = xlutil.getCellData("Sheet1",i, j);
			}
		}
		return logindata; // returning two dimensional array

	}

	// DataProvider 2

	// DataProvider 3

	// DataProvider 4
}