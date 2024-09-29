Feature: Checkout
  Scenario: Successful order placement
    Given the user has items in the cart
    When the user proceeds to checkout
    And enters valid customer information
    And completes the order
    Then the order should be successfully placed