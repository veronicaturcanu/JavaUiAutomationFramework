package com.opencart.stepdefinitions;

import com.opencart.managers.ConfigReaderManager;
import com.opencart.managers.DriverManager;
import com.opencart.pageobjects.HomePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class HomePageSteps {
    WebDriver driver = DriverManager.getInstance().getDriver();
    HomePage homePage = new HomePage(driver);
    private static final Logger logger = LogManager.getLogger(HomePageSteps.class);
    @Given("Home Page is displayed")
    public void homePageIsDisplayed() {
        driver.get(ConfigReaderManager.getPropertyValue("url"));
        logger.log(Level.INFO, "The driver accessed the Home Page");
    }

    @When("RegisterLink from Header menu is clicked")
    public void registerLinkFromHeaderMenuIsClicked() {
        homePage.navigateToRegisterPageFromHeader();
        logger.log(Level.INFO,"The Register Link has been accessed from the Header Menu");
    }
}
