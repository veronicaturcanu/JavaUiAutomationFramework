package com.opencart;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/main/java/com/opencart/features",
        glue = "com.opencart.stepdefinitions",
        plugin = {"pretty", "html:target/cucumber-reports"}, //"usage", - timpul utilizat la rulare
        monochrome = true,
        tags = ""
)

public class CucumberTestRunner {
}
