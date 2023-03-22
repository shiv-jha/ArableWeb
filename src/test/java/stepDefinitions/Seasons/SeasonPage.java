package stepDefinitions.Seasons;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import support.World;

import java.io.IOException;

import static support.World.loginPage;
import static support.World.seasonsPage;

public class SeasonPage {
    @Given("assign testcase {string} to log data to TestRail")
    public void assignTestcaseToLogDataToTestRail(String testCaseID) {
        World.TestCaseID = testCaseID;
    }

    @Given("user logg into application with a {string} user")
    public void userLoggIntoApplicationWithAUser(String role) throws InterruptedException {
        loginPage.logInToTheApplication(role);
        loginPage.verifyLoggedIn();
    }

    @When("navigate to {string} and verify season table is displayed for {string} user")
    public void navigateToAndVerifySeasonTableIsDisplayedForUser(String page, String role) throws IOException, InterruptedException {
        seasonsPage.navigateToPage(page);
    }

    @Then("write in Excel for {string} user the time taken to load the page")
    public void writeInExcelForUserTheTimeTakenToLoadThePage(String role) throws IOException {
        seasonsPage.captureSeasonPageLoadTime(role);
    }
}
