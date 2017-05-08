package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Add_Beneficiary_page {
	static WebElement elem=null;		
	public static WebElement comnmenu_frame(WebDriver driver) {
		return elem = driver.findElement(By.name("common_menu1"));
		}
	public static WebElement fund_trans(WebDriver driver) {
		return elem = driver.findElement(By.cssSelector("img[alt='Funds Transfer']"));
		}
	public static WebElement benifit_leftframe(WebDriver driver) {
		return elem = driver.findElement(By.name("left_menu"));
		}
	public static WebElement req_elm(WebDriver driver) {
	//return elem = driver.findElement(By.cssSelector("li[class='accord-menu last-child'] a[class='accounts opener btmBluArw']"));
		return elem = driver.findElement(By.linkText("Request"));
	}
	public static WebElement add_benift(WebDriver driver) {
		//return elem = driver.findElement(By.xpath("//*[contains(text(),'Add a Beneficiary')]"));
		return elem = driver.findElement(By.linkText("Add a Beneficiary"));
		}
	public static WebElement main_frame(WebDriver driver) {
		return elem = driver.findElement(By.name("main_part"));
		}
	public static WebElement ntfs_go_btn(WebDriver driver) {
		return elem = driver.findElements(By.cssSelector("tr:nth-child(3)>td>a>img")).get(1);
		}
	public static WebElement beni_acc_no(WebDriver driver) {
		return elem = driver.findElement(By.name("fldAcctNo"));
		}
	public static WebElement beni_acc_no1(WebDriver driver) {
		return elem = driver.findElement(By.name("fldAcctNo2"));
		}
	public static WebElement beni_ifsc(WebDriver driver) {
		return elem = driver.findElement(By.name("fldIFSCCode"));
		}
	public static WebElement beni_name(WebDriver driver) {
		return elem = driver.findElement(By.name("fldNamBenef"));
		}
	public static WebElement beni_email(WebDriver driver) {
		return elem = driver.findElement(By.name("fldEmail"));
		}
	public static WebElement beni_add_btn(WebDriver driver) {
		return elem = driver.findElement(By.cssSelector("a>img[alt='Add']"));
		}
	public static WebElement beni_confirm_btn(WebDriver driver) {
		return elem = driver.findElement(By.cssSelector("a>img[alt='Confirm']"));
		}
}
