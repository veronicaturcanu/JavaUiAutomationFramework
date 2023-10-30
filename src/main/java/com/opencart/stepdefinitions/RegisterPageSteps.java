package com.opencart.stepdefinitions;

import com.opencart.managers.DriverManager;
import com.opencart.managers.FakeDataManager;
import com.opencart.pageobjects.RegisterPage;
import io.cucumber.java.en.And;
import org.openqa.selenium.WebDriver;

public class RegisterPageSteps {
    WebDriver driver = DriverManager.getInstance().getDriver();
    RegisterPage registerPage = new RegisterPage(driver);
    @And("The register form is populated with valid random data")
    public void theRegisterFormIsPopulatedWithValidRandomData() {
        String email = FakeDataManager.getRandomEmail();
        String password = FakeDataManager.getRandomPassword(10,18);
        System.out.println("The generated email is: " + email);
        System.out.println("The generated password is: " + password);

        registerPage.fillInTheRegisterForm(FakeDataManager.getRandomFirstName(),FakeDataManager.getRandomLastName(),
                email, password, true);

    }

    @And("Continue button is clicked")
    public void continueButtonIsClicked() {
        registerPage.clickTheContinueBtn();
        System.out.println("The continue button has been clicked");
    }
}
