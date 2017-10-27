package huit2017.testing.page;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class BasePage {

	protected final WebDriver driver;
	
	public BasePage(WebDriver driver, String title) {
		this.driver = driver;
		if (!title.equals(this.driver.getTitle())) {
			throw new IllegalStateException("現在のページが間違っています: " + this.driver.getTitle());
		}
	}
	
	public void saveScreenshot(String pathname) throws IOException {
		File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.moveFile(file, new File(pathname));
	}
}
