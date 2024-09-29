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

public class SwagLabsCheckoutSteps {
    WebDriver driver;
    LoginPage loginPage;
    ProductsPage productsPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;
    OrderConfirmationPage orderConfirmationPage;

    @Given("the user has items in the cart")
    public void theUserHasItemsInTheCart() {
        driver = new ChromeDriver();  
        driver.get("https://www.saucedemo.com/");

        loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        productsPage = new ProductsPage(driver);
        productsPage.addItemToCart("Sauce Labs Backpack");
        productsPage.goToCart();
    }

    @When("the user proceeds to checkout")
    public void theUserProceedsToCheckout() {
        cartPage = new CartPage(driver);
        cartPage.proceedToCheckout();
    }

    @When("enters valid customer information")
    public void entersValidCustomerInformation() {
        checkoutPage = new CheckoutPage(driver);
        checkoutPage.enterCustomerInformation("John", "Doe", "12345");
        checkoutPage.clickContinueButton();
    }

    @When("completes the order")
    public void completesTheOrder() {
        checkoutPage.clickFinishButton();
    }

    @Then("the order should be successfully placed")
    public void theOrderShouldBeSuccessfullyPlaced() {
        orderConfirmationPage = new OrderConfirmationPage(driver);
        String confirmationMessage = orderConfirmationPage.getConfirmationMessage();
        Assert.assertEquals(confirmationMessage, "THANK YOU FOR YOUR ORDER");
        driver.quit();
    }
}