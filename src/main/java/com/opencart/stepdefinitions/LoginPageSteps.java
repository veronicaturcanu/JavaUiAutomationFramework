package com.opencart.stepdefinitions;

import com.opencart.managers.DriverManager;
import com.opencart.pageobjects.LoginPage;
import io.cucumber.java.en.And;
import org.openqa.selenium.WebDriver;

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
}
