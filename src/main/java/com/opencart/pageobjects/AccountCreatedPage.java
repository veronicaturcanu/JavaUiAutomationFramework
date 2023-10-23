package com.opencart.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountCreatedPage extends Page{

    public AccountCreatedPage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//a[@class='dropdown-item'][normalize-space()='Logout']")
    private WebElement logoutBtn;

    public void logoutAfterRegistration(){
        myAccountIcon.click();
        logoutBtn.click();
    }
}
