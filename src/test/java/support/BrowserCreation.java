package support;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class BrowserCreation {
    public static final int SUCCESS_STATE = 1;
    public static final int FAIL_STATE = 5;
    private static final String SUCCESS_COMMENT = "This test passed with Selenium";
    private static final String FAILED_COMMENT = "This test failed with Selenium";
    public static WebDriver driver;
    public static String TEST_RUN_ID = "2097";
    final PropertiesReader propertiesReader = new PropertiesReader();
    final ChromeOptions options = new ChromeOptions();
    final EdgeOptions EOptions = new EdgeOptions();

    public static void deleteExcel() {
        String userDirector = System.getProperty("user.dir");
        File src = new File(userDirector + "\\data\\excelSheet.xlsx");
        if (src.exists()) {
            src.delete();
        }
    }

    @Before
    public void setUp(Scenario scenario) throws IOException {
        propertiesReader.loadProperties();
        if (PropertiesReader.browserHeadless.equals("true")) {
            options.addArguments("--window-size=1325x744");
      //      options.addArguments("--headless");
//            options.addArguments("--disable-gpu");
//            options.addArguments("--ignore-certificate-errors");
//            options.addArguments("--disable-extensions");
//            options.addArguments("--disable-dev-shm-usage");
            options.setPageLoadStrategy(PageLoadStrategy.NONE);
            System.out.println(scenario.toString());
        }
        if (PropertiesReader.browserRequired.equals("true")) {
            if (PropertiesReader.browserType.equals("chrome")) {
                options.addArguments("start-maximized");
                options.addArguments("--ignore-certificate-errors");
                options.addArguments("--silent");
//                options.addArguments("--log-level=3");
//                options.addArguments("--disable-extensions");
//                options.addArguments("--test-type");
//                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--remote-allow-origins=*");
                options.setPageLoadStrategy(PageLoadStrategy.NONE);
                WebDriverManager.chromedriver().clearResolutionCache().setup();
//                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(options);
                System.out.println(scenario.toString());
            } else if (PropertiesReader.browserType.equals("firefox")) {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                // need to add script for firefox
            } else if (PropertiesReader.browserType.equals("edge")) {
                EOptions.addArguments("start-maximized");
                EOptions.addArguments("--ignore-certificate-errors");
                EOptions.addArguments("--silent");
                EOptions.addArguments("--log-level=3");
                EOptions.addArguments("--disable-extensions");
                EOptions.addArguments("--test-type");
                EOptions.addArguments("--disable-dev-shm-usage");
                EOptions.setPageLoadStrategy(PageLoadStrategy.NONE);
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver(EOptions);
                driver.manage().window().maximize();
            }
        }
        new World().driverClass();
    }

    @After
    public void embedScreenshot(Scenario scenario) throws InterruptedException {
        //If test fails takes a screenshot and embeds it in the Cucumber report
        if (scenario.isFailed()) {
            try {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                String testName = scenario.getName();
                scenario.embed(screenshot, "image/png");
                scenario.write(testName);
            } catch (WebDriverException wde) {
                System.err.println(wde.getMessage());
            } catch (ClassCastException cce) {
                cce.printStackTrace();
            }
        }
        driver.quit();
    }

    private void logResultToTestRail(Scenario scenario) {
        String caseId = World.TestCaseID;
        String testName = scenario.getName();
        Integer Line = scenario.getLine();
        String featureFilePath = scenario.getId();

        Map<String, Serializable> data = new HashMap<>();
        if (!scenario.isFailed()) {
            data.put("status_id", SUCCESS_STATE);
            data.put("comment", SUCCESS_COMMENT + "\n Scenario Outline :- "
                    + testName + "\n Feature File Path :- " + featureFilePath);

        } else if (scenario.isFailed()) {
            data.put("status_id", FAIL_STATE);
            data.put("comment", FAILED_COMMENT + "\n Scenario Outline :- "
                    + testName + "\n Failed Line in Feature File :-"
                    + Line + "\n Feature file path :- " + featureFilePath);
        }
    }

    public void tearDown() {
        if (PropertiesReader.browserRequired.equals("true")) {
            driver.quit();
        }
    }

    public WebDriver getDriver() {
        return driver;
    }
}

