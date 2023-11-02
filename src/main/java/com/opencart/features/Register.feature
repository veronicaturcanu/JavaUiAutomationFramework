Feature: Register flow Test Suite

  Background:
    Given "/index.php?route=account/register&language=en-gb" endpoint is accessed

  Scenario: Register Page can be accessed from the Home Page
    Given "/" endpoint is accessed
    When RegisterLink from Header menu is clicked
    Then The current url contains "register" keyword

  Scenario: The Account Page URL is opened when the registration is successful
    And The following form from "RegisterPage" is populated as follow:
      | firstNameInput | RandomFirstName |
      | lastNameInput  | RandomLastName  |
      | emailInput     | RandomEmail     |
      | passwordInput  | RandomPassword  |
    And The "privacyToggle" from "RegisterPage" is clicked
    When The "continueBtn" from "RegisterPage" is clicked
    Then The current url contains "route=account/success" keyword

  Scenario: User remains on Register Page when continue button is not clicked during registration
    And The following form from "RegisterPage" is populated as follow:
      | firstNameInput | RandomFirstName |
      | lastNameInput  | RandomLastName  |
      | emailInput     | RandomEmail     |
      | passwordInput  | RandomPassword  |
    And The "privacyToggle" from "RegisterPage" is clicked
    Then The current url contains "route=account/register" keyword

  Scenario Outline: An error message is displayed when invalid <impacted attribute> is used for register flow
    And The following form from "RegisterPage" is populated as follow:
      | firstNameInput | <firstName>    |
      | lastNameInput  | <lastName>     |
      | emailInput     | <emailData>    |
      | passwordInput  | <passwordData> |
    And The "privacyToggle" from "RegisterPage" is clicked
    When The "continueBtn" from "RegisterPage" is clicked
    Then The following list of error message is displayed:
      | <impacted attribute> must be between 1 and 32 characters! |
    Examples:
      | impacted attribute | firstName                             | lastName                              | emailData   | passwordData   |
      | First Name         | Ana1234567890123456789012345678901234 | RandomLastName                        | RandomEmail | RandomPassword |
      | Last Name          | RandomFirstName                       | Ana1234567890123456789012345678901234 | RandomEmail | RandomPassword |

  Scenario Outline: An error message is displayed when invalid E-Mail Address is used for register flow
    And The following form from "RegisterPage" is populated as follow:
      | firstNameInput | <firstName>    |
      | lastNameInput  | <lastName>     |
      | emailInput     | <emailData>    |
      | passwordInput  | <passwordData> |
    And The "privacyToggle" from "RegisterPage" is clicked
    When The "continueBtn" from "RegisterPage" is clicked
    Then The following list of error message is displayed:
      | E-Mail Address does not appear to be valid! |
    Examples:
      | impacted attribute | firstName       | lastName       | emailData       | passwordData   |
      | E-Mail Address     | RandomFirstName | RandomLastName | junksd@klkjhjhj | RandomPassword |

  Scenario Outline: An error message is displayed when invalid Password is used for register flow
    And The following form from "RegisterPage" is populated as follow:
      | firstNameInput | <firstName>    |
      | lastNameInput  | <lastName>     |
      | emailInput     | <emailData>    |
      | passwordInput  | <passwordData> |
    And The "privacyToggle" from "RegisterPage" is clicked
    When The "continueBtn" from "RegisterPage" is clicked
    Then The following list of error message is displayed:
      | Password must be between 4 and 20 characters! |
    Examples:
      | impacted attribute | firstName       | lastName       | emailData   | passwordData                                   |
      | Password           | RandomFirstName | RandomLastName | RandomEmail | no                                             |
      | Password           | RandomFirstName | RandomLastName | RandomEmail | 46Char1234567890123456789012345678901234567890 |

    Scenario: An error message is displayed when Privacy Policy is not clicked for register flow
      And The following form from "RegisterPage" is populated as follow:
        | firstNameInput | RandomFirstName |
        | lastNameInput  | RandomLastName  |
        | emailInput     | RandomEmail     |
        | passwordInput  | RandomPassword  |
      When The "continueBtn" from "RegisterPage" is clicked
      Then The following list of error message is displayed:
        | Warning: You must agree to the Privacy Policy! |