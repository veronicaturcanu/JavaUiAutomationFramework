package com.opencart.stepdefinitions;

import com.opencart.managers.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;

public class Hooks {

    @BeforeAll
    public static void theMethodExecutedBeforeAllTests(){
        System.out.println("The tests execution started.");
    }

    @Before
    public void beforeEachTest(){
        System.out.println("A new test execution started.");
    }

    @After
    public void afterEachTest(){
        DriverManager.getInstance().quitTheDriver();
        System.out.println("A test execution ended.");
    }

    @AfterAll
    public static void theMethodExecutedAfterAllTests(){
        System.out.println("The tests execution ended.");
    }
}
