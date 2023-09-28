package com.adequateshop.restapi.baseTest;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class BaseApiTest {
    @BeforeClass
    public void setUp(){
        RestAssured.baseURI = "http://restapi.adequateshop.com";
        RestAssured.basePath = "/api";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}
