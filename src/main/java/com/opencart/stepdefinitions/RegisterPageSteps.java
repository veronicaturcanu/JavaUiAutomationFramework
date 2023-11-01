package com.opencart.stepdefinitions;

import com.opencart.managers.DriverManager;
import com.opencart.managers.FakeDataManager;
import com.opencart.pageobjects.RegisterPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

import java.util.Map;

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
    }

    @When("The register form is populated with the following data:")
    public void theRegisterFormIsPopulatedWithTheFolowingData(Map <String, String> formDataMap) {
        String firstNameValue = formDataMap.get("firstName");
        if (firstNameValue != null && firstNameValue.toUpperCase().equals("RANDOM")){
            firstNameValue = FakeDataManager.getRandomFirstName();
        }
        String lastNameValue = formDataMap.get("lastName");
        if (lastNameValue != null && lastNameValue.toUpperCase().equals("RANDOM")){
            lastNameValue = FakeDataManager.getRandomLastName();
        }
        String emailValue = formDataMap.get("email");
        if (emailValue != null && emailValue.toUpperCase().equals("RANDOM")){
            emailValue = FakeDataManager.getRandomEmail();
        }
        String passwordValue = formDataMap.get("password");
        if (passwordValue !=null && passwordValue.toUpperCase().equals("RANDOM")){
            passwordValue = FakeDataManager.getRandomPassword(8,12);
        }
        registerPage.fillInTheRegisterForm(firstNameValue,lastNameValue,emailValue,passwordValue,true);
    }
}
