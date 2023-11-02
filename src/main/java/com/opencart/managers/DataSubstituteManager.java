package com.opencart.managers;

public class DataSubstituteManager {

    public static String substituteString(String value) {
            switch (value.toUpperCase()) {
                case "RANDOMFIRSTNAME":
                    return FakeDataManager.getRandomFirstName();
                case "RANDOMLASTNAME":
                    return FakeDataManager.getRandomLastName();
                case "RANDOMEMAIL":
                    return FakeDataManager.getRandomEmail();
                case "RANDOMPASSWORD":
                    return FakeDataManager.getRandomPassword(8, 12);
            }
        return value;
    }
}
