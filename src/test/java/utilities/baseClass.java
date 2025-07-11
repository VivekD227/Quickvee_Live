package utilities;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pageObjects.CustomerLogin;
import pageObjects.HomePage;
import pageObjects.MerchantLogin;
import pageObjects.RegisterPage;

public class baseClass { 
	
	public WebDriver driver;
	public HomePage homePage;
	public CustomerLogin customerLogin;
	public RegisterPage register;
	public MerchantLogin merchantLogin;
	
	public Logger logger;
	
	public Properties p;
	
	@BeforeMethod
	public void setUp() throws IOException {
		
		//loading config properties files
		FileReader file = new FileReader("./src//test//resources//config.properties");
		p = new Properties();
		p.load(file);
		
		//loading logger
		logger = LogManager.getLogger(this.getClass());
		
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get(p.getProperty("liveUrlFrontend"));
		homePage = new HomePage(driver);
		customerLogin = new CustomerLogin(driver);
		register = new RegisterPage(driver);
		merchantLogin = new MerchantLogin(driver);
	}
	
	
	@AfterMethod
	public void tearDown() throws InterruptedException {
		Thread.sleep(500);
		driver.quit();
	}
}
