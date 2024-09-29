package org.example.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.example.pages.LoginPage;
import org.example.pages.ProductsPage;
import org.example.pages.CartPage;

public class SwagLabsCatalogSteps {
    WebDriver driver;
    LoginPage loginPage;
    ProductsPage productsPage;
    CartPage cartPage;

    @Given("the user is logged in")
    public void theUserIsLoggedIn() {
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
    }

    @When("the user adds a {string} to the cart")
    public void theUserAddsAToTheCart(String itemName) {
        productsPage = new ProductsPage(driver);
        productsPage.addItemToCart(itemName);
    }

    @Then("the item should be displayed in the cart")
    public void theItemShouldBeDisplayedInTheCart() {
        productsPage.goToCart();
        cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.isItemInCart("Sauce Labs Backpack"));
        driver.quit();
    }

    @When("the user removes the {string} from the cart")
    public void theUserRemovesTheFromTheCart(String itemName) {
        productsPage = new ProductsPage(driver);
        productsPage.removeItemFromCart(itemName);
    }

    @Then("the cart should be empty")
    public void theCartShouldBeEmpty() {
        cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.isCartEmpty());
        driver.quit();
    }
}