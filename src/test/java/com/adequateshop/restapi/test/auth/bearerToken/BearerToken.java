package com.adequateshop.restapi.test.auth.bearerToken;

import com.adequateshop.restapi.baseTest.BaseApiTest;
import io.restassured.response.ValidatableResponse;
import org.json.simple.JSONObject;

import static io.restassured.RestAssured.given;

public class BearerToken extends BaseApiTest {
    public static String getBearerToken(){
        String email = "apiTester@gmail.com";
        String password = "123456";

        JSONObject loginJson = new JSONObject();
        loginJson.put("email", email);
        loginJson.put("password", password);

        ValidatableResponse validatableResponse = given()
                .header("Content-Type", "application/json")
                .log().uri()
                .body(loginJson.toString())
             //   .log().body()
                .when()
                .post("/authaccount/login")
                .then()
                .statusCode(200);

        String token = validatableResponse.extract().jsonPath()
                .getString("data.Token");

        return token;
    }
}
