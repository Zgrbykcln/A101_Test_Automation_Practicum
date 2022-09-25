package driver.driver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.thoughtworks.gauge.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class DriverFactory {

    private static WebDriver driver = null;

    public static WebDriver getDriver() {
        return driver;
    }
    protected static WebDriverWait webDriverWait;
    private static Logger logger = LoggerFactory.getLogger(DriverFactory.class);


    @BeforeScenario
    public void setUp(ExecutionContext executionContext) throws InterruptedException{

        ChromeOptions options = new ChromeOptions();
        System.setProperty("webdriver.chrome.driver", "src/test/web_driver/chromedriver");
        options.addArguments("incognito");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        webDriverWait = new WebDriverWait(driver, 45, 150);

    }

    @AfterScenario
    public void tearDown(){
        driver.quit();
    }
}
