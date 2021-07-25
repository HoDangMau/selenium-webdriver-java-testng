package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic06_Web_Element_Command_I {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", ".\\browserDrivers\\geckodriver.exe");
		// System.setProperty("webdriver.chrome.driver",".\\browserDrivers\\chromedriver.exe");
		// driver = new ChromeDriver();
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_Elements() {
		//WebBrowser command/method/API
		//driver instance/variable
		
		//WebElement command/method/API
		//one times
		driver.findElement(By.name("login")).click();
		
		//many times
		WebElement emailTextbox = driver.findElement(By.id("email"));
		emailTextbox.clear();
		emailTextbox.sendKeys("abc@gmail.com");
		emailTextbox.isDisplayed();
		
		WebElement element = driver.findElement(By.id(""));
		
		//Delete data in editable field(textbox/textarea/dropdown)
		element.clear(); //**
		
		//Enter data in editable field
		element.sendKeys("aad");
		element.sendKeys(Keys.ENTER); //**
		
		//Click
		element.click(); //**
		
		//Return data in attribute of element
		element.getAttribute("placeholder"); //**
		element.getAttribute("value");
		
		//Rerurn css value: font, color
		element.getCssValue("font"); //**
		
		//GUI
		element.getLocation();
		element.getRect();
		element.getSize();
		
		//Take screenshot => Attach to HTML report
		element.getScreenshotAs(OutputType.FILE); //**
		element.getScreenshotAs(OutputType.BASE64);
		element.getScreenshotAs(OutputType.BYTES);

		//Tagname HTML
		//Use By.id/class/css/name
		//Output of this step => Input of that step
		element = driver.findElement(By.cssSelector("#save-info-button"));
		String saveButtonTagname = element.getTagName();
		driver.findElement(By.xpath("//"+ saveButtonTagname +"[@name='email']"));
		
		String addressName = "123 Ly Thuong Kiet";
		String cityName = "Ho Chi Minh";
		System.out.println(addressName + cityName);
		System.out.println(addressName.concat(cityName));
		System.out.println(addressName + "-" + cityName);
		
		//Get text of header/link/label/error/success message
		element.getText(); //**
		
		//Verify 1 element
		Assert.assertTrue(element.isDisplayed()); //**
		element.isEnabled(); //**
		element.isSelected(); //**
		
		//Submit 1 form
		element.submit();	
		
	}

	@Test
	public void TC_02() {

	}

	@Test
	public void TC_03() {

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}