package com.opencart.stepdefinitions;

import com.opencart.managers.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

public class GenericSteps {
    WebDriver driver = DriverManager.getInstance().getDriver();
    private static final Logger logger = LogManager.getLogger(GenericSteps.class);

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
        driver.get(ConfigReaderManager.getPropertyValue("url") + endpointValue);
    }

    @Then("The following list of error message is displayed:")
    public void theFollowingListOfErrorMessageIsDisplayed(List<String> errorMessageList) throws InterruptedException {
        Thread.sleep(500);
        errorMessageList.forEach(errorMessage -> {
            boolean errorMessageIsDisplayed = driver.findElement(By.xpath("//*[contains(text(),'" + errorMessage + "')]")).isDisplayed();
            Assertions.assertTrue(errorMessageIsDisplayed, "The error message: " + errorMessage + " is displayed");
        });
    }

    @And("The {string} from {string} is clicked")
    public void theElementFromPageNameIsClicked(String elementName, String pageName) {
        WebElement elementToBeClicked = findWebElementFromPageObjectClass(elementName,pageName);
        ExplicitWaitManager.waitTillTheElementIsClickable(elementToBeClicked);
        ScrollManager.scrollToTheElement(elementToBeClicked);
        elementToBeClicked.click();
        logger.log(Level.INFO,elementToBeClicked.getAccessibleName() + " is clicked");
    }

    @When("The following form from {string} is populated as follow:")
    public void theFollowingFormFromIsPopulatedAsFollow(String pageName, Map<String, String> fieldAndValueMap) {
        fieldAndValueMap.forEach((fieldName, fieldValue) -> {
            fieldValue = DataSubstituteManager.substituteString(fieldValue);
            WebElement inputElement = findWebElementFromPageObjectClass(fieldName, pageName);
            ExplicitWaitManager.waitTillTheElementIsVisible(inputElement);
            logger.log(Level.INFO,"The [" + fieldName + "] is populated with [" + fieldValue+"]");
        });
    }

    private WebElement findWebElementFromPageObjectClass(String elementName, String pageName){
        Class classInstance = null;
        WebElement webElement = null;
        try {
            classInstance = Class.forName("com.opencart.pageobjects." + pageName);
            Field classField = classInstance.getDeclaredField(elementName);
            classField.setAccessible(true);
            webElement = (WebElement) classField.get(classInstance.getConstructor(WebDriver.class).newInstance(driver));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return webElement;
    }
}
