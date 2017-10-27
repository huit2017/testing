package huit2017.testing;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.apache.commons.io.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    		System.setProperty("webdriver.gecko.driver", "geckodriver");
        WebDriver driver = new FirefoxDriver();
    		//System.setProperty("webdriver.chrome.driver", "chromedriver");
    		ChromeOptions options = new ChromeOptions();
    		options.addArguments("test-type");
       // WebDriver driver = new ChromeDriver(options);
       // WebDriver driver = new SafariDriver();
        driver.get("https://www.yahoo.co.jp/");
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
			FileUtils.moveFile(file, new File("./sample.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
//        Wait<WebDriver> wait = new WebDriverWait(driver, 30);
//        WebElement inputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='text']")));
//        inputField.sendKeys("初音ミク");
//        inputField.sendKeys(Keys.ENTER);
//        wait.until(ExpectedConditions.titleContains("初音ミク"));
//        WebElement imageButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@href,'tbm=isch')]")));
//        imageButton.sendKeys(Keys.ENTER);
//        try {
//			Thread.sleep(10000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//        try {
//			FileUtils.copyFile(((TakesScreenshot) driver)
//			        .getScreenshotAs(OutputType.FILE), new File(driver
//			        .getClass().getName() + "-miku-search.png"));
//		} catch (WebDriverException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
        
    }
}
