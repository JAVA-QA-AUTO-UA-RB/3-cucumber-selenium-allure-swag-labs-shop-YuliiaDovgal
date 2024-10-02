package org.example.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import org.example.pages.LoginPage;
import org.example.pages.ProductsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class SwagLabsLoginSteps {

    WebDriver driver;
    LoginPage loginPage;
    ProductsPage productsPage;

    @Given("the user is on the login page")
    public void theUserIsOnTheLoginPage() {
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);
    }

    @When("the user logs in with valid credentials")
    public void theUserLogsInWithValidCredentials() {
        loginPage.login("standard_user", "secret_sauce");
    }

    @Then("the user should see the products page")
    public void theUserShouldSeeTheProductsPage() {
        productsPage = new ProductsPage(driver);
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"));
        driver.quit();
    }

    @When("the user enters an invalid username and password")
    public void theUserEntersInvalidUsernameAndPassword() {
        loginPage.enterUsername("invalid_user");
        loginPage.enterPassword("invalid_password");
    }

    @And("the user clicks the login button")
    public void theUserClicksLoginButton() {
        loginPage.clickLogin();
    }

    @Then("an error message should be displayed")
    public void anErrorMessageShouldBeDisplayed() {
        String errorMessage = loginPage.getErrorMessage();
        Assert.assertTrue(errorMessage.contains("Epic sadface: Username and password do not match"));
    }

    @Then("the user should remain on the login page")
    public void theUserShouldRemainOnTheLoginPage() {
        Assert.assertTrue(driver.getCurrentUrl().contains("saucedemo.com"));
        driver.quit();
    }
    @Given("the user is logged in with valid credentials")
    public void theUserIsLoggedInWithValidCredentials() {
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        productsPage = new ProductsPage(driver);
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"));  // Verify that the user is logged in
    }

    @When("the user logs out from the site")
    public void theUserLogsOutFromTheSite() {
        productsPage.clickLogout();  // Perform the logout action
    }

    @Then("the user should be redirected to the login page")
    public void theUserShouldBeRedirectedToTheLoginPage() {
        Assert.assertTrue(driver.getCurrentUrl().contains("saucedemo.com"));
        driver.quit();  // Close the browser after the test
    }
}