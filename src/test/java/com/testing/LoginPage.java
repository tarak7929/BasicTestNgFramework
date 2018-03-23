package com.testing;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	
	public WebDriver driver;
	public String browser;
	//public WebDriverWait wait = new WebDriverWait(driver, 200);
	
	@FindBy(xpath = ".//*[@id='identifierId']")
    public static WebElement email;
	
	@FindBy(name = "password")
    public static WebElement password;
	
	@FindBy(id = "identifierNext")
    public static WebElement emailNext;
	
	@FindBy(id = "passwordNext")
    public static WebElement passwordNext;
	
	@FindBy(xpath = ".//*[@id='password']/div[2]/div[2]")
    public static WebElement passwordError;
	
	@FindBy(xpath = ".//*[@class='dEOOab RxsGPe']")
    public static WebElement EmailError;
	
	public void openBrowser() throws IOException {
		String path = System.getProperty("user.dir");
		FileInputStream fs = new FileInputStream(path + "\\Data\\prop.properties");

		Properties prop = new Properties();
		prop.load(fs);
		
		browser = prop.getProperty("browser"); 
		
		if(browser.equals("firefox")) {
			driver = new FirefoxDriver();
		}else if(browser.equals("Chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:/SeleniumJars/chromedriver.exe");
			driver = new ChromeDriver();
		}else {
			System.setProperty("webdriver.ie.driver", "C:/SeleniumJars/IEDriverServer.exe");
		    driver = new InternetExplorerDriver();
		}
		
		PageFactory.initElements(driver, this);
	}
	
	public void closeBrowser() {
		driver.quit();
	}
	
	public void openGmail() {
		driver.get("https://accounts.google.com/signin/v2/identifier?service=mail&passive=true&rm=false&continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&ss=1&scc=1&ltmpl=default&ltmplcache=2&emr=1&osid=1&flowName=GlifWebSignIn&flowEntry=ServiceLogin");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	public void enterEmail(String Email) throws InterruptedException {
		email.sendKeys(Email);
		emailNext.click();
		
		Thread.sleep(4000);
	}
	
	public void enterPassword(String Password) throws InterruptedException {
		password.sendKeys(Password);
		passwordNext.click();
		
		Thread.sleep(4000);
	}
	
	public String readEmailErrMsg() {
		String actualErrMsg = EmailError.getText();
		return actualErrMsg;
	}

	public String readPasswordErrMsg() {
		String actualErrMsg = passwordError.getText();
		return actualErrMsg;
	}
}
