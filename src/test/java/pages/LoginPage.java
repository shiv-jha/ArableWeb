package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import support.ExcelReader;
import support.PropertiesReader;

import java.io.IOException;
import java.util.List;

import static support.World.*;

public class LoginPage {

    private final WebDriver driver;
    private final PropertiesReader getPropertyValue = new PropertiesReader();
    public String loadInSeconds;
    public long start;
    @FindBy(xpath = "//arable-login/div/div/h1")
    private WebElement loginHeaderF;
    @FindBy(xpath = "//input[@name='email']")
    private WebElement emailF;
    @FindBy(xpath = "//input[@name='password']")
    private WebElement passwordF;
    @FindBy(xpath = "//input[@value='Log In']")
    private WebElement logInF;
    @FindBy(xpath = "//button[contains(.,'Close')]")
    private WebElement CloseBtn;
    @FindBy(xpath = "//div[@id='61b04fd88fcf7800145a4829']//div[@class='column-33 column-flex grid-center']")
    private WebElement siteDetails;
    @FindAll(@FindBy(xpath = "//div[@class='credentials-view']"))
    private List<WebElement> titlesOnLoginPage;
    @FindBy(xpath = "(//div[@class='site-summary-card grid clickable'])[1]")
    private WebElement MapPage_SiteF;
    @FindBy(xpath = "(//div[@class='column'])[2]")
    private WebElement teamDropdown_ALLTeams;
    @FindBy(xpath = "(//arable-site-summary-card/div)[1]")
    private WebElement firstSiteIdF;
    @FindBy(xpath = "//button[@title='Zoom out']")
    private WebElement mapZoomOutBtnF;
    @FindBy(xpath = "//p[@class='1 selected']")
    private WebElement globalOrgSelected_MapPage;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void verifyLoggedIn() throws InterruptedException {
        webDriverHelper.waitUntilVisible(mapZoomOutBtnF, 200, 1);
        Thread.sleep(1000);
        if (CloseBtn.isDisplayed()) {
            Assert.assertTrue(CloseBtn.isDisplayed(), "Modal Close button is not displayed on the dashboard page");
            CloseBtn.click();
        } else {
            webDriverHelper.waitUntilVisible(MapPage_SiteF, 200, 1);
            Assert.assertTrue(MapPage_SiteF.isDisplayed(), "MapPage_Site details is not displayed on the dashboard page");
        }
    }

    public void captureMapLoadTime(String role) throws IOException, InterruptedException {
        verifyLoggedIn();
        webDriverHelper.waitUntilVisible(firstSiteIdF, 200, 1);
        Assert.assertTrue(firstSiteIdF.isDisplayed(), "map page sites not displayed");
        loadInSeconds = stringHelper.stopTime(start, driver);
        ExcelReader.setValueForColumn("Map", loadInSeconds, role);
    }


    public void verifyTitlesOnLoginPage() {
        driver.get(PropertiesReader.webApplicationUrl);
        webDriverHelper.waitUntilVisible(emailF, 100, 2);
        for (WebElement title : titlesOnLoginPage) {
            String name = title.getText();
            Assert.assertTrue(name.contains("Enter your email & password."), "Enter your email & password text is not displayed");
            Assert.assertTrue(name.contains("Forgot Password?"), "Forgot Password? is not displayed");
            Assert.assertTrue(name.contains("Sign In to Arable"), "Sign In to Arable is not displayed");
        }
    }
    public void logInToTheApplication(String userRole){
        driver.get(PropertiesReader.webApplicationUrl);
        webDriverHelper.waitUntilVisible(loginHeaderF, 100, 2);
        webDriverHelper.waitUntilVisible(emailF, 100, 2);
        emailId = getPropertyValue.loadProperties(userRole + ".email");
        emailF.sendKeys(emailId);
        passwordF.sendKeys(PropertiesReader.applicationPassword);
        webDriverHelper.waitUntilVisible(logInF, 100, 2);
        logInF.click();
        loadInSeconds = null;
        start = System.currentTimeMillis();
    }
}
