package com.adequateshop.restapi.test.auth.write;

import com.adequateshop.restapi.baseTest.BaseApiTest;
import com.adequateshop.restapi.test.auth.bearerToken.BearerToken;
import com.adequateshop.restapi.utill.General;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class PostCreateUser extends BaseApiTest {
    @Test
    public void createUser() {
        String name = General.getDummyName();
        String email = General.getDummyEmail();
        String location = General.getDummyLocation();

        JSONObject createUserJson = new JSONObject();
        createUserJson.put("name", name);
        createUserJson.put("email", email);
        createUserJson.put("location", location);

        ValidatableResponse validatableResponse = given()
                .header("Authorization", "Bearer " + BearerToken.getBearerToken())
                .contentType(ContentType.JSON)
                .body(createUserJson)
                .log().uri()
                //  .log().body()
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .log().body();

        int id = validatableResponse.extract().jsonPath().getInt("id");

        given()
                .header("Authorization", "Bearer " + BearerToken.getBearerToken())
                .contentType(ContentType.JSON)
                .log().uri()
                .when()
                .get("/users/" + id)
                .then()
                .statusCode(200)
                .log().body()
                .body("id", equalTo(id))
                .body("email", equalTo(email));
    }
}
