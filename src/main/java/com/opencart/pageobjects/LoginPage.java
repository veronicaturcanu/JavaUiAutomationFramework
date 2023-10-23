package com.opencart.pageobjects;

import com.opencart.managers.DriverManager;
import com.opencart.managers.ScrollManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends Page{

    public LoginPage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//input[@id='input-email']")
    WebElement emailInput;

    @FindBy(xpath = "//input[@id='input-password']")
    WebElement passwordInput;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement loginBtn;

    public void fillInTheLoginPage(String email, String password){
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
    }

    public void clickTheLoginBtn(){
        ScrollManager.scrollToTheElement(loginBtn);
        loginBtn.click();
    }

}
