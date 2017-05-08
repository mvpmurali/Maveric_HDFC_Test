package source;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import datahandling.Readdata;
import pageobjects.Add_Beneficiary_page;
import pageobjects.Dashboard_page;
import pageobjects.Home_page;
import pageobjects.Login_page;
import pageobjects.View_Beneficiary_page;

public class Testscripts {
	static WebDriver driver = null;
	static JavascriptExecutor js;
	static Actions act;
	static Readdata rd;
	/*
	Description          : Initiate Driver based on User Input
	Author               : Murali
	**************************************************************
	Change History
	Change date:
	Changed by:
	**************************************************************/
	@BeforeClass
	public void webDriver() throws IOException, userexpection {
		 rd = new Readdata();
		System.out.println(System.getProperty("user.dir"));
		String browser = rd.exceldata(1, 0);//"IE";
		switch(browser.toUpperCase()){
		case "IE":
			System.setProperty("webdriver.ie.driver", "D:/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			break;
		case "CHROME":
			System.setProperty("webdriver.chrome.driver", "D:/chromedriver.exe");
			driver = new ChromeDriver();
			break;
		case "FF":
			System.setProperty("webdriver.gecko.driver", "D:/geckodriver.exe");
			driver = new FirefoxDriver();
			break;
		case "SAFARI":
			driver = new SafariDriver();
			break;
		default:
			System.setProperty("webdriver.chrome.driver", "D:/chromedriver.exe");
			driver = new ChromeDriver();
			break;
		}
		js = (JavascriptExecutor)driver;
		act = new Actions(driver);
	}
	/*
	Description          : Login to the HDFC application
	Author               : Murali
	**************************************************************
	Change History
	Change date:
	Changed by:
	**************************************************************/
	@Test
	public void login() throws IOException{
		String user =rd.exceldata(1, 1),
		pass=rd.exceldata(1, 2);
		driver.get("https://netbanking.hdfcbank.com/netbanking");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		try{
			driver.switchTo().frame(Login_page.login_frame(driver));
			if(Home_page.userid_txt(driver).isDisplayed()){
				Home_page.userid_txt(driver).sendKeys(user);
				Home_page.continue_btn(driver).click();
				driver.switchTo().defaultContent();
				driver.switchTo().frame(Login_page.login_frame(driver));
				js.executeScript("window.scrollBy(0,50)");
				if(Login_page.login_btn(driver).isDisplayed()){
					Login_page.pass_txt(driver).sendKeys(pass);
					act.click(Login_page.calc_chkbx(driver)).perform();
					act.click(Login_page.login_btn(driver)).perform();
					driver.switchTo().defaultContent();
					driver.switchTo().frame(Dashboard_page.amt_frame(driver));
					if(Dashboard_page.amt_txt(driver).isDisplayed()){
						saveScreen(".//Login_Sucess.png",driver);
					}
					else saveScreen(".//Login_Failure.png",driver);
				}
			}
		}catch(Exception e){
			System.out.println(e);
			System.out.println("Unable to Login in the System");
			saveScreen(".//Login_Failure.png",driver);
		}
		System.out.println("Login");
	}
	
	/*
	Description          : Validates the amount available in Dashboard with the user data
	Author               : Murali
	**************************************************************
	Change History
	Change date:
	Changed by:
	**************************************************************/
	@Test(priority=1)
	static void validateAmount() throws IOException{
		try{
		driver.switchTo().defaultContent();
		driver.switchTo().frame(Dashboard_page.amt_frame(driver));
		String givenamount = rd.exceldata(1, 3);
		String availableamt = Dashboard_page.amt_txt(driver).getText();
		System.out.println(availableamt.split("INR")[1].trim());
		if(givenamount.equals(availableamt.split("INR")[1].trim())){
			System.out.println("Amount Validated");
			saveScreen(".//Valid_Amount.png",driver);
		}else{
			System.out.println("Failed");
			saveScreen(".//Invalid_Amount.png",driver);
			throw new userexpection("Amount value is invalid");
		}
		}catch(Exception e){
			saveScreen(".//Unable to validatet the amount.png",driver);
		}
	}
	/*
	Description          : Validate the Given Benificiaries Names
	Author               : Murali
	**************************************************************
	Change History
	Change date:
	Changed by:
	**************************************************************/
	@Test(priority=2)
	static void validateBenifiary() throws IOException{
		navigateFundpage();
		String benefiary_Names = rd.exceldata(1, 4);
		String[] names = benefiary_Names.split(",");
		act.click(View_Beneficiary_page.enquire_elm(driver)).perform();
		act.click(View_Beneficiary_page.view_benift(driver)).perform();
		driver.switchTo().defaultContent().switchTo().frame(Add_Beneficiary_page.main_frame(driver));
		View_Beneficiary_page.ntfs_view_btn(driver).click();
		for(String name:names){
		try{
			if(View_Beneficiary_page.check_name(driver, name).isDisplayed()){
			System.out.println("Name "+name+" is available");
			act.moveToElement(View_Beneficiary_page.check_name(driver, name)).perform();
			saveScreen(".//"+name+".png",driver);
		}
		}catch(Exception e){
			System.out.println("Name "+name+" is not available");
			saveScreen(".//"+name+"not available.png",driver);
		}
		}
	}
	
	/*
	Description          : Add the new Benificiary
	Author               : Murali
	**************************************************************
	Change History
	Change date:
	Changed by:
	**************************************************************/
	@Test(priority=3)
	static void addBenifiary() throws IOException{
		navigateFundpage();
		String accName = rd.exceldata(1, 5),accNo = rd.exceldata(1, 6),ifsc = rd.exceldata(1, 7),email = rd.exceldata(1, 8);;
		act.click(Add_Beneficiary_page.req_elm(driver)).perform();
		act.click(Add_Beneficiary_page.add_benift(driver)).perform();
		driver.switchTo().defaultContent().switchTo().frame(Add_Beneficiary_page.main_frame(driver));
		try{
		Add_Beneficiary_page.ntfs_go_btn(driver).click();
		Add_Beneficiary_page.beni_acc_no(driver).sendKeys(accNo);
		Add_Beneficiary_page.beni_acc_no1(driver).sendKeys(accNo);
		Add_Beneficiary_page.beni_ifsc(driver).sendKeys(ifsc);
		Add_Beneficiary_page.beni_name(driver).sendKeys(accName);
		Add_Beneficiary_page.beni_email(driver).sendKeys(email);
		Add_Beneficiary_page.beni_add_btn(driver).click();
		if(Add_Beneficiary_page.beni_confirm_btn(driver).isDisplayed()){
			System.out.println("Beneficiary adding process has been initiated for the User "+accName);
			saveScreen(".//"+accName+"benificiary_add.png",driver);
		}
		}
		catch(Exception e)
		{
			System.out.println(" Unable to add the "+accName);
			saveScreen(".//"+accName+"not able to add.png",driver);
		}
	}
	
	/*
	Description          : Navigates to TransferFund Page
	Author               : Murali
	**************************************************************
	Change History
	Change date:
	Changed by:
	**************************************************************/
	static void navigateFundpage(){
		driver.switchTo().defaultContent();
		driver.switchTo().frame(Add_Beneficiary_page.comnmenu_frame(driver));
		act.click(Add_Beneficiary_page.fund_trans(driver)).perform();
		driver.switchTo().defaultContent();
		driver.switchTo().frame(Add_Beneficiary_page.benifit_leftframe(driver));
	}

	/*
	Description          : Takes Browser Screen and store in give Path
	Author               : Murali
	**************************************************************
	Change History
	Change date:
	Changed by:
	**************************************************************/
	public static void saveScreen(String path,WebDriver driver) throws IOException{
		File img = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(img, new File(path));
	}

}
/*
Description          : User Defined Exception to fail TestNG
Author               : Murali
**************************************************************
Change History
Change date:
Changed by:
**************************************************************/
class userexpection extends Exception{
	public userexpection(String error){
		//super(error);
		System.out.print("Exception Occured "+error);
	}
}
