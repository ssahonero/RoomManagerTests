package Locations.RoomManager;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CreateLocationWithSameNameTest {
	private WebDriver driver;
	private String baseUrl;
	private String userName;
	private String password;
	private String locationName;
	
	@BeforeClass
	public void testSetUp() {
		
		driver = new FirefoxDriver();
		baseUrl = "https://172.20.208.183:4040/admin/#/admin/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		userName = "SamuelSahonero";
	    password = "Control123!";
	    locationName = "Location With Same Name Test Selenium";
	    
	    driver.get(baseUrl);
	    driver.findElement(By.id("loginUsername")).clear();
	    driver.findElement(By.id("loginUsername")).sendKeys(userName);
	    driver.findElement(By.id("loginPassword")).clear();
	    driver.findElement(By.id("loginPassword")).sendKeys(password);
	    driver.findElement(By.xpath("//button[@type='submit']")).click();
	    driver.findElement(By.linkText("Locations")).click();
	    driver.findElement(By.xpath("//button[@href='#/admin/locations'][@ui-sref='admin.locations.modal.add']/descendant::span[text()='Add']")).click();
	    driver.findElement(By.id("location-add-name")).clear();
	    driver.findElement(By.id("location-add-name")).sendKeys(locationName);
	    driver.findElement(By.id("location-add-display-name")).clear();
	    driver.findElement(By.id("location-add-display-name")).sendKeys(locationName);
	    driver.findElement(By.id("location-add-description")).clear();
	    driver.findElement(By.id("location-add-description")).sendKeys(locationName);
	    driver.findElement(By.xpath("//button[@ng-click='save()'][@class='btn btn-primary']/descendant::span[text()='Save']")).click();
	    (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='ng-binding ng-scope']")));
	    driver.navigate().refresh();
	}
	
	@Test
	public void verifyAWarningMessageIsDisplayedWhenCreatingANewLocationWithAnExistingLocationName() {
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@href='#/admin/locations'][@ui-sref='admin.locations.modal.add']/descendant::span[text()='Add']")));
	    driver.findElement(By.xpath("//button[@href='#/admin/locations'][@ui-sref='admin.locations.modal.add']/descendant::span[text()='Add']")).click();
	    driver.findElement(By.id("location-add-name")).clear();
	    driver.findElement(By.id("location-add-name")).sendKeys(locationName);
	    driver.findElement(By.id("location-add-display-name")).clear();
	    driver.findElement(By.id("location-add-display-name")).sendKeys(locationName);
	    driver.findElement(By.id("location-add-description")).clear();
	    driver.findElement(By.id("location-add-description")).sendKeys(locationName);
	    (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@ng-click='save()'][@class='btn btn-primary']/descendant::span[text()='Save']")));
	    driver.findElement(By.xpath("//button[@ng-click='save()'][@class='btn btn-primary']/descendant::span[text()='Save']")).click();
	    (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//small[@ng-repeat='errorMessage in locationForm.name.errorMessages']")));
	    String getText = driver.findElement(By.xpath("//small[@ng-repeat='errorMessage in locationForm.name.errorMessages']")).getText();
		Assert.assertEquals(getText, "Name already exist");
	}
	
	@AfterClass
	public void tearDown() {
		(new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@ng-click='cancel()'][@class='btn btn-default']/descendant::span[text()='Cancel']")));
	    driver.findElement(By.xpath("//button[@ng-click='cancel()'][@class='btn btn-default']/descendant::span[text()='Cancel']")).click();
	    (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='ngCellText ng-binding ng-scope'][@ng-dblclick='editLocation(row.entity)'][text()='"+ locationName +"']/parent::div/parent::div/parent::div/descendant::input[@type='checkbox'][@class='ngSelectionCheckbox']")));
		driver.findElement(By.xpath("//div[@class='ngCellText ng-binding ng-scope'][@ng-dblclick='editLocation(row.entity)'][text()='"+ locationName +"']/parent::div/parent::div/parent::div/descendant::input[@type='checkbox'][@class='ngSelectionCheckbox']")).click();
		(new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@href='#/admin/locations'][@ui-sref='admin.locations.remove']/descendant::span[text()='Remove']")));
		driver.findElement(By.xpath("//button[@href='#/admin/locations'][@ui-sref='admin.locations.remove']/descendant::span[text()='Remove']")).click();
		(new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@ng-click='removeLocations()'][@class='btn btn-primary']/descendant::span[text()='Remove']")));
		driver.findElement(By.xpath("//button[@ng-click='removeLocations()'][@class='btn btn-primary']/descendant::span[text()='Remove']")).click();
		(new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='ng-binding ng-scope']")));
		driver.navigate().refresh();
		(new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@ng-click='removeSession()']/descendant::span[text()='sign out']")));
		driver.findElement(By.xpath("//a[@ng-click='removeSession()']/descendant::span[text()='sign out']")).click();
		(new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='col-xs-12']/descendant::span[text()='Sign In']")));
		driver.quit();
	}

}
