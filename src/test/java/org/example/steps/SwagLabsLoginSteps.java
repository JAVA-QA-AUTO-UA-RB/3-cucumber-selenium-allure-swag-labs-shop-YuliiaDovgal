package org.example.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
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
}