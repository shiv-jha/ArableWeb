package support;

import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import pages.*;
import support.JsonReader;
import support.World;

public class World {

    public static MapPage map;
    public static LoginPage loginPage;
    public static SeasonsPage seasonsPage;
    public static WeatherPage weatherPage;
    public static AccountPage accountPage;
    public static StringHelper stringHelper;
    public static WebDriverHelper webDriverHelper;
    public static SitesApiHelper sitesApiHelper;
    public static RestAssuredHelper restAssuredHelper;
    public static JsonReader jsonReader;
    public static String emailId;
    public static String siteID;
    public static String siteName;
    public static String orgName;
    public static String token;
    public static String tAir;
    public static String sun;
    public static String slp;
    public static String rh;
    public static String tempUnit;
    public static String presUnit;
    public static String sizeUnit;
    public static String speedUnit;
    public static String precipitation;
    public static String accountSizeUnit;
    public static String accountTemperatureUnit;
    public static String accountPressureUnit;
    public static String accountVolumeUnit;
    public static String accountSpeedUnit;
    public static String TestCaseID;
    public static JSONObject jsonPayload;
    public static JSONObject jsonLegendPayload;

    final WebDriver driver = new BrowserCreation().getDriver();

    public void driverClass() {
        World.map = new MapPage(driver);
        World.stringHelper = new StringHelper();
        World.loginPage = new LoginPage(driver);
        World.seasonsPage = new SeasonsPage(driver);
        World.weatherPage = new WeatherPage(driver);
        World.accountPage = new AccountPage(driver);
        World.webDriverHelper = new WebDriverHelper(driver);
        World.restAssuredHelper = new RestAssuredHelper();
        World.sitesApiHelper = new SitesApiHelper();
        World.jsonReader = new JsonReader();
    }
}
