package org.git;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class GitBaseClass {
	
	public static WebDriver driver;

	public static  WebDriver  BrowserLaunch() {
		WebDriverManager.chromedriver().setup();
	     driver=new ChromeDriver();
	    return driver;
	    
	}
	
	
	
	public static void urlLaunch(String url) {
		driver.get(url);
		driver.manage().window().maximize();
	}
	public static void implicitywait(int a) {
		driver.manage().timeouts().implicitlyWait(a, TimeUnit.SECONDS);
	}

	public static  void senkeys(WebElement a,String value) {
		a.sendKeys(value);
	}
	public static void click(WebElement e) {
		e.click();

	}
	public static  String gettext(WebElement e) {
	   return e.getText();

	}
	
	public static void MoveToElement(WebElement e) {
		Actions a=new Actions(driver);
		a.moveToElement(e).perform();
        
	}
	
	public static void click1(WebElement e) {
		Actions a=new Actions(driver);
		a.click(e).perform();
	}
	
	public static void Dragandrop(WebElement f,WebElement e ) {
		Actions a=new Actions(driver);
		a.dragAndDrop(f, e);

	}
	public static void scrollDwon(WebElement e) {
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true)", e);
		

	}
	
	
     public static void scrollup(WebElement e) {
    	 JavascriptExecutor js=(JavascriptExecutor) driver;
    	 js.executeScript("arguments[0].scrollIntoView(false)", e);
}
     
    public static void SwitchTourl() {
		String webpage = driver.getWindowHandle();
		
	}
    
    public static void SwitchtoNewurl() {
    	Set<String> pages = driver.getWindowHandles();
    	for(String allpage:pages) {
    			driver.switchTo().window(allpage);
}
	}
    
    public static void Screenshot(String  name) throws IOException {
		TakesScreenshot tk=(TakesScreenshot) driver;
		File screenshot = tk.getScreenshotAs(OutputType.FILE);
		File  save=	new File("C:\\Users\\admin\\eclipse-workspace\\MavenProject1\\src\\test\\resources\\Screenshot\\"+name+".png");
		FileUtils.copyFile(screenshot, save);

	}
    
    public static void SelectbyIndex(WebElement e,int a) {
		Select s=new Select(e);
		s.selectByIndex(a);
		
		
	} 
    
    public static String InnerText(WebElement e) {
	   String attribute = e.getAttribute("innertext");
	return attribute;

	   
}
    
    public static String ValueAttribute(WebElement e) {
    	String attribute = e.getAttribute("value");
		return attribute;

	}
    
   public static  String Readexcel(String filename,String sheet ,int a,int b) throws IOException {
		File f=new File("C:\\Users\\admin\\eclipse-workspace\\MavenProject1\\src\\test\\resources\\Excel\\"+filename+".xlsx");
		FileInputStream st=new FileInputStream(f);
		Workbook w=new XSSFWorkbook(st);
		Sheet s = w.getSheet(sheet);
		Row row = s.getRow(a);
		Cell cell = row.getCell(b);
		int Type = cell.getCellType();
		
		String Value= null;
		
		if(Type==1) {
			 Value = cell.getStringCellValue();
		}
		else {
			if(DateUtil.isCellDateFormatted(cell)) {
			Date V = cell.getDateCellValue();
			SimpleDateFormat sd=new SimpleDateFormat();
			Value = sd.format(V);
		}
		else {
			double numericCellValue = cell.getNumericCellValue();
			long l=(long) numericCellValue ;
			Value = String.valueOf(cell);
		}
			
		}
		return Value;
   }
   
   
   	private static String GetCurrenUrl() {
   	String currentUrl = driver.getCurrentUrl();
	return currentUrl;

}

}
