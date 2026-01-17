package stepDefinations;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.cucumber.java.*;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;

public class Hooks {
    
    public static WebDriver driver;
    public static ExtentReports extent;
    public static ExtentTest test;

    @BeforeAll
    public static void init() {
        ExtentSparkReporter spark = new ExtentSparkReporter("reports/extent-report.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    @Before
    public void setup(Scenario scenario) {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        test = extent.createTest(scenario.getName());
    }

    @After
    public void tearDown(Scenario scenario) throws IOException {
        if (scenario.isFailed()) {
            TakesScreenshot ts = (TakesScreenshot) driver;
            byte[] src = ts.getScreenshotAs(OutputType.BYTES);
            String base64 = Base64.getEncoder().encodeToString(src);

            test.fail("Test Failed").addScreenCaptureFromBase64String(base64);
        } else {
            test.pass("Test Passed");
        }

        driver.quit();
    }

    @AfterAll
    public static void flush() {
        extent.flush();
    }
}
