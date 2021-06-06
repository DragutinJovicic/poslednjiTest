package objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Home {
	
	public static void goToPage(WebDriver driver) {
		driver.get(Resources.URL);
	}
	
	public static void inputUsername (WebDriver driver, String username) {
		driver.findElement(By.id("user-name")).sendKeys(username);
	}
	
	public static void inputPassword (WebDriver driver, String password) {
		driver.findElement(By.id("password")).sendKeys(password);
	}
	
	public static void clickLogin (WebDriver driver) {
		driver.findElement(By.xpath(Resources.LOG_IN_BTN_XPATH)).click();;
	}
	
	public static void sortByPrice(WebDriver driver) {
		
		driver.navigate().to(Resources.PRODUCTS_PAGE);
		
		Select select = new Select(driver.findElement(By.cssSelector(Resources.SORT_XPATH)));
		select.selectByValue("lohi");  
		
	}

}
