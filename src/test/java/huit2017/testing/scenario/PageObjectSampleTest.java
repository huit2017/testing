package huit2017.testing.scenario;

import static org.junit.Assert.assertThat;

import java.io.IOException;
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
import org.openqa.selenium.safari.SafariDriver;

import huit2017.testing.page.ReserveConfirmPage;
import huit2017.testing.page.ReserveInputPage;

public class PageObjectSampleTest {
	
	private WebDriver driver;
	
	private String saveDir = null;

	@Before
	public void setUp() {
		System.setProperty("webdriver.gecko.driver", "geckodriver");
		driver = new FirefoxDriver();
		
//		System.setProperty("webdriver.chrome.driver", "chromedriver");
//    		ChromeOptions options = new ChromeOptions();
//    		options.addArguments("test-type");
//    		driver = new ChromeDriver(options);
    		
//    		driver = new SafariDriver();
		
		saveDir = new StringBuilder()
				.append((new SimpleDateFormat("yyyyMMdd_HHmmss")).format(new Date()))
				.append("/")
				.append(driver.getClass().getSimpleName())
				.append("/").toString();
	}

	@After
	public void tearDown() {
		driver.quit();
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
