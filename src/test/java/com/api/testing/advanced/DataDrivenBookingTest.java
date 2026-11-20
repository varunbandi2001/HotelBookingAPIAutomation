package com.api.testing.advanced;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.Assert;

import com.api.testing.base.BaseTest;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class DataDrivenBookingTest extends BaseTest {

    @DataProvider(name = "bookingData")
    public Object[][] getBookingData() {

        return new Object[][] {
            {"Varun", "Kumar", 1500},
            {"Rahul", "Reddy", 2000},
            {"John", "David", 2500},
            {"Virat", "Kohli", 3000}
        };
    }

    @Test(dataProvider = "bookingData")
    public void createBookingWithMultipleData(
            String firstname,
            String lastname,
            int totalprice) {

        String requestBody = "{"
                + "\"firstname\":\"" + firstname + "\","
                + "\"lastname\":\"" + lastname + "\","
                + "\"totalprice\":" + totalprice + ","
                + "\"depositpaid\":true,"
                + "\"bookingdates\":{"
                + "\"checkin\":\"2026-12-10\","
                + "\"checkout\":\"2026-12-15\""
                + "},"
                + "\"additionalneeds\":\"Breakfast\""
                + "}";

        Response response =
                given()
                    .spec(requestSpec)
                    .contentType("application/json")
                    .body(requestBody)
                    .log().all()
                .when()
                    .post("/booking");

        response.then()
                .log().all()
                .statusCode(200);

        int bookingId = response.jsonPath().getInt("bookingid");

        Assert.assertTrue(bookingId > 0,
                "Booking ID should be greater than zero");

        Assert.assertEquals(
                response.jsonPath().getString("booking.firstname"),
                firstname);

        Assert.assertEquals(
                response.jsonPath().getString("booking.lastname"),
                lastname);

        Assert.assertEquals(
                response.jsonPath().getInt("booking.totalprice"),
                totalprice);

        System.out.println(
                "Successfully created booking. ID: " + bookingId);
    }
}