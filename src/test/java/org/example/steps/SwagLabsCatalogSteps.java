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
import org.example.pages.CheckoutPage;
import org.example.pages.OrderConfirmationPage;

public class SwagLabsCatalogSteps {
    WebDriver driver;
    LoginPage loginPage;
    ProductsPage productsPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;
    OrderConfirmationPage orderConfirmationPage;


    @Given("the user is logged in")
    public void theUserIsLoggedIn() {
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        productsPage = new ProductsPage(driver);
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"));
    }

    @When("the user adds a {string} to the cart")
    public void theUserAddsAnItemToTheCart(String itemName) {
        if (cartPage == null) {
            cartPage = new CartPage(driver);
        }
        cartPage.addItemToCart(itemName);
    }

    @Then("the item should be displayed in the cart")
    public void theItemShouldBeDisplayedInTheCart() {
        Assert.assertTrue(cartPage.isItemInCart("Sauce Labs Backpack"));
    }

    @Given("the user has added {string} to the cart")
    public void theUserHasAddedItemToTheCart(String itemName) {
        theUserIsLoggedIn();
        productsPage.addItemToCart(itemName);
        if (cartPage == null) {
            cartPage = new CartPage(driver);
        }
        Assert.assertTrue(cartPage.isItemInCart(itemName));
    }

    @When("the user removes the {string} from the cart")
    public void theUserRemovesItemFromTheCart(String itemName) {
        if (cartPage == null) {
            cartPage = new CartPage(driver);
        }
        cartPage.removeItemFromCart(itemName);
    }

    @Then("the cart should be empty")
    public void theCartShouldBeEmpty() {
        Assert.assertTrue(cartPage.isCartEmpty());
    }
}