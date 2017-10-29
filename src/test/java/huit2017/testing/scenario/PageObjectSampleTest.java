package huit2017.testing.scenario;

import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.After;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import huit2017.testing.page.ReserveConfirmPage;
import huit2017.testing.page.ReserveInputPage;

/**
 * java -Dwebdriver.gecko.driver=/Users/yamakawahachidai/testing/geckodriver -Dwebdriver.chrome.driver=/Users/yamakawahachidai/testing/chromeiver -jar selenium-server-standalone-3.6.0.jar -role node
 */
public class PageObjectSampleTest {
	
	private WebDriver driver;
	
	private String saveDir = null;

	@Before
	public void setUp() throws MalformedURLException {
		DesiredCapabilities desiredCapabilities = DesiredCapabilities.firefox();
		//DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
		//DesiredCapabilities desiredCapabilities = DesiredCapabilities.safari();
		driver = new RemoteWebDriver(new URL("http://192.168.33.1:4444/wd/hub"), desiredCapabilities);
		//driver = new RemoteWebDriver(new URL("http://192.168.32.17:4444/wd/hub"), desiredCapabilities);
//    		ChromeOptions options = new ChromeOptions();
//    		options.addArguments("test-type");
//    		driver = new ChromeDriver(options);
		
		saveDir = new StringBuilder()
				.append((new SimpleDateFormat("yyyyMMdd_HHmmss")).format(new Date()))
				.append("/")
				.append(driver.getClass().getSimpleName())
				.append("/").toString();
	}

	@After
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
	
	private String getPathName() {
		return new StringBuilder().append(saveDir)
				.append(String.valueOf(System.currentTimeMillis()))
				.append(".png")
				.toString();
	}

	private static Calendar nextSaturday() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		return calendar;
	}

	@Test
	public void 宿泊予約が成功すること() throws IOException {
		// 予約情報入力ページ
		driver.get("http://example.selenium.jp/reserveApp");
		ReserveInputPage inputPage = new ReserveInputPage(driver);
		Calendar nextSaturday = nextSaturday();
		inputPage.setReserveDate(Integer.toString(nextSaturday.get(Calendar.YEAR)),
				Integer.toString(nextSaturday.get(Calendar.MONTH) + 1),
				Integer.toString(nextSaturday.get(Calendar.DATE)));
		inputPage.setReserveTerm("1");
		inputPage.setHeadCount("2");
		inputPage.setBreakfast(true);
		inputPage.setEarlyCheckInPlan(true);
		inputPage.setGuestName("サンプルユーザ");
		inputPage.saveScreenshot(getPathName());
		// 予約内容確認ページ
		ReserveConfirmPage confirmPage = inputPage.goToNext();
		assertThat(confirmPage.getPrice(), CoreMatchers.is("21500"));
		confirmPage.saveScreenshot(getPathName());
		confirmPage.commit();
	}
}
