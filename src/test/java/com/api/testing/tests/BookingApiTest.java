
package com.api.testing.tests;

import org.testng.annotations.Test;

import com.api.testing.base.BaseTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class BookingApiTest extends BaseTest {

    @Test
    public void verifyGetBookingIds() {

        given()
            .spec(requestSpec)
            .log().all()

        .when()
            .get("/booking")

        .then()
            .log().all()
            .statusCode(200)
            
            .body("$", notNullValue());
    }
}
