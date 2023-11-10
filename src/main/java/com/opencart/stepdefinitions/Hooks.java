package com.opencart.stepdefinitions;

import com.opencart.managers.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Hooks {

    private static final Logger logger = LogManager.getLogger(Hooks.class);

    @BeforeAll
    public static void theMethodExecutedBeforeAllTests(){
        logger.log(Level.INFO,"The tests execution started.");
    }

    @Before
    public void beforeEachTest(){
        logger.log(Level.INFO,"A new test execution started.");
    }

    @After
    public void afterEachTest(){
        DriverManager.getInstance().deleteCookies();
        logger.log(Level.INFO,"A test execution ended.");
    }

    @AfterAll
    public static void theMethodExecutedAfterAllTests(){
        DriverManager.getInstance().quitTheDriver();
        logger.log(Level.INFO,"The tests execution ended.");
    }
}
