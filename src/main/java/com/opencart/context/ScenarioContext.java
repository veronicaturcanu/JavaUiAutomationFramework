package com.opencart.context;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {
    private static final Logger logger = LogManager.getLogger(ScenarioContext.class);
    private static ScenarioContext scenarioContextInstance;

    private Map<CONTEXT, String> contextStorageMap;

    private ScenarioContext(){
        contextStorageMap = new HashMap<>();
    }

    public static ScenarioContext getInstance(){
        if (scenarioContextInstance == null) {
            scenarioContextInstance = new ScenarioContext();
        }
        return scenarioContextInstance;
    }

    public void addToStorageContext(CONTEXT contextKey, String contextValue){
        contextStorageMap.put(contextKey,contextValue);
        logger.log(Level.INFO,"The context was populated with ["+ contextValue+"] for the key ["+
                contextKey+"]");
    }

    public String getStoredValueFromContext(CONTEXT contextKey){
        return contextStorageMap.get(contextKey);
    }

}
