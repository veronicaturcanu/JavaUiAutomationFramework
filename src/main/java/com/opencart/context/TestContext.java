package com.opencart.context;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class TestContext {
    private static TestContext testContextInstance;
    private Map<String, String> testContextStorage;

    private static final Logger logger = LogManager.getLogger(TestContext.class);

    private TestContext(){
        testContextStorage = new HashMap<>();
    }

    public static TestContext getInstance(){
        if (testContextInstance == null){
            testContextInstance = new TestContext();
        }
        return testContextInstance;
    }

    public void addToStorageContext(String contextKey, String contextValue){
        testContextStorage.put(contextKey,contextValue);
        logger.log(Level.INFO,"The test context was populated with ["+ contextValue+"] for the key ["+
                contextKey+"]");
    }

    public String getStoredValueFromContext(String contextKey){
        return testContextStorage.get(contextKey);
    }
}
