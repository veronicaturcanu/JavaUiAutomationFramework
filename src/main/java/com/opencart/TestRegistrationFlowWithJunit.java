package com.opencart;

import com.opencart.managers.DriverManager;
import com.opencart.managers.FakeDataManager;
import com.opencart.pageobjects.AccountCreatedPage;
import com.opencart.pageobjects.HomePage;
import com.opencart.pageobjects.LoginPage;
import com.opencart.pageobjects.RegisterPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TestRegistrationFlowWithJunit {

    WebDriver driver;
    HomePage homePage;
    RegisterPage registerPage;
    AccountCreatedPage accountCreatedPage;
    LoginPage loginPage;

    @BeforeAll
    public static void executeThisMethodBeforeAllTheTests(){
        System.out.println("The execution of the test suite has started");
    }

    @BeforeEach
    public void executeThisMethodBeforeEachTest(){
        System.out.println("Before each");

        driver = DriverManager.getInstance().getDriver();
        driver.get("https://andreisecuqa.host/");

        homePage = new HomePage(driver);
        registerPage = new RegisterPage(driver);
        accountCreatedPage = new AccountCreatedPage(driver);
        loginPage = new LoginPage(driver);

        homePage.navigateToRegisterPageFromHeader();
    }

    @Test
    @DisplayName("The user is successfully signing in with new valid credentials.")
    public void registerWithNewValidCredentials() throws InterruptedException {

        //salvam email si password pentru verificari ulterioare
        String email = FakeDataManager.getRandomEmail();
        String password = FakeDataManager.getRandomPassword(10,18);
        System.out.println("The generated email is: " + email);
        System.out.println("The generated password is: " + password);

        // completam formularul cu datele generate random
        registerPage.fillInTheRegisterForm(FakeDataManager.getRandomFirstName(),FakeDataManager.getRandomLastName(),
                email, password, true);

        registerPage.clickTheContinueBtn();

        Thread.sleep(5000);
        // facem verificari cu ajutorul metodelor din clasa Assertions
        String currentUrl = driver.getCurrentUrl();
        boolean doesTheUrlContainSuccess = currentUrl.contains("route=account/success");
        Assertions.assertTrue(doesTheUrlContainSuccess, "The current url: " + currentUrl + " contains: route=account/success");

    }

    @Test
    @DisplayName("The user is successfully signing in with new valid credentials and successfully signing out.")
    public void registerWithNewValidCredentialsAndLoggedOut() throws InterruptedException {

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
        // facem verificari cu ajutorul metodelor din clasa Assertions
        String currentUrl = driver.getCurrentUrl();
        boolean doesTheUrlContainSuccess = currentUrl.contains("route=account/success");
        Assertions.assertTrue(doesTheUrlContainSuccess, "The user successfully registered.");

        accountCreatedPage = new AccountCreatedPage(driver);
        accountCreatedPage.logoutAfterRegistration();
        Thread.sleep(500);
        // facem verificari cu ajutorul metodelor din clasa Assertions
        String currentUrlAfterLogout = driver.getCurrentUrl();
        String expectedUrlAfterLogout = "https://andreisecuqa.host/index.php?route=account/logout&language=en-gb";
        Assertions.assertEquals(expectedUrlAfterLogout,currentUrlAfterLogout, "The current URL is not the expected URL after Logout.");

    }

    @Test
    @DisplayName("The user is still on Register page when is trying to register with invalid password.")
    public void registerWithInvalidPassword() throws InterruptedException {

        String email = FakeDataManager.getRandomEmail();
        System.out.println("The generated email is: " + email);
        String password = FakeDataManager.getRandomPassword(0,4);
        System.out.println("The generated password is: " + password);

        // completam formularul cu datele generate random
        registerPage.fillInTheRegisterForm(FakeDataManager.getRandomFirstName(),FakeDataManager.getRandomLastName(),
                email, password, true);

        registerPage.clickTheContinueBtn();

        Thread.sleep(15000);
        // facem verificari cu ajutorul metodelor din clasa Assertions
        String currentUrl = driver.getCurrentUrl();
        String expectedUrl = "https://andreisecuqa.host/index.php?route=account/register&language=en-gb";
        Assertions.assertEquals(expectedUrl,currentUrl, "The current URL is not the expected URL.");
    }

    @Test
    @DisplayName("Error message is displayed on Register flow when password is less than 4 chars.")
    public void errorMessageIsDisplayedWhenInvalidPasswordIsUsedForRegistrationFlow() throws InterruptedException {

        String email = FakeDataManager.getRandomEmail();
        System.out.println("The generated email is: " + email);
        String password = FakeDataManager.getRandomPassword(0,4);
        System.out.println("The generated password is: " + password);

        registerPage.fillInTheRegisterForm(FakeDataManager.getRandomFirstName(),FakeDataManager.getRandomLastName(),
                email, password, true);

        registerPage.clickTheContinueBtn();

        Thread.sleep(500);

        String actualErrorMessage = driver.findElement(By.id("error-password")).getText();
        String expectedErrorMessage = "Password must be between 4 and 20 characters!";
        Assertions.assertEquals(expectedErrorMessage,actualErrorMessage, "The error message is displayed.");
    }

    @Test
    @DisplayName("Unsuccessfully Login with invalid random credentials.")
    public void unsuccessfullyLoginWithInvalidCredentials() throws InterruptedException {

        String email = FakeDataManager.getRandomEmail();
        System.out.println("The generated email is: " + email);
        String password = FakeDataManager.getRandomPassword(8,24);
        System.out.println("The generated password is: " + password);

        loginPage.navigateToLoginFromHeader();
        loginPage.fillInTheLoginPage(email,password);
        loginPage.clickTheLoginBtn();

        Thread.sleep(1000);
        //https://andreisecuqa.host/index.php?route=account/login&language=en-gb
        String currentUrl = driver.getCurrentUrl();
        boolean doesTheUrlContainSuccess = currentUrl.contains("route=account/success");
        Assertions.assertFalse(doesTheUrlContainSuccess, "Unsuccessfully login.");
    }

    @AfterEach
    public void afterEachTestCase(){
        DriverManager.getInstance().quiteTheDriver();
        System.out.println("After each");
    }

    @AfterAll
    public static void afterAll(){
        System.out.println("The execution of the test suite has ended");
    }

}
