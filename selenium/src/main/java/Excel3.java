package demo2;
import java.io.FileInputStream;
import java.io.IOException;
 
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class Excel3 {
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
    //	System.setProperty("webdriver.gecko.driver","C:/Software/Selenium/support/geckodriver.exe");
		driver= new FirefoxDriver();
		driver.manage().window().maximize();
	}
	
		@Test(dataProvider = "inputdata")
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
		
	   @DataProvider(name="inputdata")
	 
		    public Object[][] getcellData() throws IOException {
	  //step1: Locate the path of excel file    	 
			 FileInputStream file = new FileInputStream("C:/Software/Selenium/sampledoc.xlsx");
	  //step2: create workbook instance from excel sheet. 		
			 XSSFWorkbook wb = new XSSFWorkbook(file);
	  //step3: Get to the desired sheet.		
			 XSSFSheet s = wb.getSheet("sheet1");
	  //step4: getrow() specify which row we want to read and getcell() specify which column.		
			 int rowcount = s.getLastRowNum()+1;
			 int cellcount = s.getRow(0).getLastCellNum();
			
			 Object data[][] = new Object[rowcount][cellcount];
			
			      for(int i=0;i<rowcount;i++){
				  Row r =s.getRow(i);
				
				  for(int j = 0;j<cellcount;j++){
					Cell c = r.getCell(j);
					data[i][j] = c.getStringCellValue();
				}
			}
			wb.close();
			return data;
			
		}
	 
	}