package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Dashboard_page {
	static WebElement elem=null;		
	public static WebElement amt_frame(WebDriver driver) {
		return elem = driver.findElement(By.name("main_part"));
		}
	public static WebElement amt_txt(WebDriver driver) {
	return elem = driver.findElement(By.id("SavingTotalSummary"));
	}
}
