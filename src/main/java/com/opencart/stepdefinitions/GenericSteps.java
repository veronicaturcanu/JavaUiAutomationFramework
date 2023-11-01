package com.opencart.stepdefinitions;

import com.opencart.managers.DriverManager;
import com.opencart.managers.ScrollManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class GenericSteps {
    WebDriver driver = DriverManager.getInstance().getDriver();

    @Then("The current url contains {string} keyword")
    public void theCurrentUrlContainsKeyword(String keyWordFromTheUrl) throws InterruptedException {
        Thread.sleep(500);
        String currentUrl = driver.getCurrentUrl();
        boolean doesTheUrlContainKeyWordFromTheUrl = currentUrl.contains(keyWordFromTheUrl);
        Assertions.assertTrue(doesTheUrlContainKeyWordFromTheUrl, "The current url: "
                + currentUrl + " contains: " + keyWordFromTheUrl);
    }

    @Given("{string} endpoint is accessed")
    public void endpointIsAccessed(String endpointValue) {
        driver.get("https://andreisecuqa.host" + endpointValue);
    }

    @Then("The following list of error message is displayed:")
    public void theFollowingListOfErrorMessageIsDisplayed(List<String> errorMessageList) throws InterruptedException {
        Thread.sleep(500);
        errorMessageList.forEach(errorMessage -> {
            boolean errorMessageIsDisplayed = driver.findElement(By.xpath("//*[contains(text(),'"+errorMessage+"')]")).isDisplayed();
            Assertions.assertTrue(errorMessageIsDisplayed, "The error message: " + errorMessage + " is displayed");
        });
    }
}
