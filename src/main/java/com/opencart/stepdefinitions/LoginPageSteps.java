package com.opencart.stepdefinitions;

import com.opencart.managers.DriverManager;
import com.opencart.managers.FakeDataManager;
import com.opencart.pageobjects.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Map;

public class LoginPageSteps {
    WebDriver driver = DriverManager.getInstance().getDriver();
    LoginPage loginPage = new LoginPage(driver);

    @And("Login icon is clicked from Header menu")
    public void loginIconIsClickedFromHeaderMenu() {
        loginPage.navigateToLoginFromHeader();
    }
    @And("The Login button is clicked")
    public void theLoginButtonIsClicked() {
        loginPage.clickTheLoginBtn();
    }
    @And("The login form is populated with valid email {string} and password {string}")
    public void theLoginFormIsPopulatedWithValidEmailAndPassword(String emailValue, String passwordValue) {
        loginPage.fillInTheLoginPage(emailValue, passwordValue);
    }

    @When("The login form is populated with following details:")
    public void theLoginFormIsPopulatedWithFollowingDetails(Map<String, String> loginCredentials) {
        String emailValue = loginCredentials.get("email");
        if (emailValue != null && emailValue.toUpperCase().equals("RANDOM")){
            emailValue = FakeDataManager.getRandomEmail();
        }
        String passwordValue = loginCredentials.get("password");
        if (passwordValue !=null && passwordValue.toUpperCase().equals("RANDOM")){
            passwordValue = FakeDataManager.getRandomPassword(8,12);
        }
        loginPage.fillInTheLoginPage(emailValue,passwordValue);
    }
}
