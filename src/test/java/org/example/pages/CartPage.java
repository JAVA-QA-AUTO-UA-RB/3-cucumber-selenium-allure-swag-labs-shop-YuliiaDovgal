package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


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

    public void removeItemFromCart(String itemName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            WebElement removeButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[text()='" + itemName + "']/following-sibling::button[text()='Remove']")
            ));
            removeButton.click();
        } catch (Exception e) {
            System.out.println("Error removing item: " + e.getMessage());
        }
    }

    public void proceedToCheckout() {
        checkoutButton.click();
    }

    public boolean isCartEmpty() {

        List<WebElement> itemsInCart = driver.findElements(By.className("cart_item"));

        return itemsInCart.isEmpty();
    }
    public void addItemToCart(String itemName) {
        WebElement addItemButton = driver.findElement(By.xpath("//button[contains(@data-test, 'add-to-cart-" + itemName.toLowerCase().replace(" ", "-") + "')]"));
        addItemButton.click();
    }
}