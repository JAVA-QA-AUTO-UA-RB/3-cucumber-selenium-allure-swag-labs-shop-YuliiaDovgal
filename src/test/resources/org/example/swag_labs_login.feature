Feature: Swag Labs User Login

  Scenario: Successful login with valid credentials
      Given the user is on the login page
      When the user logs in with valid credentials
      Then the user should see the products page

  Scenario: Unsuccessful login with invalid credentials
      Given the user is on the login page
      When the user logs in with invalid credentials
      Then an error message should be displayed

   Scenario: Successful logout from the site
      Given the user is logged in with valid credentials
      When the user logs out from the site
      Then the user should be redirected to the login page