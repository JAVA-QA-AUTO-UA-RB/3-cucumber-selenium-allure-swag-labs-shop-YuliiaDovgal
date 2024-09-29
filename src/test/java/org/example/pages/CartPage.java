package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;
import org.openqa.selenium.By;


public class CartPage extends BasePage {

    @FindBy(id = "checkout")
    WebElement checkoutButton;

    @FindBy(id = "remove-sauce-labs-backpack")
    WebElement removeItemButton;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public boolean isItemInCart(String itemName) {
        return driver.getPageSource().contains(itemName);
    }

    public void removeItemFromCart() {
        removeItemButton.click();
    }

    public void proceedToCheckout() {
        checkoutButton.click();
    }

    public boolean isCartEmpty() {

        List<WebElement> itemsInCart = driver.findElements(By.className("cart_item"));

        return itemsInCart.isEmpty();
    }
}