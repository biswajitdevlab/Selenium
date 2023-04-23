package Base;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ExtentReportManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Base {
    public static WebDriver driver;
    public static Properties prop;
    public static WebDriverWait wait;
    JavascriptExecutor js;
    public static ExtentTest logger;
    public ExtentReports report= ExtentReportManager.getReportInstance();
    public void driversetup() {
        prop = new Properties();
        try {
            prop.load(new FileInputStream("src/test/java/Config/Config.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (prop.getProperty("browser").matches("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        if (prop.getProperty("browser").matches("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        if (prop.getProperty("browser").matches("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }
        //window maximize and waits
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
    }
        public void openURL() {
                driver.get(prop.getProperty("url"));
        }
        public void reportFail(String report) {
                logger.log(Status.FAIL, report);
        }
        public void reportPass(String report){
                logger.log(Status.PASS, report);
        }
        public void endReport(){
                report.flush();
        }
        public void wait(int sec, By locator) {
            wait = new WebDriverWait(driver, sec);
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        }
        public void closebrowser() {
               driver.quit();
        }
            public void Screenshot(String filename) throws IOException {
                    TakesScreenshot capture= (TakesScreenshot) driver;
                    File src=capture.getScreenshotAs(OutputType.FILE);
                    File dest=new File(System.getProperty("user.dir")+ "/ScreenShot/" + filename + ".png");
                    Files.copy(src.toPath(), dest.toPath());
                }

    }

