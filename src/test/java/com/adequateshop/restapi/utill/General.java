package com.adequateshop.restapi.utill;

import com.adequateshop.restapi.baseTest.BaseApiTest;
import com.thedeanda.lorem.LoremIpsum;
import io.restassured.response.ValidatableResponse;
import org.json.simple.JSONObject;

import static io.restassured.RestAssured.given;

public class General extends BaseApiTest {
    public static String getDummyName(){
        String dummyName = LoremIpsum.getInstance().getName();
        return dummyName;
    }
    public static String getDummyEmail(){
        String dummyEmail = LoremIpsum.getInstance().getEmail();
        return dummyEmail;
    }

    public static String getDummyLocation(){
        String location = LoremIpsum.getInstance().getCountry();
        return location;
    }

}
