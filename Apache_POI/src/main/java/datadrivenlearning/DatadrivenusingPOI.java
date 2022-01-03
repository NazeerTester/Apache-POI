package datadrivenlearning;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class DatadrivenusingPOI<usernamelist> {
	
	static List<String> usernamelist= new ArrayList<String>();
    static List<String> passwordlist= new ArrayList<String>();
	
	public void readexcel() throws IOException
	{
		FileInputStream excel = new FileInputStream("C:\\Users\\NAZEER\\Documents\\logintest1.xlsx");
	 
		Workbook workbook = new XSSFWorkbook(excel);
		
		Sheet sheet = workbook.getSheetAt(0);
		
		Iterator<Row> rowiterator = sheet.iterator(); // Sheet iterator will give row values
				
	    while(rowiterator.hasNext()){// has next will check if value are there next row if yes it will allow the loop
	    	
	    	Row rowvalue=rowiterator.next();// next function will take next value
	    	
	    	Iterator<Cell> columniterator= rowvalue.iterator(); 
	    	 int i=2;
	    	 while(columniterator.hasNext()) {
	    		 if(i%2==0) {
	    			 usernamelist.add(columniterator.next().getStringCellValue());
	    		 }else {
	    			  passwordlist.add(columniterator.next().getStringCellValue());
	    		 }
	    		i++;
	    }
	    }
	
	}
	
	
	public void login(String uname,String pword)
	{
		System.setProperty("webdriver.chromedriver", "C:\\Users\\NAZEER\\Desktop\\chromedriver.exe");
		
		ChromeDriver driver =new ChromeDriver();
		
		driver.get("https://opensource-demo.orangehrmlive.com/");
		
		 WebElement username=driver.findElement(By.id("txtUsername"));
		 username.sendKeys(uname);
		 
		 WebElement password =driver.findElement(By.id("txtPassword"));
		 password.sendKeys(pword);
		 
		 WebElement loginbutton=driver.findElement(By.id("btnLogin"));
		 loginbutton.click();
		 
		 driver.quit();
	}
	
	public void executetest()
	{
		for(int i=0;i<usernamelist.size();i++){
			login(usernamelist.get(i),passwordlist.get(i));
		}
	}
	

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		 DatadrivenusingPOI usingpoi= new DatadrivenusingPOI();
		 usingpoi.readexcel();
		 System.out.println("Username list is"+ usernamelist);
		 System.out.println("Password list  is"+ passwordlist);
		 usingpoi.executetest();
	}

}
