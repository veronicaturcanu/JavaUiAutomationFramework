package com.opencart.managers;

import com.opencart.context.CONTEXT;
import com.opencart.context.ScenarioContext;

public class DataSubstituteManager {

    public static String substituteString(String value) {
            switch (value.toUpperCase()) {
                case "RANDOMFIRSTNAME":
                    return FakeDataManager.getRandomFirstName();
                case "RANDOMLASTNAME":
                    return FakeDataManager.getRandomLastName();
                case "RANDOMEMAIL":
                    String email = FakeDataManager.getRandomEmail();
                    ScenarioContext.getInstance().addToStorageContext(CONTEXT.EMAIL, email);
                    return email;
                case "RANDOMPASSWORD":
                    String password = FakeDataManager.getRandomPassword(8, 12);
                    ScenarioContext.getInstance().addToStorageContext(CONTEXT.PASSWORD,password);
                    return password;
            }
        return value;
    }
}
