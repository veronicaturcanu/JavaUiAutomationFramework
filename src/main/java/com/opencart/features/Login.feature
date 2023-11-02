Feature: Login flow Test Suite

  Background:
    Given "/index.php?route=account/login&language=en-gb" endpoint is accessed

  Scenario: Login Page can be accessed from Home Page
    Given "/" endpoint is accessed
    When Login icon is clicked from Header menu
    Then The current url contains "route=account/login" keyword

  Scenario Outline: An error message is displayed when using invalid <affected attribute> for login form
    When The following form from "LoginPage" is populated as follow:
      | emailInput    | <email>        |
      | passwordInput | <password> |
    And The "loginBtn" from "LoginPage" is clicked
    Then The following list of error message is displayed:
      | Warning: No match for E-Mail Address and/or Password. |
    Examples:
      | affected attribute | email                     | password        |
      | password           | stephen.kuvalis@gmail.com | fdsdsaa343      |
      | email              | efdsvcs@kjjhjk.vom        | i17980hpxdzpnwd |

    @Regression
  Scenario Outline: Successful login is performed with valid credentials
    When The following form from "LoginPage" is populated as follow:
      | emailInput    | <username> |
      | passwordInput | <password> |
    And The "loginBtn" from "LoginPage" is clicked
    Then The current url contains "route=account/account" keyword
    Examples:
      | username                  | password        |
      | stephen.kuvalis@gmail.com | i17980hpxdzpnwd |