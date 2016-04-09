package Locations.RoomManager;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LocationAssignedTest {
	private WebDriver driver;
	private String baseUrl;
	private String userName;
	private String password;
	private String locationName;
	private String parentRoom;
	private String roomName;
	
	@BeforeClass
	public void testSetUp() {
		
		driver = new FirefoxDriver();
		baseUrl = "https://172.20.208.183:4040/admin/#/admin/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		userName = "SamuelSahonero";
	    password = "Control123!";
	    locationName = "My Location Test Selenium";
	    parentRoom = "Organization";
	    roomName = "Qadev06 Room";
	    
	    driver.get(baseUrl);
	    driver.findElement(By.id("loginUsername")).clear();
	    driver.findElement(By.id("loginUsername")).sendKeys(userName);
	    driver.findElement(By.id("loginPassword")).clear();
	    driver.findElement(By.id("loginPassword")).sendKeys(password);
	    driver.findElement(By.xpath("//button[@type='submit'][text()='Sign In']")).click();
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
	    (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='ngCellText ng-binding ng-scope'][@ng-dblclick='editLocation(row.entity)'][text()='"+ locationName +"']")));
	}
	
	@Test
	  public void verifyALocationIsAssignedToAConferenceRoom() throws Exception {
	    driver.findElement(By.linkText("Conference Rooms")).click();
	    driver.findElement(By.xpath("//span[@class='ng-binding'][@ng-show='row.entity.enabled'][text()='"+ roomName +"']")).click();
	    (new Actions(driver)).moveToElement(driver.findElement(By.xpath("//span[@class='ng-binding'][@ng-show='row.entity.enabled'][text()='"+ roomName +"']"))).doubleClick().build().perform();
	    driver.findElement(By.id("add-location")).click();
	    driver.findElement(By.xpath("//button[@type='button'][@ng-click='toggleTree()']//descendant::i[@class='fa fa-plus-square']")).click();
	    driver.findElement(By.xpath("//treeview/div/div/span[@ng-click='collapse($event)']")).click();
	    driver.findElement(By.xpath("//b[@class='ng-binding'][text()='"+ locationName +"']")).click();
	    driver.findElement(By.xpath("//button[@ng-click='save()'][@class='info']")).click();
	    (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='ng-binding ng-scope']")));
	    String getText = driver.findElement(By.xpath("//div[@class='ng-binding ng-scope']")).getText();
		Assert.assertEquals(getText, "Room successfully Modified");
	  }
	
	@AfterClass
	public void tearDown() {
		(new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='ng-binding'][@ng-show='row.entity.enabled'][text()='"+ roomName +"']")));
		(new Actions(driver)).moveToElement(driver.findElement(By.xpath("//span[@class='ng-binding'][@ng-show='row.entity.enabled'][text()='"+ roomName +"']"))).doubleClick().build().perform();
		(new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(By.cssSelector("span.input-group-btn > button.btn.btn-default")));
		driver.findElement(By.id("add-location")).click();
		(new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='button'][@ng-click='toggleTree()']//descendant::i[@class='fa fa-plus-square']")));
		driver.findElement(By.xpath("//button[@type='button'][@ng-click='toggleTree()']//descendant::i[@class='fa fa-plus-square']")).click();
		(new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(By.xpath("//b[@class='ng-binding'][text()='Organization']")));
		driver.findElement(By.xpath("//b[@class='ng-binding'][text()='"+ parentRoom +"']")).click();
		driver.findElement(By.xpath("//button[@ng-click='save()'][@class='info']")).click();
		(new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='ng-binding ng-scope']")));
		driver.findElement(By.linkText("Locations")).click();
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
