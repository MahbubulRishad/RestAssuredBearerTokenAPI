package com.adequateshop.restapi.test.auth.write;

import com.adequateshop.restapi.baseTest.BaseApiTest;
import com.adequateshop.restapi.utill.General;
import io.restassured.response.ValidatableResponse;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.given;

public class PostRegistrationTest extends BaseApiTest {
    @Test
    public void registrationTest() {

        JSONObject registrationJson = new JSONObject();
        registrationJson.put("name", "apiTester"); //General.getDummyName()
        registrationJson.put("email", "apiTester@gmail.com"); // General.getDummyEmail()
        registrationJson.put("password", "123456");

       ValidatableResponse validatableResponse = given()
                .header("Content-Type", "application/json")
                .body(registrationJson.toString())
                .log().uri()
                .log().body()
                .when()
                .post("/authaccount/registration")
                .then()
                .log().body()
                .statusCode(200);

        String getEmail = validatableResponse.extract().jsonPath()
                .getString("data.Email");
    }

}
