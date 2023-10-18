package com.opencart.managers;

import com.github.javafaker.Faker;

public class FakeDataManager {

    private static Faker fakerObject = new Faker();

    public static String getRandomEmail(){
        return fakerObject.internet().emailAddress();
    }

    public static String getRandomFirstName(){
        return fakerObject.name().firstName();
    }

    public static String getRandomLastName(){
        return fakerObject.name().lastName();
    }

    public static String getRandomPassword(int min, int max){
        return fakerObject.internet().password(min,max);
    }

    public static String generateRandomEmail(String prefix, String suffix){
        String randomMidPart = String.valueOf(fakerObject.random().nextInt(1,999999));
        return prefix + randomMidPart + suffix;
    }
}
