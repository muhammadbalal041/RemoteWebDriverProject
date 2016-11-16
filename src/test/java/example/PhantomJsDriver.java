package example;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import java.io.File;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import java.net.MalformedURLException;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;

public class PhantomJsDriver {
	
	WebDriver driver;
	boolean outOfStock = false;
	public static final String KEY = "1aa8518808db386059c92274ed4466c2";
	public static final String SECRET = "f1359f37086a31e9d6304f52b27d2e8e";
	public static final String URL = "http://" + KEY + ":" + SECRET + "@hub.testingbot.com/wd/hub";

	@BeforeTest
	public void beforeTest() throws MalformedURLException {	
		
		//Firefox Driver
//		String Xport = System.getProperty("lmportal.xvfb.id", ":1");
//		System.setProperty("firefox.gecko.driver", "geckodriver");
//		final File firefoxPath = new File(System.getProperty("lmportal.deploy.firefox.path", "/usr/bin/firefox"));
//		FirefoxBinary firefoxBinary = new FirefoxBinary(firefoxPath);
//		firefoxBinary.setEnvironmentProperty("DISPLAY", Xport);
//		driver = new FirefoxDriver(firefoxBinary, null); 
		
		//chrome remote Driver
		//System.setProperty("webdriver.chrome.driver", "chromedriver");
		//driver=new ChromeDriver();
//		DesiredCapabilities caps = new DesiredCapabilities();
//		caps.setCapability("platform", "LINUX");
//		//caps.setCapability("version", "54");
//		caps.setCapability("browserName", "chrome");
//		driver = new RemoteWebDriver(new URL(URL), caps);
		
		//PhantomJs Driver
		System.setProperty("phantomjs.binary.path", "phantomjs");
		String[] cli_args = new String[]{ "--ignore-ssl-errors=true" };
		DesiredCapabilities caps = DesiredCapabilities.phantomjs();
		caps.setCapability("takeScreenshot", "false");
		caps.setCapability( PhantomJSDriverService.PHANTOMJS_CLI_ARGS, cli_args );
		caps.setCapability( PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "phantomjs");
		driver =  new PhantomJSDriver( caps );

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		//driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS); 
	}
	
	@AfterTest
	public void afterTest(){
	
		driver.quit();

	}
	
	@Test				
	public void LeisureClub() {

		Logger logger = Logger.getLogger("");
		logger.setLevel(Level.OFF);

		//OPEN BEACHTREE WEBSITE
		driver.get("http://www.leisureclub.pk/");
		//driver.get("http://www.beechtree.pk");

		System.out.println("Page title is: " + driver.getTitle());

		driver.findElement(By.xpath("html/body/div[1]/div/div[3]/div/div[1]/span")).click();
		//driver.findElement(By.cssSelector("span.close")).click();

		List<WebElement> allCategories = driver.findElements(By.cssSelector("div.landing-content"));
		Random random1 = new Random();
		WebElement randomCategory = allCategories.get(random1.nextInt(allCategories.size()));

		String temp = randomCategory.getText(); 
		System.out.println("print the selected Category "+temp);
		randomCategory.click();

		//SELECT A RANDOM PRODUCT
		List<WebElement> allProducts = driver.findElements(By.cssSelector("a.product-image"));
		System.out.println("Print the allProducts "+allProducts);
		System.out.println("print the allProducts.size() "+allProducts.size());
		Random random2 = new Random();
		WebElement randomProduct = allProducts.get(random2.nextInt(allProducts.size()));
		System.out.println("Random product is "+randomProduct);

		// WebElement element = driver.findElement(By("element_path"));

		Actions actions = new Actions(driver);

		actions.moveToElement(randomProduct).click().perform();

		//WebDriverWait waitForProduct = new WebDriverWait(driver, 100);
		//waitForProduct.until(ExpectedConditions.visibilityOf(randomProduct));
		//randomProduct.click();
		System.out.println("Random product is clicked");

		//WebDriverWait waitSwatch = new WebDriverWait(driver, 50);
		//waitSwatch.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='showChart']/span")));
		//*[@id="product-options-wrapper"]/dl[1]/dt/label/text()

		List<WebElement> allsizes = driver.findElements(By.cssSelector("span[class='swatch']"));
		System.out.println("allsizes is "+allsizes);
		Random random3 = new Random();
		WebElement randomSize = allsizes.get(random3.nextInt(allsizes.size()));
		if(!allsizes.isEmpty())//if the size is availabe,click/select it
		{
			randomSize.click();
			System.out.println("Random size is clicked");
		}
		else//if the sizes are not available, print on console that the product is out of stock
		{
			System.out.println("the item selected is out of stock");
			outOfStock = true;
		}
		//SELECT QUANTITY = 1
		//Select oSelect = new Select(driver.findElement(By.xpath("//*[@id='qty']")));
		//oSelect.selectByVisibleText("1");

		//ADD TO CART
		//*[@id="product_addtocart_form"]/div[4]/div[5]/div[3]/button/span/span
		WebElement addCart=driver.findElement(By.xpath("//*[@id='product_addtocart_form']/div[4]/div[5]/div[3]/button/span/span"));
		addCart.click();
		System.out.println("add to Cart button is clicked");

		//CHECKOUT
		WebDriverWait wait = new WebDriverWait(driver, 100);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='header-cart']/div[3]/div[3]/div/a")));
		WebElement CheckOut = driver.findElement(By.xpath("//*[@id='header-cart']/div[3]/div[3]/div/a"));
		if(!(CheckOut.isDisplayed()&& CheckOut.isEnabled()))
		{
			if(!CheckOut.isDisplayed())
			{
				System.out.println("CHECKOUT button is not displayed on the webpage");
			}
			if(!CheckOut.isEnabled())
			{
				System.out.println("CHECKOUT button is disabled on webpage");
			}
		}
		else
		{
			CheckOut.click();
			System.out.println("Checkout Button is clicked");

			//FILL IN THE BILLING INFORMATION
			driver.findElement(By.xpath("//*[@id='billing:firstname']")).sendKeys("test");
			System.out.println("First Name is Enterd");
			driver.findElement(By.xpath("//*[@id='billing:lastname']")).sendKeys("test");
			System.out.println("Last Name is Enterd");
			//*[@id="billing:email"]
			driver.findElement(By.xpath("//*[@id='billing:email']")).sendKeys("test@shopistan.pk");
			System.out.println("Email is Enterd");
			//*[@id="billing:confirm_email"]
			driver.findElement(By.xpath("//*[@id='billing:confirm_email']")).sendKeys("test@shopistan.pk");
			System.out.println("Email is confirmed");
			//*[@id="billing:street1"]
			driver.findElement(By.xpath("//*[@id='billing:street1']")).sendKeys("test");
			System.out.println("Street 1 is Enterd");
			//*[@id="billing:street2"]
			driver.findElement(By.xpath("//*[@id='billing:street2']")).sendKeys("test");
			System.out.println("Street2 is Enterd");
			driver.findElement(By.xpath("//*[@id='billing:postcode']")).sendKeys("test");
			System.out.println("Billing postcode is Enterd");

			//*[@id="billing:country_id"]
			//Select oSelect2 = new Select(driver.findElement(By.xpath("//*[@id='billing:country_id']")));
			//oSelect2.selectByVisibleText("PAKISTAN");

			//*[@id="billing:city"]
			//Select oSelect3 = new Select(driver.findElement(By.xpath("html/body/div[1]/div/div[5]/div/div/div[1]/div[5]/form[1]/div/ul/li[1]/fieldset/ul/li[7]/div[1]/div[1]/div/select")));
			//Select oSelect3 = new Select(driver.findElement(By.name("billing[city]")));
			//Select oSelect3 = new Select(driver.findElement(By.xpath("//*[@id='billing:city']")));
			//oSelect3.selectByIndex(1);

			driver.findElement(By.xpath("//*[@id='billing:city']")).sendKeys("Texas");;
			System.out.println("City is Enterd");

			//*[@id="billing:region_id"]
			Select oSelect3 = new Select(driver.findElement(By.xpath("//*[@id='billing:region_id']")));
			oSelect3.selectByIndex(1);
			//*[@id="billing:telephone"]
			driver.findElement(By.xpath("//*[@id='billing:telephone']")).sendKeys("03001234567");
			System.out.println("Telephone is Enterd");
			//*[@id="tel2"]
			//driver.findElement(By.xpath("//*[@id='tel2']")).sendKeys("03001234567");



			//SELECT CASH ON DELEIVERY
			//driver.findElement(By.id("p_method_cashondelivery")).click();

			//PLACE ORDER
			WebDriverWait wait3 = new WebDriverWait(driver, 200);
			wait3.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='review-buttons-container']/button")));
			driver.findElement(By.xpath("//*[@id='review-buttons-container']/button")).click();
			System.out.println("Place Order Now Button is Clicked");
		}

	}

}
