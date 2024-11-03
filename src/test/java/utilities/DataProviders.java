package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	
	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException
	{
		String path=".\\testData\\Opencart_LoginData.xlsx";//taking xl file from test data
		
		ExcelUtility xlutil=new ExcelUtility(path);//creating an object for xlutility
		
		int totalrows=xlutil.getRowCount("Sheet1");
		int totalcols=xlutil.getCellCount("Sheet1", 1);
		
		String logindata[][]=new String[totalrows][totalcols];//craeted for two dimensional array which can store
		
		for(int i=1;i<=totalrows;i++)//1 read the data from xl storing in two dimesional array
		{
			for(int j=0;j<totalcols;j++)//0 i is row j is col
			{
				logindata[i-1][j]=xlutil.getCellData("Sheet1", i, j);//1,0
			}
			
		}
		return logindata;//returning two dimensional array
	}
	}

	//DataProvider 2
	
	//DataProvider 3
	
	//DataProvider 4
	

