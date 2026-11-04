package NEXTGEN;

import java.sql.Driver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Testng {
	WebDriver driver;
	@Parameters({"browser"})
	@BeforeMethod
	public void launchUrl(String browser) {
		if ( browser .equals("chrome")) {
		WebDriverManager.chromedriver().setup();
		driver =new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(" https://www.saucedemo.com/");
	}
				
	}
	@Test(retryAnalyzer = Rerun.class)
	public void validLoginTest() {
		WebElement user= driver.findElement(By.id("user-name"));
		user.sendKeys("standard_user");
		WebElement password= driver.findElement(By.id("password"));
		password.sendKeys("secret_sauce");		
		driver.findElement(By.id("login-button")).click();
		String currentUrl =driver.getCurrentUrl();
		Assert.assertTrue(currentUrl.contains("wrong"));
		System.out.println("Valid Login Test passed");
	}
	
	@Test
	public void invalidLoginTest() {
		WebElement user= driver.findElement(By.id("user-name"));
		user.sendKeys("wrong_user");
		WebElement password= driver.findElement(By.id("password"));
		password.sendKeys("secret_sauce");		
		driver.findElement(By.id("login-button")).click();
		 String errorMessage=driver.findElement(By.tagName("h3")).getText();
		 Assert.assertTrue(errorMessage.contains("Username and password do not match"));
			System.out.println("Invalid Login Test passed");

		
	}
	
	@AfterMethod
	public void quitBrowser() throws InterruptedException {
		Thread.sleep(3000);
		driver.quit();
		
	}
	

}
