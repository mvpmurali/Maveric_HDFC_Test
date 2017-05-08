package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Home_page {
	static WebElement elem=null;
	public static WebElement userid_txt(WebDriver driver) {
	return elem = driver.findElement(By.name("fldLoginUserId"));
	}
	public static WebElement continue_btn(WebDriver driver) {
	return elem = driver.findElement(By.cssSelector("img[alt='continue']"));
	}
}
