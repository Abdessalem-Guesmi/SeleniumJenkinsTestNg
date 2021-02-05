package base;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.google.common.io.Files;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.GooglePage;

public class BaseTests {

	public WebDriver driver;

	private static ExtentHtmlReporter htmlReport;
	private static ExtentReports extent;
	private static ExtentTest extentTest;
	protected static GooglePage googlePage;

	public static ExtentReports getInstance() {

		String reportName = getReporttName();
		String directory = System.getProperty("user.dir") + "/reports/";
		new File(directory).mkdirs();
		String path = directory + reportName;
		htmlReport = new ExtentHtmlReporter(path);
		htmlReport.config().setEncoding("utf-8");
		htmlReport.config().setDocumentTitle("Automation Test");
		htmlReport.config().setTheme(Theme.STANDARD);

		extent = new ExtentReports();
		extent.setSystemInfo("organization", "abdou guesmi");
		extent.setSystemInfo("Browser", "Chrome");
		extent.setSystemInfo("organization", "abdou guesmi");
		extent.attachReporter(htmlReport);

		// WebDriverManager.chromedriver().setup();
		return extent;
	}

	public static String getReporttName() {
		Date d = new Date();
		String fileName = "Report_" + d.toString().replace(":", "-") + ".html";
		return fileName;

	}

	@BeforeMethod
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		// ChromeOptions chromeOptions = new ChromeOptions();
		// chromeOptions.addArguments("--headless");
//		driver = new ChromeDriver(chromeOptions);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://www.google.com/");
		googlePage = new GooglePage(driver);
	}

	@AfterMethod
	public void recordFailure(ITestResult result) {
		if (ITestResult.FAILURE == result.getStatus()) {
			TakesScreenshot camera = (TakesScreenshot) driver;
			File screenshot = camera.getScreenshotAs(OutputType.FILE);
			try {
				Files.move(screenshot, new File("screenshot/" + result.getName() + ".png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		driver.close();
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
