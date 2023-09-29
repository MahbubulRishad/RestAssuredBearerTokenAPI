package com.adequateshop.restapi.test.auth.read;

import com.adequateshop.restapi.baseTest.BaseApiTest;
import com.adequateshop.restapi.test.auth.bearerToken.BearerToken;
import com.adequateshop.restapi.test.auth.write.PostLoginTest;
import com.adequateshop.restapi.utill.General;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class getAllUsers extends BaseApiTest {
    @Test
    public void getAllUsers() {
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

    @Test
    public void getDynamicUserByID() {
        ValidatableResponse validatableResponse = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + BearerToken.getBearerToken())
                .log().uri()
                .when()
                .get("/users?page=1")
                .then()
                .log().body()
                .statusCode(200);

        int id = validatableResponse.extract().jsonPath().getInt("data[0].id");

        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + BearerToken.getBearerToken())
                .log().uri()
                .when()
                .get("/users/"+ id)
                .then()
                .log().body()
                .statusCode(200)
                .body("id", equalTo(id));
    }
}
