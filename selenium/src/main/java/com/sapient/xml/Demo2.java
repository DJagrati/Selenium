package com.sapient.xml;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.annotations.BeforeTest;

//import org.testng.annotations.Configuration;

import org.testng.annotations.Parameters;

import org.testng.annotations.Test;

public class Demo2 {

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

		driver.get("https://jpetstore.cfapps.io/catalog");

		driver.findElement(By.linkText("Sign In")).click();

		Thread.sleep(300);

		driver.findElement(By.name("username")).sendKeys("sapp");

		Thread.sleep(300);

		driver.findElement(By.name("password")).sendKeys("123456789");

		Thread.sleep(300);

		driver.findElement(By.id("login")).click();

		Thread.sleep(5000);

		driver.close();

	}

}
