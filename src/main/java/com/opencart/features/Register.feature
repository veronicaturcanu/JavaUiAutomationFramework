Feature: Register flow Test Suite

  Scenario: Register Page can be accessed from the Home Page
    Given "/" endpoint is accessed
    When RegisterLink from Header menu is clicked
    Then The current url contains "register" keyword

  Scenario: The Account Page URL is opened when the registration is successful
    Given "/index.php?route=account/register&language=en-gb" endpoint is accessed
    And The register form is populated with valid random data
    And Continue button is clicked
    Then The current url contains "route=account/success" keyword

  Scenario: User remains on Register Page when continue button is not clicked
    Given "/index.php?route=account/register&language=en-gb" endpoint is accessed
    And The register form is populated with valid random data
    Then The current url contains "route=account/register" keyword