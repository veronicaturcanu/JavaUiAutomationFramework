package com.opencart;

import com.opencart.managers.DriverManager;
import com.opencart.managers.FakeDataManager;
import org.openqa.selenium.*;


public class TestRunner {
    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = DriverManager.getInstance().getDriver();

        String currentWindowName = driver.getWindowHandle();

        //new Window Code
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://andreisecuqa.host/");
        Thread.sleep(1000);
        System.out.println("The current URL is " + driver.getCurrentUrl());

        WebElement myAccountIcon = driver.findElement(By.xpath("//i[@class='fa-solid fa-user']"));
        myAccountIcon.click();

        WebElement registerButton = driver.findElement(By.xpath("//a[@class='dropdown-item'][normalize-space()='Register']"));
        registerButton.click();

        System.out.println("The current URL is " + driver.getCurrentUrl());

        // register page web elements
        WebElement firstNameElement = driver.findElement(By.id("input-firstname"));
        String firstName = FakeDataManager.getRandomFirstName();
        System.out.println("The generated first name is: " + firstName);
        firstNameElement.sendKeys(firstName);

        WebElement lastNameElement = driver.findElement(By.id("input-lastname"));
        String lastName = FakeDataManager.getRandomLastName();
        System.out.println("The generated last name is: " + lastName);
        lastNameElement.sendKeys(lastName);

        WebElement emailElement = driver.findElement(By.id("input-email"));
        String email = FakeDataManager.getRandomEmail();
        System.out.println("The generated email is: " + email);
        emailElement.sendKeys(email);

        WebElement passwordElement = driver.findElement(By.id("input-password"));
        String password = FakeDataManager.getRandomPassword(10,18);
        System.out.println("The generated password is: " + password);
        passwordElement.sendKeys(password);

        WebElement subscribeNewsletterElement = driver.findElement(By.xpath("//input[@id='input-newsletter']"));
        subscribeNewsletterElement.click();

        WebElement privacyPolicyElement = driver.findElement(By.cssSelector("input[value='1'][name='agree']"));
        privacyPolicyElement.click();  // xpath: //input[@name='agree']  cssSelector: input[value='1'][name='agree']

        Thread.sleep(1000); //pentru a reusi sa vizualizez datele inserate

        WebElement continueBtnElement = driver.findElement(By.xpath("//button[@type='submit']"));
        continueBtnElement.click();

        System.out.println(driver.getTitle());
        driver.close(); //permite inchiderea unui Tab

        driver.switchTo().window(currentWindowName);
        driver.get("https://tekwill.md/");
        // Thread.sleep(1000);
        System.out.println(driver.getTitle());
        driver.quit(); //permite inchiderea instantei (driver)

        System.out.println("The execution is over.");
    }
}