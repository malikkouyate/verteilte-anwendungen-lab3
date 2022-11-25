package de.berlin.htw;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
class BasketResourceTest {

    @Test
    void testAddItem() {
        given()
            .log().all()
            .when().header("X-User-Id", "2")
            .contentType(ContentType.JSON)
            .post("/basket")
            .then()
            .log().all()
            .statusCode(501);
    }

    @Test
    void testCheckout() {
        given()
            .log().all()
            .when().header("X-User-Id", "3")
            .post("/basket")
            .then()
            .log().all()
            .statusCode(201)
            .header("Location", "http://localhost:8081/hierFehltNoEtwas");
    }

}