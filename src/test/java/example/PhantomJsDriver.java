package example;
import org.testng.annotations.Test;

import com.google.common.base.Predicate;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
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
import org.openqa.selenium.support.ui.FluentWait;
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


//	private void waitUntilSelectOptionsPopulated(final Select select) {
//        new FluentWait<WebDriver>(driver)
//                .withTimeout(60, TimeUnit.SECONDS)
//                .pollingEvery(10, TimeUnit.MILLISECONDS)
//                .until(new Predicate<WebDriver>() {
//                    public boolean apply(WebDriver d) {
//                        return (select.getOptions().size() > 1);
//                    }
//                });
//    }
	
	@BeforeTest
	public void beforeTest() throws MalformedURLException {	

		//PhantomJs Driver
		System.setProperty("phantomjs.binary.path", "phantomjs");
		String[] cli_args = new String[]{ "--ignore-ssl-errors=true" };
		DesiredCapabilities caps = DesiredCapabilities.phantomjs();
		caps.setCapability("takeScreenshot", "false");
		caps.setCapability( PhantomJSDriverService.PHANTOMJS_CLI_ARGS, cli_args );
		caps.setCapability( PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "phantomjs");
		this.driver =  new PhantomJSDriver( caps );

		//driver.manage().window().maximize();
		driver.manage().window().setSize(new Dimension(1920,1080));
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		//driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS); 
	}

	@AfterTest
	public void afterTest(){

		driver.quit();

	}
	@Test				
	public void RajaSahib() {

		//OPEN WEBSITE
		driver.get("http://www.rajasahib.com/");
		Logger logger = Logger.getLogger("");
		logger.setLevel(Level.OFF);

		System.out.println("Page title is: " + driver.getTitle());
		//,"SALE","BRANDS"
		List<String> BrandNames = Arrays.asList("DESIGNER FABRICS","SALE");

		Random randomString = new Random();
		String randomBrand = BrandNames.get(randomString.nextInt(BrandNames.size()));
		driver.findElement(By.linkText(randomBrand)).click();
		System.out.println("Randomly selected tab is: " + randomBrand);

		if(randomBrand==BrandNames.get(0))
		{
			//SELECT A RANDOM CATEGORY
			List<WebElement> allCategories = driver.findElements(By.cssSelector("#Designer-Fabrics a.level0"));
			System.out.println("print the allCategories.size() "+allCategories.size());
			System.out.println("print the selected allCategories "+allCategories);
			Random random2 = new Random();
			WebElement randomCategory = allCategories.get(random2.nextInt(allCategories.size()));
			System.out.println("print the selected randomCategory "+randomCategory);
			WebDriverWait rc=new WebDriverWait(driver,100);
			rc.until(ExpectedConditions.visibilityOf(randomCategory));
			randomCategory.click();

			System.out.println("Page title is: " + driver.getTitle());
			
			//SELECT A RANDOM PRODUCT	
			List<WebElement> allProducts = driver.findElements(By.cssSelector("a.product-image"));
			System.out.println("print the allProducts "+allProducts);
			System.out.println("print the allProducts.size() "+allProducts.size());
			Random random3 = new Random();
			WebElement randomProduct = allProducts.get(random3.nextInt(allProducts.size()));
			randomProduct.click();
			System.out.println("Random product is clicked");
			
			//Add To Cart
			WebDriverWait waitProduct = new WebDriverWait(driver, 100);
			waitProduct.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.button.btn-cart"))).click();

			//CheckOut
			WebDriverWait waitCheckOut=new WebDriverWait(driver,100);
			waitCheckOut.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("PLACE ORDER"))).click();
		}
		else if(randomBrand==BrandNames.get(1))
		{
			//SELECT A RANDOM CATEGORY
			List<WebElement> allCategories = driver.findElements(By.cssSelector("#Sale a.level0"));
			System.out.println("print the allCategories.size() "+allCategories.size());
			System.out.println("print the selected allCategories "+allCategories);
			Random random2 = new Random();
			WebElement randomCategory = allCategories.get(random2.nextInt(allCategories.size()));
			System.out.println("print the selected randomCategory "+randomCategory);
			randomCategory.click();
			
			System.out.println("Page title is: " + driver.getTitle());
			
			//SELECT A RANDOM PRODUCT	
			List<WebElement> allProducts = driver.findElements(By.cssSelector("a.product-image"));
			System.out.println("print the allProducts "+allProducts);
			System.out.println("print the allProducts.size() "+allProducts.size());
			Random random3 = new Random();
			WebElement randomProduct = allProducts.get(random3.nextInt(allProducts.size()));
			randomProduct.click();
			System.out.println("Random product is clicked");

			//Add To Cart
			WebDriverWait waitProduct = new WebDriverWait(driver, 100);
			waitProduct.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.button.btn-cart"))).click();

			//CheckOut
			WebDriverWait waitCheckOut=new WebDriverWait(driver,100);
			waitCheckOut.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("PLACE ORDER"))).click();
		}
//		else 
//			if(randomBrand==BrandNames.get(2))
//			{		
//			//SELECT A RANDOM CATEGORY
//			List<WebElement> allCategories = driver.findElements(By.cssSelector("li#Brands ul.level0 a.level1"));
//			System.out.println("print the allCategories.size() "+allCategories.size());
//			System.out.println("print the selected product "+allCategories);
//			Random random2 = new Random();
//			WebElement randomCategory = allCategories.get(random2.nextInt(allCategories.size()));
//			
//			WebDriverWait wait = new WebDriverWait(driver, 100);
//			wait.until(ExpectedConditions.visibilityOf(randomCategory));
//			randomCategory.click();
//
////			Actions actions = new Actions(driver);
////			actions.moveToElement(randomCategory).click().perform();
//			
//			System.out.println("Page title is: " + driver.getTitle());
//
//			//SELECT A RANDOM PRODUCT	
//			List<WebElement> allProducts = driver.findElements(By.cssSelector("a.product-image"));
//			System.out.println("Print the allProducts "+allProducts);
//			System.out.println("Print the allProducts.size() "+allProducts.size());
//			Random random3 = new Random();
//			WebElement randomProduct = allProducts.get(random3.nextInt(allProducts.size()));
//			randomProduct.click();
//			System.out.println("print the selected randomCategory ");
//
//			WebDriverWait waitProduct = new WebDriverWait(driver, 100);
//			waitProduct.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.button.btn-cart"))).click();
//
//			WebDriverWait waitCheckOut=new WebDriverWait(driver,100);
//			waitCheckOut.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("PLACE ORDER"))).click();
//		}


		//FILL IN THE BILLING INFORMATION

		WebDriverWait waitFirstName=new WebDriverWait(driver,100);
		waitFirstName.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='billing:firstname']"))).sendKeys("test");

		System.out.println("FirstName is Enterd");

		WebDriverWait waitLastName=new WebDriverWait(driver,100);
		waitLastName.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='billing:lastname']"))).sendKeys("test");
		System.out.println("LastName is Enterd");

		WebDriverWait waitEmail = new WebDriverWait(driver, 100);
		waitEmail.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='bill_form']/div[3]/div[1]/input"))).sendKeys("test@gmail.com");
		System.out.println("Email is Enterd");

		WebDriverWait waitConfirmEmail = new WebDriverWait(driver, 100);
		waitConfirmEmail.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='billing:confirm_email']"))).sendKeys("test@gmail.com");
		System.out.println("Email is Confirmed");

		WebDriverWait waitAddress = new WebDriverWait(driver, 100);
		waitAddress.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='billing:street1']"))).sendKeys("test");

		System.out.println("Street is Enterd");

		Select oSelect2 = new Select(driver.findElement(By.xpath("//*[@id='billing:country_id']")));
		oSelect2.selectByVisibleText("Pakistan");
		System.out.println("Country is selected");

		Select oSelect3 = new Select(driver.findElement(By.xpath("//*[@id='billing:city']")));
		oSelect3.selectByIndex(3);
		System.out.println("City is Enterd");

		WebDriverWait waitPhoneNumber = new WebDriverWait(driver, 100);
		waitPhoneNumber.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='billing:telephone']"))).sendKeys("03001234567");

		System.out.println("Phone number is Enterd");

		WebDriverWait waitTel2 = new WebDriverWait(driver, 100);
		waitTel2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='tel2']")));

		WebElement ConfirmMobileNumber = driver.findElement(By.xpath("//*[@id='tel2']"));

		ConfirmMobileNumber.sendKeys("03001234567");
		System.out.println("Phone number is confirmed");

		//driver.findElement(By.xpath("html/body/div[1]/div[3]/div/div/div[2]/div[3]/form/div/div[1]/div[2]/ul/li/input")).click();
		//driver.findElement(By.xpath("//input[contains(@title,'Ship to this address')]")).click();
		//driver.findElement(By.id("shipping:same_as_billing")).click();

		//WebElement checkbox= driver.findElement(By.name("shipping[same_as_billing]"));
		//checkbox.click();

		//driver.findElement(By.xpath("//input[@onclick='shipping.setSameAsBilling(this.checked)']")).click();
		//driver.findElement(By.xpath("//input[contains(@title,'Ship to this address')]")).click();
		//driver.findElement(By.linkText("Ship to this address")).click();

		//FILL IN THE Shipping INFORMATION

		WebDriverWait waitFirstNam=new WebDriverWait(driver,50);
		waitFirstNam.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='shipping:firstname']"))).sendKeys("tests");

		System.out.println("Shipping FirstName is Enterd");

		WebDriverWait waitLastNam=new WebDriverWait(driver,50);
		waitLastNam.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='shipping:lastname']"))).sendKeys("tests");
		System.out.println("Shipping LastName is Enterd");

		WebDriverWait waitAddresss = new WebDriverWait(driver, 50);
		waitAddresss.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='shipping:street1']"))).sendKeys("tests");

		System.out.println("Shipping Street is Enterd");

		Select oSelectp = new Select(driver.findElement(By.xpath("//*[@id='shipping:country_id']")));
		oSelectp.selectByVisibleText("Pakistan");
		System.out.println("Shipping Country is selected");

		Select oSelectc = new Select(driver.findElement(By.xpath("//*[@id='shipping:city']")));
		oSelectc.selectByIndex(3);
		System.out.println("Shipping City is Enterd");
		
		WebDriverWait waitPhoneNumbers = new WebDriverWait(driver, 50);
		waitPhoneNumbers.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='shipping:telephone']"))).sendKeys("03001231231");
		System.out.println("Shipping Phone number is Enterd");

		WebDriverWait waitTel2s = new WebDriverWait(driver, 50);
		waitTel2s.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='tel3']"))).sendKeys("03001231231");
		System.out.println("Shipping Phone number is confirmed");

		//SELECT CASH ON DELEIVERY
		//*[@id="p_method_cashondelivery"]
		driver.findElement(By.id("p_method_cashondelivery")).click();

		//PLACE ORDER
		WebDriverWait wait3 = new WebDriverWait(driver, 100);
		wait3.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='review-buttons-container']/button")));
		driver.findElement(By.xpath("//*[@id='review-buttons-container']/button")).click();
		//driver.findElement(By.xpath("//*[@id='review-buttons-container']/button")).click();

		System.out.println("Place Order Now Button is clicked");
	}
	@Test				
	public void HouseOfIthad() {

		Logger logger = Logger.getLogger("");
		logger.setLevel(Level.OFF);

		//OPEN BEACHTREE WEBSITE
		driver.get("http://www.houseofittehad.com/");
		//driver.get("http://www.beechtree.pk");

		driver.findElement(By.xpath("html/body/div/div[1]/div[2]/div/div[1]/span")).click();
		System.out.println("Page title is: " + driver.getTitle());

		List<String> brandNames = Arrays.asList("ROYAL EMBROIDERED SHAWLS", "GERMAN KHADDER", "GERMAN LINEN", "ROYAL EMBROIDERED");

		Random randomCategories = new Random();
		String randomSize = brandNames.get(randomCategories.nextInt(brandNames.size()));
		driver.findElement(By.linkText(randomSize)).click();
		System.out.println("Randomly selected label is : " + randomSize);

		if(randomSize==brandNames.get(0))
		{
			System.out.println("Page title is: " + driver.getTitle());
			//SELECT A RANDOM PRODUCT
			List<WebElement> allProducts = driver.findElements(By.cssSelector("a.product-image"));
			Random random2 = new Random();
			WebElement randomProduct = allProducts.get(random2.nextInt(allProducts.size()));
			System.out.println("print the selected product "+randomProduct);
			randomProduct.click();

			WebDriverWait waitProduct = new WebDriverWait(driver, 100);
			waitProduct.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("html/body/div[1]/div[3]/div/div[2]/div[2]/div[1]/form/div[3]/div[5]/div/div/div/div[2]/button")));
			driver.findElement(By.xpath("html/body/div[1]/div[3]/div/div[2]/div[2]/div[1]/form/div[3]/div[5]/div/div/div/div[2]/button")).click();

			WebDriverWait waitCheckOut=new WebDriverWait(driver,50);
			waitCheckOut.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='header-cart']/div[3]/div[3]/div/a[1]/span"))).click();


		}
		else if(randomSize==brandNames.get(1))
		{
			System.out.println("Page title is: " + driver.getTitle());
			//SELECT A RANDOM PRODUCT
			List<WebElement> allProducts = driver.findElements(By.cssSelector("a.product-image"));
			Random random2 = new Random();
			WebElement randomProduct = allProducts.get(random2.nextInt(allProducts.size()));
			System.out.println("print the selected product "+randomProduct);
			randomProduct.click();

			WebDriverWait waitProduct = new WebDriverWait(driver, 100);
			waitProduct.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("html/body/div[1]/div[3]/div/div[2]/div[2]/div[1]/form/div[3]/div[5]/div/div/div/div[2]/button")));
			driver.findElement(By.xpath("html/body/div[1]/div[3]/div/div[2]/div[2]/div[1]/form/div[3]/div[5]/div/div/div/div[2]/button")).click();

			WebDriverWait waitCheckOut=new WebDriverWait(driver,50);
			waitCheckOut.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='header-cart']/div[3]/div[3]/div/a[1]/span"))).click();

		}
		else if(randomSize==brandNames.get(2))
		{		
			//SELECT A RANDOM PRODUCT
			List<WebElement> allProducts = driver.findElements(By.cssSelector("a.product-image"));
			Random random2 = new Random();
			WebElement randomProduct = allProducts.get(random2.nextInt(allProducts.size()));
			System.out.println("print the selected product "+randomProduct);
			randomProduct.click();

			WebDriverWait waitProduct = new WebDriverWait(driver, 100);
			waitProduct.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("html/body/div[1]/div[3]/div/div[2]/div[2]/div[1]/form/div[3]/div[5]/div/div/div/div[2]/button")));
			driver.findElement(By.xpath("html/body/div[1]/div[3]/div/div[2]/div[2]/div[1]/form/div[3]/div[5]/div/div/div/div[2]/button")).click();

			WebDriverWait waitCheckOut=new WebDriverWait(driver,50);
			waitCheckOut.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='header-cart']/div[3]/div[3]/div/a[1]/span"))).click();

		}
		else if(randomSize==brandNames.get(3))
		{
			System.out.println("Page title is: " + driver.getTitle());
			//SELECT A RANDOM PRODUCT
			List<WebElement> allProducts = driver.findElements(By.cssSelector("a.product-image"));
			Random random2 = new Random();
			WebElement randomProduct = allProducts.get(random2.nextInt(allProducts.size()));
			System.out.println("print the selected product "+randomProduct);
			randomProduct.click();

			WebDriverWait waitProduct = new WebDriverWait(driver, 100);
			waitProduct.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("html/body/div[1]/div[3]/div/div[2]/div[2]/div[1]/form/div[3]/div[5]/div/div/div/div[2]/button")));
			driver.findElement(By.xpath("html/body/div[1]/div[3]/div/div[2]/div[2]/div[1]/form/div[3]/div[5]/div/div/div/div[2]/button")).click();

			WebDriverWait waitCheckOut=new WebDriverWait(driver,50);
			waitCheckOut.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='header-cart']/div[3]/div[3]/div/a[1]/span"))).click();

		}

		//FILL IN THE BILLING INFORMATION

		WebDriverWait waitFirstName=new WebDriverWait(driver,50);
		waitFirstName.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='billing:firstname']"))).sendKeys("test");

		System.out.println("FirstName is Enterd");

		WebDriverWait waitLastName=new WebDriverWait(driver,50);
		waitLastName.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='billing:lastname']"))).sendKeys("test");
		System.out.println("LastName is Enterd");

		WebDriverWait waitEmail = new WebDriverWait(driver, 50);
		waitEmail.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='bill_form']/div[3]/div[1]/input"))).sendKeys("test@gmail.com");
		System.out.println("Email is Enterd");

		WebDriverWait waitConfirmEmail = new WebDriverWait(driver, 50);
		waitConfirmEmail.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='billing:confirm_email']"))).sendKeys("test@gmail.com");
		System.out.println("Email is Confirmed");

		WebDriverWait waitAddress = new WebDriverWait(driver, 50);
		waitAddress.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='billing:street1']"))).sendKeys("test");

		System.out.println("Street is Enterd");

		WebDriverWait waitAddress2 = new WebDriverWait(driver, 50);
		waitAddress2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='billing:street2']"))).sendKeys("test");

		System.out.println("Street2 is Enterd");

		Select oSelect2 = new Select(driver.findElement(By.xpath("//*[@id='billing:country_id']")));
		oSelect2.selectByVisibleText("Pakistan");
		System.out.println("Country is selected");

		Select oSelect3 = new Select(driver.findElement(By.xpath("//*[@id='billing:city']")));
		oSelect3.selectByIndex(3);
		System.out.println("City is Enterd");

		WebDriverWait waitZipCode = new WebDriverWait(driver, 50);
		waitZipCode.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='billing:postcode']"))).sendKeys("test");

		System.out.println("ZipCode is Enterd");

		WebDriverWait waitPhoneNumber = new WebDriverWait(driver, 50);
		waitPhoneNumber.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='billing:telephone']"))).sendKeys("03001234567");

		System.out.println("Phone number is Enterd");

//		WebDriverWait waitTel2 = new WebDriverWait(driver, 50);
//		waitTel2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='tel2']")));
//
//		WebElement ConfirmMobileNumber = driver.findElement(By.xpath("//*[@id='tel2']"));
//
//		ConfirmMobileNumber.sendKeys("03001234567");
//		System.out.println("Phone number is confirmed");
		
		//Check Box Element
		//driver.findElement(By.xpath("html/body/div[1]/div[3]/div/div/div[2]/div[3]/form/div/div[1]/div[2]/ul/li/input")).click();
		//driver.findElement(By.xpath("//input[contains(@title,'Ship to this address')]")).click();
		//driver.findElement(By.id("shipping:same_as_billing")).click();

		//WebElement checkbox= driver.findElement(By.name("shipping[same_as_billing]"));
		//checkbox.click();

		//driver.findElement(By.xpath("//input[@onclick='shipping.setSameAsBilling(this.checked)']")).click();
		//driver.findElement(By.xpath("//input[contains(@title,'Ship to this address')]")).click();
		//driver.findElement(By.linkText("Ship to this address")).click();

		
		//FILL IN THE Shipping INFORMATION
		WebDriverWait waitFirstNam=new WebDriverWait(driver,50);
		waitFirstNam.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='shipping:firstname']"))).sendKeys("tests");

		System.out.println("Shipping FirstName is Enterd");

		WebDriverWait waitLastNam=new WebDriverWait(driver,50);
		waitLastNam.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='shipping:lastname']"))).sendKeys("tests");
		System.out.println("Shipping LastName is Enterd");

		WebDriverWait waitAddresss = new WebDriverWait(driver, 50);
		waitAddresss.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='shipping:street1']"))).sendKeys("tests");

		System.out.println("Shipping Street is Enterd");

		WebDriverWait waitAddresss2 = new WebDriverWait(driver, 50);
		waitAddresss2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='shipping:street2']"))).sendKeys("tests");

		System.out.println("Shipping Street2 is Enterd");

		Select oSelectp = new Select(driver.findElement(By.xpath("//*[@id='shipping:country_id']")));
		oSelectp.selectByVisibleText("Pakistan");
		System.out.println("Shipping Country is selected");

		Select oSelectc = new Select(driver.findElement(By.xpath("//*[@id='shipping:city']")));
		oSelectc.selectByIndex(3);
		System.out.println("Shipping City is Enterd");

		WebDriverWait waitRegions=new WebDriverWait(driver,50);
		waitRegions.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='shipping:region']"))).sendKeys("tests");
		System.out.println("Shipping Region is Enterd");

		WebDriverWait waitZipCodes = new WebDriverWait(driver, 50);
		waitZipCodes.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("html/body/div[1]/div[3]/div/div/div[2]/div[3]/form/div/div[1]/div[2]/div[3]/div[2]/div/div[8]/div[1]/div/input"))).sendKeys("tests");

		System.out.println("Shipping ZipCode is Enterd");

		WebDriverWait waitPhoneNumbers = new WebDriverWait(driver, 50);
		waitPhoneNumbers.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("html/body/div[1]/div[3]/div/div/div[2]/div[3]/form/div/div[1]/div[2]/div[3]/div[2]/div/div[8]/div[2]/div/input"))).sendKeys("03001231231");

		System.out.println("Shipping Phone number is Enterd");

//		WebDriverWait waitTel2s = new WebDriverWait(driver, 50);
//		waitTel2s.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("html/body/div[1]/div[3]/div/div/div[2]/div[3]/form/div/div[1]/div[2]/div[3]/div[2]/div/div[8]/div[3]/div/input"))).sendKeys("03001231231");
//		System.out.println("Shipping Phone number is confirmed");

		//*[@id="shipping:same_as_billing"]
		//		if ( !driver.findElement(By.xpath("//*[@id='shipping:same_as_billing']")).isSelected() )
		//		{
		//
		//			System.out.println("Shipping to same Address");
		//		}

		//html/body/div[1]/div[3]/div/div/div[2]/div[3]/form/div/div[1]/div[2]/ul/li/input

		//PLACE ORDER
		WebDriverWait wait3 = new WebDriverWait(driver, 100);
		wait3.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='review-buttons-container']/button")));
		driver.findElement(By.xpath("//*[@id='review-buttons-container']/button")).click();
		//driver.findElement(By.xpath("//*[@id='review-buttons-container']/button")).click();

		System.out.println("Place Order Now Button is clicked");

		//CLOSE THE BROWSER
		//		WebDriverWait waitForOrder = new WebDriverWait(driver, 200);
		//		waitForOrder.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='resend_btn']")));
		//		System.out.println("Order is successfully is placed");
		//		System.out.println("Title of the page is After Reviw Button Clicked " + driver.getTitle());
	}

}
