package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import support.Constants;
import support.ExcelReader;
import support.MathHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static support.World.*;

public class MapPage {

    private final WebDriver driver;
   

    @FindBy(xpath = "(//div[@class='column'])[2]/p")
    private WebElement orgDropdownF;
    @FindAll({@FindBy(xpath = "//div[@class='scrollable-content']/arable-site-summary-card")})
    private List<WebElement> siteListF;
    @FindBy(xpath = "(//arable-site-summary-card/div)[1]")
    private WebElement firstSiteIdF;
    @FindBy(xpath = "(//arable-site-summary-card/div)[1]/div/h3")
    private WebElement firstSiteNameF;
    @FindBy(xpath = "(//arable-site-summary-card/div/div[2]/div/div)[1]/h2")
    private WebElement firstSiteTempF;
    @FindBy(xpath = "(//arable-site-summary-card/div/div[2]/div/div)[1]/h2/span")
    private WebElement firstSiteTempUnitF;
    @FindBy(xpath = "(//arable-site-summary-card/div/div[2]/div/div)[2]/h2")
    private WebElement firstSitePrepF;
    @FindBy(xpath = "(//arable-site-summary-card/div/div[2]/div/div)[2]/h2/span")
    private WebElement firstSitePrepUnitF;
    @FindBy(xpath = "(//arable-site-summary-card/div/div[2]/div/div)[3]/h2")
    private WebElement firstSiteSolarF;
    @FindBy(xpath = "//div[@class='info-modal welcome']/div/h1")
    private WebElement welcomeToSiteF;
    @FindBy(xpath = "//div[@class='info-modal welcome']/div[3]/button")
    private WebElement welcomeCloseButtonF;
    @FindBy(xpath = "//p[normalize-space()='Map']")
    private WebElement mapPage;
//    -----------------new xpath after angular merge
    @FindAll(@FindBy(xpath = "//div[@class='1 dropdown-selector open']//div[@class='options']//p"))
    private List<WebElement> orgDropdown_orgList;
    @FindBy(xpath = "//p[@class='1 selected']")
    private WebElement globalOrgSelected_MapPage;
    @FindBy(xpath = "//span[@class='1 caret']")
    private WebElement globalOrgDropdownBtn_MapPage;
    @FindBy(xpath = "//div[@class='1 dropdown-selector open']//input[@type='search']")
    private WebElement globalOrgDropdownSearch_MapPage;
    @FindBy(xpath = "//p[normalize-space()='arable-team']")
    private WebElement globalOrgSearchSuggestion_MapPage;

    @FindBy(xpath = "//arable-dropdown-selector[@class='meassure-selector']//div[@class='column']")
    private WebElement measurementSelected_MapPage;
    @FindAll(@FindBy(xpath = "//div[@class='options']/div[contains(@class,'grid grid-center option')]//div[@class='icon']"))
    private List<WebElement> measurements_MapPage;
    @FindAll(@FindBy(xpath = "//div[@class='options']/div[contains(@class,'grid grid-center option')]//div[@class='icon']/following-sibling::p"))
    private List<WebElement> measurements_MapPage_header;
    @FindAll(@FindBy(xpath = "//div[@class='options']/div[contains(@class,'grid grid-center option')]//div[@class='icon']//img"))
    private List<WebElement> measurements_MapPage_text;
    @FindBy(xpath = "(//span[@class='0 caret'])[1]")
    private WebElement measurementDropdownOpenBtn_MapPage;
    @FindBy(xpath = "//div[@class='column-auto measure text-center']/h2")
    private WebElement measurement_gradientBar_MapPage;

    @FindBy(xpath = "//div[@id='site-list']")
    private WebElement siteList_mapPage;
    
    @FindBy(xpath = "(//div[@id='site-list']//div[@class='column name clickable'])[1]")
    private WebElement measurement_temp_field;
    @FindBy(xpath = "(//div[@id='site-list']//div[@class='column name clickable'])[2]")
    private WebElement measurement_rain_field;
    @FindBy(xpath = "(//div[@id='site-list']//div[@class='column name clickable'])[3]")
    private WebElement measurement_solar_field;

    @FindAll(@FindBy(xpath = "//div[@class='current column column-flex']/div[1]/h2"))
    private List<WebElement> site_tempList;
    @FindAll(@FindBy(xpath = "//div[@class='current column column-flex']/div[2]/h2"))
    private List<WebElement> site_rainList;
    @FindAll(@FindBy(xpath = "//div[@class='current column column-flex']/div[3]/h2"))
    private List<WebElement> site_solarList;

    @FindBy(xpath = "//span[@class='active-sort caret-down']")
    private WebElement sort_active_down;
    @FindBy(xpath = "//span[@class='caret-up active-sort']")
    private WebElement sort_active_up;
 
    
  //span[@class='active-sort caret-down']

    public MapPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void navigateToPage(String page) throws IOException, InterruptedException {
        webDriverHelper.waitUntilVisible(mapPage, 30, 1);
        switchOrgToArableTeam();
    }

    public void switchOrgToArableTeam() throws InterruptedException, IOException {
        if (!(globalOrgSelected_MapPage.getText().contains("arable-team"))) {
            globalOrgDropdownBtn_MapPage.click();
            webDriverHelper.waitUntilVisible(globalOrgDropdownSearch_MapPage, 30, 1);
            globalOrgDropdownSearch_MapPage.sendKeys("arable-team");
            globalOrgSearchSuggestion_MapPage.click();
            webDriverHelper.waitUntilVisible(firstSiteIdF, 150, 1);
        } else {
            Assert.assertTrue(globalOrgSelected_MapPage.getText().contains("arable-team"), "org name is not arable team");
        }
    }
    
    public void validatesortingInSiteList() throws InterruptedException, IOException {
    	webDriverHelper.waitUntilVisible(firstSiteIdF, 30, 1);
    	verifyTempSorted();
    	verifyRainSorted();
    	verifySolarSorted();
    }
    
    public void validateMeasurementWithlegends() throws Exception {
    	jsonLegendPayload = jsonReader.getLegendsJsonPayload("mapping");
    	//jsonLegendPayload.get(driver)
    	webDriverHelper.waitUntilVisible(measurementSelected_MapPage, 30, 1);
    	measurementDropdownOpenBtn_MapPage.click();
    	for(int i=0;i<measurements_MapPage.size();i++) {
    		JavascriptExecutor js = (JavascriptExecutor)driver;
    		js.executeScript("arguments[0].click();", measurements_MapPage.get(i));
    		webDriverHelper.waitUntilTextToBePresentInElement(measurement_gradientBar_MapPage, 60, 2,  jsonLegendPayload.get(measurements_MapPage_text.get(i).getAttribute("alt")).toString());
    		System.out.println(measurements_MapPage_text.get(i).getAttribute("alt"));
    		System.out.println(measurement_gradientBar_MapPage .getText());
    		assertEquals(measurement_gradientBar_MapPage.getText(), jsonLegendPayload.get(measurements_MapPage_text.get(i).getAttribute("alt")));
    		
    		measurementDropdownOpenBtn_MapPage.click();
    	}
    }

    public void verifyTempSorted() {
    	webDriverHelper.waitUntilVisible(measurement_temp_field, 30, 1);
    	//revamp as per site name
    	try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	measurement_temp_field.click();
    	List<Integer> tempValue= new ArrayList<>();
    	for(WebElement tempE:site_tempList) {
    		if(tempE.getText().equals("--")) {
    			continue;
    		}
    		else {
    			tempValue.add(Integer.parseInt(tempE.getText().trim().replace("°F", "")));
    		}
    	}
    	MathHelper mathhelper= new MathHelper();
    	Assert.assertTrue(mathhelper.verifyAscInt(tempValue),"temp list is sorted");
    	measurement_temp_field.click();
    	tempValue.removeAll(tempValue);
    	for(WebElement tempE:site_tempList) {
    		if(tempE.getText().equals("--")) {
    			continue;
    		}
    		else {
    			tempValue.add(Integer.parseInt(tempE.getText().trim().replace("°F", "")));
    		}
    	}
    	Assert.assertTrue(mathhelper.verifyDescInt(tempValue),"temp list is sorted");
    }
    
    public void verifyRainSorted() {
    	measurement_rain_field.click();
    	List<Double> rainValue = new ArrayList<>();
    	for(WebElement rainE:site_rainList) {
    		if(rainE.getText().equals("--")) {
    			continue;
    		}
    		else {
    			rainValue.add(Double.parseDouble(rainE.getText().trim().replace("\"", "")));
    		}
    	}
    	MathHelper mathhelper= new MathHelper();
    	Assert.assertTrue(mathhelper.verifyAscDouble(rainValue),"rain list is sorted");
    	measurement_rain_field.click();
    	rainValue.removeAll(rainValue);
    	for(WebElement rainE:site_rainList) {
    		if(rainE.getText().equals("--")) {
    			continue;
    		}
    		else {
    			rainValue.add(Double.parseDouble(rainE.getText().trim().replace("\"", "")));
    		}
    	}
    	Assert.assertTrue(mathhelper.verifyDescDouble(rainValue),"rain list is sorted");
    }
    
    public void verifySolarSorted() {
    	measurement_solar_field.click();
    	List<Integer> solarValue = new ArrayList<>();
    	for(WebElement solarE:site_solarList) {
    		if(solarE.getText().equals("--")) {
    			continue;
    		}
    		else {
    			solarValue.add(Integer.parseInt(solarE.getText().trim().replace("°F", "")));
    		}
    	}
    	MathHelper mathhelper= new MathHelper();
    	Assert.assertTrue(mathhelper.verifyAscInt(solarValue),"solar list is sorted");
    	measurement_solar_field.click();
    	solarValue.removeAll(solarValue);
    	for(WebElement solarE:site_solarList) {
    		if(solarE.getText().equals("--")) {
    			continue;
    		}
    		else {
    			solarValue.add(Integer.parseInt(solarE.getText().trim().replace("°F", "")));
    		}
    	}
    	Assert.assertTrue(mathhelper.verifyDescInt(solarValue),"solar list is sorted");
    }
    public void captureMapLoadTime(String role) throws IOException {
        String loadInSeconds = null;
        long start = System.currentTimeMillis();
        webDriverHelper.waitUntilVisible(firstSiteIdF, 30, 1);
        loadInSeconds = stringHelper.stopTime(start, driver);
        Assert.assertTrue(firstSiteIdF.isDisplayed(), "map page sites not displayed");
        ExcelReader.setValueForColumn("Map", loadInSeconds, role);
    }
}