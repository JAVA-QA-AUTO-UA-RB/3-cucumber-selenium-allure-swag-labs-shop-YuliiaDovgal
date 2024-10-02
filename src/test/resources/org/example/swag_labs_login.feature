Feature: Swag Labs User Login

  Scenario: Successful login with valid credentials
      Given the user is on the login page
      When the user logs in with valid credentials
      Then the user should see the products page

   Scenario: Unsuccessful login with invalid credentials
      Given the user is on the login page
      When the user enters an invalid username and password
      And the user clicks the login button
      Then an error message should be displayed
      And the user should remain on the login page

   Scenario: Successful logout from the site
      Given the user is logged in with valid credentials
      When the user logs out from the site
      Then the user should be redirected to the login page