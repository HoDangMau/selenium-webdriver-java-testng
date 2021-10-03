package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic15_JavascriptExecutor {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
		jsExecutor = (JavascriptExecutor) driver;

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	//@Test
	public void TC_01_LiveGuru() {
		navigateToUrlByJS("http://live.techpanda.org/");
		
		String liveGuruDomain = (String) executeForBrowser("return document.domain;");
		Assert.assertEquals(liveGuruDomain, "live.techpanda.org");
		
		String homePageUrl = (String) executeForBrowser("return document.URL;");
		Assert.assertEquals(homePageUrl, "http://live.techpanda.org/");
		
		hightlightElement("//a[text()='Mobile']");
		clickToElementByJS("//a[text()='Mobile']");
		
		hightlightElement("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
		clickToElementByJS("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
		
		String innerTextValue = getInnerText();
		Assert.assertTrue(innerTextValue.contains("Samsung Galaxy was added to your shopping cart."));	
		Assert.assertTrue(areExpectedTextInInnerText("Samsung Galaxy was added to your shopping cart."));
		
		hightlightElement("//a[text()='Customer Service']");
		clickToElementByJS("//a[text()='Customer Service']");
		
		Assert.assertEquals(executeForBrowser("return document.title;"), "Customer Service");	
		
		hightlightElement("//input[@id='newsletter']");
		scrollToElementOnTop("//input[@id='newsletter']");
		sleepInSecond(3);
		
		hightlightElement("//input[@id='newsletter']");
		sendkeyToElementByJS("//input[@id='newsletter']", "testting" + getRandomNumber() + "@mail.vn");
		sleepInSecond(3);
		
		hightlightElement("//button[@title= 'Subscribe']");
		clickToElementByJS("//button[@title= 'Subscribe']");

		Assert.assertTrue(getInnerText().contains("Thank you for your subscription."));	
		Assert.assertTrue(areExpectedTextInInnerText("Thank you for your subscription."));
		
		navigateToUrlByJS("http://live.techpanda.org/");
		sleepInSecond(3);
		
		Assert.assertEquals(executeForBrowser("return document.domain;"), "live.techpanda.org");
	}

	//@Test
	public void TC_02_HTLM5() {
		driver.get("https://login.ubuntu.com/");
		
		driver.findElement(By.cssSelector("button[data-qa-id='login_button']")).click();
		sleepInSecond(3);
		Assert.assertEquals(getElementValidationMessage("//form[@id='login-form']//input[@id='id_email']"), "Please fill out this field.");

		driver.findElement(By.xpath("//form[@id='login-form']//input[@id='id_email']")).sendKeys("123@123@#$");
		driver.findElement(By.cssSelector("button[data-qa-id='login_button']")).click();
		sleepInSecond(3);
		Assert.assertEquals(getElementValidationMessage("//form[@id='login-form']//input[@id='id_email']"), "A part following '@' should not contain the symbol '@'.");
		
		driver.findElement(By.xpath("//form[@id='login-form']//input[@id='id_email']")).clear();
		driver.findElement(By.xpath("//form[@id='login-form']//input[@id='id_email']")).sendKeys("abc@gmail.net");
		driver.findElement(By.cssSelector("button[data-qa-id='login_button']")).click();
		sleepInSecond(3);
		Assert.assertEquals(getElementValidationMessage("//form[@id='login-form']//input[@id='id_password']"), "Please fill out this field.");

	}

	@Test
	public void TC_03_Remove_Attribute() {
		String loginPageUrl, userID, password, name, gender, dateOfBirthInput, dateOfBirthOutput, addressInput,
		addressOutput, city, state, pin, phone, email;
		
		By nameTextboxBy = By.name("name");
		By genderRadioBy = By.xpath("//input[@value='f']");
		By genderTextboxBy = By.name("gender");
		By dateOfBirthTextboxBy = By.name("dob");
		By addressTextareaBy = By.name("addr");
		By cityTextboxBy = By.name("city");
		By stateTextboxBy = By.name("state");
		By pinTextboxBy = By.name("pinno");
		By phoneTextboxBy = By.name("telephoneno");
		By emailTextboxBy = By.name("emailid");
		By passwordTextboxBy = By.name("password");
		
		name = "Angela Jolie";
		gender = "female";
		dateOfBirthInput = "01/01/1990";
		dateOfBirthOutput = "1990-01-01";
		addressInput = "234 PO Bridge\nNew York";
		addressOutput = "234 PO Bridge New York";
		city = "Los Angeles";
		state = "California";
		pin = "225588";
		phone = "0985632658";
		email = "angela" + getRandomNumber() + "@mail.net";
		password = "vyjYdUv";
		
		driver.get("http://demo.guru99.com/v4/");
		
		driver.findElement(By.name("uid")).sendKeys("msgr332636");
		driver.findElement(By.name("password")).sendKeys("vyjYdUv");
		driver.findElement(By.name("btnLogin")).click();

		Assert.assertTrue(driver
				.findElement(By.xpath(
						"//marquee[@class='heading3' and text() = \"Welcome To Manager's Page of Guru99 Bank\"]"))
				.isDisplayed());

		driver.findElement(By.xpath("//a[text()='New Customer']")).click();

		driver.findElement(nameTextboxBy).sendKeys(name);
		driver.findElement(genderRadioBy).click();
		
		removeAttributeInDOM("//input[@name='dob']", "type");
		
		sleepInSecond(5);
		driver.findElement(dateOfBirthTextboxBy).sendKeys(dateOfBirthInput);
		driver.findElement(addressTextareaBy).sendKeys(addressInput);
		driver.findElement(cityTextboxBy).sendKeys(city);
		driver.findElement(stateTextboxBy).sendKeys(state);
		driver.findElement(pinTextboxBy).sendKeys(pin);
		driver.findElement(phoneTextboxBy).sendKeys(phone);
		driver.findElement(emailTextboxBy).sendKeys(email);
		driver.findElement(passwordTextboxBy).sendKeys(password);
		driver.findElement((By.name("sub"))).click();

		Assert.assertEquals(driver.findElement(By.xpath("//p[@class='heading3']")).getText(),"Customer Registered Successfully!!!");

		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), name);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(), gender);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(),dateOfBirthOutput);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(),addressOutput);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(),state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(),phone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(),email);

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(999999);
	}

	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public Object executeForBrowser(String javaScript) {
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText() {
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void hightlightElement(String locator) {
		WebElement element = getElement(locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}

	public void clickToElementByJS(String locator) {
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
	}

	public void scrollToElementOnTop(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
	}

	public void scrollToElementOnDown(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
	}

	public void sendkeyToElementByJS(String locator, String value) {
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
	}

	public String getElementValidationMessage(String locator) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
	}

	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(locator));
		if (status) {
			return true;
		}
		return false;
	}

	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
	}
}