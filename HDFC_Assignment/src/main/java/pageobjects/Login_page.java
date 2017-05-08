package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Login_page {
	static WebElement elem=null;		
	public static WebElement login_frame(WebDriver driver) {
		return elem = driver.findElement(By.name("login_page"));
		}
	public static WebElement pass_txt(WebDriver driver) {
	return elem = driver.findElement(By.name("fldPassword"));
	}
	public static WebElement calc_chkbx(WebDriver driver) {
		return elem = driver.findElement(By.name("chkrsastu"));
		}
	public static WebElement login_btn(WebDriver driver) {
	return elem = driver.findElement(By.cssSelector("img[alt='Login']"));
	}
}
