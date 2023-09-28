package com.adequateshop.restapi.test.auth.write;

import com.adequateshop.restapi.baseTest.BaseApiTest;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
//import static org.hamcrest.core.IsEqual.equalTo;

public class PostLoginTest extends BaseApiTest {
    @Test
    public void loginShouldSuccessful() {
        String email = "apiTester@gmail.com";
        String password = "123456";

        JSONObject loginJson = new JSONObject();
        loginJson.put("email", email);
        loginJson.put("password", password);

        given()
                .header("Content-Type", "application/json")
                .log().uri()
                .body(loginJson.toString())
                .when()
                .post("/authaccount/login")
                .then()
                .log().body()
                .body("data.Email", equalTo(email))
                .statusCode(200);
    }

}
