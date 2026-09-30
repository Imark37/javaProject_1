package org.example;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasItem;

class GoodsApiTest {

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = "http://localhost:8080";
        RestAssured.authentication = RestAssured.preemptive().basic("admin", "secret123");
    }
    // 1. given() / when() / then()
    @Test
    @Tag("api")
    void testGetGoodsList_GWT() {
        given()
                .log().all()
                .when()
                .get("/goods/list")
                .then()
                .log().all()
                .statusCode(200)
                .body("goods", empty());
    }

    // 2. RequestSpecification
    @Test
    @Tag("api")
    void testGetGoodsList_RequestSpec() {
        RequestSpecification localhost = new RequestSpecBuilder()
                .setBaseUri("http://localhost:8080")
                .setContentType(ContentType.JSON)
                .build();

        given()
                .spec(localhost)
                .log().all()
                .when()
                .get("/goods/list")
                .then()
                .log().all()
                .statusCode(200)
                .body("goods", empty());
    }

    // 3. POST + GET — встроенные проверки Rest Assured
    @Test
    @Tag("api")
    void testAddGood_ThenCheckList_RestAssured() {
        String body = """
                {
                  "name": "Молоко",
                  "price": 89.90
                }
                """;

        given()
                .contentType(ContentType.JSON)
                .body(body)
                .log().all()
                .when()
                .post("/goods/add")
                .then()
                .log().all()
                .statusCode(200);

        given()
                .log().all()
                .when()
                .get("/goods/list")
                .then()
                .log().all()
                .statusCode(200)
                .body("goods.name", hasItem("Молоко"));
    }

    // 4. POST + GET — AssertJ
    @Test
    @Tag("api")
    void testAddGood_ThenCheckList_AssertJ() {
        String body = """
                {
                  "name": "Петрушка",
                  "price": 13.50
                }
                """;

        given()
                .contentType(ContentType.JSON)
                .body(body)
                .log().all()
                .when()
                .post("/goods/add")
                .then()
                .log().all()
                .statusCode(200);

        Response response = given()
                .log().all()
                .when()
                .get("/goods/list")
                .then()
                .extract().response();

        assertThat(response.statusCode())
                .as("Код ответа должен быть 200")
                .isEqualTo(200);

        assertThat(response.jsonPath().getList("goods.name"))
                .as("Список товаров должен содержать 'Петрушка'")
                .contains("Петрушка");
    }
}