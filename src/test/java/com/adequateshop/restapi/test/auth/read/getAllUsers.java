package com.adequateshop.restapi.test.auth.read;

import com.adequateshop.restapi.baseTest.BaseApiTest;
import com.adequateshop.restapi.test.auth.bearerToken.BearerToken;
import com.adequateshop.restapi.test.auth.write.PostLoginTest;
import com.adequateshop.restapi.utill.General;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class getAllUsers extends BaseApiTest {
    @Test
    public void getAllUsers(){
        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + BearerToken.getBearerToken())
                .log().uri()
                .when()
                .get("/users?page=1")
                .then()
                .statusCode(200)
                .log().body();
    }
}
