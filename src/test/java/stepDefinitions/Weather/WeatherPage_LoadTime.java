package stepDefinitions.Weather;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;

import static support.World.weatherPage;


public class WeatherPage_LoadTime {

    @When("on {string} page click the site and go to site detail page")
    public void onPageClickTheSiteAndGoToSiteDetailPage(String page) throws InterruptedException {
        weatherPage.navigateToPage(page);
    }

    @When("navigate to {string} page and search the site and click on the site card")
    public void navigateToPageAndSearchTheSiteAndClickOnTheSiteCard(String page) throws InterruptedException {
        weatherPage.navigateToPage(page);
    }

    @When("navigate to {string} page and click on Specific Site and go to site detail page")
    public void onPageClickTheSiteAndGoToSiteDetailPage1(String page) throws InterruptedException {
        weatherPage.navigateToPageAndClickSpecificSiteMap(page);
    }
    
    @Then("user is on site weather tab and verify the current conditions table and update excel for {string} user.")
    public void userIsOnSiteWeatherTabAndVerifyTheCurrentConditionsTableAndUpdateExcelForUser(String role) throws IOException {
        weatherPage.captureWeatherPageLoadTime(role);
    }

    @Then("verify weather page site details for {string} user.")
    public void verifyWeatherPageSiteDetailsForUser(String role) throws IOException {
        weatherPage.verifyContentsOfWeatherPage(role);
    }
    
    @Then("verify mark lbl with device list count")
    public void verifyMarkLblWithDeviceListCount() throws IOException {
        weatherPage.verifymarkLblWithDeviceList();
    }
}
