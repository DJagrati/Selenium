package com.sapient.xml;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class flipkart {
	
	static WebDriver driver;

	@BeforeTest

	@Parameters("browser")

	public void setup(String browser) throws Exception

	{

		if (browser.equalsIgnoreCase("firefox"))

		{

			System.setProperty("webdriver.gecko.driver",
					"C:\\Users\\jagkulsh\\Documents\\firefoxKaDriver\\geckodriver.exe");

			driver = new FirefoxDriver();

		}

		if (browser.equalsIgnoreCase("chrome"))

		{

			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\jagkulsh\\Documents\\chromeKaDriver\\chromedriver.exe");

			driver = new ChromeDriver();

		}

	}

	@Test

	public static void open() throws Exception

	{

		driver.get("https://www.flipkart.com/");

		driver.findElement(By.xpath("//input[@class='_2zrpKA _1dBPDZ']")).sendKeys("jagrati1997@gmail.com");

		Thread.sleep(300);

		driver.findElement(By.xpath("//input[@class='_2zrpKA _3v41xv _1dBPDZ']")).sendKeys("Divujaggu1602");

		Thread.sleep(300);
		
		driver.findElement(By.xpath("//button[@class='_2AkmmA _1LctnI _7UHT_c']")).click();
		
		Thread.sleep(300);

		driver.findElement(By.className("LM6RPg")).sendKeys("nature's tattva witch hazel");

		Thread.sleep(300);

		driver.findElement(By.xpath("//body/div[@id='container']/div/div[@class='_3ybBIU']/div[@class='_1tz-RS']/div[@class='_3pNZKl']/div[@class='Y5-ZPI']/form[@class='_1WMLwI header-form-search']/div[@class='col-12-12 _2tVp4j']/button[@class='vh79eN']/*[1]")).click();

		Thread.sleep(300);
	
		driver.findElement(By.cssSelector("div.t-0M7P._2doH3V div._3e7xtJ div._1HmYoV.hCUpcT div._1HmYoV._35HD7C:nth-child(2) div.bhgxx2.col-12-12:nth-child(2) div._3O0U0u div:nth-child(1) div._3liAhj > a._2cLu-l")).click();
		
		Thread.sleep(300);
		
        ArrayList tabs = new ArrayList (driver.getWindowHandles());
		
		driver.switchTo().window((String) tabs.get(1)); 
		
		Thread.sleep(300);
		
		driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[3]/div[2]/div[1]/div[1]/div[2]/div[1]/ul[1]/li[1]/button[1]")).click();
		
		Thread.sleep(300);
		
		//driver.findElement(By.xpath("//*[@class='_34Yjv1']")).

		driver.quit();

	}

}
