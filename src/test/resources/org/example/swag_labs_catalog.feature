Feature: Shopping Cart
  Scenario: Add item to the cart
    Given the user is logged in
    When the user adds a "Sauce Labs Backpack" to the cart
    Then the item should be displayed in the cart

  Scenario: Remove item from the cart
     Given the user has added "Sauce Labs Backpack" to the cart
     When the user removes the "Sauce Labs Backpack" from the cart
     Then the cart should be empty
