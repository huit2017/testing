package huit2017.testing.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ReserveErrorPage extends BasePage {

	public static final String TITLE = "予約エラー";

	public ReserveErrorPage(WebDriver driver) {
		super(driver, TITLE);
	}

	public String getMessage() {
		return driver.findElement(By.id("errorcheck_result")).getText();
	}
}
