package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import support.Constants;
import support.ExcelReader;

import java.io.IOException;
import java.util.List;

import static support.World.*;

public class SeasonsPage {

    private final WebDriver driver;
    private String upcomingInitialValue;

    @FindAll({@FindBy(xpath = "//ul[@class='menu-items']/li/a")})
    private List<WebElement> dashboardMenuF;

    @FindBy(xpath = "//p[normalize-space()='Settings']")
    private WebElement dashboardMenu_Settings;

    @FindBy(xpath = "//p[normalize-space()='Seasons']")
    private WebElement dashboardMenu_Seasons;
    
    @FindBy(xpath = "//p[normalize-space()='Olive Orchard Site']")
    private WebElement olive_orchard_site;
    
    @FindBy(xpath = "//a[contains(text(),'View More Seasons ')]")
    private WebElement view_more_season;

    @FindBy(xpath = "//div[@class='actions']/div")
    private WebElement createNewSeasonsButtonF;

    @FindAll({@FindBy(xpath = "//div[@class='actions']")})
    private List<WebElement> createNewSeasonsButtonF1;

    @FindBy(xpath = "//div[@class='seasons-table']/div[1]")
    private WebElement seasonsTableHeaderF;

    @FindBy(css = "div[class='grid body-list season-row last-row'] div[class='season-column site']")
    private WebElement seasonsTableLastRow;

    @FindBy(css = "div[class='grid message-list'] p")
    private WebElement seasonsNoTable;

    @FindBy(xpath = "//div[@class='seasons-header']/div[2]/div[2]/div[3]/div/span")
    private WebElement seasonsHeaderUpcomingF;

    @FindBy(xpath = "//div[@class='seasons-header']/div[2]/div[2]/div[1]/div/span")
    private WebElement seasonsHeaderCurrentF;

    @FindBy(xpath = "//div[@class='modal fade in show']//button[@class='btn-close']")
    private WebElement CreateSeasonPopupCancelF;

    @FindBy(xpath = "//div[@class='modal fade in show']//div[@class='content-modal']/arable-dropdown-selector[1]/div")
    private WebElement siteDropdownF;
    @FindAll({@FindBy(xpath = "//arable-dropdown-selector[@class='input-dropdown regions']//div[@class='options']/div")})
    private List<WebElement> siteDropdownListF;

    @FindBy(xpath = "//div[@class='modal fade in show']//div[@class='content-modal']/arable-dropdown-selector[2]/div")
    private WebElement cropTypeDropdownF;

    public SeasonsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void navigateToPage(String page) throws IOException, InterruptedException {
        map.switchOrgToArableTeam();
        webDriverHelper.waitUntilVisible(dashboardMenu_Settings, 30, 1);
        Actions action = new Actions(driver);
        action.moveToElement(dashboardMenu_Settings).moveToElement(dashboardMenu_Seasons).click().build().perform();
    }
    
    public void captureSeasonPageLoadTime(String role) throws IOException {
        long start = System.currentTimeMillis();
        if (!(role.equalsIgnoreCase("sysReader"))) {
            webDriverHelper.waitUntilVisible(seasonsTableLastRow, 30, 1);
            String loadInSeconds = stringHelper.stopTime(start, driver);
            ExcelReader.setValueForColumn("Seasons", loadInSeconds, role);
            Assert.assertTrue(seasonsTableLastRow.isDisplayed(), "seasons table is displayed");
        } else {
            webDriverHelper.waitUntilVisible(seasonsNoTable, 30, 1);
            String loadInSeconds = stringHelper.stopTime(start, driver);
            ExcelReader.setValueForColumn("Seasons", loadInSeconds, role);
            Assert.assertTrue(seasonsNoTable.isDisplayed(), "No seasons table is displayed");
        }
    }
}