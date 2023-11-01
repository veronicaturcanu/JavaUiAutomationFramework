Feature: Register flow Test Suite

Background:
  Given "/index.php?route=account/register&language=en-gb" endpoint is accessed

  Scenario: Register Page can be accessed from the Home Page
    Given "/" endpoint is accessed
    When RegisterLink from Header menu is clicked
    Then The current url contains "register" keyword

  Scenario: The Account Page URL is opened when the registration is successful
    And The register form is populated with valid random data
    And Continue button is clicked
    Then The current url contains "route=account/success" keyword

  Scenario: User remains on Register Page when continue button is not clicked
    And The register form is populated with valid random data
    Then The current url contains "route=account/register" keyword

  @run
  Scenario Outline: An error message is displayed when invalid <impacted attribute> is used for register flow
    And The register form is populated with the following data:
      | firstName | <firstName>    |
      | lastName  | <lastName>     |
      | email     | <emailData>    |
      | password  | <passwordData> |
    When Continue button is clicked
    Then The following list of error message is displayed:
      | <impacted attribute> must be between 1 and 32 characters! |
    Examples:
      | impacted attribute | firstName                             | lastName                              | emailData | passwordData |
      | First Name         | Ana1234567890123456789012345678901234 | Random                                | Random    | Random       |
      | Last Name          | Random                                | Ana1234567890123456789012345678901234 | Random    | Random       |
      | First Name         | Ana1234567890123456789012345678901234 | Random                                | Random    | Random       |
      | Last Name          | Random                                | Ana1234567890123456789012345678901234 | Random    | Random       |
      | First Name         | Ana1234567890123456789012345678901234 | Random                                | Random    | Random       |
      | Last Name          | Random                                | Ana1234567890123456789012345678901234 | Random    | Random       |
      | First Name         | Ana1234567890123456789012345678901234 | Random                                | Random    | Random       |
      | Last Name          | Random                                | Ana1234567890123456789012345678901234 | Random    | Random       |
      | First Name         | Ana1234567890123456789012345678901234 | Random                                | Random    | Random       |
      | Last Name          | Random                                | Ana1234567890123456789012345678901234 | Random    | Random       |
