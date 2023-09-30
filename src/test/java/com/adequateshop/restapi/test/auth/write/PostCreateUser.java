package com.adequateshop.restapi.test.auth.write;

import com.adequateshop.restapi.baseTest.BaseApiTest;
import com.adequateshop.restapi.test.auth.bearerToken.BearerToken;
import com.adequateshop.restapi.utill.General;
import com.thedeanda.lorem.LoremIpsum;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static com.adequateshop.restapi.test.auth.bearerToken.BearerToken.getBearerToken;
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
                .header("Authorization", "Bearer " + getBearerToken())
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
                .header("Authorization", "Bearer " + getBearerToken())
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

    @Test
    public void updateUser() {
        String name = General.getDummyName();
        String email = General.getDummyEmail();
        String location = General.getDummyLocation();

        JSONObject createUserJson = new JSONObject();
        createUserJson.put("name", name);
        createUserJson.put("email", email);
        createUserJson.put("location", location);

        ValidatableResponse validatableResponse = given()
                .header("Authorization", "Bearer " + getBearerToken())
                .contentType(ContentType.JSON)
                .body(createUserJson)
                .log().uri()
                .log().body()
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .log().body();

        int id = validatableResponse.extract().jsonPath().getInt("id");

        JSONObject updateUserJson = new JSONObject();
        updateUserJson.put("name", General.getDummyName());
        createUserJson.put("email", General.getDummyEmail());
        createUserJson.put("location", General.getDummyLocation());
        updateUserJson.put("id", id);

        given()
                .header("Authorization", "Bearer " + getBearerToken())
                .contentType(ContentType.JSON)
                .header("Content-Type", "application/json")
                .body(updateUserJson)
                .log().uri()
                .log().body()
                .when()
                .put("/users/" + id)
                .then()
                .statusCode(201);
        //  .body("name", equalTo("Mashrafee"));
    }

    @Test
    public void deleteUser(){
        String name = General.getDummyName();
        String email = General.getDummyEmail();
        String location = General.getDummyLocation();

        JSONObject createUserJson = new JSONObject();
        createUserJson.put("name", name);
        createUserJson.put("email", email);
        createUserJson.put("location", location);

        ValidatableResponse validatableResponse = given()
                .header("Authorization", "Bearer " + getBearerToken())
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
                .header("Authorization", "Bearer " + getBearerToken())
                .contentType(ContentType.JSON)
                .log().uri()
                .log().body()
                .when()
                .delete("/users/" + id)
                .then()
                .statusCode(201)
                .log().body();
    }
}
