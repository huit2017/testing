package huit2017.testing.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ReserveConfirmPage extends BasePage {

	public static final String TITLE = "予約内容確認";

	public ReserveConfirmPage(WebDriver driver) {
		super(driver, TITLE);
	}

	public String getPrice() {
		return driver.findElement(By.id("price")).getText();
	}

	public void commit() {
		driver.findElement(By.id("commit")).click();
	}
}
