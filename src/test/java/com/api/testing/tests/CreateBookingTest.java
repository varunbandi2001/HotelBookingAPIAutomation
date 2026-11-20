package com.api.testing.tests;

import org.testng.annotations.Test;

import com.api.testing.base.BaseTest;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CreateBookingTest extends BaseTest {
	
	@Test
	
	public void createBooking()
	{
		
		
		String requestBody = "{\n" +
                "  \"firstname\": \"Varun\",\n" +
                "  \"lastname\": \"Kumar\",\n" +
                "  \"totalprice\": 1500,\n" +
                "  \"depositpaid\": true,\n" +
                "  \"bookingdates\": {\n" +
                "    \"checkin\": \"2026-12-10\",\n" +
                "    \"checkout\": \"2026-12-15\"\n" +
                "  },\n" +
                "  \"additionalneeds\": \"Breakfast\"\n" +
                "}";
		
		Response response = 
				
				given()
				.spec(requestSpec)
				.contentType("application/json")
				.body(requestBody)
				.log().all()
				
				.when()
				
				.post("/booking")
				
				.then()
				.log().all()
				
				.statusCode(200)
				.extract()

				.response();
		
		int bookingId = response.jsonPath()
				.getInt("bookingid");
		
		System.out.println("Created Booking ID:" + bookingId);
		response.then()
		
		.body("booking.firstname", org.hamcrest.Matchers.equalTo("Varun"))
		.body("booking.lastname",
			      org.hamcrest.Matchers.equalTo("Kumar"))
		

	    .body("booking.totalprice",
	        org.hamcrest.Matchers.equalTo(1500))

	    .body("booking.depositpaid",
	        org.hamcrest.Matchers.equalTo(true))

	    .body("booking.bookingdates.checkin",
	        org.hamcrest.Matchers.equalTo("2026-12-10"))

	    .body("booking.bookingdates.checkout",
	        org.hamcrest.Matchers.equalTo("2026-12-15"))

	    .body("booking.additionalneeds",
	        org.hamcrest.Matchers.equalTo("Breakfast"));	
		
		
		
		int bookingid = response.jsonPath().getInt("bookingid");

		org.testng.Assert.assertTrue(
		    bookingId > 0,
		    "Booking ID should be greater than zero"
		);

		System.out.println("Created Booking ID: " + bookingId);
		
	}

}
