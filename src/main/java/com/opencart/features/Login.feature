Feature: Login flow Test Suite

  Background:
    Given "/index.php?route=account/login&language=en-gb" endpoint is accessed

  Scenario: Login Page can be accessed from Home Page
    Given "/" endpoint is accessed
    When Login icon is clicked from Header menu
    Then The current url contains "route=account/login" keyword

  Scenario: The user is successfully signing in with valid credentials
    And The login form is populated with valid email "stephen.kuvalis@gmail.com" and password "i17980hpxdzpnwd"
    When The Login button is clicked
    Then The current url contains "route=account/account" keyword

  Scenario Outline: An error message is displayed when using invalid <affected attribute> for login form
    When The login form is populated with following details:
      | email    | <email>    |
      | password | <password> |
    And The Login button is clicked
    Then The following list of error message is displayed:
      | Warning: No match for E-Mail Address and/or Password. |
    Examples:
      | affected attribute | email                     | password        |
      | password           | stephen.kuvalis@gmail.com | fdsdsaa343      |
      | email              | efdsvcs@kjjhjk.vom        | i17980hpxdzpnwd |
