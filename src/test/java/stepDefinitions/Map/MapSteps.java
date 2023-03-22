package stepDefinitions.Map;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;
import static support.World.map;

public class MapSteps {

    @When("navigate to {string} page")
    public void navigateToPage(String page) throws IOException, InterruptedException {
        map.navigateToPage(page);
    }

    @Then("verify site name is displayed and update Excel with tme taken sheet for {string} user")
    public void verifySiteNameIsDisplayedAndUpdateExcelWithTmeTakenSheetForUser(String role) throws IOException, InterruptedException {
        map.captureMapLoadTime(role);
    }
    
    @Then("verify temp rain and solar is sorted")
    public void verifytempRainAndSolarIsSorted() throws IOException, InterruptedException {
        map.validatesortingInSiteList();
    }
    
    @Then("verify measurement with units in legend")
    public void verifyMeasurementWithlegends() throws Exception {
        map.validateMeasurementWithlegends();
    }
}
