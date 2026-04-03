package utilities;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.logging.Level;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import pageObjects.BrandPage;
import pageObjects.CategoryPage;
import pageObjects.TagPage;
import pageObjects.ProductAttributesPage;
import pageObjects.ProductsPage;
import pageObjects.CustomerLogin;
import pageObjects.DashBoard;
import pageObjects.ForgotPassword;
import pageObjects.HomePage;
import pageObjects.MerchantLogin;
import pageObjects.RegisterPage;

public class baseClass { 
	
	public WebDriver driver;
	public HomePage homePage;
	public CustomerLogin customerLogin;
	public RegisterPage register;
	public MerchantLogin merchantLogin;
	public ForgotPassword forgotPassword;
	public DashBoard dashboard;
	public CategoryPage category;
	public BrandPage brand;
	public TagPage tag;
	public ProductAttributesPage productAttributes;
	public ProductsPage products;
	public Logger logger;
	public DataGenerator data;
	
	public Properties p;
	
	@BeforeClass
	public void setUp() throws IOException {
		
		//loading config properties files
		FileReader file = new FileReader("./src//test//resources//config.properties");
		p = new Properties();
		p.load(file);
		
		//loading logger
		logger = LogManager.getLogger(this.getClass());
		
		LoggingPreferences logPrefs = new LoggingPreferences();
		logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
		ChromeOptions options = new ChromeOptions();
		options.setCapability("goog:loggingPrefs", logPrefs);
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get(p.getProperty("backendURL"));
		homePage = new HomePage(driver);
		customerLogin = new CustomerLogin(driver);
		register = new RegisterPage(driver);
		merchantLogin = new MerchantLogin(driver);
		forgotPassword = new ForgotPassword(driver);
		dashboard = new DashBoard(driver);
		category = new CategoryPage(driver);
		brand = new BrandPage(driver);
		tag = new TagPage(driver);
		productAttributes = new ProductAttributesPage(driver);
		products = new ProductsPage(driver);
		data = new DataGenerator();
	}
	
	
	@AfterClass
	public void tearDown() throws InterruptedException {
		Thread.sleep(500);
		driver.quit();
	}
}
