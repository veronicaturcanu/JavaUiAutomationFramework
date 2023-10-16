package com.opencart;

import com.opencart.managers.DriverManager;
import org.openqa.selenium.ScriptTimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;


public class TestRunner {
    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = DriverManager.getInstance().getDriver();

        String currentWindowName = driver.getWindowHandle();

        //new Window Code
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://www.google.com/");
        Thread.sleep(1000);
        System.out.println(driver.getTitle());
        driver.close(); //permite inchiderea unui Tab

        driver.switchTo().window(currentWindowName);
        driver.get("https://tekwill.md/");
        Thread.sleep(1000);
        System.out.println(driver.getTitle());
        driver.quit(); //permite inchiderea instantei (driver)

        System.out.println("The execution is over.");
    }
}