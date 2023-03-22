package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import support.ExcelReader;

import java.io.IOException;
import java.util.List;

import static support.World.*;

public class WeatherPage {

    private final WebDriver driver;
    public String site_Details_MapPage;

    @FindBy(xpath = "//div[@class='site-name ng-star-inserted']//h3")
    private WebElement siteNameF;
    @FindBy(xpath = "(//div[@class='card'])[2]")
    private WebElement currentConditionTableF;
    @FindBy(xpath = "//arable-card[@ng-reflect-card-title='Current Conditions']/div/div/div/arable-tooltip")
    private WebElement currentTableToolTipF;
    @FindBy(xpath = "((//div[@class='arable-card-body'])[2]/div/div/div[2]/p)[1]")
    private WebElement currentTempValueF;
    @FindBy(xpath = "((//div[@class='arable-card-body'])[2]/div/div[2]/div[2]/p)[1]")
    private WebElement currentHumidityValueF;
    @FindBy(xpath = "((//div[@class='arable-card-body'])[2]/div/div[3]/div[2]/p)[1]")
    private WebElement currentPressureValueF;
    @FindBy(xpath = "(//p[@class='display-number'])[3]//span[@class='measure']")
    private WebElement currentPressureUnitF;
    @FindBy(xpath = "((//div[@class='arable-card-body'])[2]/div/div[4]/div[2]/p)[1]")
    private WebElement currentSolarValueF;
    @FindBy(xpath = "//p[normalize-space()='Search']")
    private WebElement dashboardMenu_Search;
    @FindBy(xpath = "//input[@placeholder='Search']")
    private WebElement searchBar;
    @FindBy(xpath = "//p[@class='1 selected']")
    private WebElement globalOrgSelected_MapPage;
    @FindBy(xpath = "//span[@class='1 caret']")
    private WebElement globalOrgDropdownBtn_MapPage;
    @FindBy(xpath = "//div[@class='1 dropdown-selector open']//input[@type='search']")
    private WebElement globalOrgDropdownSearch_MapPage;
    @FindBy(xpath = "//p[normalize-space()='arable-team']")
    private WebElement globalOrgSearchSuggestion_MapPage;
    @FindBy(xpath = "(//div[@class='site-summary-card grid clickable'])[1]")
    private WebElement MapPage_SiteF;
    @FindBy(xpath = "//div[@class='site-detail']")
    private WebElement siteDetails;
    @FindBy(xpath = "//*[contains(@title,'Jans Backyard C009070')]")
    private WebElement Jan_backyard_C009070_Section;
    @FindBy(xpath = "//h3[contains(@title,'Jans Backyard C009070')]")
    private WebElement Jan_backyard_C009070_Site;
    @FindBy(xpath = "//h3[contains(text(),'Jans Backyard C009070')]")
    private WebElement Jan_backyard_C009070_Header;
    @FindBy(xpath = "//div[@class='site-info']/div[2]/p/span")
    private WebElement siteInfoMarkText;
    @FindBy(xpath = "//div[@class='device-table-entries']/div[@class='device-table-entry-container grid']")
    public List<WebElement> deviceList;
    @FindBy(xpath = "//h1[@class='fancy-link'][normalize-space()='Current Conditions']")
    private WebElement currentConditionsText;


    public WeatherPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void verifyContentsOfWeatherPage(String role) throws IOException {
        webDriverHelper.waitUntilVisible(currentConditionTableF, 200, 1);
        String weatherPageContent = siteDetails.getText();
        Assert.assertTrue(weatherPageContent.contains("Current Conditions"));
        Assert.assertTrue(weatherPageContent.contains("Today's Temperature"));
        Assert.assertTrue(weatherPageContent.contains("Hourly Forecast"));
        Assert.assertTrue(weatherPageContent.contains("Daily Forecast"));
        Assert.assertTrue(weatherPageContent.contains("Wind Speed Hourly"));
        Assert.assertTrue(weatherPageContent.contains("Wind Speed Daily"));
    }
    public void navigateToPage(String page) throws InterruptedException {
        Thread.sleep(10000);
        webDriverHelper.waitUntilVisible(globalOrgSelected_MapPage, 120, 1);
        if (!(globalOrgSelected_MapPage.getText().contains("arable-team"))) {
            globalOrgDropdownBtn_MapPage.click();
            webDriverHelper.waitUntilVisible(globalOrgDropdownSearch_MapPage, 30, 1);
            globalOrgDropdownSearch_MapPage.sendKeys("arable-team");
            globalOrgSearchSuggestion_MapPage.click();
            Thread.sleep(1000);
        }
        webDriverHelper.waitUntilVisible(MapPage_SiteF, 300, 1);
        site_Details_MapPage = MapPage_SiteF.getText();
        System.out.println("site_Details_MapPage" + site_Details_MapPage);
        MapPage_SiteF.click();
    }
    
    public void navigateToPageAndClickSpecificSiteMap(String page) throws InterruptedException {
        Thread.sleep(10000);
        webDriverHelper.waitUntilVisible(globalOrgSelected_MapPage, 120, 1);
        if (!(globalOrgSelected_MapPage.getText().contains("arable-team"))) {
            globalOrgDropdownBtn_MapPage.click();
            webDriverHelper.waitUntilVisible(globalOrgDropdownSearch_MapPage, 30, 1);
            globalOrgDropdownSearch_MapPage.sendKeys("arable-team");
            globalOrgSearchSuggestion_MapPage.click();
            Thread.sleep(1000);
        }
        webDriverHelper.waitUntilVisible(MapPage_SiteF, 30, 1);
        webDriverHelper.waitUntilVisible(Jan_backyard_C009070_Section, 30, 1);
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();", Jan_backyard_C009070_Section);
        webDriverHelper.waitUntilVisible(Jan_backyard_C009070_Site, 30, 1);
        Jan_backyard_C009070_Site.click();
        webDriverHelper.waitUntilVisible(Jan_backyard_C009070_Header, 30, 1);
    }
    
    public void verifymarkLblWithDeviceList() throws IOException {
    	 Assert.assertTrue(Jan_backyard_C009070_Header.isDisplayed(), "Jan backyard C009070_Header is displayed");
    	 webDriverHelper.waitUntilVisible(siteInfoMarkText, 300, 1);
    	 Assert.assertTrue(siteInfoMarkText.getText().contains("("),"Mark text present");
         String marktext=siteInfoMarkText.getText().replace("(", "").replace(")","");
         int marktextvalue=Integer.parseInt(marktext);
         Assert.assertEquals(marktextvalue,deviceList.size());
    }

    public void captureWeatherPageLoadTime(String role) throws IOException {
        String loadInSeconds = null;
        long start = System.currentTimeMillis();
        webDriverHelper.waitUntilVisible(currentConditionTableF, 200, 1);
        if (currentConditionTableF.isDisplayed()) {
            loadInSeconds = stringHelper.stopTime(start, driver);
            Assert.assertTrue(currentConditionTableF.isDisplayed(), "current condition table is displayed");
        }
        ExcelReader.setValueForColumn("Weather", loadInSeconds, role);
    }
}