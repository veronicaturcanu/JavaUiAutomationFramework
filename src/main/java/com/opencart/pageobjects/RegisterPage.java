package com.opencart.pageobjects;

import com.opencart.managers.ScrollManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends Page {

    public RegisterPage(WebDriver driver){
        super(driver);
    }

    @FindBy(id = "input-firstname")
    private WebElement firstNameInput;

    @FindBy(id = "input-lastname")
    private WebElement lastNameInput;

    @FindBy(id = "input-email")
    private WebElement emailInput;

    @FindBy(id = "input-password")
    private WebElement passwordInput;

    @FindBy(css = "input[value='1'][name='agree']")
    private WebElement privacyToggle;

    @FindBy(css = "button[type='submit']")
    private WebElement continueBtn;

    public void fillInTheRegisterForm(String firstName, String lastName, String email, String password, boolean toggle){
        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);

        if (toggle){
            ScrollManager.scrollToTheElement(privacyToggle);
            privacyToggle.click();
        }
    }

    public void clickTheContinueBtn(){
        continueBtn.click();
    }

}
