package com.opencart;

import com.opencart.managers.DriverManager;
import com.opencart.managers.FakeDataManager;
import com.opencart.pageobjects.AccountCreatedPage;
import com.opencart.pageobjects.HomePage;
import com.opencart.pageobjects.LoginPage;
import com.opencart.pageobjects.RegisterPage;
import org.openqa.selenium.*;


public class TestRunnerWithPageObjects {
    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = DriverManager.getInstance().getDriver();

        driver.get("https://andreisecuqa.host/");

        HomePage homePage = new HomePage(driver);
        homePage.navigateToRegisterPageFromHeader();

        RegisterPage registerPage = new RegisterPage(driver);

        //salvam email si password pentru verificari ulterioare
        String email = FakeDataManager.getRandomEmail();
        String password = FakeDataManager.getRandomPassword(10,18);
        System.out.println("The generated email is: " + email);
        System.out.println("The generated password is: " + password);

        // completam formularul cu datele generate random
        registerPage.fillInTheRegisterForm(FakeDataManager.getRandomFirstName(),FakeDataManager.getRandomLastName(),
                email, password, true);

        registerPage.clickTheContinueBtn();

        Thread.sleep(500);
        AccountCreatedPage accountCreatedPage = new AccountCreatedPage(driver);
        accountCreatedPage.logoutAfterRegistration();

        // verificarea contului creat
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateToLoginFromHeader();
        loginPage.fillInTheLoginPage(email,password);
        loginPage.clickTheLoginBtn();
        Thread.sleep(500);

        DriverManager.getInstance().quitTheDriver();

        System.out.println("The execution is over.");
    }
}