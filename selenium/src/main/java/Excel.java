import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Excel {
	public WebDriver driver;
	public WebDriverWait Wait;
	String appURL="https://www.linkedin.com/";
	
	private By bySignIn=By.linkText("Sign in");
	private By byEmail= By.name("session_key");
	private By byPassword= By.name("session_password");
	private By bySubmit=By.xpath("//button[@class='btn__primary--large from__button--floating']");
	private By byError=By.id("error-for-username");
	
	@BeforeClass
	public void testSetup(){
   	System.setProperty("webdriver.gecko.driver","C:\\Users\\jagkulsh\\Documents\\firefoxKaDriver\\geckodriver.exe");
		driver= new FirefoxDriver();
		driver.manage().window().maximize();
		Wait= new WebDriverWait(driver,5);
	}
	
	@Test(dataProvider="empLogin")
	public void VerifyInvalidLogin(String userName,String password){
		driver.navigate().to(appURL);
		driver.findElement(bySignIn).click();
		driver.findElement(byEmail).sendKeys(userName);
		driver.findElement(byPassword).sendKeys(password);
		Wait.until(ExpectedConditions.visibilityOfElementLocated(bySubmit));
		driver.findElement(bySubmit).click();
		
		Wait.until(ExpectedConditions.presenceOfElementLocated(byError));
		String actualErrorDisplayed=driver.findElement(byError).getText();
		String requiredErrorMessage="Please enter a valid username";
		Assert.assertEquals(requiredErrorMessage, actualErrorDisplayed);
	}
	
	@DataProvider(name="empLogin")
	public Object[][] loginData(){
		Object[][] arrayObject= getExcelData("C:\\Users\\jagkulsh\\Desktop\\Book1.xls","Sheet1");
		return arrayObject;
	}
	
	public String[][] getExcelData(String fileName, String sheetName){
		String[][] arrayExcelData=null;
		try{
			FileInputStream fs= new FileInputStream(fileName);
			Workbook wb= Workbook.getWorkbook(fs);
			Sheet sh = wb.getSheet(sheetName);
			
			int totalNoOfCols=sh.getColumns();
			int totalNoOfRows=sh.getRows();
			
			arrayExcelData= new String[totalNoOfRows-1][totalNoOfCols];
			for(int i=1;i<totalNoOfRows;i++){
				for (int j=0;j<totalNoOfCols;j++){
					arrayExcelData[i-1][j]=sh.getCell(j,i).getContents();
					
				}
				
			}
		}
	catch(FileNotFoundException e){
		e.printStackTrace();
	}
		catch(IOException e){
			e.printStackTrace();
		}
		catch (BiffException e){
			e.printStackTrace();
		}
		
		return arrayExcelData;
	}
	
	@AfterClass
	public void tearDown(){
		driver.quit();
	
	
		
	}
}