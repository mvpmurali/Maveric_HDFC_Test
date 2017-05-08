package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class View_Beneficiary_page {
	static WebElement elem=null;		
	public static WebElement benifit_frame(WebDriver driver) {
		return elem = driver.findElement(By.name("left_menu"));
		}
	public static WebElement enquire_elm(WebDriver driver) {
	return elem = driver.findElement(By.linkText("Enquire"));
	}
	public static WebElement view_benift(WebDriver driver) {
		//return elem = driver.findElement(By.xpath("//a/span[contains(text(),'View/Delete List of Beneficiaries')]"));
		return elem = driver.findElement(By.linkText("View/Delete List of Beneficiaries"));
		}
	
	public static WebElement ntfs_view_btn(WebDriver driver) {
		return elem = driver.findElements(By.cssSelector("tr:nth-child(3)>td>a>img")).get(1);
		}
	public static WebElement check_name(WebDriver driver,String Name) {
		return elem = driver.findElements(By.xpath("//td[contains(text(),'"+Name+"')]")).get(0);
		}
	
}
