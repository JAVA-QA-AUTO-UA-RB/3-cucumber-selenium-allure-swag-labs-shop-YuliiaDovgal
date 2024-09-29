package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.By;
import java.util.List;


public class ProductsPage extends BasePage {

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


    public void removeItemFromCart(String itemName) {
        WebElement removeButton = driver.findElement(By.xpath("//div[text()='" + itemName + "']/ancestor::div[@class='inventory_item']//button[text()='Remove']"));
        removeButton.click();
    }

    public boolean isCartEmpty() {
        // Find all elements representing items in the cart
        List<WebElement> itemsInCart = driver.findElements(By.className("cart_item"));

        // If the list is empty, the cart is empty
        return itemsInCart.size() == 0;
    }
}