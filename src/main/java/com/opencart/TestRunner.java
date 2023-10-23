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
        Thread.sleep(500);
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

        //Thread.sleep(5000);

        WebElement continueBtnElement = driver.findElement(By.xpath("//button[@type='submit']"));
        JavascriptExecutor je = (JavascriptExecutor) driver;
        je.executeScript("arguments[0].scrollIntoView(true);", continueBtnElement);


        WebElement subscribeNewsletterElement = driver.findElement(By.xpath("/html[1]/body[1]/main[1]/div[2]/div[1]/div[1]/form[1]/fieldset[3]/div[1]/div[1]/div[1]/input[2]"));
        subscribeNewsletterElement.click();

        WebElement privacyPolicyElement = driver.findElement(By.xpath("/html[1]/body[1]/main[1]/div[2]/div[1]/div[1]/form[1]/div[1]/div[1]/input[1]"));
        privacyPolicyElement.click();  // xpath: //input[@name='agree']  cssSelector: input[value='1'][name='agree']

        Thread.sleep(500); //pentru a reusi sa vizualizez datele inserate


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