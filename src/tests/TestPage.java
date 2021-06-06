package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import objects.Home;
import objects.Resources;

public class TestPage {

	private static WebDriver driver;

	@BeforeClass
	public void createDriver() {
		System.setProperty("webdriver.chrome.driver", "C:\\DriverChrome\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	@Test(priority = 2)
	public void testLogIn() {
		File f = new File("data.xlsx");
		try {
			InputStream in = new FileInputStream(f);
			XSSFWorkbook wb = new XSSFWorkbook(in);
			Sheet sheet = wb.getSheetAt(0);
			Row row = sheet.getRow(0);
			Cell c0 = row.getCell(0);
			Cell c1 = row.getCell(1);
			String username = c0.toString();
			String password = c1.toString();
			wb.close();

			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

			SoftAssert sa = new SoftAssert();

			Home.goToPage(driver);
			Home.inputUsername(driver, username);
			Home.inputPassword(driver, password);
			Home.clickLogin(driver);

			String actual = driver.getCurrentUrl();
			String expected = Resources.SUCCESS;

			sa.assertEquals(actual, expected);
			sa.assertAll();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Test(priority = 1)
	public void testLogInFail() {
		File f = new File("data.xlsx");
		try {
			InputStream in = new FileInputStream(f);
			XSSFWorkbook wb = new XSSFWorkbook(in);
			Sheet sheet = wb.getSheetAt(0);
			Row row = sheet.getRow(1);
			Cell c0 = row.getCell(0);
			Cell c1 = row.getCell(1);
			String username = c0.toString();
			String password = c1.toString();
			wb.close();

			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

			SoftAssert sa = new SoftAssert();

			Home.goToPage(driver);
			Home.inputUsername(driver, username);
			Home.inputPassword(driver, password);
			Home.clickLogin(driver);

			String actual = driver.getCurrentUrl();
			String expected = Resources.SUCCESS;

			sa.assertNotEquals(actual, expected);
			sa.assertAll();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	@Test (priority = 3)
	public void testSort() {
		Home.sortByPrice(driver);
		driver.findElement(By.xpath("//*[@id=\"item_2_title_link\"]/div")).click();
		String actual = driver.getCurrentUrl();
		String expected = Resources.ITEM_1_URL;
		
		Assert.assertEquals(actual, expected);
	}

}
