package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;



public class ProductsPage extends BasePage {

    By logoutButton = By.id("logout_sidebar_link");

    public ProductsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    public void addItemToCart(String itemName) {
        WebElement addToCartButton = driver.findElement(By.xpath("//div[text()='" + itemName + "']/ancestor::div[@class='inventory_item']//button"));
        addToCartButton.click();
    }


    public void goToCart() {
        WebElement cartLink = driver.findElement(By.className("shopping_cart_link"));
        cartLink.click();
    }


    public void clickLogout() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement menuButton = driver.findElement(By.id("react-burger-menu-btn"));
        if (menuButton != null && menuButton.isDisplayed() && menuButton.isEnabled()) {
            menuButton.click();

            WebElement logoutLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logout_sidebar_link")));
            if (logoutLink != null && logoutLink.isDisplayed() && logoutLink.isEnabled()) {
                logoutLink.click();
            } else {
                System.out.println("Logout link is not visible or enabled.");
            }
        } else {
            System.out.println("Menu button is not visible or enabled.");
        }
    }
}