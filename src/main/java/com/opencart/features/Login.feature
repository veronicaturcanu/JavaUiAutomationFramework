Feature: Login flow Test Suite

  Scenario: Login Page can be accessed from Home Page
    Given "/" endpoint is accessed
    When Login icon is clicked from Header menu
    Then The current url contains "route=account/login" keyword

  Scenario: The user is successfully signing in with valid credentials
    Given "/index.php?route=account/login&language=en-gb" endpoint is accessed
    And The login form is populated with valid email "stephen.kuvalis@gmail.com" and password "i17980hpxdzpnwd"
    When The Login button is clicked
    Then The current url contains "route=account/account" keyword
