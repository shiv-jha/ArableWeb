package stepDefinitions.Accounts;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static support.World.accountPage;
import static support.World.loginPage;

public class Account_Steps {

    @Given("log into application with a {string} user")
    public void LogIntoApplicationWithAUser(String role) throws InterruptedException {
        loginPage.logInToTheApplication(role);
        loginPage.verifyLoggedIn();
    }

    @When("navigate to Settings and then to {string} page")
    public void navigateToSettingsAndThenToPage(String page) throws Exception {
        accountPage.navigateToPage(page);
    }

    @Then("verify lbls are translating into portugal")
    public void verifyGeneratingCopyingRefreshingForUser() throws Exception {
    	accountPage.verifyPortuguesLanguage();
    }
    
//    @Then("verify generating,copying & refreshing for {string} user")
//    public void verifyGeneratingCopyingRefreshingForUser(String role) throws InterruptedException {
//        accountPage.verifyGenerateKey(role);
//    }
}
