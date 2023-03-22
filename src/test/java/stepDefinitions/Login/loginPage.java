package stepDefinitions.Login;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.io.IOException;

import static support.World.loginPage;

public class loginPage {

    @Given("log into application with valid credential for different {string} user")
    public void logIntoApplicationWithValidCredentialForDifferentUser(String role) throws InterruptedException {
        loginPage.logInToTheApplication(role);
        loginPage.verifyLoggedIn();
    }

    @Then("on login page verify all titles on login page")
    public void onLoginPageVerifyAllTitlesOnLoginPage() {
        loginPage.verifyTitlesOnLoginPage();
    }
}
