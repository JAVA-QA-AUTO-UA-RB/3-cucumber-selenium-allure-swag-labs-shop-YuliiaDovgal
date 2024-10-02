package org.example.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.example.pages.LoginPage;
import org.example.pages.ProductsPage;
import org.example.pages.CartPage;
import org.example.pages.CheckoutPage;
import org.example.pages.OrderConfirmationPage;
import org.openqa.selenium.By;

public class SwagLabsCheckoutSteps {
    WebDriver driver;
    LoginPage loginPage;
    ProductsPage productsPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;
    OrderConfirmationPage orderConfirmationPage;


    public SwagLabsCheckoutSteps() {
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        orderConfirmationPage = new OrderConfirmationPage(driver);


        loginPage.login("standard_user", "secret_sauce");
    }

    @Given("the user has items in the cart")
    public void the_user_has_items_in_the_cart() {
        productsPage.addItemToCart("Sauce Labs Backpack");
        productsPage.goToCart();
    }

    @When("the user proceeds to checkout")
    public void the_user_proceeds_to_checkout() {
        cartPage.proceedToCheckout();
    }

    @And("enters valid customer information")
    public void enters_valid_customer_information() {
        checkoutPage.enterCustomerInformation("John", "Doe", "12345");
        checkoutPage.clickContinueButton();
    }

    @And("completes the order")
    public void completes_the_order() {
        checkoutPage.clickFinishButton();
    }

    @Then("the order should be successfully placed")
    public void the_order_should_be_successfully_placed() {
        String actualText = driver.findElement(By.cssSelector(".complete-header")).getText();
        Assert.assertEquals(actualText, "Thank you for your order!");
        driver.quit();
    }
}